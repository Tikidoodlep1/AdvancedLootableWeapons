package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ForgingGuiButtonRepresentation;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ForgingGuiRepresentation;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import com.tiki.advancedlootableweapons.handlers.PacketHandler;
import com.tiki.advancedlootableweapons.packet.PacketForgeWeaponButtonPress;

import net.minecraft.block.Block;
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
		
		public static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_weapon_new.png");
	    private final GuiWeaponButton daggerButton = new GuiWeaponButton(0, 30, 40, 23, 23, I18n.format("alw.weapon.dagger.name"), 1, 1);
	    private final GuiWeaponButton kabutowariButton = new GuiWeaponButton(1, 60, 40, 23, 23, I18n.format("alw.weapon.kabutowari.name"), 22, 1);
	    private final GuiWeaponButton talwarButton = new GuiWeaponButton(2, 90, 40, 23, 23, I18n.format("alw.weapon.talwar.name"), 64, 1);
	    private final GuiWeaponButton rapierButton = new GuiWeaponButton(3, 30, 70, 23, 23, I18n.format("alw.weapon.rapier.name"), 43, 1);
	    private final GuiWeaponButton maceButton = new GuiWeaponButton(4, 60, 70, 23, 23, I18n.format("alw.weapon.mace.name"), 1, 22);
	    private final GuiWeaponButton cleaverButton = new GuiWeaponButton(5, 90, 70, 23, 23, I18n.format("alw.weapon.cleaver.name"), 22, 22);
	    private final GuiWeaponButton staffButton = new GuiWeaponButton(6, 30, 100, 23, 23, I18n.format("alw.weapon.staff.name"), 43, 22);
	    private final GuiWeaponButton longswordButton = new GuiWeaponButton(7, 60, 100, 23, 23, I18n.format("alw.weapon.longsword.name"), 64, 22);
	    private final GuiWeaponButton kodachiButton = new GuiWeaponButton(8, 90, 100, 23, 23, I18n.format("alw.weapon.kodachi.name"), 1, 43);
	    private final GuiWeaponButton battleaxeButton = new GuiWeaponButton(9, 30, 130, 23, 23, I18n.format("alw.weapon.battleaxe.name"), 22, 43);
	    private final GuiWeaponButton zweihanderButton = new GuiWeaponButton(10, 60, 130, 23, 23, I18n.format("alw.weapon.zweihander.name"), 43, 43);
	    private final GuiWeaponButton nodachiButton = new GuiWeaponButton(11, 90, 130, 23, 23, I18n.format("alw.weapon.nodachi.name"), 64, 43);
	    private final GuiWeaponButton sabreButton = new GuiWeaponButton(12, 30, 160, 23, 23, I18n.format("alw.weapon.sabre.name"), 1, 64);
	    private final GuiWeaponButton makhairaButton = new GuiWeaponButton(13, 60, 160, 23, 23, I18n.format("alw.weapon.makhaira.name"), 22, 64);
	    private final GuiWeaponButton spearButton = new GuiWeaponButton(14, 90, 160, 23, 23, I18n.format("alw.weapon.spear.name"), 43, 64);
	    private final GuiWeaponButton chainButton = new GuiWeaponButton(15, 60, 190, 23, 23, I18n.format("alw.weapon.chain_ring.name"), 22, 85);
	    private final GuiWeaponButton plateButton = new GuiWeaponButton(16, 90, 190, 23, 23, I18n.format("alw.weapon.armor_plate.name"), 43, 85);
	    
	    private final GuiWeaponButton toolrodButton = new GuiWeaponButton(98, 45, 190, 23, 23, I18n.format("alw.button.tool_handle.name"), 64, 64);
	    private final GuiWeaponButton forgeButton = new GuiWeaponButton(99, 75, 190, 23, 23, I18n.format("alw.button.forge_weapon.name"), 1, 85);
	    
	    //							- 57 to account for space inbetween the texture and the edge so I can use the png as a reference, + 7 to account for drawing x location
	    private final GuiWeaponButton rightButton = new GuiWeaponButton(100, 145 - 57 + 7, 12 + 10, 16, 13, I18n.format("alw.button.next.name"), 232, 1, 0, 23, 23, 23);
	    private final GuiWeaponButton leftButton = new GuiWeaponButton(101, 81 - 57 + 7, 12 + 10, 16, 13, I18n.format("alw.button.prev.name"), 232, 1, 0, 36, 23, 36);
	    
	    private final ArrayList<GuiWeaponButton> addedButtons = new ArrayList<GuiWeaponButton>();
	    
	    private final ArrayList<ArrayList<GuiButton>> buttonPages = new ArrayList<ArrayList<GuiButton>>();
	    private final GuiWeaponButton[] staticButtons = new GuiWeaponButton[4];
	    private int staticButtonsLength = 2;
	    private int rows = 5;
	    private int cols = 3;
	    private int rowCounter = 1;
	    private int colCounter = 1;
	    private int pageCounter = 0;
	    private int currPage = 0;
	    //private boolean didInitButtons = false;
	    
	    private int buttonPressed;
	    //private Container container;
	    private final InventoryPlayer player;
	    private final ForgingGuiRepresentation CUSTOM_TEXTURE;
	    //private final Block block;
	    
	    public GuiForgeWeapon(InventoryPlayer inventoryIn, Container container, Block block)
	    {
	    	super(container);
	    	//this.block = block;
	        //this.container = container;
	        this.player = inventoryIn;
	        if(ZenDynamicAlwResources.guiLists.containsKey(block)) {
	        	CUSTOM_TEXTURE = ZenDynamicAlwResources.guiLists.get(block);
	        	for(ForgingGuiButtonRepresentation rep : CUSTOM_TEXTURE.buttons) {
	        		addedButtons.add(new GuiWeaponButton(rep.getId(), rep.getX(), rep.getY(), 23, 23, rep.getName(), rep.getOverlayX(), rep.getOverlayY()));
	        	}
	        }else {
	        	CUSTOM_TEXTURE = null;
	        }
	        
	        staticButtons[0] = toolrodButton;
	        staticButtons[1] = forgeButton;
	    }
	    
	    @Override
	    public void initGui() {
	    	super.initGui();
	    	
//	    	for(Entry<Block, ForgingGuiRepresentation> gui : ZenDynamicAlwResources.guiLists.entrySet()) {
//	    		System.out.println("Block is " + gui.getKey().getRegistryName());
//	    		System.out.print("Representation is: [Texture: " + gui.getValue().textureLocation + ", ");
//	    		System.out.print("Slots: " + Arrays.toString(gui.getValue().slots.toArray()) + ", ");
//	    		for(ForgingGuiButtonRepresentation button : gui.getValue().buttons) {
//	    			System.out.print("Button: " + button.getId() + ", " + button.getName() + ", " + button.getOverlay() + ", ");
//	    		}
//	    		System.out.println("]");
//	    		System.out.println("==========================================================================================================================");
//	    	}
	    	
//	    	System.out.println("Block: " + this.block.getRegistryName());
//	    	if(this.CUSTOM_TEXTURE != null) {
//	    		System.out.println("Custom button list: " + this.CUSTOM_TEXTURE.buttons);
//	    		System.out.println("Button list: " + this.CUSTOM_TEXTURE == null ? "null" : Arrays.toString(this.CUSTOM_TEXTURE.buttons.toArray(new ForgingGuiButtonRepresentation[0])));
//	    	}
//	    	System.out.println(Arrays.toString(this.addedButtons.toArray(new GuiWeaponButton[0]))); // Not being set somewhere
	    	
//	    	if(!didInitButtons) {
	    	this.rowCounter = 1;
	    	this.colCounter = 1;
	    	this.currPage = 0;
	    	this.pageCounter = 0;
	    	for(ArrayList<GuiButton> page : buttonPages) {
	    		page.clear();
	    	}
	    	
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
        	this.addButton(chainButton);
        	this.addButton(plateButton);
        	
        	for(GuiWeaponButton button : this.addedButtons) {
        		this.addButton(button);
        	}
        	
        	if(this.buttonPages.size() > 1) {
        		staticButtons[2] = leftButton;
    	        staticButtons[3] = rightButton;
    	        this.staticButtonsLength = 4;
        	}
    	    
        	for(int i = 0; i < this.buttonPages.size(); i++) {
        		List<GuiButton> list = this.buttonPages.get(i);
        		for(int j = 0; j < this.staticButtonsLength; j++) {
        			list.add(this.staticButtons[j]);
        		}
        	}
	        
//	        	this.didInitButtons = true;
//	    	}
        	
        	this.buttonList = this.buttonPages.get(currPage);
	    }
	    
	    public void addButton(GuiWeaponButton button) {
	    	if(colCounter <= cols && rowCounter <= rows) { // add to this row
	    		button.setPos(colCounter * 30, (rowCounter * 30) + 10);
	    		colCounter++;
	    		if(buttonPages.size() == pageCounter || buttonPages.get(pageCounter) == null) {
	    			ArrayList<GuiButton> page = new ArrayList<GuiButton>();
	    			page.add(button);
	    			buttonPages.add(page);
	    		}else {
	    			buttonPages.get(pageCounter).add(button);
	    		}
	    	}
	    	
	    	if(colCounter > cols) { // new row needed
	    		rowCounter++;
	    		colCounter = 1;
	    	}
	    	
	    	if(rowCounter > rows) { // new page needed
	    		 pageCounter++;
	    		 rowCounter = 1;
	    		 colCounter = 1;
	    	}
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
	    
	    protected void actionPerformed(GuiButton button) {
	    	this.setButtonPressed(button.id);
	    	if(button.id == 100) {
	    		this.buttonList = this.getNextButtonPage();
	    	}else if(button.id == 101) {
	    		this.buttonList = this.getPrevButtonPage();
	    	}else {
	    		PacketForgeWeaponButtonPress packet = new PacketForgeWeaponButtonPress(this.getButtonPressed());
		    	PacketHandler.INSTANCE.sendToServer(packet);
		    	//System.out.println("Sending button packet with ID " + button.id);
		    	//this.mc.playerController.sendEnchantPacket(this.container.windowId, this.getButtonPressed());
	    	}
	    	
	    }
	    
	    private List<GuiButton> getPrevButtonPage() {
			if(this.currPage == 0) {
				this.currPage = this.buttonPages.size() - 1;
			}else {
				this.currPage--;
			}
			
			return this.buttonPages.get(currPage);
		}
	    
		private List<GuiButton> getNextButtonPage() {
			if(this.currPage == this.buttonPages.size() - 1) {
				this.currPage = 0;
			}else {
				this.currPage++;
			}
			
			return this.buttonPages.get(currPage);
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
	        
	        this.renderHoveredToolTip(mouseX, mouseY);
	        
	        GlStateManager.enableLighting();
	        GlStateManager.disableBlend();
	    }
	    
	    @Override
	    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	    {	    	
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        if(CUSTOM_TEXTURE == null) {
	        	this.mc.getTextureManager().bindTexture(TEXTURES);
	        }else {
	        	this.mc.getTextureManager().bindTexture(CUSTOM_TEXTURE.getTextureLocation());
	        }
	        
	        int i = (this.width - this.xSize) / 2;
	        int j = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	        
	        this.mc.getTextureManager().bindTexture(GuiWeaponButton.BUTTON_TEXTURES);
	        this.drawTexturedModalRect(7, 10, 57, 0, 129, 211);
	        
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