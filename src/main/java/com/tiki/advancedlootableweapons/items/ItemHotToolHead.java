package com.tiki.advancedlootableweapons.items;

import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.handlers.EnumMaterialType;
import com.tiki.advancedlootableweapons.init.ItemInit;

import net.minecraft.ChatFormatting;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemHotToolHead extends Item {
    private final CompoundTag nbt = new CompoundTag();
    private final ItemHotToolHead next;
    private final int level;
    private final boolean finished;

    public ItemHotToolHead(@Nullable ItemHotToolHead next, int level, boolean finished, Properties prop) {
        super(prop);
        this.next = next;
        this.level = level;
        this.finished = finished;
        nbt.putString("material", "null");
        nbt.putDouble("addedDamage", 0.0D);
        nbt.putInt("addedDurability", 0);
    }

    public ItemHotToolHead getNextToolHead() {
        return this.next;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setMaterial(Tier mat) {
        nbt.putString("material", EnumMaterialType.getMaterialNameF(mat));
    }

    public void setMaterial(String matName) {
        nbt.putString("material", matName);
    }

    public String getMaterial() {
        return nbt.getString("material");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if (!Objects.equals(this.getMaterial(), EnumMaterialType.NULL_MAT.getMaterialNameF())) {
            tooltip.add(new TextComponent(ChatFormatting.BLUE + nbt.getString("material")));
        } else {
            tooltip.add(new TextComponent(ChatFormatting.BLUE + "No Material"));
        }

        tooltip.add(new TextComponent(ChatFormatting.BLUE + "Forging Quality"));
        tooltip.add(new TextComponent(ChatFormatting.GRAY + "--------------------"));
        tooltip.add(new TextComponent(ChatFormatting.BLUE + nbt.getString("addedDamage")));
        tooltip.add(new TextComponent(ChatFormatting.BLUE + nbt.getString("addedDurability")));
        tooltip.add(new TextComponent(ChatFormatting.GRAY + "--------------------"));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity player, int p_41407_, boolean p_41408_) {
        int damage = stack.getDamageValue();
        if (damage < getMaxDamage()) {
            this.setDamage(stack, damage + 1);
        }
    }

    @Override
    public Component getName(ItemStack pStack) {
        Temp temp = HEAT_FUNCTION.apply(pStack,null,null,null);
        Component baseName = super.getName(pStack);
        return temp.translation.copy().append(" ").append(baseName);
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    /**
     * related to ItemPropertyFunction
     * damage < 3000 = hot
     */
    public static final PropertyDispatch.QuadFunction<ItemStack,Level, LivingEntity,Integer,Temp> HEAT_FUNCTION = (stack, world, player, id) -> {
        if (stack.getDamageValue() <= 3000) {
            return Temp.hot;
        } else if (stack.getDamageValue() > 3000 && stack.getDamageValue() < 5000) {
            return Temp.warm;
        } else {
            return Temp.cool;
        }
    };

    public enum Temp {
    hot,warm,cool;
    public final TranslatableComponent translation;
    Temp() {
        translation = new TranslatableComponent("advancedlootableweapons.temperature."+this.name());
    }
    }

    public ItemHotToolHead addToRegistryMap() {
        ItemInit.hotToolHeads.add(this);
        return this;
    }
}
