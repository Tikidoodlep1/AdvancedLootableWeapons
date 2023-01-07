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
	private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_new.png");
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
		this.fontRenderer.drawStringWithShadow(tileName, ((this.xSize / 2) - this.fontRenderer.getStringWidth(tileName) / 2), 6, 0xFFFFFFFF);
		this.fontRenderer.drawStringWithShadow(this.player.getDisplayName().getUnformattedText(), 0, this.ySize - 97, 0xFFFFFFFF);
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
		
		int k = this.getSlotTempScaled() == 0 ? 1 : this.getSlotTempScaled();
		this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 73 - k, 176, 16 - k, 15, k);
		
		//Static smoke texture being drawn
		this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 27, 176, 41, 25, 25);
		//Smoke drawing the temperature too
//		int j = this.getSmokeTempScaled();
//		this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 52 - j, 176, 66 - j, 25, j);
	}
	
	public void drawHoveringForgeTempText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 76 && mouseX < this.guiLeft + 101 && mouseY > this.guiTop + 27 && mouseY < this.guiTop + 52) {
			this.drawHoveringText("Forge Temperature: " + (int)((this.tileentity.getField(1)-32)*5/9) + " Celcius", mouseX, mouseY);
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
