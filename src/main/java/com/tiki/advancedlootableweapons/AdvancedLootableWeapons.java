package com.tiki.advancedlootableweapons;

import com.mojang.logging.LogUtils;
import com.tiki.advancedlootableweapons.client.ALWClient;
import com.tiki.advancedlootableweapons.data.ModDatagen;
import com.tiki.advancedlootableweapons.handlers.ArmorBonus;
import com.tiki.advancedlootableweapons.handlers.GlobalDropHandler;
import com.tiki.advancedlootableweapons.handlers.config.ClientConfigHandler;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.*;

import com.tiki.advancedlootableweapons.menu.AnvilForgingMenu;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.util.MCVersion;
import com.tiki.advancedlootableweapons.world.ModOrePlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.IForgeRegistry;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AdvancedLootableWeapons.MODID)
public class AdvancedLootableWeapons
{
    public static final String MODID = "advancedlootableweapons";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public AdvancedLootableWeapons()
    {
        ForgeMod.enableMilkFluid();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemInit.register(eventBus);
        BlockInit.register(eventBus);
        FluidInit.register(eventBus);
        
        BlockEntityInit.register(eventBus);
        MenuInit.register(eventBus);
        SoundInit.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigHandler.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigHandler.SPEC);
        
        ModRecipeSerializers.register(eventBus);
        
        ForgeMod.enableMilkFluid();
        
        eventBus.addListener(this::setup);
        if (FMLEnvironment.dist.isClient()) {
            eventBus.addListener(this::clientSetup);
            eventBus.addListener(ALWClient::models);
            eventBus.addListener(ALWClient::colors);
        }
        eventBus.addListener(ModDatagen::start);
        eventBus.addGenericListener(GlobalLootModifierSerializer.class,this::registerLootSerializers);
        MinecraftForge.EVENT_BUS.addListener(this::attributeModifiers);
        MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::addOres);
    }

    //copied from ArmorItem
    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private void attributeModifiers(ItemAttributeModifierEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof ArmorItem armorItem) {
            ArmorBonus armorBonus = ArmorBonus.getArmorBonus(armorItem.getMaterial());
            EquipmentSlot slot = event.getSlotType();
            if (slot == armorItem.getSlot()) {
                UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[slot.getIndex()];
                if (CommonConfigHandler.USE_ARMOR_BONUS_HEALTH.get()) {
                    event.addModifier(Attributes.MAX_HEALTH, new AttributeModifier(uuid,"Armor Bonus", armorBonus.bonusHealth()[slot.getIndex()], AttributeModifier.Operation.ADDITION));
                }

                if (CommonConfigHandler.USE_ARMOR_WEIGHT.get()) {
                    event.addModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid,"Armor Bonus", armorBonus.bonusSpeed()[slot.getIndex()], AttributeModifier.Operation.ADDITION));
                }
                if (slot == EquipmentSlot.CHEST) {
                    if (CommonConfigHandler.USE_ARMOR_BONUS_DAMAGE.get()) {
                        event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid,"Armor Bonus", armorBonus.damageBonus(), AttributeModifier.Operation.ADDITION));
                    }
                }
            }
        }
    }

    private void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getPlayer();
        Level level = player.level;
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();

        if (state.is(BlockTags.ANVIL) && stack.getItem() instanceof ForgeHammerItem) {
            if (!level.isClientSide) {
                player.openMenu(new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new
                        AnvilForgingMenu(pContainerId, pPlayerInventory, ContainerLevelAccess.create(level, pos)),
                        MCVersion.translation("container.advancedlootableweapons.anvil_forging")));
            }
            event.setCanceled(true);
        }
    }

    private void addOres(BiomeLoadingEvent event) {
        if (isValidBiome(event.getCategory())) {
            BiomeGenerationSettingsBuilder generation = event.getGeneration();
            if (CommonConfigHandler.ENABLE_TIN_ORE_GENERATION.get())
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOrePlacements.ORE_TIN);
            if (CommonConfigHandler.ENABLE_SILVER_ORE_GENERATION.get())
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOrePlacements.ORE_SILVER_EXTRA);
            if (CommonConfigHandler.ENABLE_PLATINUM_ORE_GENERATION.get())
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOrePlacements.ORE_PLATINUM_EXTRA);
            if (CommonConfigHandler.ENABLE_CRYSTALLITE_ORE_GENERATION.get())
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOrePlacements.ORE_CRYSTALLITE_EXTRA);
            if (CommonConfigHandler.ENABLE_GYPSUM_GENERATION.get())
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,ModOrePlacements.ORE_GYPSUM);
            if (CommonConfigHandler.ENABLE_FELDSPAR_GENERATION.get())
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,ModOrePlacements.ORE_FELDSPAR);
            if (CommonConfigHandler.ENABLE_DOLOMITE_GENERATION.get())
                generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,ModOrePlacements.ORE_DOLOMITE);
        }
    }

    private static boolean isValidBiome(Biome.BiomeCategory biomeCategory) {
        //If this does weird things to unclassified biomes (Category.NONE), then we should also mark that biome as invalid
        return biomeCategory != Biome.BiomeCategory.THEEND && biomeCategory != Biome.BiomeCategory.NETHER && biomeCategory != Biome.BiomeCategory.NONE;//void uses none
    }

    private void registerLootSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        IForgeRegistry<GlobalLootModifierSerializer<?>> registry = event.getRegistry();
        GlobalDropHandler.initDropList();
        for(GlobalLootModifierSerializer<?> s : GlobalDropHandler.dropList) {
            registry.register(s);
        }
        ModRecipeTypes.poke();
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ALWClient.setup(event);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID,path);
    }

}
