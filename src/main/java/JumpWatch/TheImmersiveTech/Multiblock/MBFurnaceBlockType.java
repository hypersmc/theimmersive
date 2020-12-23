package JumpWatch.TheImmersiveTech.Multiblock;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public enum  MBFurnaceBlockType implements IStringSerializable {
    Wall,
    Power,
    Input,
    Output,
    Fluid
    ;

    public int toMeta(){
        return this.ordinal();
    }
    public static MBFurnaceBlockType getTypeFromMeta(int meta) {
        MBFurnaceBlockType[] values = MBFurnaceBlockType.values();

        return ((meta >= 0) && (meta < values.length)) ? values[meta] : MBFurnaceBlockType.Wall;
    }
    @Override
    public String getName() {
        return this.toString();
    }

    public static final PropertyEnum TYPE = PropertyEnum.create("type", MBFurnaceBlockType.class);
}
