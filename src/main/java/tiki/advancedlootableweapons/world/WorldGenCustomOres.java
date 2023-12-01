package tiki.advancedlootableweapons.world;

import java.util.Random;

import com.google.common.base.Predicates;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import tiki.advancedlootableweapons.handlers.ConfigHandler;
import tiki.advancedlootableweapons.init.BlockInit;

public class WorldGenCustomOres implements IWorldGenerator {

	private WorldGenerator ore_copper, ore_silver, ore_platinum, ore_crystallite, ore_tin, ore_salt, rock_feldspar, rock_gypsum, rock_dolomite;
	
	public WorldGenCustomOres(){
		rock_feldspar = new WorldGenMinable(BlockInit.rock_feldspar.getDefaultState(), ConfigHandler.FELDSPAR_BLOCKS_PER_VEIN, BlockMatcher.forBlock(Blocks.STONE));
		rock_gypsum = new WorldGenMinable(BlockInit.gypsum.getDefaultState(), ConfigHandler.GYPSUM_BLOCKS_PER_VEIN, Predicates.or(BlockMatcher.forBlock(Blocks.STONE), BlockMatcher.forBlock(Blocks.SANDSTONE)));
		rock_dolomite = new WorldGenMinable(BlockInit.dolomite.getDefaultState(), ConfigHandler.DOLOMITE_BLOCKS_PER_VEIN, Predicates.or(BlockMatcher.forBlock(Blocks.STONE), BlockMatcher.forBlock(Blocks.SANDSTONE)));
		ore_tin = new WorldGenMinable(BlockInit.ore_tin.getDefaultState(), ConfigHandler.TIN_ORE_BLOCKS_PER_VEIN, BlockMatcher.forBlock(Blocks.STONE));
		ore_copper = new WorldGenMinable(BlockInit.ore_copper.getDefaultState(), ConfigHandler.COPPER_ORE_BLOCKS_PER_VEIN, BlockMatcher.forBlock(Blocks.STONE));
		ore_silver = new WorldGenMinable(BlockInit.ore_silver.getDefaultState(), ConfigHandler.SILVER_ORE_BLOCKS_PER_VEIN, BlockMatcher.forBlock(Blocks.STONE));
		ore_platinum = new WorldGenMinable(BlockInit.ore_platinum.getDefaultState(), ConfigHandler.PLATINUM_ORE_BLOCKS_PER_VEIN, BlockMatcher.forBlock(Blocks.STONE));
		ore_crystallite = new WorldGenMinable(BlockInit.ore_crystallite.getDefaultState(), ConfigHandler.CRYSTALLITE_ORE_BLOCKS_PER_VEIN, BlockMatcher.forBlock(Blocks.STONE));
		ore_salt = new WorldGenMinable(BlockInit.ore_salt.getDefaultState(), ConfigHandler.SALT_ORE_BLOCKS_PER_VEIN, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1:
			break;
		case 0:
			runGenerator(rock_feldspar, world, random, chunkX, chunkZ, ConfigHandler.FELDSPAR_MAX_VEINS_PER_CHUNK, ConfigHandler.FELDSPAR_MIN_SPAWN_HEIGHT, ConfigHandler.FELDSPAR_MAX_SPAWN_HEIGHT);
			runGenerator(rock_gypsum, world, random, chunkX, chunkZ, ConfigHandler.GYPSUM_MAX_VEINS_PER_CHUNK, ConfigHandler.GYPSUM_MIN_SPAWN_HEIGHT, ConfigHandler.GYPSUM_MAX_SPAWN_HEIGHT);
			runGenerator(rock_dolomite, world, random, chunkX, chunkZ, ConfigHandler.DOLOMITE_MAX_VEINS_PER_CHUNK, ConfigHandler.DOLOMITE_MIN_SPAWN_HEIGHT, ConfigHandler.DOLOMITE_MAX_SPAWN_HEIGHT);
			runGenerator(ore_tin, world, random, chunkX, chunkZ, ConfigHandler.TIN_ORE_MAX_VEINS_PER_CHUNK, ConfigHandler.TIN_ORE_MIN_SPAWN_HEIGHT, ConfigHandler.TIN_ORE_MAX_SPAWN_HEIGHT);
			runGenerator(ore_copper, world, random, chunkX, chunkZ, ConfigHandler.COPPER_ORE_MAX_VEINS_PER_CHUNK, ConfigHandler.COPPER_ORE_MIN_SPAWN_HEIGHT, ConfigHandler.COPPER_ORE_MAX_SPAWN_HEIGHT);
			runGenerator(ore_silver, world, random, chunkX, chunkZ, ConfigHandler.SILVER_ORE_MAX_VEINS_PER_CHUNK, ConfigHandler.SILVER_ORE_MIN_SPAWN_HEIGHT, ConfigHandler.SILVER_ORE_MAX_SPAWN_HEIGHT);
			runGenerator(ore_platinum, world, random, chunkX, chunkZ, ConfigHandler.PLATINUM_ORE_MAX_VEINS_PER_CHUNK, ConfigHandler.PLATINUM_ORE_MIN_SPAWN_HEIGHT, ConfigHandler.PLATINUM_ORE_MAX_SPAWN_HEIGHT);
			runGenerator(ore_crystallite, world, random, chunkX, chunkZ, ConfigHandler.CRYSTALLITE_ORE_MAX_VEINS_PER_CHUNK, ConfigHandler.CRYSTALLITE_ORE_MIN_SPAWN_HEIGHT, ConfigHandler.CRYSTALLITE_ORE_MAX_SPAWN_HEIGHT);
			runGenerator(ore_salt, world, random, chunkX, chunkZ, ConfigHandler.SALT_ORE_MAX_VEINS_PER_CHUNK, ConfigHandler.SALT_ORE_MIN_SPAWN_HEIGHT, ConfigHandler.SALT_ORE_MAX_SPAWN_HEIGHT);
			break;
		case 1:
			break;
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) {
			throw new IllegalArgumentException("Ore Generated out of bounds");
		}
		
		int heightDiff = maxHeight - minHeight + 1;
		
		for(int i = 0; i < (rand.nextInt(chance) + 1); i ++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x, y, z));
		}
	}
	
}
