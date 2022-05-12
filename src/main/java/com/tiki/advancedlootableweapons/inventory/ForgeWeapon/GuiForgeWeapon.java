package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.io.IOException;

import com.tiki.advancedlootableweapons.ModInfo;
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

public class GuiForgeWeapon extends GuiContainer implements IContainerListener{
		
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
	    
	    private final GuiWeaponButton toolrodButton = new GuiWeaponButton(98, 60, this.getGuiTop() + 190, 20, 20, "Tool Handle", 61, 60);
	    private final GuiWeaponButton forgeButton = new GuiWeaponButton(99, 90, this.getGuiTop() + 190, 20, 20, "Forge Weapon", 61, 60);
	    private int buttonPressed;
	    private final ContainerForgeWeapon container;

	    public GuiForgeWeapon(InventoryPlayer inventoryIn, ContainerForgeWeapon container)
	    {
	    	super(new ContainerForgeWeapon(inventoryIn, inventoryIn.player.getEntityWorld(), inventoryIn.player));
	        this.container = container;
	        this.drawButtons();
	    }
	    
	    public void drawButtons() {
	        this.addButton(daggerButton);
	        this.addButton(kabutowariButton);
	        this.addButton(talwarButton);
	        this.addButton(rapierButton);
	        this.addButton(maceButton);
	        this.addButton(cleaverButton);
	        this.addButton(staffButton);
	        this.addButton(longswordButton);
	        this.addButton(kodachiButton);
	        this.addButton(battleaxeButton);
	        this.addButton(zweihanderButton);
	        this.addButton(nodachiButton);
	        this.addButton(sabreButton);
	        this.addButton(makhairaButton);
	        this.addButton(spearButton);
	        
	        this.addButton(forgeButton);
	        this.addButton(toolrodButton);
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
	        this.fontRenderer.drawString(I18n.format("container.forgeWeapon"), 62, 3, 4210752);

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
	    
	    @Override
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
