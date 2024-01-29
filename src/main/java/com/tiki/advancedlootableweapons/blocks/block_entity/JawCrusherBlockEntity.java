package com.tiki.advancedlootableweapons.blocks.block_entity;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherContainer;
import com.tiki.advancedlootableweapons.inventory.jaw_crusher.JawCrusherHandler;
import com.tiki.advancedlootableweapons.recipes.JawCrusherRecipe;

import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class JawCrusherBlockEntity extends BlockEntity implements MenuProvider {

	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	public static final int INPUT_SLOT = 0;
	public static final int OUTPUT_SLOT = 1;

	private Component name;

	private final ItemStackHandler itemHandler = new JawCrusherHandler(getContainerSize()) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};

	private JawCrusherRecipe lastRecipe;

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

	public void setCustomName(Component pName) {
		this.name = pName;
	}

	public Component getName() {
		return this.name != null ? this.name : this.getDefaultName();
	}

	@Override
	public Component getDisplayName() {
		return this.getName();
	}

	@Nullable
	public Component getCustomName() {
		return this.name;
	}

	protected Component getDefaultName() {
		return MCVersion.translation("container.advancedlootableweapons.jaw_crusher");
	}

	protected boolean hasRoom() {
		ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);
		//if the output is empty, then there's always enough room
		if (output.isEmpty()) return true;
		ItemStack result = lastRecipe.assemble(new RecipeWrapper(itemHandler));
		//if the result of the recipe does NOT match what is currently in the output, like say bronze and steel, there's no room
		if (!ItemStack.isSameItemSameTags(result, output)) return false;

		int bonus = lastRecipe.getBonus();
		int max = result.getCount() + bonus;
		//make sure max possible amount can't exceed stack size
		return result.getCount() + bonus + output.getCount() <= output.getMaxStackSize();
	}

	public void crushContents() {
		RecipeWrapper inv = new RecipeWrapper(itemHandler);
		if (lastRecipe == null || !lastRecipe.matches(inv, level)) {
			lookupRecipe();
		}
		if (lastRecipe != null) {
			if (hasRoom()) {
				ItemStack baseResult = lastRecipe.assemble(inv);
				int maxBonus = lastRecipe.getBonus();
				int actualBonus = level.random.nextInt(maxBonus + 1);
				this.itemHandler.extractItem(INPUT_SLOT, 1, false);
				this.itemHandler.setStackInSlot(OUTPUT_SLOT, ItemHandlerHelper.copyStackWithSize(baseResult,
						baseResult.getCount() + actualBonus + itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()));
			}
		}
	}

	void lookupRecipe() {
		RecipeWrapper inv = new RecipeWrapper(itemHandler);
		lastRecipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.CRUSHING, inv, level).orElse(null);
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
		super.saveAdditional(tag);
		tag.put("inventory", itemHandler.serializeNBT());
		if (this.name != null) {
			tag.putString("CustomName", Component.Serializer.toJson(this.name));
		}
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		itemHandler.deserializeNBT(tag.getCompound("inventory"));
		if (tag.contains("CustomName", Tag.TAG_STRING)) {
			this.name = Component.Serializer.fromJson(tag.getString("CustomName"));
		}
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
