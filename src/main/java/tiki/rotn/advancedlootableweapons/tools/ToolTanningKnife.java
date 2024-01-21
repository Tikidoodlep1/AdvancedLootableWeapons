package tiki.rotn.advancedlootableweapons.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.IHasModel;
import tiki.rotn.advancedlootableweapons.init.ItemInit;

public class ToolTanningKnife extends Item implements IHasModel {
	private float attackDamage;
	
	public ToolTanningKnife(String name, ToolMaterial material) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Alw.AlwTab);
		
		ItemInit.items.add(this);
		
		this.setMaxDamage(material.getMaxUses() / 2);
		this.maxStackSize = 1;
		this.attackDamage = material.getAttackDamage() * 0.75F;
		this.canRepair = true;
		this.setContainerItem(this);
	}
	
	@Override
	public void registerModels() {
		Alw.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean doesSneakBypassUse(ItemStack stack, net.minecraft.world.IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        return false;
    }
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (2.0D - 4.0D), 0));
        }
        
        return multimap;
    }
}
