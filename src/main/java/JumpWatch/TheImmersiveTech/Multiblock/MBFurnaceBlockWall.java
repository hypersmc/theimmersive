package JumpWatch.TheImmersiveTech.Multiblock;

import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceTileEntity;
import com.google.common.collect.Lists;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.lib.BlockFacings;
import it.zerono.mods.zerocore.lib.PropertyBlockFacings;
import it.zerono.mods.zerocore.util.CodeHelper;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

import java.util.ArrayList;

public class MBFurnaceBlockWall extends MBFurnaceBlockBase{

    public MBFurnaceBlockWall(String name) {
        super(name, MBFurnaceBlockType.Wall);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IMultiblockPart tile = this.getMultiblockPartAt(worldIn, pos);
        if (tile instanceof MBFurnaceTileEntity) {
            MBFurnaceTileEntity part = (MBFurnaceTileEntity)tile;
            MBFurnaceController controller = (MBFurnaceController)part.getMultiblockController();
            if (null == controller) FMLLog.warning("WALL - GOT NULL CONTROLLER!");
            if ((null != controller) && controller.isAssembled()) {
                worldIn.markBlockRangeForRenderUpdate(pos, pos);
                int energy = controller.getEnergyStored(EnumFacing.UP);
                playerIn.sendMessage(new TextComponentString(String.format("Energy stored = %d RF", energy)));
                return true;
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        IMultiblockPart part = this.getMultiblockPartAt(worldIn, pos);

        if (part instanceof  MBFurnaceTileEntity) {
            MBFurnaceTileEntity wallTile = (MBFurnaceTileEntity)part;
            boolean assembled = wallTile.isConnected() && wallTile.getMultiblockController().isAssembled();
            BlockFacings facings = assembled ? wallTile.getOutwardsDir() : BlockFacings.ALL;

            state = state.withProperty(FACES, facings.toProperty());

            if (wallTile.getPartPosition().isFace()) {
                MBFurnaceController controller = (MBFurnaceController)wallTile.getMultiblockController();
                boolean active = controller.isAssembled() && controller.isActive();
                if (active) state = state.withProperty(FACES, PropertyBlockFacings.Opposite_EW);
            }
        }
        return state;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACES});
    }
    public void initModels(){
        super.initBlock();
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACES, PropertyBlockFacings.All));
    }

    private final static PropertyEnum FACES ;

    static {

        ArrayList<PropertyBlockFacings> values = Lists.newArrayList();

        values.addAll(PropertyBlockFacings.ALL_AND_NONE);
        values.addAll(PropertyBlockFacings.FACES);
        values.addAll(PropertyBlockFacings.ANGLES);
        values.addAll(PropertyBlockFacings.CORNERS);
        values.add(PropertyBlockFacings.Opposite_EW);

        FACES = PropertyEnum.create("faces", PropertyBlockFacings.class, values);
    }
}
