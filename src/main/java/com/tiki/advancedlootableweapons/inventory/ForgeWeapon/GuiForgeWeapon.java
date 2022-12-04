package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.inventory.ForgeWeapon.ContainerForgeWeapon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
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

public class GuiForgeWeapon extends GuiContainer implements IContainerListener {
		
		private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_weapon.png");
	    private final GuiWeaponButton daggerButton = new GuiWeaponButton(0, 30, 40, 20, 20, "Dagger", 0, 0);
	    private final GuiWeaponButton kabutowariButton = new GuiWeaponButton(1, 60, 40, 20, 20, "Kabutowari", 21, 0);
	    private final GuiWeaponButton talwarButton = new GuiWeaponButton(2, 90, 40, 20, 20, "Talwar", 61, 0);
	    private final GuiWeaponButton rapierButton = new GuiWeaponButton(3, 30, 70, 20, 20, "Rapier", 41, 0);
	    private final GuiWeaponButton maceButton = new GuiWeaponButton(4, 60, 70, 20, 20, "Mace", 0, 20);
	    private final GuiWeaponButton cleaverButton = new GuiWeaponButton(5, 90, 70, 20, 20, "Cleaver", 21, 20);
	    private final GuiWeaponButton staffButton = new GuiWeaponButton(6, 30, 100, 20, 20, "Staff", 41, 20);
	    private final GuiWeaponButton longswordButton = new GuiWeaponButton(7, 60, 100, 20, 20, "Longsword", 61, 20);
	    private final GuiWeaponButton kodachiButton = new GuiWeaponButton(8, 90, 100, 20, 20, "Kodachi", 0, 40);
	    private final GuiWeaponButton battleaxeButton = new GuiWeaponButton(9, 30, 130, 20, 20, "Battleaxe", 21, 40);
	    private final GuiWeaponButton zweihanderButton = new GuiWeaponButton(10, 60, 130, 20, 20, "Zweihander", 41, 40);
	    private final GuiWeaponButton nodachiButton = new GuiWeaponButton(11, 90, 130, 20, 20, "Nodachi", 61, 40);
	    private final GuiWeaponButton sabreButton = new GuiWeaponButton(12, 30, 160, 20, 20, "Sabre", 0, 60);
	    private final GuiWeaponButton makhairaButton = new GuiWeaponButton(13, 60, 160, 20, 20, "Makhaira", 21, 60);
	    private final GuiWeaponButton spearButton = new GuiWeaponButton(14, 90, 160, 20, 20, "Spear", 41, 60);
	    private final GuiWeaponButton chainButton = new GuiWeaponButton(15, 60, 190, 20, 20, "Chain", 21, 80);
	    private final GuiWeaponButton plateButton = new GuiWeaponButton(16, 90, 190, 20, 20, "Plate", 41, 80);
	    
	    private final GuiWeaponButton toolrodButton = new GuiWeaponButton(98, 30, 190, 20, 20, "Tool Handle", 61, 60);
	    private final GuiWeaponButton forgeButton = new GuiWeaponButton(99, 90, 220, 20, 20, "Forge Weapon", 0, 80);
	    private int buttonPressed;
	    private final ContainerForgeWeapon container;
	    private final InventoryPlayer player;
	    
	    public GuiForgeWeapon(InventoryPlayer inventoryIn, ContainerForgeWeapon container)
	    {
	    	super(new ContainerForgeWeapon(inventoryIn, inventoryIn.player.getEntityWorld(), inventoryIn.player));
	        this.container = container;
	        this.player = inventoryIn;
	    }
	    
	    public void drawButtons(int mouseX, int mouseY, float partialTicks) {
	    	if(this.buttonList.size() <= 0) {
	    		this.buttonList.add(daggerButton);
	        	this.buttonList.add(kabutowariButton);
	        	this.buttonList.add(talwarButton);
	        	this.buttonList.add(rapierButton);
	        	this.buttonList.add(maceButton);
	        	this.buttonList.add(cleaverButton);
	        	this.buttonList.add(staffButton);
	        	this.buttonList.add(longswordButton);
	        	this.buttonList.add(kodachiButton);
	        	this.buttonList.add(battleaxeButton);
	        	this.buttonList.add(zweihanderButton);
	        	this.buttonList.add(nodachiButton);
	        	this.buttonList.add(sabreButton);
	        	this.buttonList.add(makhairaButton);
	        	this.buttonList.add(spearButton);
	        	this.buttonList.add(chainButton);
	        	this.buttonList.add(plateButton);
	        
	        	this.buttonList.add(forgeButton);
	        	this.buttonList.add(toolrodButton);
	    	}
	    	
	        for (int k = 0; k < this.buttonList.size(); ++k)
	        {
	            (this.buttonList.get(k)).drawButton(this.mc, mouseX, mouseY, partialTicks);
	            
	            //int width = (mc.fontRenderer.getStringWidth(this.buttonList.get(k).displayString) + this.buttonList.get(k).getButtonWidth()) / 2;
//	            GL11.glPushMatrix();
//	            GL11.glScalef(0.8f, 0.8f, 0.8f);
	        	//this.buttonList.get(k).drawCenteredString(mc.fontRenderer, this.buttonList.get(k).displayString, (int)((this.buttonList.get(k).x - width) ), (int)((this.buttonList.get(k).y - 8) ), Color.DARK_GRAY.getRGB());
//	        	GL11.glPopMatrix();
	        }
	    }
	    
	    public void updateScreen(){
	    }
	    
	    public void setButtonPressed(int button) {
	    	this.buttonPressed = button;
	    }
	    
	    public int getButtonPressed() {
	    	return this.buttonPressed;
	    }
	    
	    protected void actionPerformed(GuiButton button){
	    	this.setButtonPressed(button.id);
	    	this.mc.playerController.sendEnchantPacket(this.container.windowId, this.getButtonPressed());
	    }
	    
	    @Override
	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        GlStateManager.disableLighting();
	        GlStateManager.disableBlend();
	        this.fontRenderer.drawString(I18n.format("container.forgeWeapon"), 62, 5, 4210752);
	        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	        GlStateManager.enableLighting();
	    }
	    
	    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	    {
	    	super.mouseClicked(mouseX, mouseY, mouseButton);
	    }
	    
	    @Override
	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        this.drawDefaultBackground();
	        super.drawScreen(mouseX, mouseY, partialTicks);
	        
	        for (int k = 0; k < this.buttonList.size(); ++k)
	        {
	            GL11.glPushMatrix();
	            GL11.glScalef(0.55f, 0.55f, 0.55f);
	            GlStateManager.disableLighting();
	        	this.buttonList.get(k).drawCenteredString(mc.fontRenderer, this.buttonList.get(k).displayString, (int)((this.buttonList.get(k).x + (this.buttonList.get(k).getButtonWidth() / 2)) / 0.55), (int)((this.buttonList.get(k).y - 7) / 0.55), Color.WHITE.getRGB());
	        	GlStateManager.enableLighting();
	        	GL11.glPopMatrix();
	        }
	        
	        this.renderHoveredToolTip(mouseX, mouseY);
	        
	        GlStateManager.disableLighting();
	        GlStateManager.disableBlend();
	    }
	    
	    @Override
	    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	    {
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURES);
	        int i = (this.width - this.xSize) / 2;
	        int j = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	        
	        this.drawButtons(mouseX, mouseY, partialTicks);
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
