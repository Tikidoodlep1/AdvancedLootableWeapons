package tiki.advancedlootableweapons.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorBinding extends ItemBase {

	private final String name;
	private final int extraDur;
	
	public ItemArmorBinding(String name, ArmorMaterial extraDur) {
		super(name);
		this.name = name;
		this.extraDur = extraDur.getDurability(EntityEquipmentSlot.CHEST)/4;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getExtraDur() {
		return this.extraDur;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.BLUE + new TextComponentTranslation("alw.armor_binding.bonus_dur.name").getFormattedText() + ": " + this.extraDur);
    }

}
