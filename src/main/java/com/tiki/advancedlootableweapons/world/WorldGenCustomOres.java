package com.tiki.advancedlootableweapons.world;

import java.util.Random;

import com.google.common.base.Predicates;
import com.tiki.advancedlootableweapons.init.BlockInit;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator {

	private WorldGenerator ore_copper, ore_silver, ore_platinum, ore_crystallite, ore_tin, ore_salt, rock_feldspar, rock_gypsum, rock_dolomite;
	
	@SuppressWarnings("unchecked")
	public WorldGenCustomOres(){
		rock_feldspar = new WorldGenMinable(BlockInit.rock_feldspar.getDefaultState(), 15, BlockMatcher.forBlock(Blocks.STONE));
		rock_gypsum = new WorldGenMinable(BlockInit.gypsum.getDefaultState(), 12, Predicates.or(BlockMatcher.forBlock(Blocks.STONE), BlockMatcher.forBlock(Blocks.SANDSTONE), BlockMaterialMatcher.forMaterial(Material.WATER)));
		rock_dolomite = new WorldGenMinable(BlockInit.dolomite.getDefaultState(), 16, Predicates.or(BlockMatcher.forBlock(Blocks.STONE), BlockMatcher.forBlock(Blocks.SANDSTONE)));
		ore_tin = new WorldGenMinable(BlockInit.ore_tin.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.STONE));
		ore_copper = new WorldGenMinable(BlockInit.ore_copper.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.STONE));
		ore_silver = new WorldGenMinable(BlockInit.ore_silver.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.STONE));
		ore_platinum = new WorldGenMinable(BlockInit.ore_platinum.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_crystallite = new WorldGenMinable(BlockInit.ore_crystallite.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));
		ore_salt = new WorldGenMinable(BlockInit.ore_salt.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1:
			break;
		case 0:
			runGenerator(rock_feldspar, world, random, chunkX, chunkZ, 8, 33, 143);
			runGenerator(rock_gypsum, world, random, chunkX, chunkZ, 7, 14, 124);
			runGenerator(rock_dolomite, world, random, chunkX, chunkZ, 8, 42, 182);
			runGenerator(ore_tin, world, random, chunkX, chunkZ, 14, 34, 102);
			runGenerator(ore_copper, world, random, chunkX, chunkZ, 12, 30, 73);
			runGenerator(ore_silver, world, random, chunkX, chunkZ, 13, 18, 54);
			runGenerator(ore_platinum, world, random, chunkX, chunkZ, 8, 5, 46);
			runGenerator(ore_crystallite, world, random, chunkX, chunkZ, 3, 0, 20);
			runGenerator(ore_salt, world, random, chunkX, chunkZ, 23, 42, 145);
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
