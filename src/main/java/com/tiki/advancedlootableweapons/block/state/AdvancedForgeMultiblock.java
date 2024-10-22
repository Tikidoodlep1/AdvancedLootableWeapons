package com.tiki.advancedlootableweapons.block.state;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

import java.util.function.BiFunction;
//        BlockPos leftPos = pos.relative(direction.getCounterClockWise());
//        BlockPos rightPos = pos.relative(direction.getClockWise());
//
//        BlockPos backPos = pos.relative(direction.getOpposite());
//        BlockPos backLeftPos = backPos.relative(direction.getCounterClockWise());
//        BlockPos backRightPos = backPos.relative(direction.getClockWise());
//
//
//        BlockPos upPos = pos.relative(Direction.UP);
//        BlockPos upLeftPos = upPos.relative(direction.getCounterClockWise());
//        BlockPos upRightPos = upPos.relative(direction.getClockWise());
//
//        BlockPos upBackPos = upPos.relative(direction.getOpposite());
//        BlockPos upBackLeftPos = upBackPos.relative(direction.getCounterClockWise());
//        BlockPos upBackRightPos = upBackPos.relative(direction.getClockWise());
public enum AdvancedForgeMultiblock implements StringRepresentable {
    main((pos, direction) -> pos),
    left((pos, direction) -> pos.relative(direction.getCounterClockWise())),
    right((pos, direction) -> pos.relative(direction.getClockWise())),
    back((pos, direction) -> pos.relative(direction.getOpposite())),
    back_left((pos, direction) -> pos.relative(direction.getOpposite()).relative(direction.getCounterClockWise())),
    back_right((pos, direction) -> pos.relative(direction.getOpposite()).relative(direction.getClockWise())),
    up((pos, direction) -> pos.relative(Direction.UP)),
    up_left((pos, direction) -> pos.relative(Direction.UP).relative(direction.getCounterClockWise())),
    up_right((pos, direction) -> pos.relative(Direction.UP).relative(direction.getClockWise())),
    up_back((pos, direction) -> pos.relative(Direction.UP).relative(direction.getOpposite())),
    up_back_left((pos, direction) -> pos.relative(Direction.UP).relative(direction.getOpposite()).relative(direction.getCounterClockWise())),
    up_back_right((pos, direction) -> pos.relative(Direction.UP).relative(direction.getOpposite()).relative(direction.getClockWise()));

    private final BiFunction<BlockPos, Direction, BlockPos> computeOffset;

    AdvancedForgeMultiblock(BiFunction<BlockPos, Direction,BlockPos> computeOffset){
        this.computeOffset = computeOffset;
    }

    public BlockPos offsetFromMain(BlockPos mainPos,Direction direction) {
        return computeOffset.apply(mainPos,direction);
    }

    @Override
    public String getSerializedName() {
        return name();
    }
    public boolean isMain() {
        return this == main;
    }

}
