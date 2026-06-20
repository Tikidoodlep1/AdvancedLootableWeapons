package tiki.advancedlootableweaponsrotn.blocks;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.blocks.BlockForgeFuel;
import tiki.advancedlootableweaponsrotn.blocks.tileentities.TileEntityForgeAirflowConsumer;

public class BlockForgeFuelAirflowConsumer extends BlockForgeFuel {

	public String name;
	private String tool;
	private int level;
	
	public BlockForgeFuelAirflowConsumer(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister) {
		super(name, mat, sound, tool, harvestLevel, shouldRegister);
		this.name = name;
		this.tool = tool;
		this.level = harvestLevel;
	}
	
	public BlockForgeFuelAirflowConsumer(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister, Set<Item> fuel, HashSet<Item> acceptedMats) {
		super(name, mat, sound, tool, harvestLevel, shouldRegister, fuel, acceptedMats);
		this.name = name;
		this.tool = tool;
		this.level = harvestLevel;
	}
	
	public void register() {
		setUnlocalizedName(this.name);
		setRegistryName(ModInfo.ID, this.name); //Alw ModInfo NOT AlwRotn ModInfo
		setCreativeTab(Alw.AlwBlocksTab);
		this.setHarvestLevel(this.tool, this.level);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityForgeAirflowConsumer(false, false, this.getRegistryName());
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityForgeAirflowConsumer(false, false, this.getRegistryName());
	}
}
