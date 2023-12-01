package tiki.advancedlootableweapons.items;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.IHasModel;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.inventory.SharpeningStone.ContainerSharpeningStone;

public class ItemSharpeningStone extends Item implements IHasModel {
	
	private final ToolMaterial material;
	
	public ItemSharpeningStone(String name, ToolMaterial materialIn) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		this.material = materialIn;
		
		ItemInit.items.add(this);
	}
	
	public ToolMaterial getToolMaterial() {
		return this.material;
	}
	
	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,@Nonnull EnumHand handIn) {
		
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote) {
			playerIn.openGui(Alw.instance, ModInfo.GUI_SHARPENING_STONE, worldIn, handIn.ordinal(), -1, -1);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}else {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}
	
	public static class SharpeningStone implements IInteractionObject
    {
        private final World world;
        private ItemSharpeningStone stone;

        public SharpeningStone(World worldIn, BlockPos pos, ItemSharpeningStone stone)
        {
            this.world = worldIn;
            this.stone = stone;
        }

        public String getName()
        {
            return "sharpening_stone";
        }

        public boolean hasCustomName()
        {
            return false;
        }


        public ITextComponent getDisplayName()
        {
            return new TextComponentTranslation(stone.getUnlocalizedName() + ".name");
        }

        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
        {
            return new ContainerSharpeningStone(playerInventory, world, playerIn);
        }

        public String getGuiID()
        {
            return "ModInfo.GUI_SHARPENING_STONE";
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.DARK_PURPLE + new TextComponentTranslation("alw.sharpening_stone.tooltip").getFormattedText());
    }

	public String getMaterial() {
		return this.material.toString();
	}
}
