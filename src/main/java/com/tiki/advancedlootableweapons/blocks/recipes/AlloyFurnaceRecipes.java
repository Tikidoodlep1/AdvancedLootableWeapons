package com.tiki.advancedlootableweapons.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class AlloyFurnaceRecipes 
{	
	private static final AlloyFurnaceRecipes INSTANCE = new AlloyFurnaceRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static AlloyFurnaceRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private AlloyFurnaceRecipes() 
	{
		FurnaceRecipes furnace = FurnaceRecipes.instance();
		
		for(ItemStack input : FurnaceRecipes.instance().getSmeltingList().keySet()) {
			ItemStack result = furnace.getSmeltingResult(input);
			if(result.getItem() == Items.IRON_INGOT || result.getItem() == Items.GOLD_INGOT || result.getItem() == Items.IRON_NUGGET || result.getItem() == Items.GOLD_NUGGET) {
				addAlloyingRecipe(input, input, new ItemStack(result.getItem(), 2), (furnace.getSmeltingExperience(input) * 2));
			}
		}
		
		//Alloys
		addAlloyingRecipe(new ItemStack(Items.IRON_INGOT, 2), new ItemStack(BlockInit.rock_feldspar), new ItemStack(ItemInit.INGOT_KOBOLD, 4), 2.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.NUGGET_TIN), new ItemStack(ItemInit.INGOT_COPPER), new ItemStack(ItemInit.INGOT_BRONZE, 1), 4.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.INGOT_TIN), new ItemStack(ItemInit.INGOT_COPPER, 9), new ItemStack(ItemInit.INGOT_BRONZE, 9), 20.0F);
		addAlloyingRecipe(new ItemStack(Items.IRON_INGOT, 4), new ItemStack(Items.COAL), new ItemStack(ItemInit.INGOT_STEEL, 4), 5.0F);
		addAlloyingRecipe(new ItemStack(Items.IRON_INGOT, 4), new ItemStack(Items.COAL, 1, 1), new ItemStack(ItemInit.INGOT_STEEL, 4), 5.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.INGOT_SILVER), new ItemStack(ItemInit.INGOT_PLATINUM), new ItemStack(ItemInit.INGOT_FROST_STEEL), 8.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.SHADOW_BLOB), new ItemStack(ItemInit.INGOT_PLATINUM), new ItemStack(ItemInit.INGOT_SHADOW_PLATINUM), 8.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.SHARD_OBSIDIAN), new ItemStack(ItemInit.INGOT_STEEL), new ItemStack(ItemInit.INGOT_OBSIDIAN), 9.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.INGOT_STEEL), new ItemStack(ItemInit.CRYSTAL, 4), new ItemStack(ItemInit.INGOT_CRYSTALLITE, 2), 10.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.INGOT_SHADOW_PLATINUM, 3), new ItemStack(ItemInit.INGOT_STEEL), new ItemStack(ItemInit.INGOT_DUSKSTEEL), 12.0F);
	}
	
	public int getInputCount(ItemStack stack) {
		ItemStack copyStack = new ItemStack(stack.getItem());
		for(ItemStack rowStack : this.smeltingList.rowKeySet()) {
			ItemStack rowCopy = new ItemStack(rowStack.getItem());
			if(rowCopy.isItemEqualIgnoreDurability(copyStack)) {
				return rowStack.getCount();
			}
		}
		return -1;
	}
	
	public void addAlloyingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) 
	{
		if(getAlloyingResult(input1, input2) != ItemStack.EMPTY || getAlloyingResult(input2, input1) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.smeltingList.put(input2, input1, result);
		this.experienceList.put(result, experience);
	}
	
	public ItemStack getAlloyingResult(ItemStack input1, ItemStack input2) 
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) 
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) 
					{
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
	public Map<ItemStack, Float> getExperienceMap(){
		return this.experienceList;
	}
	
	public float getAlloyingExperience(ItemStack stack)
	{
		Map<ItemStack, Float> exp = getExperienceMap();
		ItemStack checkStack = new ItemStack(stack.getItem());
		//System.out.println("expStack is: " + stack);
		for (ItemStack output : exp.keySet()) 
		{
			if(checkStack.isItemEqualIgnoreDurability(new ItemStack(output.getItem()))) {
				//System.out.println(output + " : " + exp.get(output));
				return exp.get(output);
			}
		}
		return 0.0F;
	}
}
