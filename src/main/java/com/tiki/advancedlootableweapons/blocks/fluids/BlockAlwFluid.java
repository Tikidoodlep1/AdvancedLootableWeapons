package com.tiki.advancedlootableweapons.blocks.fluids;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.IHasModel;
import com.tiki.advancedlootableweapons.init.BlockInit;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockAlwFluid extends BlockFluidClassic implements IHasModel {
	
	public BlockAlwFluid(String name, Fluid fluid, Material material) {
		this(name, fluid, material, material.getMaterialMapColor());
	}
	
	public BlockAlwFluid(String name, Fluid fluid, Material material, MapColor mapColor) {
		super(fluid, material, mapColor);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		BlockInit.blocks.add(this);
		ItemInit.items.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public void registerModels() 
	{
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}