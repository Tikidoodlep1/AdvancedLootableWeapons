package com.tiki.advancedlootableweapons.inventory.Forge2;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

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
	
	public GuiForge2(InventoryPlayer player, TileEntityForge2 tileentity) 
	{
		super(new ContainerForge2(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawStringWithShadow(tileName, ((this.xSize / 2) - this.fontRenderer.getStringWidth(tileName) / 2), 3, 0xFFFFFFFF);
		this.fontRenderer.drawStringWithShadow(this.player.getDisplayName().getUnformattedText(), -22, this.ySize - 98, 0xFFFFFFFF);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.drawHoveringForgeTempText(mouseX, mouseY);
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
		this.drawTexturedModalRect(this.guiLeft + 50, this.guiTop + 64 - k, 176, 19 - k, 77, k);
	}
	
	public void drawHoveringForgeTempText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 40 && mouseX < this.guiLeft + 138 && mouseY > this.guiTop + 40 && mouseY < this.guiTop + 65 && 
				!(mouseX > this.guiLeft + slot1x && mouseX < this.guiLeft + slot1x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize) &&
				!(mouseX > this.guiLeft + slot2x && mouseX < this.guiLeft + slot2x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize) &&
				!(mouseX > this.guiLeft + slot3x && mouseX < this.guiLeft + slot3x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize)) {
			this.drawHoveringText("Forge Temperature: " + (int)((this.tileentity.getField(3)-32)*5/9) + " Celcius", mouseX, mouseY);
		}
	}
	
	private int getSlotTempScaled()
	{
		return (int)((this.tileentity.getField(3) - 850) * 0.0136);
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
