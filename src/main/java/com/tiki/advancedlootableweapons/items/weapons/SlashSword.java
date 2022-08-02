package com.tiki.advancedlootableweapons.items.weapons;

import java.util.List;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.init.AttributeModifiers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class SlashSword extends TieredItem implements Vanishable {
    private final double attackDamage;
    public final WeaponAttributes attributes;
    //private final Tier tier;
    private final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

    public SlashSword(Tier pTier, WeaponAttributes attributes, Item.Properties pProperties) {
        super(pTier, pProperties);
        //this.tier = pTier;
        this.attributes = attributes;
        this.attackDamage = attributes.getBaseDamage() + pTier.getAttackDamageBonus();
        this.defaultModifiers = Lazy.of(() -> {
        	ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        	builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attributes.getBaseAttackSpeed(), AttributeModifier.Operation.ADDITION));
            if(ForgeMod.REACH_DISTANCE.isPresent()) {
            	builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(AttributeModifiers.ATK_RNG_UUID, "Weapon modifier", attributes.getReach() - 0, AttributeModifier.Operation.ADDITION));
            }
            return builder.build();
        });
    }
    
//    @Override
//    public int getMaxDamage(ItemStack stack) {
//    	CompoundTag tag = stack.getOrCreateTag();
//    	if(tag.contains("maxDurability")) {
//    		return this.tier.getUses() + tag.getInt("maxDurability");
//    	}
//    	return this.tier.getUses();
//    }
//    
//    public void setMaximumDamage(ItemStack stack, int additionalDur) {
//    	CompoundTag tag = stack.getOrCreateTag();
//    	tag.putInt("maxDurability", additionalDur);
//    	stack.setTag(tag);
//    }
    
    public double getAttackDamage() {
        return this.attackDamage;
    }
    
    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }
    
    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = pState.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !pState.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
        }
    }
    
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
    
    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(2, pEntityLiving, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return false;
    }
    
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TextComponent(ChatFormatting.GREEN + "Chance to pierce Chain armor: " + this.attributes.getChainPenChance()));
		tooltip.add(new TextComponent(ChatFormatting.DARK_BLUE + "Chance to pierce Plate armor: " + this.attributes.getPlatePenChance()));
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
    	return enchantment.category.canEnchant(Items.IRON_SWORD);
    }
    
    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
    	return true;
    }
    
    @Override
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        return pBlock.is(Blocks.COBWEB);
    }
    
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
    	if(slot == EquipmentSlot.MAINHAND) {
    		return this.defaultModifiers.get();
    	}else {
    		return ImmutableMultimap.of();
    	}
    }
    
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
    	if(slot == EquipmentSlot.MAINHAND) {
    		return this.defaultModifiers.get();
    	}else {
    		return ImmutableMultimap.of();
    	}
    }
    
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }
}
