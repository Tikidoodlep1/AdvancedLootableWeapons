package tiki.rotn.advancedlootableweapons.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.IHasModel;
import tiki.rotn.advancedlootableweapons.init.BlockInit;
import tiki.rotn.advancedlootableweapons.init.ItemInit;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material, String toolClass, int harvestLevel, boolean shouldRegister) 
	{
		super(material);
		if(shouldRegister) {
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(Alw.AlwBlocksTab);
			this.setHarvestLevel(toolClass, harvestLevel);
			
			BlockInit.blocks.add(this);
			ItemInit.items.add(new ItemBlock(this).setRegistryName(name));
		}
	}
	
	public BlockBase(String name, Material material, String toolClass, int harvestLevel) 
	{
		this(name, material, toolClass, harvestLevel, true);
	}

	@Override
	public void registerModels() 
	{
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}
