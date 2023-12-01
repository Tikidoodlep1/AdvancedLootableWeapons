package tiki.rotn.advancedlootableweapons.items;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.BakedItemModel;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.tools.ToolSlashSword;
import tiki.rotn.advancedlootableweapons.tools.ToolStabSword;
import tiki.rotn.advancedlootableweapons.util.WeaponEffectiveness;

// unused?

//public class ModelWeapon implements IModel {
//	
//	private final ResourceLocation[] textures;
//	private final TextureAtlasSprite baseItem;
//	private final WeaponEffectiveness type;
//	private final ItemOverrideList overrides;
//	
//	public ModelWeapon(TextureAtlasSprite baseItem, WeaponEffectiveness type, ItemOverrideList overrides, ResourceLocation... textures) {
//		this.textures = textures;
//		this.baseItem = baseItem;
//		this.type = type;
//		this.overrides = overrides;
//	}
//	
//	@Override
//	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
//		ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();
//		boolean identity = !state.apply(Optional.empty()).isPresent() || state.apply(Optional.empty()).get().isIdentity();
//		
//		TextureAtlasSprite[] sprites = new TextureAtlasSprite[textures.length];
//		for(int i = 0; i < textures.length; i++) {
//            sprites[i] = bakedTextureGetter.apply(textures[i]);
//        }
//		//this.getColorPalate();
//		for(int i = 0; i < textures.length; i++) {
//			
//            builder.addAll(ItemLayerModel.getQuadsForSprite(i, sprites[i], format, state.apply(Optional.empty())));
//		}
//		
//		TextureAtlasSprite particle = bakedTextureGetter.apply(textures[0]);
//		ImmutableMap<TransformType, TRSRTransformation> map = PerspectiveMapWrapper.getTransforms(state);
//		return new BakedItemModel(builder.build(), particle, map, overrides, identity);
//	}
//	
//	@Override
//	public Collection<ResourceLocation> getTextures() {
//		ImmutableSet.Builder<ResourceLocation> builder = ImmutableSet.builder();
//		
//		for(int i = 0; i < textures.length; i++) {
//			if(textures[i] != null) {
//				builder.add(textures[i]);
//			}
//		}
//		
//		return builder.build();
//	}
//	
//	@Override
//	public IModel retexture(ImmutableMap<String, String> textures) {
//		// TODO Auto-generated method stub
//		return IModel.super.retexture(textures);
//	}
//	
////	private void getColorPalate() {
////		if(this.baseItem != null && !this.baseItem.getIconName().equals("missingno")) {
////			int[] colors = new int[5];
////			Arrays.fill(colors, -1);
////			int colorIndex = 2;
////			TextureAtlasSprite sprite = baseItem;
////			List<int[][]> textureData = new ArrayList<int[][]>(sprite.getFrameCount());
////			for(int f = 0; f < sprite.getFrameCount(); f++) {
//////				int pr = 0x00;
//////				int pg = 0x00;
//////				int pb = 0x00;
////				for(int p = 0; p < sprite.getFrameTextureData(f)[0].length; p++) {
////					for(int i = 0; i < colors.length; i++) {
////						if(colors[i] == p) {
////							continue;
////						}
////					}
////					
//////					int r = (p >> 24) & 0xFF;
//////					int g = (p >> 16) & 0xFF;
//////					int b = (p >> 8) & 0xFF;
////					int a = p & 0xFF;
////					
////					if(a > 0x6F && colorIndex < 5) {
////						for(int i = 0; i < colors.length; i++) {
////							if(colors[i] != p) {
////								colors[colorIndex++] = p;
////							}
////						}
//////						if(colorIndex < 5 && (r > (pr & 0x05) || b > (pb & 0x05) || g > (pg & 0x05)) ) {
//////							colors[colorIndex++] = p;
//////						}else if(colorIndex < 5 && (r < (pr) || b < (pb) || g < (pg)) ) {
//////							colors[colorIndex++] = p;
//////						}
////					}
////					
//////					pr = r;
//////					pg = g;
//////					pb = b;
////				}
////				
////			}
////			
////			Arrays.sort(colors);
////			
////			//System.out.println(Arrays.toString(colors));
////			int[][] template = SpriteTemplates.getByType(type);
////			for(int f = 0; f < sprite.getFrameCount(); f++) {
////				int[][] newTextureData = new int[sprite.getFrameTextureData(f).length][sprite.getFrameTextureData(f)[0].length];
////				int[][] oldData = sprite.getFrameTextureData(f);	
////				for(int i = 0; i < oldData.length; i++) {
////					for(int j = 0; j < oldData[i].length; j++) {
////						if(i > 15 || j > 15 || template[i][j] == -1) {
////							newTextureData[i][j] = oldData[i][j];
////							continue;
////						}
////						newTextureData[i][j] = (oldData[i][j] * colors[template[i][j]]) / 255;
////					}
////				}
////				textureData.add(newTextureData);
////			}
////			sprite.setFramesTextureData(textureData);
////		}
////	}
//	
//	public static enum Loader implements ICustomModelLoader
//    {
//        INSTANCE;
//		
//        @Override
//        public void onResourceManagerReload(IResourceManager resourceManager) {}
//
//        @Override
//        public boolean accepts(ResourceLocation modelLocation)
//        {
//            return modelLocation.getResourceDomain().equals(ModInfo.ID) && !modelLocation.getResourcePath().contains("hot") && !modelLocation.getResourcePath().contains("_wood") && (
//            		modelLocation.getResourcePath().startsWith("dagger_") ||
//            		modelLocation.getResourcePath().startsWith("kabutowari_") ||
//            		modelLocation.getResourcePath().startsWith("talwar_") ||
//            		modelLocation.getResourcePath().startsWith("rapier_") ||
//            		modelLocation.getResourcePath().startsWith("cleaver_") ||
//            		modelLocation.getResourcePath().startsWith("mace_") ||
//            		modelLocation.getResourcePath().startsWith("staff_") ||
//            		modelLocation.getResourcePath().startsWith("longsword_") ||
//            		modelLocation.getResourcePath().startsWith("kodachi_") ||
//            		modelLocation.getResourcePath().startsWith("nodachi_") ||
//            		modelLocation.getResourcePath().startsWith("battleaxe_") ||
//            		modelLocation.getResourcePath().startsWith("zweihander_") ||
//            		modelLocation.getResourcePath().startsWith("sabre_") ||
//            		modelLocation.getResourcePath().startsWith("makhaira_") ||
//            		modelLocation.getResourcePath().startsWith("spear_")
//            		);
//        }
//        
//        @Override
//        public IModel loadModel(ResourceLocation modelLocation)
//        {
//        	Item baseItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(modelLocation.toString().substring(0, modelLocation.toString().indexOf('#'))));
//        	System.out.println(modelLocation.toString().substring(0, modelLocation.toString().indexOf('#')));
//        	String path = modelLocation.getResourcePath();
//        	TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(new ItemStack(Items.BEEF), null, null).getParticleTexture();;
//        	if(baseItem instanceof ToolSlashSword) {
//        		sprite = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides( ((ToolSlashSword)baseItem).getToolMaterial().getRepairItemStack(), null, null).getParticleTexture();
//        	}else if(baseItem instanceof ToolStabSword) {
//        		sprite = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides( ((ToolStabSword)baseItem).getToolMaterial().getRepairItemStack(), null, null).getParticleTexture();
//        	}
//        	if(path.startsWith("dagger_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.DAGGER, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/dagger_head_metal"), new ResourceLocation(ModInfo.ID + ":items/dagger_handle"));
//        	}else if(path.startsWith("kabutowari_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.KABUTOWARI, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/kabutowari_head_metal"), new ResourceLocation(ModInfo.ID + ":items/kabutowari_handle"));
//        	}else if(path.startsWith("talwar_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.TALWAR, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/talwar_head_metal"), new ResourceLocation(ModInfo.ID + ":items/talwar_handle"));
//        	}else if(path.startsWith("rapier_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.RAPIER, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/rapier_head_metal"), new ResourceLocation(ModInfo.ID + ":items/rapier_handle"));
//        	}else if(path.startsWith("cleaver_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.CLEAVER, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/cleaver_head_metal"), new ResourceLocation(ModInfo.ID + ":items/cleaver_handle"));
//        	}else if(path.startsWith("mace_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.MACE, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/mace_head_metal"), new ResourceLocation(ModInfo.ID + ":items/mace_handle"));
//        	}else if(path.startsWith("staff_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.STAFF, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/staff_head_metal"), new ResourceLocation(ModInfo.ID + ":items/staff_handle"));
//        	}else if(path.startsWith("longsword_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.LONGSWORD, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/longsword_head_metal"), new ResourceLocation(ModInfo.ID + ":items/longsword_handle"));
//        	}else if(path.startsWith("kodachi_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.KODACHI, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/kodachi_head_metal"), new ResourceLocation(ModInfo.ID + ":items/kodachi_handle"));
//        	}else if(path.startsWith("nodachi_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.NODACHI, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/nodachi_head_metal"), new ResourceLocation(ModInfo.ID + ":items/nodachi_handle"));
//        	}else if(path.startsWith("battleaxe_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.BATTLEAXE, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/battleaxe_head_metal"), new ResourceLocation(ModInfo.ID + ":items/battleaxe_handle"));
//        	}else if(path.startsWith("zweihander_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.ZWEIHANDER, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/zweihander_head_metal"), new ResourceLocation(ModInfo.ID + ":items/zweihander_handle"));
//        	}else if(path.startsWith("sabre_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.SABRE, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/sabre_head_metal"), new ResourceLocation(ModInfo.ID + ":items/sabre_handle"));
//        	}else if(path.startsWith("makhaira_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.MAKHAIRA, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/makhaira_head_metal"), new ResourceLocation(ModInfo.ID + ":items/makhaira_handle"));
//        	}else if(path.startsWith("spear_")) {
//        		return new ModelWeapon(sprite, WeaponEffectiveness.SPEAR, ItemOverrideList.NONE, new ResourceLocation(ModInfo.ID + ":items/spear_head_metal"), new ResourceLocation(ModInfo.ID + ":items/spear_handle"));
//        	}
//            return ItemLayerModel.INSTANCE;
//        }
//    }
//}
