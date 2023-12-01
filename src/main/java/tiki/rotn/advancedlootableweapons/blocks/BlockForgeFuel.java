package tiki.rotn.advancedlootableweapons.blocks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import tiki.rotn.advancedlootableweapons.Alw;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForge;
import tiki.rotn.advancedlootableweapons.blocks.tileentities.TileEntityForgeAirflowConsumer;
import tiki.rotn.advancedlootableweapons.compat.crafttweaker.ZenDynamicAlwResources;
import tiki.rotn.advancedlootableweapons.init.BlockInit;

public class BlockForgeFuel extends BlockForge implements ITileEntityProvider
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool REQUIRES_IGNITION = PropertyBool.create("requires_ignition");
	private static boolean keepinventory;
	
	/**
	 * @param name             Block registry name.
	 * @param mat              {@link Material} for the block.
	 * @param tool             A String representation of an Effective Tool Class. Options are: {"pickaxe", "axe", "shovel"}.
	 * @param harvestLevel     The harvest level of the block. 0 = wood/gold, 1 = stone, 2 = iron, 3 = diamond.
	 */
	public BlockForgeFuel(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister)
	{
		super(name, mat, sound, shouldRegister);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(REQUIRES_IGNITION, true));
		this.setHarvestLevel(tool, harvestLevel);
		this.fullBlock = false;
	}
	
	public BlockForgeFuel(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister, Set<Item> fuel, HashSet<Item> acceptedMats) {
		super(name, mat, sound, tool, harvestLevel, shouldRegister, acceptedMats);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(REQUIRES_IGNITION, true));
		this.setHarvestLevel(tool, harvestLevel);
		this.fullBlock = false;
		
		setFuelList(fuel);
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
		
		keepinventory = true;
		
		worldIn.setBlockState(pos, BlockInit.forge.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(REQUIRES_IGNITION, requiresIgnition), 3);
		
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
//    	}
//    	else {
//    		variants.add("facing=north", y0);
//        	variants.add("facing=east", y90);
//        	variants.add("facing=south", y180);
//        	variants.add("facing=west", y270);
//    	}
//    	
//    	BlockStateJson.add("variants", variants);
//    	
//    	JsonParser parser = new JsonParser();
//    	JsonObject BlockModelJson = new JsonObject();
//		try {
//			URI modelURI = BlockForgeFuel.class.getResource("/assets/advancedlootableweapons/models/block/block_forge.json").toURI();
//			Path modelPath;
//			if(modelURI.getScheme().equals("file")) {
//				modelPath = Paths.get(modelURI);
//				BlockModelJson = parser.parse(new InputStreamReader(Files.newInputStream(modelPath))).getAsJsonObject();
//			}else {
//				throw new IOException("Unable to find file /assets/advancedlootableweapons/models/block/block_forge.json");
//			}
//			
//		} catch (IOException | URISyntaxException e) {
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