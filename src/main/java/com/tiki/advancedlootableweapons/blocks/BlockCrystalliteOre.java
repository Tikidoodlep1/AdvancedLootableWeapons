package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockCrystalliteOre extends BlockAlwOre{

	
	public BlockCrystalliteOre(String name) {
		super(name);
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.CRYSTAL;
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 1 + random.nextInt(3);
    }
	
	@Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(1 + fortune);
    }
}
