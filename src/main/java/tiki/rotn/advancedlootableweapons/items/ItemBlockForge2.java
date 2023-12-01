package tiki.rotn.advancedlootableweapons.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tiki.rotn.advancedlootableweapons.blocks.BlockForge2;

public class ItemBlockForge2 extends ItemBlock {

	public ItemBlockForge2(Block block) {
		super(block);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos placePos = pos;
		if (!worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos))
        {
            placePos = pos.offset(facing);
        }
		
		if(!BlockForge2.isClearForPlacement(placePos, player.getHorizontalFacing().getOpposite(), worldIn)) {
			return EnumActionResult.FAIL;
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

}
