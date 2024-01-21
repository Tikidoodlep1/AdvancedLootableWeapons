package tiki.rotn.advancedlootableweapons.blocks;

import java.util.HashSet;
import java.util.Random;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import tiki.rotn.advancedlootableweapons.init.BlockInit;

public class BlockForge extends BlockBase implements ITileEntityProvider
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public final HashSet<Item> acceptedMaterials;
	private static boolean keepinventory;
	
	public BlockForge(String name, Material mat, SoundType sound, boolean shouldRegister)
	{
		this(name, mat, sound, "pickaxe", 1, shouldRegister, new HashSet<Item>());
		
	}
	
	public BlockForge(String name, Material mat, SoundType sound, String tool, int level, boolean shouldRegister, HashSet<Item> acceptedMaterials)
	{
		super(name, mat, tool, level, shouldRegister);
		this.setSoundType(sound);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.fullBlock = false;
		this.acceptedMaterials = acceptedMaterials;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(this);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Alw.instance, ModInfo.GUI_FORGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX() + 0.3D + rand.nextDouble() * 6.0D / 16.0D;
        double d1 = (double)pos.getY() + 0.2D + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double)pos.getZ() + 0.3D + rand.nextDouble()* 6.0D / 16.0D;

        if (rand.nextDouble() < 0.1D)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.75D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
        
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
	
	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		//System.out.println("TILE ENTITY INFORMATION: " + tileentity.getTileData());
		keepinventory = true;
		
		worldIn.setBlockState(pos, BlockInit.forge.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		
		keepinventory = false;
		
		if(tileentity != null) 
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepinventory)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityForge)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityForge)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
//		if(Alw.isPyrotechLoaded) {
//			return new TileEntityForgeAirflowConsumer(false, false, this.getRegistryName());
//		}
		return new TileEntityForge(false, false, this.getRegistryName());
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
//		if(Alw.isPyrotechLoaded) {
//			return new TileEntityForgeAirflowConsumer(false, false, this.getRegistryName());
//		}
		return new TileEntityForge(false, false, this.getRegistryName());
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
////    	if(this.getDefaultState().getValue(REQUIRES_IGNITION)) {
////    		variants.add("facing=north,requires_ignition=false", y0);
////        	variants.add("facing=east,requires_ignition=false", y90);
////        	variants.add("facing=south,requires_ignition=false", y180);
////        	variants.add("facing=west,requires_ignition=false", y270);
////        	variants.add("facing=north,requires_ignition=true", y0);
////        	variants.add("facing=east,requires_ignition=true", y90);
////        	variants.add("facing=south,requires_ignition=true", y180);
////        	variants.add("facing=west,requires_ignition=true", y270);
////    	}else {
//    		variants.add("facing=north", y0);
//        	variants.add("facing=east", y90);
//        	variants.add("facing=south", y180);
//        	variants.add("facing=west", y270);
////    	}
//    	
//    	BlockStateJson.add("variants", variants);
//    	
//    	JsonReader reader;
//    	JsonObject BlockModelJson = new JsonObject();
//		try {
//			reader = new JsonReader(new BufferedReader(new FileReader(new ResourceLocation(ModInfo.ID + ":models/block/block_forge.json").toString())));
//			Alw.logger.info("MODEL FILE ACTUALLY FOUND HOLY SHIT!!!");
//			System.out.println("MODEL FILE ACTUALLY FOUND HOLY SHIT!!!");
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
//	    			if(name.equals("textures")) {
//	    				JsonObject textures = new JsonObject();
//	    				reader.beginObject();
//	    				textures.addProperty(reader.nextName(), "contenttweaker:blocks/" + this.name);
//	    				reader.skipValue();
//	    				textures.addProperty(reader.nextName(), "contenttweaker:blocks/" + this.name);
//	    				reader.skipValue();
//	    				reader.endObject();
//	    				BlockModelJson.add("textures", textures);
//	    				name = null;
//	    			}
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
//			//JsonParser parser = new JsonParser(); // Using above method for custom texture path!
//			//BlockModelJson = parser.parse(reader).getAsJsonObject();
//			
//			reader.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//		JsonObject ItemModelJson = new JsonObject();
//		ItemModelJson.addProperty("parent", "contenttweaker:block/" + this.name);
//		
//		Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
//    	models.add(new GeneratedModel(this.name, ModelType.BLOCKSTATE, gson.toJson(BlockStateJson)));
//    	if(BlockModelJson.size() > 0) {
//    		models.add(new GeneratedModel(this.name, ModelType.BLOCK_MODEL, gson.toJson(BlockModelJson)));
//    	}else {
//    		Alw.logger.warn("Unable to generate block model for " + this.getUnlocalizedName() + ". Please check that you are using the latest version of ALW. If you are, please report this to the mod author!");
//    	}
//    	models.add(new GeneratedModel(this.name, ModelType.ITEM_MODEL, gson.toJson(ItemModelJson)));
//    	
//        return models;
//	}
}