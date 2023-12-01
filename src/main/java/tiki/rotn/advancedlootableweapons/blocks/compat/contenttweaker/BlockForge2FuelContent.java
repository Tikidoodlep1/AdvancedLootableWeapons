package tiki.rotn.advancedlootableweapons.blocks.compat.contenttweaker;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;

import net.minecraft.block.BlockHorizontal;
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
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.blocks.BlockForge2Fuel;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge2AirflowConsumer;
import tiki.rotn.advancedlootableweapons.compat.crafttweaker.ForgeRepresentation;
import tiki.rotn.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.rotn.advancedlootableweapons.init.BlockInit;

public class BlockForge2FuelContent extends BlockForge2Content implements IHasGeneratedModel {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool REQUIRES_IGNITION = PropertyBool.create("requires_ignition");
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	private static boolean keepinventory;
	private final ForgeRepresentation forge;
	
	public BlockForge2FuelContent(ForgeRepresentation forge)
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
						if(te instanceof TileEntityForge2) {
							((TileEntityForge2)te).usedIgniterOnForge();
						}
						if(mainHand.getItem().isDamageable()) {
							mainHand.damageItem(1, playerIn);
						}else {
							mainHand.setCount(mainHand.getCount() - 1);
						}
						
						playerIn.playSound(SoundEvents.ENTITY_BLAZE_BURN, 1.0F, 1.0F);
						return true;
					}
				}
				
				for(ItemStack stack : OreDictionary.getOres(ZenDynamicAlwResources.IGNITION_UPGRADE_ORE)) {
					if(stack.getItem() == mainHand.getItem()) {
						TileEntity te = worldIn.getTileEntity(pos);
						if(te instanceof TileEntityForge2) {
							((TileEntityForge2)te).setRequiresIgnition(false);
						}
						BlockForge2Fuel.setState(false, worldIn, pos);
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
			
			playerIn.openGui(Alw.instance, ModInfo.GUI_FORGE_2_FUEL, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
            
            //System.out.println("Checking isClearForPlacement!");
            if(isClearForPlacement(pos, face, worldIn)) {
//            	TileEntityForge2 TEF2 = ((TileEntityForge2)worldIn.getTileEntity(pos));
//            	TEF2.setMainTE(TEF2);
            	if(face == EnumFacing.WEST) {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
                			if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}
            				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else if(i == 1) {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}else if(face == EnumFacing.EAST) {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
            				if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}
            				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.offset(face.getOpposite()).getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else if(i == 1) {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else {
                    				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY() + j, pos.getZ() + i))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}else if(face == EnumFacing.NORTH) {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
                			if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}
            				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else if(i == 1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}else {
            		for(int i = -1; i <= 1; i++) {
            			for(int j = 0; j <= 1; j++) {
            				if(i == 0) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                    		}else if(i == -1) {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                    		}else {
                				worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                    		}
            				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.offset(face.getOpposite()).getZ()))).setMainTE(TEF2);
                    		if(i != 0 || j != 0) {
                    			if(i == -1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, true).withProperty(RIGHT, false));
                        		}else if(i == 1) {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, true));
                        		}else {
                					worldIn.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), BlockInit.forge2_1.getDefaultState().withProperty(FACING, face).withProperty(LEFT, false).withProperty(RIGHT, false));
                        		}
                				//((TileEntityForge2)worldIn.getTileEntity(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()))).setMainTE(TEF2);
                    		}
            			}
            		}
            	}
            	
            	worldIn.setBlockState(pos, state.withProperty(FACING, face).withProperty(REQUIRES_IGNITION, state.getValue(REQUIRES_IGNITION)), 2);
            	
    			//TileEntity setMain = ((TileEntityForge2) worldIn.getTileEntity(pos)).getMainTE(worldIn, pos);
            }
        }
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(REQUIRES_IGNITION, state.getValue(REQUIRES_IGNITION)), 2);
	}
	
	public static boolean isClearForPlacement(BlockPos pos, EnumFacing facing, World worldIn) {
		EnumFacing side = facing.rotateYCCW();
		BlockPos startPos = pos.offset(side);
		BlockPos endPos = pos.offset(facing.getOpposite()).offset(side.getOpposite()).offset(EnumFacing.UP);
		Iterator<MutableBlockPos> iterablePos = BlockPos.getAllInBoxMutable(startPos, endPos).iterator();
		
		while(iterablePos.hasNext()) {
			BlockPos iPos = iterablePos.next();//.toImmutable();
			if(!worldIn.mayPlace(BlockInit.forge2_1, iPos, false, EnumFacing.UP, worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false)) && !(worldIn.getBlockState(iPos).getBlock() instanceof BlockForge2FuelContent)) {
				return false;
			}
		}
		return true;
	}
	
	public static void setState(boolean requiresIgnition, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepinventory = true;
		
		worldIn.setBlockState(pos, BlockInit.forge2.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(REQUIRES_IGNITION, requiresIgnition), 3);
		
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
			return new TileEntityForge2AirflowConsumer(true, state.getValue(REQUIRES_IGNITION), this);
		}
		return new TileEntityForge2(true, state.getValue(REQUIRES_IGNITION), this);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(REQUIRES_IGNITION, true);
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
		//System.out.println(Integer.parseInt( (requiresIgnition ? 1 : 0) + String.valueOf(((EnumFacing)state.getValue(FACING)).getIndex()) ));
		return Integer.parseInt((requiresIgnition ? 1 : 0) +  String.valueOf(((EnumFacing)state.getValue(FACING)).getIndex()) );
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
    		variants.add("facing=north,requires_ignition=false", y0);
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
    	
    	JsonObject BlockModelJson = ZenDynamicAlwResources.ADVANCED_FORGE_MODEL;
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
        		Alw.logger.warn("Unable to generate block model for " + this.getUnlocalizedName() + ". Please check that you are using the latest version of ALW. If you are, please report this to the mod author!");
        	}
    	}
    	
    	models.add(new GeneratedModel(forge.getName(), ModelType.BLOCKSTATE, BlockStateJson.toString()));
    	
    	JsonObject ItemModelJson = new JsonObject();
		ItemModelJson.addProperty("parent", "contenttweaker:block/" + forge.getName());
		
    	models.add(new GeneratedModel(forge.getName(), ModelType.ITEM_MODEL, gson.toJson(ItemModelJson)));
    	
        return models;
	}
}
