package com.tiki.advancedlootableweapons.inventory;

import java.io.IOException;

import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.init.ItemInit;
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
	    private final GuiWeaponButton daggerButton = new GuiWeaponButton(0, 30, 40, 20, 20, "Dagger");
	    private final GuiWeaponButton kabutowariButton = new GuiWeaponButton(1, 60, 40, 20, 20, "Kabutowari");
	    private final GuiWeaponButton talwarButton = new GuiWeaponButton(2, 90, 40, 20, 20, "Talwar");
	    private final GuiWeaponButton rapierButton = new GuiWeaponButton(3, 30, 70, 20, 20, "Rapier");
	    private final GuiWeaponButton maceButton = new GuiWeaponButton(4, 60, 70, 20, 20, "Mace");
	    private final GuiWeaponButton cleaverButton = new GuiWeaponButton(5, 90, 70, 20, 20, "Cleaver");
	    private final GuiWeaponButton staffButton = new GuiWeaponButton(6, 30, 100, 20, 20, "Staff");
	    private final GuiWeaponButton longswordButton = new GuiWeaponButton(7, 60, 100, 20, 20, "Longsword");
	    private final GuiWeaponButton kodachiButton = new GuiWeaponButton(8, 90, 100, 20, 20, "Kodachi");
	    private final GuiWeaponButton battleaxeButton = new GuiWeaponButton(9, 30, 130, 20, 20, "Battleaxe");
	    private final GuiWeaponButton zweihanderButton = new GuiWeaponButton(10, 60, 130, 20, 20, "Zweihander");
	    private final GuiWeaponButton nodachiButton = new GuiWeaponButton(11, 90, 130, 20, 20, "Nodachi");
	    private final GuiWeaponButton sabreButton = new GuiWeaponButton(12, 30, 160, 20, 20, "Sabre");
	    private final GuiWeaponButton makhairaButton = new GuiWeaponButton(13, 60, 160, 20, 20, "Makhaira");
	    private final GuiWeaponButton spearButton = new GuiWeaponButton(14, 90, 160, 20, 20, "Spear");
	    
	    private final GuiWeaponButton toolrodButton = new GuiWeaponButton(98, 60, this.getGuiTop() + 190, 20, 20, "Tool Handle");
	    private final GuiWeaponButton forgeButton = new GuiWeaponButton(99, 90, this.getGuiTop() + 190, 20, 20, "Forge Weapon");
	    private int buttonPressed;
	    private boolean buttonsDrawn;
	    private final ContainerForgeWeapon container;

	    public GuiForgeWeapon(InventoryPlayer inventoryIn, ContainerForgeWeapon container)
	    {
	    	super(new ContainerForgeWeapon(inventoryIn, inventoryIn.player.getEntityWorld(), inventoryIn.player));
	        this.container = container;
	        this.buttonsDrawn = true;
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
	    	if(container != null) {
	    		if(this.buttonsDrawn == false && this.container.getSlot(0).getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
	    			this.daggerButton.enabled = true;
	    			this.daggerButton.visible = true;
	    			
	    			this.kabutowariButton.enabled = true;
	    			this.kabutowariButton.visible = true;
	    			
	    			this.talwarButton.enabled = true;
	    			this.talwarButton.visible = true;
	    			
	    			this.rapierButton.enabled = true;
	    			this.rapierButton.visible = true;
	    			
	    			this.maceButton.enabled = true;
	    			this.maceButton.visible = true;
	    			
	    			this.cleaverButton.enabled = true;
	    			this.cleaverButton.visible = true;
	    			
	    			this.staffButton.enabled = true;
	    			this.staffButton.visible = true;
	    			
	    			this.longswordButton.enabled = true;
	    			this.longswordButton.visible = true;
	    			
	    			this.kodachiButton.enabled = true;
	    			this.kodachiButton.visible = true;
	    			
	    			this.battleaxeButton.enabled = true;
	    			this.battleaxeButton.visible = true;
	    			
	    			this.zweihanderButton.enabled = true;
	    			this.zweihanderButton.visible = true;
	    			
	    			this.nodachiButton.enabled = true;
	    			this.nodachiButton.visible = true;
	    			
	    			this.sabreButton.enabled = true;
	    			this.sabreButton.visible = true;
	    			
	    			this.makhairaButton.enabled = true;
	    			this.makhairaButton.visible = true;
	    			
	    			this.spearButton.enabled = true;
	    			this.spearButton.visible = true;
	    			
	    			this.buttonsDrawn = true;
	    			System.out.println("Enabling Buttons");
	    		}else if(this.buttonsDrawn == true && !(this.container.getSlot(0).getHasStack() || this.container.getSlot(0).getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD)))){
	    			this.daggerButton.enabled = false;
	    			this.daggerButton.visible = false;
	    			
	    			this.kabutowariButton.enabled = false;
	    			this.kabutowariButton.visible = false;
	    			
	    			this.talwarButton.enabled = false;
	    			this.talwarButton.visible = false;
	    			
	    			this.rapierButton.enabled = false;
	    			this.rapierButton.visible = false;
	    			
	    			this.maceButton.enabled = false;
	    			this.maceButton.visible = false;
	    			
	    			this.cleaverButton.enabled = false;
	    			this.cleaverButton.visible = false;
	    			
	    			this.staffButton.enabled = false;
	    			this.staffButton.visible = false;
	    			
	    			this.longswordButton.enabled = false;
	    			this.longswordButton.visible = false;
	    			
	    			this.kodachiButton.enabled = false;
	    			this.kodachiButton.visible = false;
	    			
	    			this.battleaxeButton.enabled = false;
	    			this.battleaxeButton.visible = false;
	    			
	    			this.zweihanderButton.enabled = false;
	    			this.zweihanderButton.visible = false;
	    			
	    			this.nodachiButton.enabled = false;
	    			this.nodachiButton.visible = false;
	    			
	    			this.sabreButton.enabled = false;
	    			this.sabreButton.visible = false;
	    			
	    			this.makhairaButton.enabled = false;
	    			this.makhairaButton.visible = false;
	    			
	    			this.spearButton.enabled = false;
	    			this.spearButton.visible = false;
	    			
	    			this.buttonsDrawn = false;
	    			System.out.println("Disabling Buttons");
	    		}
	    	}
	    }
	    
	    public void setButtonPressed(int button) {
	    	this.buttonPressed = button;
	    }
	    
	    public int getButtonPressed() {
	    	return this.buttonPressed;
	    }
	    
	    protected void actionPerformed(GuiButton button){
	    	//if(this.container.getSlot(0).getStack().getItem() instanceof ItemHotToolHead) {
	    		this.setButtonPressed(button.id);
	    		this.mc.playerController.sendEnchantPacket(this.container.windowId, this.getButtonPressed());
	    	//}
	    }
	    
	    @Override
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
