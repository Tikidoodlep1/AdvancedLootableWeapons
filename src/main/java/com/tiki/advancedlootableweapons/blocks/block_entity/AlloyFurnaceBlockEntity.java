package com.tiki.advancedlootableweapons.blocks.block_entity;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.init.BlockEntityInit;
import com.tiki.advancedlootableweapons.inventory.alloy_furnace.AlloyFurnaceContainer;
import com.tiki.advancedlootableweapons.recipes.AlloyFurnaceRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class AlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {
	public static final int SLOT_INPUT_1 = 0;
	public static final int SLOT_INPUT_2 = 1;
	public static final int SLOT_FUEL = 2;
	public static final int SLOT_RESULT = 3;
	public static final int DATA_LIT_TIME = 0;
	public static final int DATA_LIT_DURATION = 1;
	public static final int DATA_COOKING_PROGRESS = 2;
	public static final int DATA_COOKING_TOTAL_TIME = 3;
	public static final int NUM_DATA_VALUES = 4;
	public static final int MAX_COOKING_TIME = 266;
	public static final int BURN_COOL_SPEED = 2;
	protected NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);
	private int totalLitTime;
	private int litTime;
	private int litDuration;
	private int cookingProgress = 0;
	private int cookingTotalTime = MAX_COOKING_TIME;
	protected final ContainerData dataAccess = new ContainerData() {
	   public int get(int data) {
	      switch(data) {
	      case 0:
	         return AlloyFurnaceBlockEntity.this.litTime;
	      case 1:
	         return AlloyFurnaceBlockEntity.this.litDuration;
	      case 2:
	         return AlloyFurnaceBlockEntity.this.cookingProgress;
	      case 3:
	         return AlloyFurnaceBlockEntity.this.cookingTotalTime;
	      default:
	         return 0;
	      }
	   }
	   
	   public void set(int data, int val) {
	      switch(data) {
	      case 0:
	    	  AlloyFurnaceBlockEntity.this.litTime = val;
	         break;
	      case 1:
	     	 AlloyFurnaceBlockEntity.this.litDuration = val;
	         break;
	      case 2:
	     	 AlloyFurnaceBlockEntity.this.cookingProgress = val;
	         break;
	      case 3:
	     	 AlloyFurnaceBlockEntity.this.cookingTotalTime = val;
	      }
	   }

	   @Override
	   public int getCount() {
		   return 4;
	   }
	   
	};
	
	private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};

	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	
	public AlloyFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.ALLOY_FURNACE_TE.get(), pos, state);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory inv, Player player) {
		return new AlloyFurnaceContainer(containerId, inv, this, this.dataAccess);
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Alloy Furnace");
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return lazyItemHandler.cast();
		}
		return super.getCapability(cap, side);
	}
	
	private boolean isLit() {
	   return this.dataAccess.get(DATA_LIT_TIME) > 0;
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
		tag.putInt("BurnTime", this.dataAccess.get(DATA_LIT_TIME));
	    tag.putInt("CookTime", this.dataAccess.get(DATA_LIT_DURATION));
	    tag.putInt("CookTimeTotal", this.dataAccess.get(DATA_COOKING_TOTAL_TIME));
	    ContainerHelper.saveAllItems(tag, this.inventory);
	}
	
	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		itemHandler.deserializeNBT(tag.getCompound("inventory"));
	    this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	    ContainerHelper.loadAllItems(tag, this.inventory);
	    this.dataAccess.set(DATA_LIT_TIME, tag.getInt("BurnTime"));
	    this.dataAccess.set(DATA_COOKING_PROGRESS, tag.getInt("CookTime"));
	    this.dataAccess.set(DATA_COOKING_TOTAL_TIME, tag.getInt("CookTimeTotal"));
	    this.litDuration = ForgeHooks.getBurnTime(this.itemHandler.getStackInSlot(SLOT_FUEL), RecipeType.SMELTING);
	}
	
	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for(int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i,  itemHandler.getStackInSlot(i));
		}
	}
	
	public static void tick(Level world, BlockPos pos, BlockState state, AlloyFurnaceBlockEntity entity) {
//		ItemStack input1 = entity.itemHandler.getStackInSlot(SLOT_INPUT_1);
//	    ItemStack input2 = entity.itemHandler.getStackInSlot(SLOT_INPUT_2);
//	    Recipe<Integer> recipe = AlloyFurnaceRecipes.INSTANCE.getRecipe(input1, input2);
//		if(recipe != null && hasNotReachedStackLimit(entity)) {
//			smeltItem(world, pos, state, entity, recipe);
//		}else {
//			entity.dataAccess.set(DATA_COOKING_PROGRESS, 0);
//			setChanged(world, pos, state);
//		}
		if(hasRecipe(entity)) {
			entity.cookingProgress++;
			setChanged(world, pos, state);
			if(entity.cookingProgress >= entity.cookingTotalTime) {
				smeltItem(entity, world, state, pos);
			}
		}else {
			entity.cookingProgress = 0;
			setChanged(world, pos, state);
		}
	}
	
	private static boolean hasRecipe(AlloyFurnaceBlockEntity entity) {
		Level level = entity.level;
		SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
		for(int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
		}
		
		Optional<AlloyFurnaceRecipe> match = level.getRecipeManager().getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inv, level);
		//System.out.println("Is Recipe Match Present? " + match.isPresent());
		return match.isPresent() && canMakeRecipe(entity, match.get().getResultItem());
	}
	
	private static void smeltItem(AlloyFurnaceBlockEntity entity, Level level, BlockState state, BlockPos pos) {		
		boolean flag = entity.isLit();
	    boolean flag1 = false;
	    if (entity.isLit()) {
	       entity.dataAccess.set(0, --entity.litTime);
	       if(entity.dataAccess.get(DATA_LIT_TIME) == 0) {
	    	   entity.totalLitTime = 0;
	       }
	    }
	    
	    ItemStack fuel = entity.itemHandler.getStackInSlot(SLOT_FUEL);
	    
	    if (entity.isLit() || !fuel.isEmpty()) {
	    	entity.dataAccess.set(DATA_COOKING_TOTAL_TIME, MAX_COOKING_TIME);
	        if (!entity.isLit()) {
	        	entity.dataAccess.set(DATA_LIT_TIME, ForgeHooks.getBurnTime(fuel, RecipeType.SMELTING));
	        	entity.totalLitTime = ForgeHooks.getBurnTime(fuel, RecipeType.SMELTING);
	        	entity.dataAccess.set(DATA_LIT_DURATION, entity.litTime);
	        	
	            if (entity.isLit()) {
	            	flag1 = true;
	            	if (fuel.hasContainerItem())
	            		entity.itemHandler.setStackInSlot(SLOT_FUEL, fuel.getContainerItem());
	            	else
	            	if (!fuel.isEmpty()) {
	            		fuel.shrink(1);
	            		state = state.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(true));
	            		if (fuel.isEmpty()) {
	            			entity.itemHandler.setStackInSlot(SLOT_FUEL, fuel.getContainerItem());
	            		}
	            	}
	            }
	        }
	        
	        if (entity.isLit()) {
	        	entity.dataAccess.set(DATA_COOKING_PROGRESS, ++entity.cookingProgress);
	        	//System.out.println("PROGRESS: " + entity.dataAccess.get(DATA_COOKING_PROGRESS));
	            if(entity.dataAccess.get(DATA_COOKING_PROGRESS) >= entity.dataAccess.get(DATA_COOKING_TOTAL_TIME)) {
	            	entity.dataAccess.set(DATA_COOKING_PROGRESS, 0);
	            	SimpleContainer inv = new SimpleContainer(entity.itemHandler.getSlots());
	        		for(int i = 0; i < entity.itemHandler.getSlots(); i++) {
	        			inv.setItem(i, entity.itemHandler.getStackInSlot(i));
	        		}
	        		
	        		Optional<AlloyFurnaceRecipe> match = level.getRecipeManager().getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inv, level);
	        		if(match.isPresent()) {
	        			entity.itemHandler.extractItem(SLOT_INPUT_1, 1, false);
	        			entity.itemHandler.extractItem(SLOT_INPUT_2, 1, false);
	        			entity.itemHandler.setStackInSlot(SLOT_RESULT, new ItemStack(match.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(SLOT_RESULT).getCount() + match.get().getResultItem().getCount()));
	        		}
	                flag1 = true;
	            }
	        }else {
	        	entity.dataAccess.set(DATA_COOKING_PROGRESS, 0);
	         }
	      }else if (!entity.isLit() && entity.dataAccess.get(DATA_COOKING_PROGRESS) > 0) {
	    	  entity.dataAccess.set(DATA_COOKING_PROGRESS, Mth.clamp(entity.dataAccess.get(DATA_COOKING_PROGRESS) - 2, 0, entity.dataAccess.get(DATA_COOKING_TOTAL_TIME)));
	      }
	    
	      if (flag != entity.isLit()) {
	         flag1 = true;
	         state = state.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(entity.isLit()));
	         level.setBlock(pos, state, 3);
	      }

	      if (flag1) {
	         setChanged(level, pos, state);
	      }
	}
	
	public int getMaxBurnDuration() {
		return this.totalLitTime;
	}

//	private void burn(Recipe<Integer> recipe, ItemStackHandler itemHandler) {
//		int input1Count = recipe.getItemCount(this.itemHandler.getStackInSlot(SLOT_INPUT_1).getItem());
//		int input2Count = recipe.getItemCount(this.itemHandler.getStackInSlot(SLOT_INPUT_2).getItem());
//		this.itemHandler.getStackInSlot(SLOT_INPUT_1).shrink(input1Count);
//		this.itemHandler.getStackInSlot(SLOT_INPUT_2).shrink(input2Count);
//		itemHandler.setStackInSlot(SLOT_RESULT, new ItemStack(recipe.getOutput().getItem(), itemHandler.getStackInSlot(SLOT_RESULT).getCount() + recipe.getOutput().getCount()));
//	}
	
	private static boolean canMakeRecipe(AlloyFurnaceBlockEntity entity, ItemStack stack) {
		return entity.itemHandler.getStackInSlot(SLOT_RESULT) == ItemStack.EMPTY || (entity.itemHandler.getStackInSlot(SLOT_RESULT).getItem() == stack.getItem() && entity.itemHandler.getStackInSlot(SLOT_RESULT).getCount() + stack.getCount() < entity.itemHandler.getStackInSlot(SLOT_RESULT).getMaxStackSize());
	}
	
	public int getContainerSize() {
		return 4;
	}
}
