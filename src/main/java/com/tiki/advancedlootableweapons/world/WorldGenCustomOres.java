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

	private WorldGenerator ore_copper, ore_silver, ore_platinum, ore_crystallite, ore_tin, rock_feldspar;
	
	public WorldGenCustomOres(){
		rock_feldspar = new WorldGenMinable(BlockInit.rock_feldspar.getDefaultState(), 20, BlockMatcher.forBlock(Blocks.STONE));
		ore_tin = new WorldGenMinable(BlockInit.ore_tin.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.STONE));//5
		ore_copper = new WorldGenMinable(BlockInit.ore_copper.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.STONE));//5
		ore_silver = new WorldGenMinable(BlockInit.ore_silver.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));//4
		ore_platinum = new WorldGenMinable(BlockInit.ore_platinum.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.STONE));//3
		ore_crystallite = new WorldGenMinable(BlockInit.ore_crystallite.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));//4
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1:
			break;
		case 0:
			runGenerator(rock_feldspar, world, random, chunkX, chunkZ, 3, 15, 100);//3
			runGenerator(ore_tin, world, random, chunkX, chunkZ, 17, 12, 70);//13
			runGenerator(ore_copper, world, random, chunkX, chunkZ, 20, 11, 64);//13
			runGenerator(ore_silver, world, random, chunkX, chunkZ, 15, 10, 54);//10
			runGenerator(ore_platinum, world, random, chunkX, chunkZ, 10, 5, 46);//3
			runGenerator(ore_crystallite, world, random, chunkX, chunkZ, 10, 0, 20);//5
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
		/*
		for(int i = 0; i < chance; i++) {
			//rand.nextInt(100);
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
		*/
	}
	
}
