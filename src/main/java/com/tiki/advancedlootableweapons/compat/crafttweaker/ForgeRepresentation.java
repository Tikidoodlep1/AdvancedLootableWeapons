package com.tiki.advancedlootableweapons.compat.crafttweaker;

import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.contenttweaker.ContentTweaker;
import com.teamacronymcoders.contenttweaker.api.ctobjects.resourcelocation.CTResourceLocation;
import com.teamacronymcoders.contenttweaker.modules.vanilla.blocks.BlockRepresentation;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2Content;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForge2FuelContent;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeContent;
import com.tiki.advancedlootableweapons.blocks.compat.contenttweaker.BlockForgeFuelContent;

import crafttweaker.annotations.ZenRegister;
import net.minecraft.creativetab.CreativeTabs;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.advancedlootableweapons.ForgeRepresentation")
@ZenRegister
public class ForgeRepresentation extends BlockRepresentation {

	private boolean oneSlot;
	private boolean threeSlot;
	private boolean usesSimpleForgeModel;
	private boolean usesAdvancedForgeModel;
	private boolean usesFuel;
	private boolean needsIgnition;
	private CTResourceLocation guiRL;
	private float baseHeatingSpeed = 1.0F;
	private CreativeTabs tab = null;
	
	public ForgeRepresentation(String name, boolean oneSlot, boolean usesAdvancedForgeModel, boolean usesFuel, boolean needsIgnition) {
		this.unlocalizedName = name;
		this.oneSlot = oneSlot;
		this.threeSlot = !oneSlot;
		this.usesSimpleForgeModel = !usesAdvancedForgeModel;
		this.usesAdvancedForgeModel = usesAdvancedForgeModel;
		this.usesFuel = usesFuel;
		this.needsIgnition = needsIgnition;
	}
	
	public float getBaseHeatingSpeed() {
		return baseHeatingSpeed;
	}
	
	@ZenMethod
	public void setBaseAlwCreativeTab() {
		this.tab = Alw.AlwBlocksTab;
	}
	
	@ZenMethod
	public void setBaseHeatingSpeed(float speed) {
		this.baseHeatingSpeed = speed;
	}
	
	@ZenGetter("oneSlot")
    public boolean hasOneSlot() {
        return oneSlot;
    }
    
    @ZenGetter("threeSlot")
    public boolean hasThreeSlot() {
        return threeSlot;
    }
    
    @ZenGetter("usesSimpleForgeModel")
    public boolean useSimpleForgeModel() {
        return usesSimpleForgeModel;
    }
    
    @ZenGetter("usesAdvancedForgeModel")
    public boolean useAdvancedForgeModel() {
        return usesAdvancedForgeModel;
    }
    
    @ZenGetter("usesFuel")
    public boolean usesFuel() {
    	return usesFuel;
    }
    
    @ZenGetter("needsIgnition")
    public boolean needsIgnition() {
    	return needsIgnition;
    }
    
    @ZenGetter("guiRL")
    public CTResourceLocation getGuiResourceLocation() {
    	return this.guiRL;
    }
    
    @ZenMethod
    @Override
    public void register() {    	
    	BlockForgeContent forge = null;
    	BlockForge2Content forge2 = null;
    	if(this.usesFuel && !this.usesAdvancedForgeModel) {
    		forge = new BlockForgeFuelContent(this);
    	}else if(this.usesFuel && this.usesAdvancedForgeModel) {
    		forge2 = new BlockForge2FuelContent(this);
    	}else if(!this.usesFuel && !this.usesAdvancedForgeModel) {
    		forge = new BlockForgeContent(this);
    	}else if(!this.usesFuel && this.usesAdvancedForgeModel) {
    		forge2 = new BlockForge2Content(this);
    	}
    	
    	if(forge != null) {
    		if(this.tab == null && this.getCreativeTab().getInternal() instanceof CreativeTabs) {
    			forge.setCreativeTab((CreativeTabs)this.getCreativeTab().getInternal());
    		}else if(this.tab != null) {
    			forge.setCreativeTab(tab);
    		}
    		forge.setLightOpacity(this.getLightOpacity());
    		forge.setLightLevel(this.getLightValue());
    		forge.setHardness(this.getBlockHardness());
    		forge.setResistance(this.getBlockResistance());
    		forge.setTickRandomly(this.getOnRandomTick() != null);
    		
    		ContentTweaker.instance.getRegistry(BlockRegistry.class, "BLOCK").register(forge); // Backup registry
    	}else if(forge2 != null) {
    		if(this.tab == null && this.getCreativeTab().getInternal() instanceof CreativeTabs) {
    			forge2.setCreativeTab((CreativeTabs)this.getCreativeTab().getInternal());
    		}else if(this.tab != null) {
    			forge2.setCreativeTab(tab);
    		}
    		forge2.setLightOpacity(this.getLightOpacity());
    		forge2.setLightLevel(this.getLightValue());
    		forge2.setHardness(this.getBlockHardness());
    		forge2.setResistance(this.getBlockResistance());
    		forge2.setTickRandomly(this.getOnRandomTick() != null);
    		
    		ContentTweaker.instance.getRegistry(BlockRegistry.class, "BLOCK").register(forge2); // Backup registry
    	}
    	
    	//generateBlockModelfiles(this.modelRL);
    }
    
//    private JsonObject generateBlockModelfiles(CTResourceLocation modelRL) {
//    	JsonObject BlockStateJson = new JsonObject();
//    	BlockStateJson.addProperty("forge_marker", 1);
//    	JsonObject variants = new JsonObject();
//    	JsonObject y0 = new JsonObject();
//    	JsonObject y90 = new JsonObject();
//    	JsonObject y180 = new JsonObject();
//    	JsonObject y270 = new JsonObject();
//    	y0.addProperty("model", modelRL.toString());
//    	y0.addProperty("y", 0);
//    	y90.addProperty("model", modelRL.toString());
//    	y90.addProperty("y", 90);
//    	y180.addProperty("model", modelRL.toString());
//    	y180.addProperty("y", 180);
//    	y270.addProperty("model", modelRL.toString());
//    	y270.addProperty("y", 270);
//    	if(this.needsIgnition) {
//    		variants.add("facing=north,requires_ignition=false", y0);
//        	variants.add("facing=east,requires_ignition=false", y90);
//        	variants.add("facing=south,requires_ignition=false", y180);
//        	variants.add("facing=west,requires_ignition=false", y270);
//        	variants.add("facing=north,requires_ignition=true", y0);
//        	variants.add("facing=east,requires_ignition=true", y90);
//        	variants.add("facing=south,requires_ignition=true", y180);
//        	variants.add("facing=west,requires_ignition=true", y270);
//    	}else {
//    		variants.add("facing=north", y0);
//        	variants.add("facing=east", y90);
//        	variants.add("facing=south", y180);
//        	variants.add("facing=west", y270);
//    	}
//    	
//    	BlockStateJson.add("variants", variants);
//    	
//    	return BlockStateJson;
//    }
    
}
