package com.tiki.advancedlootableweapons.world;

import java.util.Random;

import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator{

	private WorldGenerator ore_copper, ore_silver, ore_platinum, ore_crystallite, ore_tin;
	
	public WorldGenCustomOres(){
		ore_tin = new WorldGenMinable(BlockInit.ore_tin.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_copper = new WorldGenMinable(BlockInit.ore_copper.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_silver = new WorldGenMinable(BlockInit.ore_silver.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
		ore_platinum = new WorldGenMinable(BlockInit.ore_platinum.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.STONE));
		ore_crystallite = new WorldGenMinable(BlockInit.ore_crystallite.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1:
			break;
		case 0:
			runGenerator(ore_tin, world, random, chunkX, chunkZ, 20, 12, 70);
			runGenerator(ore_copper, world, random, chunkX, chunkZ, 18, 11, 64);
			runGenerator(ore_silver, world, random, chunkX, chunkZ, 13, 10, 54);
			runGenerator(ore_platinum, world, random, chunkX, chunkZ, 3, 5, 46);
			runGenerator(ore_crystallite, world, random, chunkX, chunkZ, 5, 0, 20);
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
		
		for(int i = 0; i < chance; i++) {
			//rand.nextInt(100);
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}
	
}
