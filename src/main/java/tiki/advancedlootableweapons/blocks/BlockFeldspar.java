package tiki.advancedlootableweapons.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import tiki.advancedlootableweapons.init.BlockInit;

public class BlockFeldspar extends BlockBase {

	public BlockFeldspar(String name) {
		super(name, Material.ROCK, "pickaxe", 1, true);
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockInit.cobble_feldspar);
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
}
