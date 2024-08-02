package com.tiki.advancedlootableweapons.data;

import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.compat.rei.categories.DrumQuenchingCategory;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.init.*;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import com.tiki.advancedlootableweapons.items.armor.ArmorBindingItem;
import com.tiki.advancedlootableweapons.util.TranslationKeys;
import com.tiki.advancedlootableweapons.util.Utils;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(DataGenerator gen) {
        super(gen, AdvancedLootableWeapons.MODID, "en_us");
    }

    protected final Set<Item> item_exclude = new HashSet<>();
    protected final Set<Block> block_exclude = new HashSet<>();

    @Override
    protected void addTranslations() {
        ItemInit.ITEMS.getEntries().stream().map(RegistryObject::get)
                        .filter(item -> (item instanceof BlockItem) || (item instanceof HeatableToolPartItem)).forEach(item_exclude::add);
        blockTranslations();
        itemTranslations();

        addDefaultFluid(FluidInit.MILK_OF_LIME);
        addDefaultFluid(FluidInit.MAGNESIUM_LACTATE);

        add(ForgeHammerItem.INFO,"Hit an anvil with me to start forging weapons!");
        add(ArmorBindingItem.INFO,"Bonus Durability");

        add(HeatableToolPartItem.QUENCH_KEY,"Quenched");
        add(HeatableToolPartItem.UNQUENCH_KEY,"Unquenched");

        add(DrumQuenchingCategory.REQUIRES_CLAY,"Requires Clay");

        addDefaultMenu(MenuInit.ANVIL_FORGING);
        addDefaultMenu(MenuInit.JAW_CRUSHER);
        add("container.advancedlootableweapons.sharpeningStone", "Sharpen Weapon");
        add("container.advancedlootableweapons.alloy_furnace", "Alloy Furnace");
        add("container.advancedlootableweapons.forge", "Forge");
        add("container.advancedlootableweapons.forge2", "Advanced Forge");
        add("container.advancedlootableweapons.forgeWeapon", "Anvil Forging");

        add(REICompat.QUENCHING,"Drum Quenching");
        add(REICompat.DRUM_CAT,"Drum");
        add(REICompat.JAW_CRUSHER_CAT,"Jaw Crusher");
        add(REICompat.ALLOY_FURNACE_CAT,"Alloy Furnace");
        addTranslatableText(TranslationKeys.ANVIL_FORGING_CAT,"Anvil Forging");

        add("enchantment.advancedlootableweapons.refined", "Refined");


        add("attribute.name.generic.bonusAttackDamage", "Bonus Attack Damage");


        addTab(ModCreativeTabs.MISC_TAB, "Advanced Lootable Weapons");
        addTab(ModCreativeTabs.BLOCK_TAB, "ALW Blocks");
        addTab(ModCreativeTabs.WEAPON_TAB, "ALW Weapons & Hot Tool Heads");

        add(HeatableToolPartItem.Temp.cool.translation.getKey(),"Cool");
        add(HeatableToolPartItem.Temp.warm.translation.getKey(),"Warm");
        add(HeatableToolPartItem.Temp.hot.translation.getKey(),"Hot");
    }

    protected void blockTranslations() {

        BlockInit.BLOCKS.getEntries().forEach(this::addDefaultBlock);
        add("block.advancedlootableweapons.advanced_forge_1", "Advanced Forge");
    }

    protected void itemTranslations() {

        ItemInit.ITEMS.getEntries().stream().filter(itemRegistryObject -> !item_exclude.contains(itemRegistryObject.get())).forEach(this::addDefaultItem);

        for (String s : WeaponMaterial.LOOKUP.keySet()) {
            add(WeaponMaterial.getTranslationKey(s).getKey(),getBasicName(s));
        }

        add(ItemInit.TOOL_HEAD.get(), "Tool Head");

        add(ItemInit.TOOL_ROD.get(), "Tool Rod(Stage 1)");
        add(ItemInit.TOOL_ROD_2.get(), "Tool Rod(Finished)");
        
        add(ItemInit.LONG_TOOL_ROD.get(), "Long Tool Rod");

        add(ItemInit.DAGGER_HEAD.get(), "Dagger Head(Stage 1)");
        add(ItemInit.DAGGER_HEAD_2.get(), "Dagger Head(Finished)");

        add(ItemInit.KABUTOWARI_HEAD.get(), "Kabutowari Head(Stage 1)");
        add(ItemInit.KABUTOWARI_HEAD_2.get(), "Kabutowari Head(Stage 2)");
        add(ItemInit.KABUTOWARI_HEAD_3.get(), "Kabutowari Head(Stage 3)");
        add(ItemInit.KABUTOWARI_HEAD_4.get(), "Kabutowari Head(Stage 4)");
        add(ItemInit.KABUTOWARI_HEAD_5.get(), "Kabutowari Head(Finished)");

        add(ItemInit.TALWAR_HEAD.get(), "Talwar Head(Stage 1)");
        add(ItemInit.TALWAR_HEAD_2.get(), "Talwar Head(Stage 2)");
        add(ItemInit.TALWAR_HEAD_3.get(), "Talwar Head(Finished)");

        add(ItemInit.RAPIER_HEAD.get(), "Rapier Head(Stage 1)");
        add(ItemInit.RAPIER_HEAD_2.get(), "Rapier Head(Stage 2)");
        add(ItemInit.RAPIER_HEAD_3.get(), "Rapier Head(Stage 3)");
        add(ItemInit.RAPIER_HEAD_4.get(), "Rapier Head(Finished)");

        add(ItemInit.CLEAVER_HEAD.get(), "Cleaver Head(Finished)");

        add(ItemInit.MACE_HEAD.get(), "Mace Head(Stage 1)");
        add(ItemInit.MACE_HEAD_2.get(), "Mace Head(Stage 2)");
        add(ItemInit.MACE_HEAD_3.get(), "Mace Head(Finished)");

        add(ItemInit.STAFF_HEAD.get(), "Staff Head(Stage 1)");
        add(ItemInit.STAFF_HEAD_2.get(), "Staff Head(Stage 2)");
        add(ItemInit.STAFF_HEAD_3.get(), "Staff Head(Stage 3)");
        add(ItemInit.STAFF_HEAD_4.get(), "Staff Head(Stage 4)");
        add(ItemInit.STAFF_HEAD_5.get(), "Staff Head(Finished)");

        add(ItemInit.LONGSWORD_HEAD.get(), "Longsword Head(Stage 1)");
        add(ItemInit.LONGSWORD_HEAD_2.get(), "Longsword Head(Stage 2)");
        add(ItemInit.LONGSWORD_HEAD_3.get(), "Longsword Head(Stage 3)");
        add(ItemInit.LONGSWORD_HEAD_4.get(), "Longsword Head(Finished)");

        add(ItemInit.KODACHI_HEAD.get(), "Kodachi Head(Stage 1)");
        add(ItemInit.KODACHI_HEAD_2.get(), "Kodachi Head(Finished)");

        add(ItemInit.BATTLEAXE_HEAD.get(), "Battleaxe Head(Stage 1)");
        add(ItemInit.BATTLEAXE_HEAD_2.get(), "Battleaxe Head(Stage 2)");
        add(ItemInit.BATTLEAXE_HEAD_3.get(), "Battleaxe Head(Stage 3)");
        add(ItemInit.BATTLEAXE_HEAD_4.get(), "Battleaxe Head(Stage 4)");
        add(ItemInit.BATTLEAXE_HEAD_5.get(), "Battleaxe Head(Finished)");

        add(ItemInit.ZWEIHANDER_HEAD.get(), "Zweihander Head(Stage 1)");
        add(ItemInit.ZWEIHANDER_HEAD_2.get(), "Zweihander Head(Stage 2)");
        add(ItemInit.ZWEIHANDER_HEAD_3.get(), "Zweihander Head(Stage 3)");
        add(ItemInit.ZWEIHANDER_HEAD_4.get(), "Zweihander Head(Stage 4)");
        add(ItemInit.ZWEIHANDER_HEAD_5.get(), "Zweihander Head(Finished)");

        add(ItemInit.NODACHI_HEAD.get(), "Nodachi Head(Stage 1)");
        add(ItemInit.NODACHI_HEAD_2.get(), "Nodachi Head(Stage 2)");
        add(ItemInit.NODACHI_HEAD_3.get(), "Nodachi Head(Stage 3)");
        add(ItemInit.NODACHI_HEAD_4.get(), "Nodachi Head(Finished)");

        add(ItemInit.SABRE_HEAD.get(), "Sabre Head(Stage 1)");
        add(ItemInit.SABRE_HEAD_2.get(), "Sabre Head(Stage 2)");
        add(ItemInit.SABRE_HEAD_3.get(), "Sabre Head(Stage 3)");
        add(ItemInit.SABRE_HEAD_4.get(), "Sabre Head(Finished)");

        add(ItemInit.MAKHAIRA_HEAD.get(), "Makhaira Head(Stage 1)");
        add(ItemInit.MAKHAIRA_HEAD_2.get(), "Makhaira Head(Stage 2)");
        add(ItemInit.MAKHAIRA_HEAD_3.get(), "Makhaira Head(Finished)");

        add(ItemInit.SPEAR_HEAD.get(), "Spear Head(Stage 1)");
        add(ItemInit.SPEAR_HEAD_2.get(), "Spear Head(Finished)");
    }

    protected void addTranslatableText(MutableComponent translatable,String translation) {
        add(((TranslatableComponent)translatable).getKey(),translation);
    }

    protected void addTab(CreativeModeTab tab,String translation) {
        addTranslatableText((MutableComponent) tab.getDisplayName(),translation);
    }

    protected void addMenu(Supplier<? extends MenuType<?>> supplier,String key) {
        add(Utils.getMenuDescId(supplier.get()),key);
    }

    protected void addFluid(Supplier<? extends Fluid> supplier,String key) {
        add(Utils.getFluidDescId(supplier.get()),key);
    }

    protected void addDefaultMenu(Supplier<? extends MenuType<?>> supplier) {
        addMenu(supplier,getNameFromMenu(supplier.get()));
    }

    protected void addDefaultFluid(Supplier<? extends Fluid> supplier) {
        addFluid(supplier,getNameFromFluid(supplier.get()));
    }

    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier,getNameFromItem(supplier.get()));
    }

    protected void addDefaultBlock(Supplier<? extends Block> supplier) {
        addBlock(supplier,getNameFromBlock(supplier.get()));
    }

    public static String getNameFromMenu(MenuType<?> menuType) {
        return StringUtils.capitaliseAllWords(Utils.getMenuDescId(menuType).split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromFluid(Fluid fluid) {
        return StringUtils.capitaliseAllWords(Utils.getFluidDescId(fluid).split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getBasicName(String key) {
        return StringUtils.capitaliseAllWords(key.replace("_", " "));
    }


    public static String getNameFromBlock(Block block) {
        return StringUtils.capitaliseAllWords(block.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

}
