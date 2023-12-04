package tiki.rotn.advancedlootableweapons.blocks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.IHasModel;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import tiki.rotn.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.rotn.advancedlootableweapons.init.BlockInit;

public class BlockForge2Fuel extends BlockForge2 implements IHasModel
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool REQUIRES_IGNITION = PropertyBool.create("requires_ignition");
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	private static boolean keepinventory;
	
	public BlockForge2Fuel(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister)
	{
		super(name, mat, sound, shouldRegister);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(REQUIRES_IGNITION, true));
		this.setHarvestLevel(tool, harvestLevel);
	}
	
	public BlockForge2Fuel(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister, Set<Item> fuels, HashSet<Item> acceptedMats)
	{
		super(name, mat, sound, tool, harvestLevel, shouldRegister, acceptedMats);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(REQUIRES_IGNITION, true));
		this.setHarvestLevel(tool, harvestLevel);
		
		this.setFuelList(fuels);
	}
	
	public void setFuelList(Set<Item> items) {
		ZenDynamicAlwResources.setFuelListForBlock(this.getRegistryName(), items);
	}
	
	public void addItemToFuelList(Item item) {
		ZenDynamicAlwResources.fuelLists.get(this.getRegistryName()).add(item);
	}
	
	public void removeItemFromFuelList(Item item) {
		ZenDynamicAlwResources.fuelLists.get(this.getRegistryName()).remove(item);
	}
	
	public Set<Item> getFuelList() {
		return ZenDynamicAlwResources.fuelLists.get(this.getRegistryName());
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
			if(!worldIn.mayPlace(BlockInit.forge2_1, iPos, false, EnumFacing.UP, worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false)) && !(worldIn.getBlockState(iPos).getBlock() instanceof BlockForge2Fuel)) {
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
		return new TileEntityForge2(true, state.getValue(REQUIRES_IGNITION), this.getRegistryName());
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
	
//	@Override
//	public List<IGeneratedModel> getGeneratedModels() {
//		List<IGeneratedModel> models = Lists.newArrayList();
//		JsonObject BlockStateJson = new JsonObject();
//    	BlockStateJson.addProperty("forge_marker", 1);
//    	JsonObject variants = new JsonObject();
//    	JsonObject y0 = new JsonObject();
//    	JsonObject y90 = new JsonObject();
//    	JsonObject y180 = new JsonObject();
//    	JsonObject y270 = new JsonObject();
//    	y0.addProperty("model", new ResourceLocation("contenttweaker:" + this.name).toString());
//    	y0.addProperty("y", 0);
//    	y90.addProperty("model", new ResourceLocation("contenttweaker:" + this.name).toString());
//    	y90.addProperty("y", 90);
//    	y180.addProperty("model", new ResourceLocation("contenttweaker:" + this.name).toString());
//    	y180.addProperty("y", 180);
//    	y270.addProperty("model", new ResourceLocation("contenttweaker:" + this.name).toString());
//    	y270.addProperty("y", 270);
//    	if(this.getDefaultState().getValue(REQUIRES_IGNITION)) {
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
//    	JsonReader reader;
//    	JsonObject BlockModelJson = new JsonObject();
//		try {
//			reader = new JsonReader(new BufferedReader(new FileReader(new ResourceLocation(ModInfo.ID + ":models/block/block_advanced_forge.json").toString())));
//			reader.setLenient(true);
//			String name = null;
//			while(reader.hasNext()) {
//	    		JsonToken peek = reader.peek();
//	    		if(peek == JsonToken.BEGIN_ARRAY) {
//	    			reader.beginArray();
//	    		}else if(peek == JsonToken.BEGIN_OBJECT) {
//	    			reader.beginObject();
//	    		}else if(peek == JsonToken.BOOLEAN) {
//	    			if(name != null) {
//	    				BlockModelJson.addProperty(name, reader.nextBoolean());
//	    				name = null;
//	    			}
//	    		}else if(peek == JsonToken.NAME) {
//	    			name = reader.nextName();
//	    		}else if(peek == JsonToken.NUMBER) {
//	    			if(name != null) {
//	    				BlockModelJson.addProperty(name, reader.nextDouble());
//	    				name = null;
//	    			}
//	    		}else if(peek == JsonToken.STRING) {
//	    			if(name != null) {
//	    				BlockModelJson.addProperty(name, reader.nextString());
//	    				name = null;
//	    			}
//	    		}else if(peek == JsonToken.END_OBJECT) {
//	    			reader.endObject();
//	    		}else if(peek == JsonToken.END_ARRAY) {
//	    			reader.endArray();
//	    		}
//	    	}
//			reader.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//    	models.add(new GeneratedModel(this.name, ModelType.BLOCKSTATE, BlockStateJson.toString()));
//    	if(BlockModelJson.size() > 0) {
//    		models.add(new GeneratedModel(this.name, ModelType.BLOCK_MODEL, BlockModelJson.toString()));
//    	}
//    	
//        return models;
//	}
	
}