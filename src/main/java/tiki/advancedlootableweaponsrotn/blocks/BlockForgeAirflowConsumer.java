package tiki.advancedlootableweaponsrotn.blocks;

import java.util.HashSet;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.ModInfo;
import tiki.advancedlootableweapons.blocks.BlockForge;
import tiki.advancedlootableweaponsrotn.blocks.tileentities.TileEntityForgeAirflowConsumer;

public class BlockForgeAirflowConsumer extends BlockForge {

	public String name;
	private String tool;
	private int level;
	
	public BlockForgeAirflowConsumer(String name, Material mat, SoundType sound, boolean shouldRegister) {
		super(name, mat, sound, shouldRegister);
		this.name = name;
		this.tool = "pickaxe";
		this.level = 1;
	}

	public BlockForgeAirflowConsumer(String name, Material mat, SoundType sound, String tool, int level, boolean shouldRegister, HashSet<Item> acceptedMaterials) {
		super(name, mat, sound, tool, level, shouldRegister, acceptedMaterials);
		this.name = name;
		this.tool = tool;
		this.level = level;
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
