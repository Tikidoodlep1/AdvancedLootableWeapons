package com.tiki.advancedlootableweapons.blocks.compat.contenttweaker;

import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import java.util.Set;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.BlockForgeFuel;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForgeAirflowConsumer;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ForgeRepresentation;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class BlockForgeFuelContent extends BlockForgeContent implements IHasGeneratedModel, ITileEntityProvider {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool REQUIRES_IGNITION = PropertyBool.create("requires_ignition");
	private static boolean keepinventory;
	private final ForgeRepresentation forge;

	public BlockForgeFuelContent(ForgeRepresentation forge)
	{
		super(forge);
		this.forge = forge;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(REQUIRES_IGNITION, forge.needsIgnition()));
	}
	
	public void setFuelList(Set<Item> items) {
		ZenDynamicAlwResources.setFuelListForBlock(this, items);
	}
	
	public void addItemToFuelList(Item item) {
		ZenDynamicAlwResources.fuelLists.get(this).add(item);
	}
	
	public void removeItemFromFuelList(Item item) {
		ZenDynamicAlwResources.fuelLists.get(this).remove(item);
	}
	
	public Set<Item> getFuelList() {
		return ZenDynamicAlwResources.fuelLists.get(this);
	}
	
	public ForgeRepresentation getRepresentation() {
		return forge;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this.getDefaultState().getValue(REQUIRES_IGNITION)) {
			StringBuilder items = new StringBuilder();
			NonNullList<ItemStack> stacks = OreDictionary.getOres(ZenDynamicAlwResources.IGNITION_ORE);
			for(ItemStack i : stacks) {
				items.append(", " + i.getDisplayName());
			}
			tooltip.add(TextFormatting.BLUE + new TextComponentTranslation("alw.forge_ignition.tooltip").getFormattedText() + ": " + items.substring(2));
		}
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			ItemStack mainHand = playerIn.getHeldItemMainhand();
			if(state.getValue(REQUIRES_IGNITION) && !mainHand.isEmpty()) {
				for(ItemStack stack : OreDictionary.getOres(ZenDynamicAlwResources.IGNITION_ORE)) {
					if(stack.getItem() == mainHand.getItem()) {
						TileEntity te = worldIn.getTileEntity(pos);
						if(te instanceof TileEntityForge) {
							((TileEntityForge)te).usedIgniterOnForge();
						}
						if(mainHand.getItem().isDamageable()) {
							mainHand.damageItem(1, playerIn);
						}else {
							mainHand.setCount(mainHand.getCount() - 1);
						}
						
						playerIn.playSound(SoundEvents.ENTITY_GENERIC_BURN, 1.0F, 1.0F);
						return true;
					}
				}
				
				for(ItemStack stack : OreDictionary.getOres(ZenDynamicAlwResources.IGNITION_UPGRADE_ORE)) {
					if(stack.getItem() == mainHand.getItem()) {
						TileEntity te = worldIn.getTileEntity(pos);
						if(te instanceof TileEntityForge) {
							((TileEntityForge)te).setRequiresIgnition(false);
						}
						BlockForgeFuel.setState(false, worldIn, pos);
						if(mainHand.getItem().isDamageable()) {
							mainHand.damageItem(1, playerIn);
						}else {
							mainHand.setCount(mainHand.getCount() - 1);
						}
						
						playerIn.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F);
						return true;
					}
				}
			}
			
			playerIn.openGui(Alw.instance, ModInfo.GUI_FORGE_FUEL, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		if (!worldIn.isRemote) 
        {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face).withProperty(REQUIRES_IGNITION, true), 2);
        }
	}
	
	public static void setState(boolean requiresIgnition, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		//System.out.println("TILE ENTITY INFORMATION: " + tileentity.getTileData());
		keepinventory = true;
		
		worldIn.setBlockState(pos, BlockInit.forge.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(REQUIRES_IGNITION,  requiresIgnition), 3);
		
		keepinventory = false;
		
		if(tileentity != null) 
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		if(Alw.isPyrotechLoaded) {
			return new TileEntityForgeAirflowConsumer(true, state.getValue(REQUIRES_IGNITION), this);
		}
		return new TileEntityForge(true, state.getValue(REQUIRES_IGNITION), this);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(REQUIRES_IGNITION, true);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(REQUIRES_IGNITION, true), 2);
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING, REQUIRES_IGNITION});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		int requiresIgnition = meta / 10;
		int facingMeta = meta % 10;
		EnumFacing facing = EnumFacing.getFront(facingMeta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing).withProperty(REQUIRES_IGNITION, requiresIgnition == 1 ? true : false);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		boolean requiresIgnition = state.getValue(REQUIRES_IGNITION);
		return Integer.parseInt((requiresIgnition ? 1 : 0) +  String.valueOf(((EnumFacing)state.getValue(FACING)).getIndex()) );
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if(Alw.isPyrotechLoaded) {
			return new TileEntityForgeAirflowConsumer(false, false, this);
		}
		return new TileEntityForge(true, getStateFromMeta(meta).getValue(REQUIRES_IGNITION), this);
	}

	@Override
	public List<IGeneratedModel> getGeneratedModels() {
		List<IGeneratedModel> models = Lists.newArrayList();
		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
		
		JsonObject BlockStateJson = new JsonObject();
    	BlockStateJson.addProperty("forge_marker", 1);
    	JsonObject variants = new JsonObject();
    	JsonObject y0 = new JsonObject();
    	JsonObject y90 = new JsonObject();
    	JsonObject y180 = new JsonObject();
    	JsonObject y270 = new JsonObject();
    	y0.addProperty("model", new ResourceLocation("contenttweaker:" + forge.getName()).toString());
    	y0.addProperty("y", 0);
    	y90.addProperty("model", new ResourceLocation("contenttweaker:" + forge.getName()).toString());
    	y90.addProperty("y", 90);
    	y180.addProperty("model", new ResourceLocation("contenttweaker:" + forge.getName()).toString());
    	y180.addProperty("y", 180);
    	y270.addProperty("model", new ResourceLocation("contenttweaker:" + forge.getName()).toString());
    	y270.addProperty("y", 270);
//    	if(this.getDefaultState().getValue(REQUIRES_IGNITION)) {
    		variants.add("facing" + new Character('=') + "north,requires_ignition=false", y0);
        	variants.add("facing=east,requires_ignition=false", y90);
        	variants.add("facing=south,requires_ignition=false", y180);
        	variants.add("facing=west,requires_ignition=false", y270);
        	variants.add("facing=north,requires_ignition=true", y0);
        	variants.add("facing=east,requires_ignition=true", y90);
        	variants.add("facing=south,requires_ignition=true", y180);
        	variants.add("facing=west,requires_ignition=true", y270);
//    	}else {
//    		variants.add("facing=north", y0);
//        	variants.add("facing=east", y90);
//        	variants.add("facing=south", y180);
//        	variants.add("facing=west", y270);
//    	}
    	
    	BlockStateJson.add("variants", variants);
    	
    	JsonObject BlockModelJson = ZenDynamicAlwResources.FORGE_MODEL;
    	if(BlockModelJson != null) {
    		JsonElement textures = BlockModelJson.get("textures");
        	if(textures != null && !textures.isJsonNull()) {
        		JsonObject textureObj = textures.getAsJsonObject();
        		JsonObject newTextureObj = new JsonObject();
        		for(Entry<String, JsonElement> e : textureObj.entrySet()) {
        			newTextureObj.addProperty(e.getKey(), "contenttweaker:blocks/" + forge.getName());
        		}
        		BlockModelJson.add("textures", newTextureObj);
        	}
        	
        	if(BlockModelJson.size() > 0) {
        		models.add(new GeneratedModel(forge.getName(), ModelType.BLOCK_MODEL, gson.toJson(BlockModelJson)));
        	}else {
        		Alw.logger.warn("Unable to generate block model for " + this.getUnlocalizedName() + ". Please ensure that you are using the latest version of ALW. If you are, please report this to the mod author!", new JsonIOException("Unable to load JSON from ZenDynamicAlwResources.java:44"));
        	}
    	}
		
		JsonObject ItemModelJson = new JsonObject();
		ItemModelJson.addProperty("parent", "contenttweaker:block/" + forge.getName());
		
    	models.add(new GeneratedModel(forge.getName(), ModelType.BLOCKSTATE, gson.toJson(BlockStateJson)));
    	
    	models.add(new GeneratedModel(forge.getName(), ModelType.ITEM_MODEL, gson.toJson(ItemModelJson)));
    	
        return models;
	}
}
