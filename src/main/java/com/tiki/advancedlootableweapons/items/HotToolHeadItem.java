package com.tiki.advancedlootableweapons.items;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.ItemInit;

import com.tiki.advancedlootableweapons.init.ModMaterials;
import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class HotToolHeadItem extends Item {
    private final HotToolHeadItem next;
    private final int level;
    private final boolean finished;

    public HotToolHeadItem(@Nullable HotToolHeadItem next, int level, boolean finished, Properties prop) {
        super(prop);
        this.next = next;
        this.level = level;
        this.finished = finished;
    }

    //         nbt.putString("material", "null");
    //        nbt.putDouble("addedDamage", 0.0D);
    //        nbt.putInt("addedDurability", 0);

    public HotToolHeadItem getNextToolHead() {
        return this.next;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setMaterial(ItemStack stack,WeaponMaterial mat) {
        stack.getOrCreateTag().putString("material", WeaponMaterial.getMaterialNameF(mat));
    }

    public void setMaterial(ItemStack stack,String matName) {
        stack.getOrCreateTag().putString("material", matName);
    }

    public String getMaterial(ItemStack stack) {
        return stack.hasTag() ? stack.getTag().getString("material") : "null";
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag nbt = stack.getTag();
        if (!Objects.equals(this.getMaterial(stack), "null")) {
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
        if (!world.isClientSide && damage < getMaxDamage()) {
            this.setDamage(stack, damage + 1);
        }
    }

    @Override
    public Component getName(ItemStack pStack) {
        Temp temp = HEAT_FUNCTION.apply(pStack, null, null, null);
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
    public static final PropertyDispatch.QuadFunction<ItemStack, Level, LivingEntity, Integer, Temp> HEAT_FUNCTION = (stack, world, player, id) -> {
        if (stack.getDamageValue() <= 3000) {
            return Temp.hot;
        } else if (stack.getDamageValue() > 3000 && stack.getDamageValue() < 5000) {
            return Temp.warm;
        } else {
            return Temp.cool;
        }
    };

    public enum Temp {
        hot, warm, cool;
        public final TranslatableComponent translation;
        Temp() {
            translation = (TranslatableComponent) MCVersion.translation("advancedlootableweapons.temperature." + this.name());
        }
    }

    //the game only adds 1 entry per item by default, this would be the hot
    //this method adds warm and cool as well
    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        //super.fillItemCategory(pCategory, pItems);
        if (allowdedIn(pCategory)) {//be careful not to put them in every tab
            ItemStack hot = new ItemStack(this);
            setMaterial(hot, WeaponMaterial.STEEL);
            pItems.add(hot);
            ItemStack warm = new ItemStack(this);
            setMaterial(warm, WeaponMaterial.STEEL);
            warm.setDamageValue(getMaxDamage() / 2 + 1);
            ItemStack cool = new ItemStack(this);
            setMaterial(cool, WeaponMaterial.STEEL);
            cool.setDamageValue(getMaxDamage());
            pItems.add(warm);
            pItems.add(cool);
        }
    }

    public HotToolHeadItem addToRegistryMap() {
        ItemInit.hotToolHeads.add(this);
        return this;
    }
}
