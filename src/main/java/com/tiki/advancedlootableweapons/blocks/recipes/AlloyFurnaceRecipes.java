package com.tiki.advancedlootableweapons.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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
		addAlloyingRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL), new ItemStack(ItemInit.INGOT_STEEL), 5.0F);
		addAlloyingRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL, 1, 1), new ItemStack(ItemInit.INGOT_STEEL), 5.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.INGOT_STEEL), new ItemStack(ItemInit.CRYSTAL), new ItemStack(ItemInit.INGOT_CRYSTALLITE, 2), 10.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.INGOT_SILVER), new ItemStack(ItemInit.INGOT_PLATINUM), new ItemStack(ItemInit.INGOT_FROST_STEEL), 8.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.SHADOW_BLOB), new ItemStack(ItemInit.INGOT_PLATINUM), new ItemStack(ItemInit.INGOT_SHADOW_PLATINUM), 8.0F);
		addAlloyingRecipe(new ItemStack(ItemInit.INGOT_SHADOW_PLATINUM), new ItemStack(ItemInit.INGOT_STEEL), new ItemStack(ItemInit.INGOT_DUSKSTEEL), 12.0F);
	}
	
	public void addAlloyingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) 
	{
		if(getAlloyingResult(input1, input2) != ItemStack.EMPTY || getAlloyingResult(input2, input1) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.smeltingList.put(input2, input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
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
	
	public float getAlloyingExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
