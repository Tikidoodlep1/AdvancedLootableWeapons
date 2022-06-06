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
	private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_2.png");
	private final InventoryPlayer player;
	private final TileEntityForge2 tileentity;
	
	public GuiForge2(InventoryPlayer player, TileEntityForge2 tileentity) 
	{
		super(new ContainerForge2(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		this.xSize = 190;
		this.ySize = 171;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 3, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 92, 4210752);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
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
		int l = this.getBurnMidScaled();
		int m = this.getBurnRightScaled();
		this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 12 + k, 190, k , 19, 66);
		this.drawTexturedModalRect(this.guiLeft + 70, this.guiTop + 12 + l, 190, l, 19, 66);
		this.drawTexturedModalRect(this.guiLeft + 132, this.guiTop + 12 + m, 190, m, 19, 66);
	}
	
	private int getBurnLeftScaled()
	{
		return (int)(this.tileentity.getField(0) * 0.011);
	}
	
	private int getBurnMidScaled()
	{
		return (int)(this.tileentity.getField(1) * 0.011);
	}
	
	private int getBurnRightScaled()
	{
		return (int)(this.tileentity.getField(2) * 0.011);
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
