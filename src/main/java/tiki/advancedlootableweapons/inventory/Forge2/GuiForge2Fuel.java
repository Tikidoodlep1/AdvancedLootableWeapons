package tiki.advancedlootableweapons.inventory.Forge2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.advancedlootableweapons.handlers.ConfigHandler;

public class GuiForge2Fuel extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_2_new_fuel.png");
	private final InventoryPlayer player;
	private final TileEntityForge2 tileentity;
	private StringBuilder tileName = null;
	private static final int slot1x = 52;
	private static final int slot2x = 80;
	private static final int slot3x = 108;
	private static final int sloty = 27;
	private static final int slotSize = 18;
	private final Set<Item> heatableMaterials;
	private final Set<Item> fuels;
	
	public GuiForge2Fuel(InventoryPlayer player, TileEntityForge2 tileentity) 
	{
		super(new ContainerForge2Fuel(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		this.xSize = 176;
		this.ySize = 166;
		this.heatableMaterials = ZenDynamicAlwResources.getMatListForBlock(tileentity.getBlock());
		this.fuels = ZenDynamicAlwResources.getFuelListForBlock(tileentity.getBlock());
	}
	
	private String getTileName() {
		if(this.tileName != null) {
			return this.tileName.toString();
		}
		
		int namePlateSizeInGui = 116;
		this.tileName = new StringBuilder(this.tileentity.getDisplayName().getUnformattedText());
		int len = this.fontRenderer.getStringWidth(this.tileName.toString());
		int avgCharSize = this.fontRenderer.getStringWidth("I");
		int lettersToKeep = (namePlateSizeInGui / avgCharSize);
		while(len > namePlateSizeInGui) {
			this.tileName.delete(lettersToKeep--, this.tileName.length());
			this.tileName.append("...");
			len = this.fontRenderer.getStringWidth(this.tileName.toString());
		}
		
		if(this.tileName.charAt(this.tileName.length() - 4) == ' ') {
			this.tileName.deleteCharAt(this.tileName.length() - 4);
		}
		
		return this.tileName.toString();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		this.fontRenderer.drawStringWithShadow(this.getTileName(), 30, 5, 0xFFFFFFFF);
		this.fontRenderer.drawStringWithShadow(this.player.getDisplayName().getUnformattedText(), 5, 72, 0xFFFFFFFF);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.drawHoveringForgeTempText(mouseX, mouseY);
        this.drawHoveringMaterialText(mouseX, mouseY);
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int k = this.getSlotTempScaled();
		this.drawTexturedModalRect(this.guiLeft + 51, this.guiTop + 44 - k, 176, 44 - k, 74, k);
		
		
		if(this.tileentity.getField(4) > 0) {
			//Drawing static fire background
			this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 50, 176, 50, 16, 16);
			//Drawing animated flame
			int l = getBurnTimeScaled();
			this.drawTexturedModalRect(this.guiLeft + 99, this.guiTop + 64 - l, 195, 64 - l, 13, l);
		}
		
		if(ConfigHandler.USE_LARGER_TEXTURE_FOR_TEMP) {
			//Smoke drawing the temperature too
			int j = this.getLargeTempScaled();
			this.drawTexturedModalRect(this.guiLeft + 40, this.guiTop + 45 - j, 160, 193 - j, 96, j);
		}
	}
	
	public void drawHoveringForgeTempText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 40 && mouseX < this.guiLeft + 138 && mouseY > this.guiTop + 20 && mouseY < this.guiTop + 45 && 
				!(mouseX > this.guiLeft + slot1x && mouseX < this.guiLeft + slot1x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize) &&
				!(mouseX > this.guiLeft + slot2x && mouseX < this.guiLeft + slot2x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize) &&
				!(mouseX > this.guiLeft + slot3x && mouseX < this.guiLeft + slot3x + slotSize && mouseY > this.guiTop + sloty && mouseY < this.guiTop + sloty + slotSize)) {
			this.drawHoveringText(new TextComponentTranslation("alw.temp.forge.name").getFormattedText() + " " + (this.tileentity.getField(3)-273) + " " + new TextComponentTranslation("alw.temp.celcius.name").getFormattedText(), mouseX, mouseY);
		}
	}
	
	private void drawHoveringMaterialText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 156 && mouseX < this.guiLeft + 175 && mouseY > this.guiTop + 2 && mouseY < this.guiTop + 17) {
			List<String> lines = new ArrayList<String>(3);
			lines.add(new TextComponentTranslation("alw.forge.usable_mat_fuel.name").getFormattedText());
			lines.add(TextFormatting.STRIKETHROUGH + "---------------------------");
			String mat = "";
			if(this.heatableMaterials == null || this.heatableMaterials.isEmpty()) {
				mat += TextFormatting.LIGHT_PURPLE + new TextComponentTranslation("alw.forge.any_mat.name").getFormattedText();
			}else {
				
				for(Item item : this.heatableMaterials) {
					mat += TextFormatting.LIGHT_PURPLE + new TextComponentTranslation(item.getUnlocalizedName() + ".name").getFormattedText() + ", ";
				}
				mat = mat.substring(0, mat.length() - 2);
			}
			lines.add("Materials: " + mat);
			
			String fuel = "";
			if(this.fuels == null || this.fuels.isEmpty()) {
				fuel += TextFormatting.GOLD + new TextComponentTranslation("alw.forge.any_fuel.name").getFormattedText();
			}else {
				
				for(Item item : this.fuels) {
					fuel += TextFormatting.GOLD + new TextComponentTranslation(item.getUnlocalizedName() + ".name").getFormattedText() + ", ";
				}
				fuel = fuel.substring(0, fuel.length() - 2);
			}
			lines.add("Fuels: " + fuel);
			
			this.drawHoveringText(lines, mouseX, mouseY);
		}
	}
	
	private int getSlotTempScaled() {
		return (int)((this.tileentity.getField(3) - 850) * 0.0136);
	}
	
	private int getLargeTempScaled() {
		return (int)((this.tileentity.getField(3) - 850) * 0.0294);
	}
	
	private int getBurnTimeScaled() {
		int i = this.tileentity.getField(5);
		if(i == 0) i = 200;
		return this.tileentity.getField(4) * 13 / i; // 13 is the size of the fire sprite
	}
	
	public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList)
    {
        this.sendSlotContents(containerToSend, 0, containerToSend.getSlot(0).getStack());
        this.sendSlotContents(containerToSend, 1, containerToSend.getSlot(1).getStack());
        this.sendSlotContents(containerToSend, 2, containerToSend.getSlot(2).getStack());
        this.sendSlotContents(containerToSend, 3, containerToSend.getSlot(3).getStack());
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
