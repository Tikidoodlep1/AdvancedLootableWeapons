package tiki.advancedlootableweapons.inventory.Forge;

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
import tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.advancedlootableweapons.handlers.ConfigHandler;

public class GuiForge extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(ModInfo.ID + ":textures/gui/forge_new.png");
	private final InventoryPlayer player;
	private final TileEntityForge tileentity;
	private final Set<Item> heatableMaterials;
	private StringBuilder tileName = null;
	
	public GuiForge(InventoryPlayer player, TileEntityForge tileentity) 
	{
		super(new ContainerForge(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		this.xSize = 176;
		this.ySize = 166;
		
		//Get CraftTweaker defined heatable materials
		this.heatableMaterials = ZenDynamicAlwResources.getMatListForBlock(tileentity.getBlock());
	}
	
	private String getTileName() {
		//If we already set the tile name, don't redo the work, just return the string
		if(this.tileName != null) {
			return this.tileName.toString();
		}
		
		/*49 pixels are currently reserved for the forge nameplate. We get the default tile entity name, then get the length of it. 
		 * If it's longer than the reserved length, truncate letters based on how many I's we could fit in that length, assuming I is about the average size of a letter.
		 * Append ... to show we've truncated the name, then repeat, removing 1 letter at a time until it fits.
		*/
		int namePlateSizeInGui = 49;
		this.tileName = new StringBuilder(this.tileentity.getDisplayName().getUnformattedText());
		int len = this.fontRenderer.getStringWidth(this.tileName.toString());
		int avgCharSize = this.fontRenderer.getStringWidth("I");
		int lettersToKeep = (namePlateSizeInGui / avgCharSize);
		while(len > namePlateSizeInGui) {
			this.tileName.delete(lettersToKeep--, this.tileName.length());
			this.tileName.append("...");
			len = this.fontRenderer.getStringWidth(this.tileName.toString());
		}
		
		//Don't append ... after a space, that looks wonky
		if(this.tileName.length() >= 4 && this.tileName.charAt(this.tileName.length() - 4) == ' ') {
			this.tileName.deleteCharAt(this.tileName.length() - 4);
		}
		
		return this.tileName.toString();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		this.fontRenderer.drawStringWithShadow(this.getTileName(), 5, 6, 0xFFFFFFFF);
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
		
		int k = this.getSlotTempScaled() == 0 ? 1 : this.getSlotTempScaled();
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 63 - k, 176, 63 - k, 16, k+1);
		
		if(ConfigHandler.USE_LARGER_TEXTURE_FOR_TEMP) {
			//Smoke drawing the temperature too
			int j = this.getSmokeTempScaled();
			this.drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 43 - j, 200, 42 - j, 24, j);
		}
		
	}
	
	private void drawHoveringForgeTempText(int mouseX, int mouseY) {
		if(mouseX > this.guiLeft + 76 && mouseX < this.guiLeft + 101 && mouseY > this.guiTop + 27 && mouseY < this.guiTop + 52) {
			//draw the temperature of the forge out. this.tileentity.getField(1) returns the temperature in Kelvin, so we convert to C.
			this.drawHoveringText(new TextComponentTranslation("alw.temp.forge.name").getFormattedText() + " " + (this.tileentity.getField(1)-273) + " " + new TextComponentTranslation("alw.temp.celcius.name").getFormattedText(), mouseX, mouseY);
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
		
			this.drawHoveringText(lines, mouseX, mouseY);
		}
	}
	
	private int getSlotTempScaled()
	{
		//This is for drawing the temperature of the forge behind the slot for a visual representation
		return (int)((this.tileentity.getField(1) - 840) * 0.0166);
	}
	
	private int getSmokeTempScaled()
	{
		//This is for drawing the temperature of the forge over the smoke for a larger visual representation
		return (int)((this.tileentity.getField(1) - 850) * 0.0277);
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
