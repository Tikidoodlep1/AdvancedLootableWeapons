package com.tiki.advancedlootableweapons.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.item.ItemStack;

public class AnvilRecipes {
	private static final AnvilRecipes INSTANCE = new AnvilRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static AnvilRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private AnvilRecipes() 
	{
		addAnvilRecipe(new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD), new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD_2), 5.0F);
		addAnvilRecipe(new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD), new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_2), 5.0F);
	}
	
	public void addAnvilRecipe(ItemStack input1, ItemStack result, float experience) 
	{
		if(getForgingResult(input1) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getForgingResult(ItemStack input1)
	{
		for(Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				return (ItemStack)entry.getValue();
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getDualSmeltingList() 
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
