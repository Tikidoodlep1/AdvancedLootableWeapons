
package tiki.advancedlootableweapons.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.IHasModel;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.util.HotMetalHelper;

public class ItemHotToolHead extends Item implements IHasModel {
	public final ItemHotToolHead next;
	public final int level;
	public final boolean finished;
	public final boolean isMain;
	
	public ItemHotToolHead(String name, ItemHotToolHead next, int level, boolean finished, boolean isMain) {
		this.next = next;
		this.level = level;
		this.finished = finished;
		this.isMain = isMain;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwToolHeadsTab);
		
		ItemInit.items.add(this);
		
		this.setMaxDamage(6000);
		this.maxStackSize = 1;
		this.canRepair = false;
		this.setHasSubtypes(true);
		this.addPropertyOverride(new ResourceLocation("damage"), new IItemPropertyGetter() {

			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
				int i = stack.getMetadata();
				if(tag.hasKey("clay") && tag.getBoolean("clay")) {
					return 3;
				}
				
		        if(i <= 2999) {
		        	return 0;
		        }else if(i >= 2999 && i <= 4999) {
		        	return 1;
		        }else {
		        	return 2;
		        }
			}
		});
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String name = super.getItemStackDisplayName(stack);
		NBTTagCompound tag = stack.getTagCompound();
		if(tag != null && tag.hasKey("Material")) {
			name.replaceFirst(" ", " " + tag.getString("Material") + " ");
		}
		if(stack.hasTagCompound() && tag.hasKey("clay") && tag.getBoolean("clay")) {
			name = I18n.format("alw.clay_cover.name").concat(" ").concat(name);
		}
		return name;
	}
	
	public static ItemStack getMaterial(ItemStack toolHead) {
		NBTTagCompound tag = new NBTTagCompound();
		tag = toolHead.getTagCompound();
		if(toolHead.hasTagCompound() && tag.hasKey("Material")) {
			return new ItemStack(tag.getCompoundTag("Material"));
		}else {
			return ItemStack.EMPTY;
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		NBTTagCompound Stacknbt = stack.getTagCompound();
		ItemStack material = getMaterial(stack);
		
		if(stack.hasTagCompound() && Stacknbt.hasKey("Material")) {
			tooltip.add(TextFormatting.DARK_AQUA + material.getDisplayName());
		}
		
		if(stack.hasTagCompound() && this.isMain) {
			boolean quenched = Stacknbt.getBoolean("quenched");
			tooltip.add(quenched ? TextFormatting.BLUE + new TextComponentTranslation("alw.tool_head.quenched.name").getFormattedText() : TextFormatting.RED + new TextComponentTranslation("alw.tool_head.unquenched.name").getFormattedText());
		}
		
		if(stack.hasTagCompound() && Stacknbt.hasKey("addedDamage")) {
			tooltip.add(TextFormatting.BLUE + new TextComponentTranslation("alw.forging_quality.name").getFormattedText());
			tooltip.add(TextFormatting.GRAY + "--------------------");
			tooltip.add(TextFormatting.BLUE + "+" + Stacknbt.getDouble("addedDamage") + new TextComponentTranslation("alw.damage_tooltip.name").getFormattedText());
			tooltip.add(TextFormatting.BLUE + "+" + Stacknbt.getInteger("addedDurability") + new TextComponentTranslation("alw.dur_tooltip.name").getFormattedText());
			tooltip.add(TextFormatting.GRAY + "--------------------");
		}
    }
	
	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		int damage = stack.getMetadata();
		Item material = stack.getTagCompound() == null ? ItemInit.BRONZE_SHARPENING_STONE : new ItemStack(stack.getTagCompound().getCompoundTag("Material")).getItem();
		if(damage <= 5999) {
			this.setDamage(stack, (damage + HotMetalHelper.getHeatGainLoss(material, HotMetalHelper.ROOM_TEMP, this.getDamage(stack))));
		}else if(damage > 6000) {
			this.setDamage(stack, 6000);
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        if(i <= 2999) {
        	return super.getUnlocalizedName() + ".hot";
        }else if(i >= 2999 && i <= 4999) {
        	return super.getUnlocalizedName() + ".warm";
        }else {
        	return super.getUnlocalizedName() + ".cool";
        }
    }
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
        	NBTTagCompound defaultTag = new NBTTagCompound();
        	defaultTag.setTag("Material", new ItemStack(ItemInit.INGOT_STEEL).serializeNBT());
        	defaultTag.setDouble("addedDamage", 0.0D);
        	defaultTag.setInteger("addedDurability", 0);
        	ItemStack stack6000 = new ItemStack(this, 1, 6000);
        	stack6000.setTagCompound(defaultTag);
        	ItemStack stack3000 = new ItemStack(this, 1, 3000);
        	stack3000.setTagCompound(defaultTag);
        	ItemStack stack0 = new ItemStack(this, 1, 0);
        	stack0.setTagCompound(defaultTag);
        	items.add(stack6000);
        	items.add(stack3000);
        	items.add(stack0);
        }
    }
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }
}
