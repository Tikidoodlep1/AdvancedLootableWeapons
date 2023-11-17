package com.tiki.advancedlootableweapons.blocks.compat.contenttweaker;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.contenttweaker.modules.vanilla.blocks.BlockContent;
import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.ModInfo;
import com.tiki.advancedlootableweapons.blocks.BlockForge2;
import com.tiki.advancedlootableweapons.blocks.BlockForge2Placeholder;
import com.tiki.advancedlootableweapons.blocks.tileentities.TileEntityForge2;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ForgeRepresentation;
import com.tiki.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import com.tiki.advancedlootableweapons.init.BlockInit;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockForge2Content extends BlockContent implements IHasGeneratedModel {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");
	private static boolean keepinventory;
	private final ForgeRepresentation forge;
	
	public BlockForge2Content(ForgeRepresentation forge)
	{
		super(forge);
		this.forge = forge;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	public ForgeRepresentation getRepresentation() {
		return forge;
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
			playerIn.openGui(Alw.instance, ModInfo.GUI_FORGE_2, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
            	
            	worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
            	
    			//TileEntity setMain = ((TileEntityForge2) worldIn.getTileEntity(pos)).getMainTE(worldIn, pos);
            }
        }
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	public static boolean isClearForPlacement(BlockPos pos, EnumFacing facing, World worldIn) {
		EnumFacing side = facing.rotateYCCW();
		BlockPos startPos = pos.offset(side);
		BlockPos endPos = pos.offset(facing.getOpposite()).offset(side.getOpposite()).offset(EnumFacing.UP);
		Iterator<MutableBlockPos> iterablePos = BlockPos.getAllInBoxMutable(startPos, endPos).iterator();
		
		while(iterablePos.hasNext()) {
			BlockPos iPos = iterablePos.next();//.toImmutable();
			if(!worldIn.mayPlace(BlockInit.forge2_1, iPos, false, EnumFacing.UP, worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false)) && !(worldIn.getBlockState(iPos).getBlock() instanceof BlockForge2)) {
				return false;
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX() + 0.2D + rand.nextDouble() * 6.0D / 8.0D;
        double d1 = (double)pos.getY() + 0.7D + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double)pos.getZ() + 0.4D + rand.nextDouble() * 6.0D / 14.0D;

        if (rand.nextDouble() < 0.1D)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.75D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
        
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.05D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
	
	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepinventory = true;
		
		worldIn.setBlockState(pos, BlockInit.forge2.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		
		keepinventory = false;
		
		if(tileentity != null) 
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepinventory)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityForge2)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityForge2)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }
        
        EnumFacing facing = (EnumFacing)state.getValue(FACING);
		EnumFacing side = facing.rotateYCCW();
		BlockPos startPos = pos.offset(side);
		BlockPos endPos = pos.offset(facing.getOpposite()).offset(side.getOpposite()).offset(EnumFacing.UP);
		
		for(BlockPos bp : BlockPos.getAllInBox(startPos, endPos)) {
			if(worldIn.getBlockState(bp).getBlock() instanceof BlockForge2Placeholder) {
				worldIn.setBlockToAir(bp);
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
		return new TileEntityForge2(false, false);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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
	public List<IGeneratedModel> getGeneratedModels() {
		List<IGeneratedModel> models = Lists.newArrayList();
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
//    		variants.add("facing=north,requires_ignition=false", y0);
//        	variants.add("facing=east,requires_ignition=false", y90);
//        	variants.add("facing=south,requires_ignition=false", y180);
//        	variants.add("facing=west,requires_ignition=false", y270);
//        	variants.add("facing=north,requires_ignition=true", y0);
//        	variants.add("facing=east,requires_ignition=true", y90);
//        	variants.add("facing=south,requires_ignition=true", y180);
//        	variants.add("facing=west,requires_ignition=true", y270);
//    	}else {
    		variants.add("facing=north", y0);
        	variants.add("facing=east", y90);
        	variants.add("facing=south", y180);
        	variants.add("facing=west", y270);
//    	}
    	
    	BlockStateJson.add("variants", variants);
    	
    	JsonObject BlockModelJson = ZenDynamicAlwResources.ADVANCED_FORGE_MODEL;
		JsonElement textures = BlockModelJson.get("textures");
    	if(textures != null && !textures.isJsonNull()) {
    		JsonObject textureObj = textures.getAsJsonObject();
    		JsonObject newTextureObj = new JsonObject();
    		for(Entry<String, JsonElement> e : textureObj.entrySet()) {
    			newTextureObj.addProperty(e.getKey(), "contenttweaker:blocks/" + forge.getName());
    		}
    		BlockModelJson.add("textures", newTextureObj);
    	}
    	
    	models.add(new GeneratedModel(forge.getName(), ModelType.BLOCKSTATE, BlockStateJson.toString()));
    	if(BlockModelJson.size() > 0) {
    		models.add(new GeneratedModel(forge.getName(), ModelType.BLOCK_MODEL, BlockModelJson.toString()));
    	}
    	
        return models;
	}
}
