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
import tiki.advancedlootableweapons.blocks.BlockForge2Fuel;
import tiki.advancedlootableweaponsrotn.blocks.tileentities.TileEntityForge2AirflowConsumer;

public class BlockForge2FuelAirflowConsumer extends BlockForge2Fuel {

	public String name;
	private String tool;
	private int level;
	
	public BlockForge2FuelAirflowConsumer(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister) {
		super(name, mat, sound, tool, harvestLevel, shouldRegister);
		this.name = name;
		this.tool = tool;
		this.level = harvestLevel;
	}
	
	public BlockForge2FuelAirflowConsumer(String name, Material mat, SoundType sound, String tool, int harvestLevel, boolean shouldRegister, Set<Item> fuel, HashSet<Item> acceptedMats) {
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
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityForge2AirflowConsumer(false, false, this.getRegistryName());
	}
	
	
}
