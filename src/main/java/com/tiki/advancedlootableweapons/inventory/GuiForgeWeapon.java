package com.tiki.advancedlootableweapons.inventory;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
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
import scala.reflect.internal.Trees.This;

@SideOnly(Side.CLIENT)
public class GuiForgeWeapon extends GuiContainer implements IContainerListener{
	
		private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_weapon.png");
		private final ContainerForgeWeapon container;
	    private final InventoryPlayer playerInventory;
	    private final GuiWeaponButton button1 = new GuiWeaponButton(0, 20, 100, 20, 20, "Dagger");
	    private final GuiWeaponButton button2 = new GuiWeaponButton(1, 50, 100, 20, 20, "Kabutowari");
	    private final GuiWeaponButton button3 = new GuiWeaponButton(2, 80, 100, 20, 20, "Talwar");
	    private final GuiWeaponButton button4 = new GuiWeaponButton(3, 110, 100, 20, 20, "Rapier");

	    public GuiForgeWeapon(InventoryPlayer inventoryIn, World worldIn)
	    {
	    	super(new ContainerForgeWeapon(inventoryIn, worldIn, Minecraft.getMinecraft().player));
	        this.playerInventory = inventoryIn;
	        this.container = (ContainerForgeWeapon)this.inventorySlots;
	    }
	    
	    public void drawButtons() {
	        this.addButton(button1);
	        this.addButton(button2);
	        this.addButton(button3);
	        this.addButton(button4);
	    }
	    
	    protected void actionPerformed(GuiButton button) {
	    	if(button.id == 0) {
	        	if(this.container.getSlot(0).getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
	        		this.container.buttonPressed = 1;
	        		this.container.changeItem();
	        		System.out.print("CONTAINER.BUTTONPRESSED IS EQUAL TO 1");
	        	}
	        }
	    	if(button.id == 1) {
	        	if(this.container.getSlot(0).getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
	        		this.container.buttonPressed = 2;
	        		this.container.changeItem();
	        		System.out.print("CONTAINER.BUTTONPRESSED IS EQUAL TO 2");
	        	}
	        }
	    	if(button.id == 2) {
	        	if(this.container.getSlot(0).getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
	        		this.container.buttonPressed = 3;
	        		this.container.changeItem();
	        		System.out.print("CONTAINER.BUTTONPRESSED IS EQUAL TO 3");
	        	}
	        }
	    	if(button.id == 3) {
	        	if(this.container.getSlot(0).getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
	        		this.container.buttonPressed = 4;
	        		this.container.changeItem();
	        		System.out.print("CONTAINER.BUTTONPRESSED IS EQUAL TO 4");
	        	}
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
	        this.fontRenderer.drawString(I18n.format("container.forgeWeapon"), 50, 3, 4210752);

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
	        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURES);
	        int i = (this.width - this.xSize) / 2;
	        int j = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	        this.drawButtons();
	    }
	    

	    public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList)
	    {
	        this.sendSlotContents(containerToSend, 0, containerToSend.getSlot(0).getStack());
	        this.sendSlotContents(containerToSend, 1, containerToSend.getSlot(1).getStack());
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
