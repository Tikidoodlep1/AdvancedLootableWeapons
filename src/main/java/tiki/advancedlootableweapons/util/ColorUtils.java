package tiki.advancedlootableweapons.util;

import java.awt.Color;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColorUtils {

	public static int getColorFromItemStack(ItemStack stack) {
		return getColorFromItemTexture(Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, null, null).getParticleTexture());
	}
	
	public static int getColorFromItemTexture(TextureAtlasSprite sprite) {
		if(sprite == null || sprite.getIconName().equals("missingno")) {
			return 0xFFFF00FF;
		}
		
		int pixels = 0;
		int[] frameData = sprite.getFrameTextureData(0)[0];
		int width = sprite.getIconWidth();
		int height = sprite.getIconHeight();
		
		int a = 0xFF;
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int c = frameData[(j * width) + i];
				if(((c >> 24) & 0xFF) <= 0) {
					continue;
				}
				
				r += ((c >> 16) & 0xFF) * 1.05 + 20;
				g += ((c >> 8) & 0xFF) * 1.05 + 20;
				b += ((c) & 0xFF) * 1.05 + 20;
				
				pixels++;
			}
		}
		
		r = MathHelper.clamp(Math.round(((float) r / (float) pixels)), 0, 255);
		g = MathHelper.clamp(Math.round(((float) g / (float) pixels)), 0, 255);
		b = MathHelper.clamp(Math.round(((float) b / (float) pixels)), 0, 255);
		
		int color = (a << 24) | (r << 16) | (g << 8) | (b);
		return color;
	}

	//@SideOnly(Side.CLIENT) // Fix this - Is calling when thrown spears are picked up from the server. 
	public static int[] getColorPalateFromItemStack(ItemStack stack) {
		return getColorPalateFromItemTexture(Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, null, null).getParticleTexture());
	}

	public static int[] getColorPalateFromItemTexture(TextureAtlasSprite sprite) {
		if(sprite == null || sprite.getIconName().equals("missingno")) {
			return new int[] {0xFFFF00FF, 0x00000000, 0xFFFF00FF, 0x00000000, 0xFFFF00FF};
		}
		int[] colors = new int[5];
//		int colorIter = 0;
		
		int[] frameData = sprite.getFrameTextureData(0)[0];
		int width = sprite.getIconWidth();
		int height = sprite.getIconHeight();
		
		int a = 0xFF;
		int r = 0;
		int g = 0;
		int b = 0;
		int darkish = 0xFFFFFFFF;
		int darkest = 0xFFFFFFFF;
		int lightest = 0x00000000;
		int lightish = 0x00000000;
		int mid = 0x00000000;
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int c = frameData[(j * width) + i];
				if(((c >> 24) & 0xFF) <= 0x0F) {
					continue;
				}
				
				r = ((c >> 16) & 0xFF);
				g = ((c >> 8) & 0xFF);
				b = ((c) & 0xFF);
				
				int color = (a << 24) | (r << 16) | (g << 8) | (b);
//				boolean contains = false;
//				
//				for(int k = 0; k < colors.length; k++) {
//					if(colors[k] == color) {
//						contains = true;
//					}
//				}
				
				if(Integer.compareUnsigned(color, darkest) < 0) {
					darkest = color;
				}else if(Integer.compareUnsigned(color, darkest) > 0 && Integer.compareUnsigned(color, darkish) < 0) {
					darkish = color;
				}
				
				if(Integer.compareUnsigned(color, lightest) > 0) {
					lightest = color;
				}else if(Integer.compareUnsigned(color, lightest) < 0 && Integer.compareUnsigned(color, lightish) > 0) {
					lightish = color;
				}else if(Integer.compareUnsigned(color, lightest) < 0 && Integer.compareUnsigned(color, lightish) < 0 && Integer.compareUnsigned(color, mid) > 0) {
					mid = color;
				}
				
				if(Integer.compareUnsigned(color, darkish) > 0 && Integer.compareUnsigned(color, lightish) < 0) {
					colors[2] = color;
				}
				
//				if(colorIter < colors.length) {
//					if(!contains) {
//						colors[colorIter++] = color;
//					}
//				}
			}
		}
		
		colors[0] = darkest;
		colors[1] = darkish;
		colors[3] = mid;
		colors[4] = lightest;
		
//		Arrays.sort(colors);
		
//		if(colors[0] < darkest) {
//			colors[0] = darkest;
//		}
//		if(colors[1] < darkish) {
//			colors[1] = darkish;
//		}
		
		float[] hsb = new float[3];
//		Color.RGBtoHSB( (colors[0] >> 16) & 0xFF, (colors[0] >> 8) & 0xFF, (colors[0]) & 0xFF, hsb);
//		if(hsb[2] > 0.7) {
//			colors[0] = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2] - 0.2f);
//		}
//		Color.RGBtoHSB( (colors[1] >> 16) & 0xFF, (colors[1] >> 8) & 0xFF, (colors[1]) & 0xFF, hsb);
//		if(hsb[2] > 0.7) {
//			colors[1] = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2] - 0.2f);
//		}
		
		Color.RGBtoHSB( (colors[2] >> 16) & 0xFF, (colors[2] >> 8) & 0xFF, (colors[2]) & 0xFF, hsb);
		if(hsb[2] > 0.7) {
			colors[2] = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2] - 0.1f);
		}
		
		Color.RGBtoHSB( (colors[3] >> 16) & 0xFF, (colors[3] >> 8) & 0xFF, (colors[3]) & 0xFF, hsb);
		if(hsb[2] < 0.3) {
			colors[3] = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2] + 0.1f);
		}
		
		
		return colors;
	}
	
	
}
