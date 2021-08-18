package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockAlwOre extends Block implements IHasModel{
	
	private Item droppedItem = null;
	private int minDrops = -1;
	private int maxDrops = -1;
	private Random rand;
	
	public BlockAlwOre(String name, @Nullable Item droppedItem, int min, int max) 
	{
		this(name);
		this.droppedItem = droppedItem;
		this.minDrops = min;
		this.maxDrops = max;
	}
	
	public BlockAlwOre(String name) 
	{
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwBlocksTab);
		
		BlockInit.blocks.add(this);
		ItemInit.items.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public void registerModels() 
	{
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
	@Override
	public int quantityDropped(Random random)
    {
		if(minDrops == -1 && maxDrops == -1) {
			return 1;
		}else if(this.minDrops == this.maxDrops) {
			return this.maxDrops;
		}else {
			return (rand.nextInt(this.maxDrops - this.minDrops)) + this.minDrops;
		}
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	if(this.droppedItem == null) {
    		return Item.getItemFromBlock(this);
    	}else {
    		return this.droppedItem;
    	}
    }
}
