package tiki.advancedlootableweapons.util;

import java.util.HashMap;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import tiki.advancedlootableweapons.Alw;

public class MultiblockPattern {

	private HashMap<String, Block> key = new HashMap<String, Block>();
	private Block[][][] pattern;
	private int master;
	
	MultiblockPattern(int master, @Nonnull String[] keys, @Nonnull Block[] values, @Nonnull String[][][] pattern) {
		
		//================================================== ERROR HANDLING ==================================================
		for(int i = 1; i < this.pattern[0].length; i++) {
			if(this.pattern[0][i].length != this.pattern[0][i-1].length) {
				Alw.logger.error(new IllegalArgumentException("Pattern Layers must be equal length! length " + this.pattern[0][i].length + " was provided, but " + this.pattern[0][i-1].length + " was expected! For unoccupied blocks, use the WILDCARD value \'#\'"));
				this.pattern = null;
				this.master = -1;
				return;
			}
		}
		if(keys.length != values.length) {
			Alw.logger.error(new IllegalArgumentException("There must be exactly one value for every key provided in Pattern Key! " + keys.length + " keys provided with " + values.length + " values!"));
			this.pattern = null;
			this.master = -1;
			return;
		}
		//================================================== END ERROR HANDLING ==================================================
		
		for(int i = 0; i < keys.length; i++) {
			this.key.put(keys[i], values[i]);
		}
		
		this.pattern = new Block[pattern.length][pattern[0].length][pattern[0][0].length];
		for(int i = 0; i < pattern.length; i++) {
			for(int j = 0; j < pattern.length; j++) {
				for(int k = 0; k < pattern.length; k++) {
					this.pattern[i][j][k] = key.get(pattern[i][j][k]);
				}
			}
		}
		this.master = master;
	}
	
	public BlockPos getMasterFromIndexAndPos(BlockPos current, EnumFacing facing, int currentIndex) {
		if(this.pattern == null) {return null;}
		
		int masterLayer = (int)(this.master / this.getSingleLayerSize());
		int masterWidth = (int)(this.master / (this.getSingleLayerSize() + this.pattern[0].length));
		int masterLength = this.master % this.pattern[0][0].length;
		int currentLayer = (int)(currentIndex / this.getSingleLayerSize());
		int currentWidth = (int)(currentIndex / (this.getSingleLayerSize() + this.pattern[0].length));
		int currentLength = currentIndex % this.pattern[0][0].length;
		
		if(masterLayer > currentLayer) { // Bigger layer index == Higher Y coordinate
			current.offset(EnumFacing.UP, masterLayer - currentLayer);
		}else if(masterLayer < currentLayer) {
			current.offset(EnumFacing.DOWN, currentLayer - masterLayer);
		}
		
		if(masterWidth > currentWidth) { // Bigger width index == farther in the direction of facing
			current.offset(facing, masterWidth - currentWidth);
		}else if(masterWidth < currentWidth) {
			current.offset(facing.getOpposite(), currentWidth - masterWidth);
		}
		
		if(masterLength > currentLength) { // Bigger length index == farther in the counter-clockwise direction from facing
			current.offset(facing.rotateY(), masterLength - currentLength);
		}else if(masterLength < currentLength) {
			current.offset(facing.rotateYCCW(), currentLength - masterLength);
		}
		
		return current;
	}
	
	public int getMultiblockSize() {
		if(this.pattern == null) {return -1;}
		
		return this.pattern.length * this.getSingleLayerSize();
	}
	
	public int getSingleLayerSize() {
		if(this.pattern == null) {return -1;}
		
		return this.pattern[0].length * this.pattern[0][0].length;
	}
	
	public int getSingleLengthIndex(int layerIndex, int widthIndex, int lengthIndex) {
		if(this.pattern == null) {return -1;}
		
		return (this.getSingleLayerSize() * layerIndex)+(this.pattern[0][0].length * widthIndex) + lengthIndex;
	}
	
	public Block getBlockFromSingleLengthIndex(int index) {
		if(this.pattern == null) {return null;}
		
		return this.pattern
				[(int)(index / this.getSingleLayerSize())]
				[(int)(index / (this.getSingleLayerSize() + this.pattern[0].length))]
				[index % this.pattern[0][0].length];
	}
	
	public int getLayerIndexFromSingleLengthIndex(int index) {
		return index / this.getSingleLayerSize();
	}
	
	public int getWidthIndexFromSingleLengthIndex(int index) {
		return index / (this.getSingleLayerSize() + this.pattern[0].length);
	}
	
	public int getLengthIndexFromSingleLengthIndex(int index) {
		return index % this.pattern[0][0].length;
	}
	
	public Block getBlockForPlacementAt(BlockPos master, BlockPos blockAt, EnumFacing facing) {
		int masterLayer = this.getLayerIndexFromSingleLengthIndex(this.master);
		int masterWidth = this.getWidthIndexFromSingleLengthIndex(this.master);
		int masterLength = this.getLengthIndexFromSingleLengthIndex(this.master);
		
		return this.pattern
				[masterLayer + (blockAt.getY() - master.getY())]
				[masterWidth + (this.getBlockXZFromFacing(blockAt, facing) - this.getBlockXZFromFacing(master, facing))]
				[masterLength + (this.getBlockXZFromFacing(blockAt, facing.rotateY()) - this.getBlockXZFromFacing(master, facing.rotateY()))];
	}
	
	private int getBlockXZFromFacing(BlockPos pos, EnumFacing facing) {
		switch(facing.getAxis()) {
		case X:
			return pos.getX();
		case Z:
			return pos.getZ();
		default:
			return -1;
		}
	}
}

/*
For Pattern: 
new MultiblockPattern(5,
	{
		{'x', 'x', 'x', 'x'},
		{'x', 'y', 'x', 'x'},
		{'x', 'x', 'x', 'x'}
	},
	{
		{' ', 's', ' ', ' '},
		{' ', 'y', ' ', 's'},
		{' ', ' ', ' ', ' '}
	},
	{
		{' ', 'w', ' ', ' '},
		{' ', 'y', ' ', 'w'},
		{' ', ' ', ' ', ' '}
	}
)

Getting the Single length index for the 'y' in the second layer:
	getSingleLengthIndex(0, 1, 1) -> getSingleLayerSize() := 3*4 = (12 * 0) + (4 * 1) + 1 => 5
	getSingleLengthIndex(2, 1, 3) -> getSingleLayerSize() := 3*4 = (12 * 2) + (4 * 1) + 3 => 31

Getting the Block for Single Length index 5:
	getBlockFromSingleLengthIndex(5) -> this.pattern[(int)(5 / 12) = 0][(int)(5 / (12 + 4)) = 1][5 % 4 = 1] => 'y'
	getBlockFromSingleLengthIndex(31) -> this.pattern[(int)(31 / 12) = 2][(int)(31 / (12 + 4)) = 1][31 % 4 = 3] => 'w'
	
Getting Block for placement at (53, 62, 1832): Simulate master coords => (51, 62, 1833), Facing = NORTH (forward axis = Z)
	this.pattern[0 + (62 - 62) = 0][1 + (1832 - 1833) = 0][1 + (53 - 51) = 3] = 'x'
	
*/