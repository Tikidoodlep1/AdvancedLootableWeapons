package com.tiki.advancedlootableweapons.items;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.ItemInit;

import com.tiki.advancedlootableweapons.util.MCVersion;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class HeatableToolPartItem extends Item {
    private final int level;
    private final boolean isMain;

    public static final String HEAT = "heat";
    public static final int MAX_HEAT = 6000;
    public static final double COOLING_RATE = 1;

    public HeatableToolPartItem(int level, boolean isMain, Properties prop) {
        super(prop);
        this.level = level;
        this.isMain = isMain;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return getHeat(pStack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return (int) (MAX_BAR_WIDTH * getHeat(pStack) / MAX_HEAT);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        double heat = getHeat(pStack);

        double heatPercentage = heat / MAX_HEAT;

        int redScale = (int)Math.min(300 * heatPercentage,0xff);
        int greenScale = (int) Math.min(150 * heatPercentage,0xff);
        int blueScale = (int) Math.min(75 * heatPercentage,0xff);
        return Mth.color(redScale,greenScale,blueScale);
    }

    public static final String QUENCH_KEY ="advancedlootableweapons.tool_head.quenched";
    public static final String UNQUENCH_KEY ="advancedlootableweapons.tool_head.unquenched";

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag nbt = stack.getTag();
        if (!getMaterial(stack).isEmpty()) {
            tooltip.add(MCVersion.literal(ChatFormatting.BLUE + nbt.getString("material")));
        } else {
            tooltip.add(MCVersion.literal(ChatFormatting.BLUE + "No Material"));
        }

        double heat = getHeat(stack);
        tooltip.add(MCVersion.literal("Heat: "+(int)heat));


        if(nbt!= null && isMain) {
            boolean quenched = nbt.getBoolean("quenched");
            tooltip.add(quenched ? MCVersion.translation(QUENCH_KEY).withStyle(ChatFormatting.BLUE) :
                    MCVersion.translation(UNQUENCH_KEY).withStyle(ChatFormatting.RED));
        }

        tooltip.add(MCVersion.literal(ChatFormatting.BLUE + "Forging Quality"));
        tooltip.add(MCVersion.literal(ChatFormatting.GRAY + "--------------------"));
        if (nbt != null) {
            tooltip.add(MCVersion.literal(ChatFormatting.BLUE + nbt.getString("addedDamage")));
            tooltip.add(MCVersion.literal(ChatFormatting.BLUE + nbt.getString("addedDurability")));
        }
        tooltip.add(MCVersion.literal(ChatFormatting.GRAY + "--------------------"));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity player, int p_41407_, boolean p_41408_) {
        double heat = getHeat(stack);
        if (!world.isClientSide && heat > 0) {
            setHeat(stack,Math.max(getHeat(stack) - COOLING_RATE,0));
        }
    }

    @Override
    public Component getName(ItemStack pStack) {
        Temp temp = HEAT_FUNCTION.apply(pStack, null, null, null);
        Component baseName = super.getName(pStack);
        return temp.translation.copy().append(" ").append(baseName);
    }

    /**
     * related to ItemPropertyFunction
     * damage < 3000 = hot
     */
    public static final PropertyDispatch.QuadFunction<ItemStack, Level, LivingEntity, Integer, Temp> HEAT_FUNCTION = (stack, world, player, id) -> {
        double heat = getHeat(stack);
        if (heat < 2000) {
            return Temp.cool;
        } else if (heat >= 2000 && heat < 4000) {
            return Temp.warm;
        } else {
            return Temp.hot;
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
            ItemStack hot = createPart(WeaponMaterial.STEEL);
            setHeat(hot,MAX_HEAT);
            pItems.add(hot);
            ItemStack warm = createPart(WeaponMaterial.STEEL);
            setHeat(warm,MAX_HEAT / 2);
            pItems.add(warm);
            ItemStack cool = createPart(WeaponMaterial.STEEL);
            pItems.add(cool);
        }
    }

    public ItemStack createPart(WeaponMaterial weaponMaterial) {
        ItemStack stack = new ItemStack(this);
        setMaterial(stack,weaponMaterial);
        return stack;
    }

    public HeatableToolPartItem addToRegistryMap() {
        ItemInit.hotToolHeads.add(this);
        return this;
    }

    ///////helpers

    public static void setHeat(ItemStack stack, double temp) {
        stack.getOrCreateTag().putDouble(HEAT,temp);
    }


    public static void setMaterial(ItemStack stack,WeaponMaterial mat) {
        setMaterial(stack,WeaponMaterial.getMaterialNameF(mat));
    }

    public static void setMaterial(ItemStack stack,String matName) {
        stack.getOrCreateTag().putString("material", matName);
    }

    public static String getMaterial(ItemStack stack) {
        return stack.hasTag() ? stack.getTag().getString("material") : "";
    }

    public static double getHeat(ItemStack stack) {
        return stack.hasTag() ? stack.getTag().getDouble(HEAT) : 0;
    }

    public static boolean isSameMaterial(ItemStack stackA, ItemStack stackB) {
        String matA = getMaterial(stackA);
        String matB = getMaterial(stackB);
        return Objects.equals(matA,matB);
    }


}
