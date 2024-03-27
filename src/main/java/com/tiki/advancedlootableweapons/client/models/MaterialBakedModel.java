package com.tiki.advancedlootableweapons.client.models;

import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.tiki.advancedlootableweapons.AdvancedLootableWeapons;
import com.tiki.advancedlootableweapons.handlers.WeaponMaterial;
import com.tiki.advancedlootableweapons.items.weapons.AlwWeaponItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class MaterialBakedModel implements IModelGeometry<MaterialBakedModel> {

    private final Map<String, BlockModel> unbakedModels;

    public MaterialBakedModel(Map<String, BlockModel> unbakedModels) {
        this.unbakedModels = unbakedModels;
    }


    @Override
    public NBTBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
        final Map<String, BakedModel> models = new HashMap<>();

        for (Map.Entry<String, BlockModel> entry: unbakedModels.entrySet()) {
            models.put(entry.getKey(),entry.getValue().bake(bakery,entry.getValue(),spriteGetter,modelTransform,modelLocation,true));
        }

        ItemOverrides customOverrides = new ItemOverrides() {
            @Nullable
            @Override
            public BakedModel resolve(BakedModel pModel, ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
                CompoundTag tag = pStack.getTag();
                if (tag!= null && tag.contains(AlwWeaponItem.MATERIAL_KEY)) {
                    return models.get(tag.getString(AlwWeaponItem.MATERIAL_KEY));
                }
                return pModel;
            }
        };

        return new NBTBakedModel(customOverrides, spriteGetter.apply(owner.resolveTexture("particle")));
    }


    @Override
    public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        Set<Material> textures = Sets.newHashSet();
        for(BlockModel model : unbakedModels.values())
            textures.addAll(model.getMaterials(modelGetter, missingTextureErrors));
        return textures;
    }

    public static class NBTBakedModel implements BakedModel {
        private final ItemOverrides customOverrides;
        private final TextureAtlasSprite particle;

        public NBTBakedModel(ItemOverrides customOverrides, TextureAtlasSprite particle) {
            this.customOverrides = customOverrides;
            this.particle = particle;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
            return Collections.emptyList();
        }

        @Override
        public boolean useAmbientOcclusion() {
            return false;
        }

        @Override
        public boolean isGui3d() {
            return false;
        }

        @Override
        public boolean usesBlockLight() {
            return false;
        }

        @Override
        public boolean isCustomRenderer() {
            return false;
        }

        @Override
        public TextureAtlasSprite getParticleIcon() {
            return particle;
        }

        @Override
        public ItemOverrides getOverrides() {
            return customOverrides;
        }

        @Override
        public boolean doesHandlePerspectives() {
            return true;
        }

        @Override
        public ItemTransforms getTransforms() {
            return ItemTransforms.NO_TRANSFORMS;
        }
    }

    public static class Loader implements IModelLoader<MaterialBakedModel> {
        public static final Loader INSTANCE = new Loader();

        @Override
        public void onResourceManagerReload(final ResourceManager pResourceManager) {
        }

        @Override
        public MaterialBakedModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            String folder = GsonHelper.getAsString(modelContents,"folder");

            Map<String,BlockModel> models = new HashMap<>();
            for(Map.Entry<String, WeaponMaterial> entry : WeaponMaterial.LOOKUP.entrySet()) {
                if (entry.getValue().canMakeWeapon()) {
                    String material = entry.getKey();
                    JsonObject fakeModel = new JsonObject();
                    fakeModel.addProperty("parent",
                            new ResourceLocation(AdvancedLootableWeapons.MODID,
                                    "item/" + folder + "/" + material).toString());

                    BlockModel unbakedModel = deserializationContext.deserialize(fakeModel, BlockModel.class);
                    models.put(material, unbakedModel);
                }
            }
            return new MaterialBakedModel(models);
        }
    }
}
