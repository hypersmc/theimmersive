package JumpWatch.TheImmersiveTech.Multiblock;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class MBFurnaceBlockPort extends MBFurnaceBlockBase {

    public static final PropertyBool ASSEMBLED = PropertyBool.create("assembled");

    public MBFurnaceBlockPort(String name, MBFurnaceBlockType blockType) {
        super(name, blockType);

        if (MBFurnaceBlockType.Wall == blockType)
            throw new IllegalArgumentException("Invalid port type");
    }

    /*@Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumFacing = EnumFacing.getFront(meta);
        if (EnumFacing.Axis.Y == enumFacing.getAxis()) enumFacing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(, enumFacing)
    }*/
}
