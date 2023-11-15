package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockQuartzClay extends BlockBase {
	
	private Item drop;
	
	public BlockQuartzClay(String name, Item drop) {
		super(name, Material.CLAY, "shovel", 0, true);
		this.drop = drop;
		
		this.setSoundType(SoundType.GROUND);
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		
        return drop;
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 4;
    }
	
	@Override
	public boolean isToolEffective(String type, IBlockState state) {
		if(type == "shovel" || type == "spade") {
			return true;
		}
		return false;
	}

}
