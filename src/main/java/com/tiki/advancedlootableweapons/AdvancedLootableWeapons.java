package com.tiki.advancedlootableweapons;

import com.mojang.logging.LogUtils;
import com.tiki.advancedlootableweapons.client.ALWClient;
import com.tiki.advancedlootableweapons.data.ModDatagen;
import com.tiki.advancedlootableweapons.handlers.ArmorBonus;
import com.tiki.advancedlootableweapons.handlers.GlobalDropHandler;
import com.tiki.advancedlootableweapons.handlers.config.ClientConfigHandler;
import com.tiki.advancedlootableweapons.handlers.config.CommonConfigHandler;
import com.tiki.advancedlootableweapons.init.*;

import com.tiki.advancedlootableweapons.inventory.AnvilForgingMenu;
import com.tiki.advancedlootableweapons.items.ForgeHammerItem;
import com.tiki.advancedlootableweapons.util.MCVersion;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.IForgeRegistry;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

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
    }

    private void attributeModifiers(ItemAttributeModifierEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof ArmorItem armorItem) {
            ArmorBonus armorBonus = ArmorBonus.getArmorBonus(armorItem.getMaterial());
            EquipmentSlot slot = event.getSlotType();
            if (slot == armorItem.getSlot()) {
                if (CommonConfigHandler.USE_ARMOR_BONUS_HEALTH.get()) {
                    event.addModifier(Attributes.MAX_HEALTH, new AttributeModifier("Armor Bonus", armorBonus.bonusHealth()[slot.getIndex()], AttributeModifier.Operation.ADDITION));
                }

                if (CommonConfigHandler.USE_ARMOR_WEIGHT.get()) {
                    event.addModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier("Armor Bonus", armorBonus.bonusSpeed()[slot.getIndex()], AttributeModifier.Operation.ADDITION));
                }
                if (slot == EquipmentSlot.CHEST) {
                    if (CommonConfigHandler.USE_ARMOR_BONUS_DAMAGE.get()) {
                        event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("Armor Bonus", armorBonus.damageBonus(), AttributeModifier.Operation.ADDITION));
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
