package com.tiki.advancedlootableweapons.blocks.block_entity;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherContainer;
import com.tiki.advancedlootableweapons.recipes.JawCrusherRecipe;

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
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class JawCrusherBlockEntity extends BlockEntity implements MenuProvider {
	
	protected NonNullList<ItemStack> inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	private static final Random RAND = new Random();
	
	private final ItemStackHandler itemHandler = new ItemStackHandler(getContainerSize()) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};
	
	public JawCrusherBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityInit.JAW_CRUSHER_TE.get(), pPos, pBlockState);
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
		return new JawCrusherContainer(pContainerId, pPlayerInventory, itemHandler, ContainerLevelAccess.create(level,worldPosition));
	}
	
	@Override
	public Component getDisplayName() {
		return new TextComponent("Jaw Crusher");
	}
	
	private boolean canMakeRecipe(ItemStack stack) {
		return this.itemHandler.getStackInSlot(1) == ItemStack.EMPTY || (this.itemHandler.getStackInSlot(1).getItem() == stack.getItem() && this.itemHandler.getStackInSlot(1).getCount() + stack.getCount() < this.itemHandler.getStackInSlot(1).getMaxStackSize());
	}
	
	public ItemStack crushContents() {
		RecipeWrapper inv = new RecipeWrapper(itemHandler);
		Optional<JawCrusherRecipe> match = level.getRecipeManager().getRecipeFor(JawCrusherRecipe.Type.INSTANCE, inv, level);
		if(match.isPresent() && canMakeRecipe(new ItemStack(match.get().getResultItem().getItem(), match.get().getBonus()))) {
			final ItemStack result = new ItemStack(match.get().getResultItem().getItem(), this.itemHandler.getStackInSlot(1).getCount() + (RAND.nextInt(match.get().getBonus()) + match.get().getResultItem().getCount()));
			this.itemHandler.extractItem(0, 1, false);
			this.itemHandler.setStackInSlot(1, result);
			return result;
		}
		return ItemStack.EMPTY;
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
	    ContainerHelper.saveAllItems(tag, this.inventory);
	}
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		itemHandler.deserializeNBT(tag.getCompound("inventory"));
	    this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	    ContainerHelper.loadAllItems(tag, this.inventory);
	}
	
	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for(int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i,  itemHandler.getStackInSlot(i));
		}
	}
	
	public int getContainerSize() {
		return 2;
	}

}
