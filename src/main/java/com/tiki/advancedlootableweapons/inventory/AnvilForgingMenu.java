package com.tiki.advancedlootableweapons.inventory;

import com.google.common.collect.Lists;
import com.tiki.advancedlootableweapons.init.MenuInit;
import com.tiki.advancedlootableweapons.init.ModRecipeTypes;
import com.tiki.advancedlootableweapons.recipes.AbstractAnvilForgingRecipe;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.List;

public class AnvilForgingMenu extends AbstractContainerMenu {

	public static final int INPUT_SLOT = 0;
	public static final int INPUT2_SLOT = 1;
	public static final int RESULT_SLOT = 1+1;
	private static final int INV_SLOT_START = 2+1;
	private static final int INV_SLOT_END = 29+1;
	private static final int USE_ROW_SLOT_START = 29+1;
	private static final int USE_ROW_SLOT_END = 38+1;
	private final ContainerLevelAccess access;
	/** The index of the selected recipe in the GUI. */
	private final DataSlot selectedRecipeIndex = DataSlot.standalone();
	private final Level level;
	private List<AbstractAnvilForgingRecipe> recipes = Lists.newArrayList();
	/** The {@plainlink ItemStack} set in the input slot by the player. */
	private ItemStack input = ItemStack.EMPTY;
	/**
	 * Stores the game time of the last time the player took items from the the crafting result slot. This is used to
	 * prevent the sound from being played multiple times on the same tick.
	 */
	long lastSoundTime;
	final Slot inputSlot1;
	final Slot inputSlot2;
	/** The inventory slot that stores the output of the crafting recipe. */
	final Slot resultSlot;
	Runnable slotUpdateListener = () -> {
	};
	public final ItemStackHandler handler = new ItemStackHandler(2) {
		/**
		 * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
		 * it hasn't changed and skip it.
		 */
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			AnvilForgingMenu.this.slotsChanged(this);
			AnvilForgingMenu.this.slotUpdateListener.run();
		}
	};
	/** The inventory that stores the output of the crafting recipe. */
	final ResultContainer resultContainer = new ResultContainer();

	public AnvilForgingMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
	}

	public AnvilForgingMenu(int pContainerId, Inventory pPlayerInventory, final ContainerLevelAccess pAccess) {
		super(MenuInit.ANVIL_FORGING.get(), pContainerId);
		this.access = pAccess;
		this.level = pPlayerInventory.player.level;
		this.inputSlot1 = this.addSlot(new SlotItemHandler(this.handler, 0, 20, 33));
		this.inputSlot2 = this.addSlot(new SlotItemHandler(this.handler, 1, 20, 53));

		this.resultSlot = this.addSlot(new Slot(this.resultContainer, 2, 143, 33) {
			/**
			 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
			 */
			public boolean mayPlace(ItemStack p_40362_) {
				return false;
			}

			public void onTake(Player p_150672_, ItemStack p_150673_) {
				p_150673_.onCraftedBy(p_150672_.level, p_150672_, p_150673_.getCount());
				AnvilForgingMenu.this.resultContainer.awardUsedRecipes(p_150672_);
				ItemStack itemstack = AnvilForgingMenu.this.inputSlot1.remove(1);
				if (!itemstack.isEmpty()) {
					AnvilForgingMenu.this.setupResultSlot();
				}

				pAccess.execute((p_40364_, p_40365_) -> {
					long l = p_40364_.getGameTime();
					if (AnvilForgingMenu.this.lastSoundTime != l) {
						p_40364_.playSound(null, p_40365_, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
						AnvilForgingMenu.this.lastSoundTime = l;
					}

				});
				super.onTake(p_150672_, p_150673_);
			}
		});

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(pPlayerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(pPlayerInventory, k, 8 + k * 18, 142));
		}

		this.addDataSlot(this.selectedRecipeIndex);
	}

	/**
	 * Returns the index of the selected recipe.
	 */
	public int getSelectedRecipeIndex() {
		return this.selectedRecipeIndex.get();
	}

	public List<AbstractAnvilForgingRecipe> getRecipes() {
		return this.recipes;
	}

	public int getNumRecipes() {
		return this.recipes.size();
	}

	public boolean hasInputItem() {
		return this.inputSlot1.hasItem() && !this.recipes.isEmpty();
	}

	/**
	 * Determines whether supplied player can use this container
	 */
	public boolean stillValid(Player pPlayer) {
		return stillValid(this.access, pPlayer, BlockTags.ANVIL);
	}

	protected static boolean stillValid(ContainerLevelAccess pAccess, Player pPlayer, TagKey<Block> pTargetBlock) {
		return pAccess.evaluate((level, pos) -> level.getBlockState(pos).is(pTargetBlock) && pPlayer.distanceToSqr( pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5D) <= 64.0D, true);
	}


	/**
	 * Handles the given Button-click on the server, currently only used by enchanting. Name is for legacy.
	 */
	public boolean clickMenuButton(Player pPlayer, int pId) {
		if (this.isValidRecipeIndex(pId)) {
			this.selectedRecipeIndex.set(pId);
			this.setupResultSlot();
		}

		return true;
	}

	private boolean isValidRecipeIndex(int pRecipeIndex) {
		return pRecipeIndex >= 0 && pRecipeIndex < this.recipes.size();
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	public void slotsChanged(Container pInventory) {
	}

	public void slotsChanged(ItemStackHandler handler) {
		ItemStack itemstack = this.inputSlot1.getItem();
		if (!itemstack.is(this.input.getItem())) {
			this.input = itemstack.copy();
			this.setupRecipeList(new RecipeWrapper(handler), itemstack);
		}
	}

	private void setupRecipeList(RecipeWrapper pContainer, ItemStack pStack) {
		this.recipes.clear();
		this.selectedRecipeIndex.set(-1);
		this.resultSlot.set(ItemStack.EMPTY);
		if (!pStack.isEmpty()) {
			this.recipes = this.level.getRecipeManager().getRecipesFor(ModRecipeTypes.ANVIL_FORGING, pContainer, this.level);
		}

	}

	void setupResultSlot() {
		if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
			AbstractAnvilForgingRecipe forgingRecipe = this.recipes.get(this.selectedRecipeIndex.get());
			this.resultContainer.setRecipeUsed(forgingRecipe);
			this.resultSlot.set(forgingRecipe.assemble(new RecipeWrapper(this.handler)));
		} else {
			this.resultSlot.set(ItemStack.EMPTY);
		}

		this.broadcastChanges();
	}

	public MenuType<?> getType() {
		return MenuInit.ANVIL_FORGING.get();
	}

	public void registerUpdateListener(Runnable pListener) {
		this.slotUpdateListener = pListener;
	}

	/**
	 * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
	 * null for the initial slot that was double-clicked.
	 */
	public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
		return pSlot.container != this.resultContainer && super.canTakeItemForPickAll(pStack, pSlot);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	 * inventory and the other inventory(s).
	 */
	public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(pIndex);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			Item item = itemstack1.getItem();
			itemstack = itemstack1.copy();
			if (pIndex == RESULT_SLOT) {
				item.onCraftedBy(itemstack1, pPlayer.level, pPlayer);
				if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (pIndex == INPUT_SLOT) {
				if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
					return ItemStack.EMPTY;
				}
			} else if (pIndex == INPUT2_SLOT) {
				if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
					return ItemStack.EMPTY;
				}
			}


			else if (this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.ANVIL_FORGING, new RecipeWrapper(handler), this.level).isPresent()) {
				if (!this.moveItemStackTo(itemstack1, 0, 2, false)) {
					return ItemStack.EMPTY;
				}
			} else if (pIndex >= INV_SLOT_START && pIndex < USE_ROW_SLOT_START) {
				if (!this.moveItemStackTo(itemstack1, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
					return ItemStack.EMPTY;
				}
			} else if (pIndex >= USE_ROW_SLOT_START && pIndex < USE_ROW_SLOT_END && !this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_START, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}

			slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(pPlayer, itemstack1);
			this.broadcastChanges();
		}

		return itemstack;
	}

	/**
	 * Called when the container is closed.
	 */
	public void removed(Player pPlayer) {
		super.removed(pPlayer);
		this.resultContainer.removeItemNoUpdate(1);
		this.access.execute((p_40313_, p_40314_) -> {
			this.clearContainer(pPlayer, new RecipeWrapper(this.handler));
		});
	}
}