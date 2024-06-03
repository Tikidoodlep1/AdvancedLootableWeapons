package tiki.advancedlootableweapons.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.IHasModel;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.init.ItemInit;

public class BlockPowderedClay extends BlockFalling implements IHasModel {
	
	public BlockPowderedClay(String name) {
		super();
		this.setSoundType(SoundType.SAND);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwBlocksTab);
		
		BlockInit.blocks.add(this);
		ItemInit.items.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		for(EnumFacing facing : EnumFacing.values()) {
			if(worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.WATER || worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.FLOWING_WATER) {
				worldIn.setBlockState(pos, this == BlockInit.powder_granite ? BlockInit.clay_granite.getDefaultState() : BlockInit.clay_diorite.getDefaultState());
			}
		}
    }
	
	public void onEndFalling(World worldIn, BlockPos pos, IBlockState state, IBlockState fromState)
    {
        if (fromState.getMaterial().isLiquid())
        {
        	worldIn.setBlockState(pos, this == BlockInit.powder_granite ? BlockInit.clay_granite.getDefaultState() : BlockInit.clay_diorite.getDefaultState());
        }
    }

    protected boolean tryTouchWater(World worldIn, BlockPos pos, IBlockState state)
    {
        boolean flag = false;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (enumfacing != EnumFacing.DOWN)
            {
                BlockPos blockpos = pos.offset(enumfacing);

                if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER)
                {
                    flag = true;
                    break;
                }
            }
        }

        if (flag)
        {
        	worldIn.setBlockState(pos, this == BlockInit.powder_granite ? BlockInit.clay_granite.getDefaultState() : BlockInit.clay_diorite.getDefaultState());
        }

        return flag;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!this.tryTouchWater(worldIn, pos, state))
        {
            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.tryTouchWater(worldIn, pos, state))
        {
            super.onBlockAdded(worldIn, pos, state);
        }
    }

	@Override
	public void registerModels() {
		Alw.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

}
