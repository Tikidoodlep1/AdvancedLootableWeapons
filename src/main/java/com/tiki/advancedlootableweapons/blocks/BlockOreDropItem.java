package com.tiki.advancedlootableweapons.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockOreDropItem extends BlockAlwOre {

	private final Item drop;
	private final int maxCount;
	private final int minCount;
	
	public BlockOreDropItem(String name, Item drop, int min, int max, int harvestLevel) {
		super(name, harvestLevel);
		this.drop = drop;
		if(min > max) {
			this.maxCount = min;
			this.minCount = max;
		}else {
			this.maxCount = max;
			this.minCount = min;
		}
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.drop;
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return minCount + random.nextInt(maxCount);
    }
	
	@Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(1 + fortune);
    }
}
