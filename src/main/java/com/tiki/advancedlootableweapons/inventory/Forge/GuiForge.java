package com.tiki.advancedlootableweapons.inventory.Forge;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class GuiForge extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge.png");
	private final InventoryPlayer player;
	private final TileEntityForge tileentity;
	
	public GuiForge(InventoryPlayer player, TileEntityForge tileentity) 
	{
		super(new ContainerForge(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
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
		
		int k = this.getBurnLeftScaled();
		this.drawTexturedModalRect(this.guiLeft + 24, this.guiTop + 69 - k, 176, 65 - k, 18, k);
	}
	
	public void drawHoveringForgeTempText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 24 && mouseX < this.guiLeft + 43 && mouseY > this.guiTop + 4 && mouseY < this.guiTop + 70) {
			this.drawHoveringText("Forge Temperature: " + (int)((this.tileentity.getField(1)-32)*5/9) + "�C", mouseX, mouseY);
		}
	}
	
	private int getBurnLeftScaled()
	{
		return (int)(this.tileentity.getField(1) * 0.035);
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