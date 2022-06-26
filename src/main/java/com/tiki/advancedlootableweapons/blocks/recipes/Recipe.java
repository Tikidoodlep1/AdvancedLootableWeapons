package com.tiki.advancedlootableweapons.blocks.recipes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Recipe<T extends Comparable<?>> {

	private final Map<Item, Integer> inputs = new HashMap<Item, Integer>();
	private final ItemStack output;
	private final float exp;
	private Map<String, T> extra = new HashMap<String, T>();
	
	Recipe(ItemStack output, float exp, ItemStack... inputs) {
		Arrays.stream(inputs).forEach((stack) -> {
			this.inputs.put(stack.getItem(), stack.getCount());
		});
		this.output = output;
		this.exp = exp;
	}
	
	public boolean recipeInputsMatch(ItemStack... inputs) {
		//System.out.println("Lenghs: " + this.inputs.size() + " - " + inputs.length);
		if(inputs.length != this.inputs.size()) {
			return false;
		}
		for(int i = 0; i < this.inputs.size(); i++) {
			if(!this.inputs.containsKey(inputs[i].getItem())) {
				return false;
			}else {
				if(this.inputs.get(inputs[i].getItem()) >= inputs[i].getCount()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int getItemCount(Item item) {
		return this.inputs.get(item);
	}
	
	public ItemStack[] getInputArray() {
		ItemStack[] stacks = new ItemStack[this.inputs.size()];
		Set<Entry<Item, Integer>> stackSet = this.inputs.entrySet();
		Iterator<Entry<Item, Integer>> iter = stackSet.iterator();
		for(int i = 0; i < this.inputs.size(); i++) {
			if(!iter.hasNext()) {
				break;
			}
			Entry<Item, Integer> ent = iter.next();
			stacks[i] = new ItemStack(ent.getKey(), ent.getValue());
		}
		return stacks;
	}
	
	public ItemStack getOutput() {
		return this.output;
	}
	
	public float getExp() {
		return this.exp;
	}
	
	public void addExtraInfo(String name, T value) {
		extra.put(name, value);
	}
	
	public T getExtraInfo(String name) {
		return extra.get(name);
	}
}
