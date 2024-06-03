package tiki.advancedlootableweapons.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.IHasModel;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.init.ItemInit;

public class BlockAlwOre extends Block implements IHasModel {
	
	public BlockAlwOre(String name, int harvestLevel) 
	{
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwBlocksTab);
		
		BlockInit.blocks.add(this);
		ItemInit.items.add(new ItemBlock(this).setRegistryName(name));
		
		setHarvestLevel("pickaxe", harvestLevel);
	}
	
	@Override
	public void registerModels() 
	{
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
}
