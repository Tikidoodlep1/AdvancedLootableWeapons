package com.tiki.advancedlootableweapons.inventory.SharpeningStone;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.items.ItemSharpeningStone;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSharpeningStone extends GuiContainer implements IContainerListener{
	
		private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/sharpening_stone.png");
	    private final ContainerSharpeningStone stone;
	    private final InventoryPlayer playerInventory;
	    public ItemSharpeningStone item;

	    public GuiSharpeningStone(InventoryPlayer inventoryIn, World worldIn)
	    {
	        super(new ContainerSharpeningStone(inventoryIn, worldIn, inventoryIn.player));
	        this.playerInventory = inventoryIn;
	        this.stone = (ContainerSharpeningStone)this.inventorySlots;
	        if(item instanceof ItemSharpeningStone) {
	        	this.item = (ItemSharpeningStone)item;
	        }else {
	        	this.item = null;
	        }
	    }


	    public void initGui()
	    {
	        super.initGui();
	        Keyboard.enableRepeatEvents(true);
	        this.inventorySlots.removeListener(this);
	        this.inventorySlots.addListener(this);
	    }


	    public void onGuiClosed()
	    {
	        super.onGuiClosed();
	        Keyboard.enableRepeatEvents(false);
	        this.inventorySlots.removeListener(this);
	    }


	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        GlStateManager.disableLighting();
	        GlStateManager.disableBlend();
	        this.fontRenderer.drawString(I18n.format("container.sharpeningStone"), 50, 3, 4210752);

	        GlStateManager.enableLighting();
	    }


	    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	    {
	        super.mouseClicked(mouseX, mouseY, mouseButton);
	    }


	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        this.drawDefaultBackground();
	        super.drawScreen(mouseX, mouseY, partialTicks);
	        this.renderHoveredToolTip(mouseX, mouseY);
	        GlStateManager.disableLighting();
	        GlStateManager.disableBlend();
	    }


	    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	    {
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        //this.mc.getTextureManager().bindTexture(TEXTURES);
	        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURES);
	        int i = (this.width - this.xSize) / 2;
	        int j = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	        this.drawTexturedModalRect(i + 59, j + 20, 0, this.ySize + (this.stone.getSlot(0).getHasStack() ? 0 : 16), 110, 16);

	        if (this.stone.getSlot(0).getHasStack() && !this.stone.getSlot(2).getHasStack())
	        {
	            this.drawTexturedModalRect(i + 99, j + 45, this.xSize, 0, 28, 21);
	        }
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
