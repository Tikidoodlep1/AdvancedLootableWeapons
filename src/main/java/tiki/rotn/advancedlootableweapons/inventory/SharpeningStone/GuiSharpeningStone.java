package tiki.rotn.advancedlootableweapons.inventory.SharpeningStone;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

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
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.items.ItemSharpeningStone;

@SideOnly(Side.CLIENT)
public class GuiSharpeningStone extends GuiContainer implements IContainerListener{
	
		private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/sharpening_stone_new.png");
	    private final ContainerSharpeningStone stone;
	    public ItemSharpeningStone item;
	    private double t = 0;

	    public GuiSharpeningStone(InventoryPlayer inventoryIn, World worldIn)
	    {
	        super(new ContainerSharpeningStone(inventoryIn, worldIn, inventoryIn.player));
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
	        this.inventorySlots.removeListener(this);
	        this.inventorySlots.addListener(this);
	    }

	    public void onGuiClosed()
	    {
	        super.onGuiClosed();
	        this.inventorySlots.removeListener(this);
	    }

	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        GlStateManager.disableLighting();
	        GlStateManager.disableBlend();
	        this.fontRenderer.drawString(I18n.format("container.sharpening_stone"), (this.xSize / 2) - (this.fontRenderer.getStringWidth(I18n.format("container.sharpening_stone")) / 2), 3, 4210752);
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
	        
	        if(this.stone.getSlot(0).getHasStack() && t >= 0.0) {
	        	int w = (int)(89 * Math.pow(t, 2));
		        this.drawTexturedModalRect(this.guiLeft + 75, this.guiTop + 10, 0, 166, w, 30);
	        }
	        t += partialTicks / 10;
	        if(t > 1.2) {
	        	t = -0.9;
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
