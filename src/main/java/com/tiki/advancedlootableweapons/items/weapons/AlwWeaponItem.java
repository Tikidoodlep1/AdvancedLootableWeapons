package com.tiki.advancedlootableweapons.items.weapons;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.AttributeModifiers;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.armor.BoundArmorItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.util.Lazy;

public class AlwWeaponItem extends Item implements Vanishable {

    public static final String BONUS_DURABILITY_KEY = "bonus_durability";
    private final double attackDamage;
    public final WeaponAttributes attributes;
    private final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

    private static final UUID MATERIAL_UUID = UUID.fromString("2e03b879-e09e-41b4-b64e-93a10fa31944");
    
    public AlwWeaponItem(WeaponAttributes attributes, Item.Properties pProperties) {
        super(pProperties);
        this.attributes = attributes;
        this.attackDamage = attributes.getBaseDamage();
        this.defaultModifiers = Lazy.of(() -> {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attributes.getBaseAttackSpeed(), AttributeModifier.Operation.ADDITION));
            if(ForgeMod.REACH_DISTANCE.isPresent()) {
                builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(AttributeModifiers.BLOCK_REACH_UUID, "Reach Modifier", attributes.getReach() - 4, AttributeModifier.Operation.ADDITION));
            }
            if(ForgeMod.ATTACK_RANGE.isPresent()) {
                builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier(AttributeModifiers.ATTACK_REACH_UUID, "Reach Modifier", attributes.getReach() - 4, AttributeModifier.Operation.ADDITION));
            }
            return builder.build();
        });
    }

    public Tier getTier(ItemStack stack) {
        return getMaterial(stack).tier();
    }

    public WeaponMaterial getMaterial(ItemStack stack) {
        Item material = HeatableToolPartItem.getCraftingMaterial(stack);
        return WeaponMaterial.findMaterial(material);
    }

    public MutableComponent getMaterialName(ItemStack stack) {
        WeaponMaterial weaponMaterial = getMaterial(stack);
        return weaponMaterial.getTranslationKey();
    }
    
    public double getAttackDamage() {
        return this.attackDamage;
    }
    
    @Override
    public int getMaxDamage(ItemStack stack) {
        int maxDur = super.getMaxDamage(stack);
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            WeaponMaterial weaponMaterial = getMaterial(stack);
            maxDur = weaponMaterial.tier().getUses();
            if (tag.contains(BONUS_DURABILITY_KEY)) {
                maxDur += tag.getInt(BONUS_DURABILITY_KEY);
            }
        }
        return maxDur;
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

        return true;
    }

    @Override
    public Component getName(ItemStack pStack) {
        return getMaterialName(pStack).append(" ").append(super.getName(pStack));
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
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot,ItemStack stack) {
        if(slot == EquipmentSlot.MAINHAND) {

            Multimap<Attribute,AttributeModifier> baseModifiers = ArrayListMultimap.create(defaultModifiers.get());
            WeaponMaterial material = getMaterial(stack);

            if (material != WeaponMaterial.NULL) {
                baseModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(MATERIAL_UUID, "Material modifier", material.tier().getAttackDamageBonus(),
                        AttributeModifier.Operation.ADDITION));
            }
            return baseModifiers;
        }else {
            return ImmutableMultimap.of();
        }
    }

    @Override
    public AABB getSweepHitBox(ItemStack stack, Player player, Entity target) {
        if(this.attributes.getReach() > 0) {
            return target.getBoundingBox().inflate(this.attributes.getReach()/2, 0.25, this.attributes.getReach()/2);
        }else {
            return target.getBoundingBox().inflate(1-this.attributes.getReach(), 0.25, 1-this.attributes.getReach());
        }
    }
    
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        if(attributes.shouldSlash()) {
            return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
        }else {
            return toolAction == ToolActions.SWORD_DIG;
        }
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (allowdedIn(pCategory)) {
            for (WeaponMaterial material : WeaponMaterial.LOOKUP) {
                if (material.canMakeWeapon()) {
                    ItemStack stack = new ItemStack(this);
                    HeatableToolPartItem.setCraftingMaterial(stack,material.defaultItem().get());
                    pItems.add(stack);
                }
            }
        }
    }
}
