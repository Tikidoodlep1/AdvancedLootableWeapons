package com.tiki.advancedlootableweapons.inventory.Forge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import com.tiki.advancedlootableweapons.blocks.BlockForge;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeContent;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiForge extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_new.png");
	private final InventoryPlayer player;
	private final TileEntityForge tileentity;
	private final Set<Item> heatableMaterials;
	
	public GuiForge(InventoryPlayer player, TileEntityForge tileentity) 
	{
		super(new ContainerForge(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		this.xSize = 176;
		this.ySize = 166;
		
		if(tileentity.getBlockType() instanceof BlockForge) {
			this.heatableMaterials = ((BlockForge)tileentity.getBlockType()).acceptedMaterials;
		}else if(tileentity.getBlockType() instanceof BlockForgeContent) {
			this.heatableMaterials = ((BlockForgeContent)tileentity.getBlockType()).getRepresentation().getMatList();
		}else {
			heatableMaterials = new HashSet<Item>();
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawStringWithShadow(tileName, 5, 6, 0xFFFFFFFF);
		this.fontRenderer.drawStringWithShadow(this.player.getDisplayName().getUnformattedText(), 5, 72, 0xFFFFFFFF);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.drawHoveringForgeTempText(mouseX, mouseY);
        this.drawHoveringMaterialText(mouseX, mouseY);
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int k = this.getSlotTempScaled() == 0 ? 1 : this.getSlotTempScaled();
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 64 - k, 176, 63 - k, 16, k);
		
		if(ConfigHandler.USE_LARGER_TEXTURE_FOR_TEMP) {
			//Smoke drawing the temperature too
			int j = this.getSmokeTempScaled();
			this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 43 - j, 200, 42 - j, 24, j);
		}
		
	}
	
	private void drawHoveringForgeTempText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 76 && mouseX < this.guiLeft + 101 && mouseY > this.guiTop + 27 && mouseY < this.guiTop + 52) {
			this.drawHoveringText("Forge Temperature: " + (int)((this.tileentity.getField(1)-32)*5/9) + " Celcius", mouseX, mouseY);
		}
	}
	
	private void drawHoveringMaterialText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 156 && mouseX < this.guiLeft + 175 && mouseY > this.guiTop + 2 && mouseY < this.guiTop + 17) {
			List<String> lines = new ArrayList<String>(3);
			lines.add("This Forge Can Heat:");
			if(this.heatableMaterials.isEmpty()) {
				lines.add("Any Material");
			}else {
				for(Item item : this.heatableMaterials) {
					lines.add("Material: " + new TextComponentTranslation(item.getUnlocalizedName()).getFormattedText());
				}
			}
			this.drawHoveringText(lines, mouseX, mouseY);
		}
	}
	
	private int getSlotTempScaled()
	{
		return (int)((this.tileentity.getField(1) - 840) * 0.0166);
	}
	
	private int getSmokeTempScaled()
	{
		return (int)((this.tileentity.getField(1) - 850) * 0.0277);
	}
	
	public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList)
    {
        this.sendSlotContents(containerToSend, 0, containerToSend.getSlot(0).getStack());
    }
	
    public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack)
    {
    }
    
    public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue)
    {
    }
    
    public void sendAllWindowProperties(Container containerIn, IInventory inventory)
    {
    }
}
