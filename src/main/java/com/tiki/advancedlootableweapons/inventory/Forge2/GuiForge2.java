package com.tiki.advancedlootableweapons.inventory.Forge2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.BlockForge2;
import com.tiki.advancedlootableweapons.blocks.BlockForge2Fuel;
import com.tiki.advancedlootableweapons.blocks.BlockForgeFuel;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2Content;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2FuelContent;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeFuelContent;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
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

public class GuiForge2 extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_2_new.png");
	private final InventoryPlayer player;
	private final TileEntityForge2 tileentity;
	private static final int slot1x = 51;
	private static final int slot2x = 80;
	private static final int slot3x = 109;
	private static final int sloty = 47;
	private static final int slotSize = 18;
	private final Set<Item> heatableMaterials;
	
	public GuiForge2(InventoryPlayer player, TileEntityForge2 tileentity) 
	{
		super(new ContainerForge2(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		this.xSize = 175;
		this.ySize = 165;
		if(tileentity.getBlockType() instanceof BlockForge2) {
			this.heatableMaterials = ((BlockForge2)tileentity.getBlockType()).acceptedMaterials;
		}else if(tileentity.getBlockType() instanceof BlockForge2Content) {
			this.heatableMaterials = ((BlockForge2Content)tileentity.getBlockType()).getRepresentation().getMatList();
		}else {
			heatableMaterials = new HashSet<Item>();
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String tileName = this.tileentity.getBlockType().getLocalizedName();
		this.fontRenderer.drawStringWithShadow(tileName, 30, 24, 0xFFFFFFFF);
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
		
		int k = this.getSlotTempScaled();
		this.drawTexturedModalRect(this.guiLeft + 51, this.guiTop + 63 - k, 176, 63 - k, 74, k);
		
		if(ConfigHandler.USE_LARGER_TEXTURE_FOR_TEMP) {
			//Smoke drawing the temperature too
			int j = this.getLargeTempScaled();
			this.drawTexturedModalRect(this.guiLeft + 40, this.guiTop + 64 - j, 160, 193 - j, 96, j);
		}
	}
	
	public void drawHoveringForgeTempText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 40 && mouseX < this.guiLeft + 138 && mouseY > this.guiTop + 40 && mouseY < this.guiTop + 65 && 
				!(mouseX > this.guiLeft + slot1x && mouseX < this.guiLeft + slot1x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize) &&
				!(mouseX > this.guiLeft + slot2x && mouseX < this.guiLeft + slot2x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize) &&
				!(mouseX > this.guiLeft + slot3x && mouseX < this.guiLeft + slot3x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize)) {
			this.drawHoveringText("Forge Temperature: " + (int)((this.tileentity.getField(3)-32)*5/9) + " Celcius", mouseX, mouseY);
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
	
	private int getSlotTempScaled() {
		return (int)((this.tileentity.getField(3) - 850) * 0.0136);
	}
	
	private int getLargeTempScaled() {
		return (int)((this.tileentity.getField(3) - 850) * 0.0294);
	}
	
	public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList)
    {
        this.sendSlotContents(containerToSend, 0, containerToSend.getSlot(0).getStack());
        this.sendSlotContents(containerToSend, 1, containerToSend.getSlot(1).getStack());
        this.sendSlotContents(containerToSend, 2, containerToSend.getSlot(2).getStack());
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
