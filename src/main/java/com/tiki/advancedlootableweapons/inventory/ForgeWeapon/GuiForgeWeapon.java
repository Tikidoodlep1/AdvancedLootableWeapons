package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
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
		
		private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_weapon_new.png");
	    private final GuiWeaponButton daggerButton = new GuiWeaponButton(0, 30, 40, 20, 20, I18n.format("alw.weapon.dagger.name"), 0, 0);
	    private final GuiWeaponButton kabutowariButton = new GuiWeaponButton(1, 60, 40, 20, 20, I18n.format("alw.weapon.kabutowari.name"), 21, 0);
	    private final GuiWeaponButton talwarButton = new GuiWeaponButton(2, 90, 40, 20, 20, I18n.format("alw.weapon.talwar.name"), 61, 0);
	    private final GuiWeaponButton rapierButton = new GuiWeaponButton(3, 30, 70, 20, 20, I18n.format("alw.weapon.rapier.name"), 41, 0);
	    private final GuiWeaponButton maceButton = new GuiWeaponButton(4, 60, 70, 20, 20, I18n.format("alw.weapon.mace.name"), 0, 20);
	    private final GuiWeaponButton cleaverButton = new GuiWeaponButton(5, 90, 70, 20, 20, I18n.format("alw.weapon.cleaver.name"), 21, 20);
	    private final GuiWeaponButton staffButton = new GuiWeaponButton(6, 30, 100, 20, 20, I18n.format("alw.weapon.staff.name"), 41, 20);
	    private final GuiWeaponButton longswordButton = new GuiWeaponButton(7, 60, 100, 20, 20, I18n.format("alw.weapon.longsword.name"), 61, 20);
	    private final GuiWeaponButton kodachiButton = new GuiWeaponButton(8, 90, 100, 20, 20, I18n.format("alw.weapon.kodachi.name"), 0, 40);
	    private final GuiWeaponButton battleaxeButton = new GuiWeaponButton(9, 30, 130, 20, 20, I18n.format("alw.weapon.battleaxe.name"), 21, 40);
	    private final GuiWeaponButton zweihanderButton = new GuiWeaponButton(10, 60, 130, 20, 20, I18n.format("alw.weapon.zweihander.name"), 41, 40);
	    private final GuiWeaponButton nodachiButton = new GuiWeaponButton(11, 90, 130, 20, 20, I18n.format("alw.weapon.nodachi.name"), 61, 40);
	    private final GuiWeaponButton sabreButton = new GuiWeaponButton(12, 30, 160, 20, 20, I18n.format("alw.weapon.sabre.name"), 0, 60);
	    private final GuiWeaponButton makhairaButton = new GuiWeaponButton(13, 60, 160, 20, 20, I18n.format("alw.weapon.makhaira.name"), 21, 60);
	    private final GuiWeaponButton spearButton = new GuiWeaponButton(14, 90, 160, 20, 20, I18n.format("alw.weapon.spear.name"), 41, 60);
	    private final GuiWeaponButton chainButton = new GuiWeaponButton(15, 60, 190, 20, 20, I18n.format("alw.weapon.chain_ring.name"), 21, 80);
	    private final GuiWeaponButton plateButton = new GuiWeaponButton(16, 90, 190, 20, 20, I18n.format("alw.weapon.armor_plate.name"), 41, 80);
	    
	    private final GuiWeaponButton toolrodButton = new GuiWeaponButton(98, 30, 190, 20, 20, I18n.format("alw.button.tool_handle.name"), 61, 60);
	    private final GuiWeaponButton forgeButton = new GuiWeaponButton(99, 90, 220, 20, 20, I18n.format("alw.button.forge_weapon.name"), 0, 80);
	    private int buttonPressed;
	    private Container container;
	    private final InventoryPlayer player;
	    
	    public GuiForgeWeapon(InventoryPlayer inventoryIn, Container container)
	    {
	    	super(new ContainerForgeWeapon(inventoryIn, inventoryIn.player.getEntityWorld(), inventoryIn.player));
	        this.container = container;
	        this.player = inventoryIn;
//	        if(this.player.player.openContainer instanceof ContainerForgeWeapon) {
//	        	this.player.player.openContainer.addListener(this);
//	        }
	    }
	    
	    @Override
	    public void initGui() {
	    	super.initGui();
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
	    
	    public void drawButtons(int mouseX, int mouseY, float partialTicks) {	    	
	        for (int k = 0; k < this.buttonList.size(); ++k)
	        {
	            (this.buttonList.get(k)).drawButton(this.mc, mouseX, mouseY, partialTicks);
	        }
	    }
	    
	    public void updateScreen() {
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
	        this.fontRenderer.drawString(I18n.format("container.forgeWeapon"), 100, 10, 4210752);
	        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 9, this.ySize - 93, 4210752);
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
	        
	        GlStateManager.disableLighting();
	        GL11.glPushMatrix();
            GL11.glScalef(0.55f, 0.55f, 0.55f);
	        for (int k = 0; k < this.buttonList.size(); ++k)
	        {
	        	this.buttonList.get(k).drawCenteredString(mc.fontRenderer, this.buttonList.get(k).displayString, (int)((this.buttonList.get(k).x + (this.buttonList.get(k).getButtonWidth() / 2)) / 0.55), (int)((this.buttonList.get(k).y - 7) / 0.55), Color.WHITE.getRGB());
	        }
	        GL11.glPopMatrix();
	        
	        this.renderHoveredToolTip(mouseX, mouseY);
	        
	        GlStateManager.enableLighting();
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
	        
//	        if(ConfigHandler.ENABLE_QUENCHING && this.container.getInventory().get(0).hasTagCompound() && !this.container.getInventory().get(0).getTagCompound().getBoolean("quenched")) {
//	        	this.drawHoveringText("Weapon head must be quenched!", this.guiLeft + 65, this.guiTop + 28);
//	        }
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
//	    	this.container = containerIn;
//	    	for(int i = 0; i < this.inv.size(); i++) {
//	    		this.inv.set(i, inventory.getStackInSlot(i));
//	    	}
//	    	
//	    	for(int k = 0; k < this.container.getInventory().size(); k++) {
//	        	System.out.println("Slot " + k + ": " + this.container.getInventory().get(k).getDisplayName());
//	        }
	    }
}
