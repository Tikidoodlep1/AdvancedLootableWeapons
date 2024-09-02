package com.tiki.advancedlootableweapons.blocks.block_entity;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.inventory.advanced_forge.AdvancedForgeContainer;
import com.tiki.advancedlootableweapons.inventory.forge.ForgeContainer;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.util.HotMetalHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdvancedForgeBlockEntity extends BlockEntity implements MenuProvider {

	public static final int MIN_TEMP = 450;
	public static final int MAX_TEMP = 1750;
	private static int TEMP_COUNTER = 0;
	private int increaseFrames = 0;
	private double containerTemp = 850;
	private int itemTemp = 293;
	protected NonNullList<ItemStack> inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	private final ItemStackHandler itemHandler = new ItemStackHandler(getContainerSize()) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};

	protected final ContainerData dataAccess = new ContainerData() {
		public int get(int data) {
			switch(data) {
				case 0:
					return (int) AdvancedForgeBlockEntity.this.containerTemp;
				case 1:
					return AdvancedForgeBlockEntity.this.itemTemp;
				case 2:
					return AdvancedForgeBlockEntity.this.increaseFrames;
				default:
					return 0;
			}
		}

		public void set(int data, int val) {
			switch(data) {
				case 0:
					AdvancedForgeBlockEntity.this.containerTemp = val;
					break;
				case 1:
					AdvancedForgeBlockEntity.this.itemTemp = val;
					break;
				case 2:
					AdvancedForgeBlockEntity.this.increaseFrames = val;
					break;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

	};

	public AdvancedForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityInit.ADVANCED_FORGE_TE.get(), pPos, pBlockState);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return lazyItemHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
		return new AdvancedForgeContainer(pContainerId, pPlayerInventory, this.itemHandler, this.dataAccess, ContainerLevelAccess.create(level,worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Forge");
	}

	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		tag.put("inventory", itemHandler.serializeNBT());
		super.saveAdditional(tag);
		tag.putInt("containerTemp", this.dataAccess.get(0));
		tag.putInt("itemTemp", this.dataAccess.get(1));
		tag.putInt("increaseFrames", this.dataAccess.get(2));
		ContainerHelper.saveAllItems(tag, this.inventory);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		itemHandler.deserializeNBT(tag.getCompound("inventory"));
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.inventory);
		this.dataAccess.set(0, tag.getInt("containerTemp"));
		this.dataAccess.set(1, tag.getInt("itemTemp"));
		this.dataAccess.set(2, tag.getInt("increaseFrames"));
	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for(int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i,  itemHandler.getStackInSlot(i));
		}
	}

	public static void tick(Level world, BlockPos pos, BlockState state, AdvancedForgeBlockEntity entity) {
		if(entity.increaseFrames > 0) {
			entity.containerTemp += 1;
		}else if(entity.containerTemp >= MIN_TEMP) {
			entity.containerTemp -=1;
		}
		if(entity.itemHandler.getStackInSlot(0).getItem() instanceof HeatableToolPartItem toolHead) {
			ItemStack stack = entity.itemHandler.getStackInSlot(0);
			stack.setDamageValue(stack.getDamageValue() - HotMetalHelper.getHeatGainLoss(HeatableToolPartItem.getCraftingMaterial(stack), (int) entity.containerTemp, stack.getDamageValue()));
		}
	}

	public void bellowsInteraction() {
		this.increaseFrames = 60;
	}

	public int getContainerSize() {
		return 1;
	}

}
