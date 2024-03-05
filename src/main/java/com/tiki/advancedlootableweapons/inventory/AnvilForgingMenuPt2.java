package com.tiki.advancedlootableweapons.inventory;

import com.tiki.advancedlootableweapons.init.MenuInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.recipes.AnvilForgingRecipe;
import com.tiki.advancedlootableweapons.recipes.AnvilForgingRecipev2;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class AnvilForgingMenuPt2 extends ItemCombinerMenu {

	private final Level level;
	@Nullable
	private AnvilForgingRecipe selectedRecipe;
	private final List<AnvilForgingRecipev2> recipes;

	public AnvilForgingMenuPt2(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
		super(MenuInit.ANVIL_FORGING.get(), pContainerId, pPlayerInventory, pAccess);
		this.level = pPlayerInventory.player.level;
		this.recipes = this.level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.ANVIL_FORGING_V2);
	}

	public AnvilForgingMenuPt2(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
	}

	@Override
	protected boolean mayPickup(Player pPlayer, boolean pHasStack) {
		return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
	}

	@Override
	protected void onTake(Player player, ItemStack stack) {
		stack.onCraftedBy(player.level, player, stack.getCount());
		this.resultSlots.awardUsedRecipes(player);
		this.shrinkStackInSlot(0);
		this.shrinkStackInSlot(1);
		this.access.execute((level, pos) -> level.levelEvent(LevelEvent.SOUND_SMITHING_TABLE_USED, pos, 0));
	}

	private void shrinkStackInSlot(int pIndex) {
		ItemStack itemstack = this.inputSlots.getItem(pIndex);
		itemstack.shrink(1);
		this.inputSlots.setItem(pIndex, itemstack);
	}

	@Override
	protected boolean isValidBlock(BlockState pState) {
		return pState.is(BlockTags.ANVIL);
	}

	@Override
	public void createResult() {
		List<AnvilForgingRecipe> list = this.level.getRecipeManager().getRecipesFor(ModRecipeTypes.ANVIL_FORGING, this.inputSlots, this.level);
		if (list.isEmpty()) {
			this.resultSlots.setItem(0, ItemStack.EMPTY);
		} else {
			this.selectedRecipe = list.get(0);
			ItemStack itemstack = this.selectedRecipe.assemble(this.inputSlots);
			this.resultSlots.setRecipeUsed(this.selectedRecipe);
			this.resultSlots.setItem(0, itemstack);
		}
	}

	//@Override
	//protected boolean shouldQuickMoveToAdditionalSlot(ItemStack pStack) {
	//	return this.recipes.stream().anyMatch((p_40261_) -> {
	//		return p_40261_.isAdditionIngredient(pStack);
	//	});
	//}

	/**
	 * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
	 * null for the initial slot that was double-clicked.
	 */
	@Override
	public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
		return pSlot.container != this.resultSlots && super.canTakeItemForPickAll(pStack, pSlot);
	}

}
