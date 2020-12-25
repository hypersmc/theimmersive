package JumpWatch.TheImmersiveTech.Multiblock;

import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceFluidIOPortTileEntitiy;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceIOPortTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnacePowerTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceTileEntity;
import JumpWatch.TheImmersiveTech.blocks.BlockBase;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.List;

import static JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockType.*;

public class MBFurnaceBlockPort extends MBFurnaceBlockBase {

    public static final PropertyBool ASSEMBLED = PropertyBool.create("assembled");

    public MBFurnaceBlockPort(String name, MBFurnaceBlockType blockType) {
        super(name, blockType);
        setUnlocalizedName(name);
        this._myType = blockType;
        if (MBFurnaceBlockType.Wall == blockType)
            throw new IllegalArgumentException("Invalid port type");
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumFacing = EnumFacing.getFront(meta);
        if (EnumFacing.Axis.Y == enumFacing.getAxis()) enumFacing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(BlockBase.HFACING, enumFacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(BlockBase.HFACING)).getIndex();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        IMultiblockPart part = this.getMultiblockPartAt(world, pos);
        if (part instanceof MBFurnaceTileEntity) {
            MBFurnaceTileEntity wallTile = (MBFurnaceTileEntity)part;
            boolean assembled = wallTile.isConnected() && wallTile.getMultiblockController().isAssembled();

            state = state.withProperty(ASSEMBLED, assembled);

            if (assembled){
                switch (wallTile.getPartPosition()) {
                    case NorthFace:
                        state = state.withProperty(BlockBase.HFACING, EnumFacing.NORTH);
                        break;
                    case SouthFace:
                        state = state.withProperty(BlockBase.HFACING, EnumFacing.SOUTH);
                        break;
                    case WestFace:
                        state = state.withProperty(BlockBase.HFACING, EnumFacing.WEST);
                        break;
                    case EastFace:
                        state = state.withProperty(BlockBase.HFACING, EnumFacing.EAST);
                        break;
                }
            }
        }
        return state;
    }
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

        facing = (null != placer) ? placer.getHorizontalFacing().getOpposite() : EnumFacing.NORTH;

        return this.getDefaultState().withProperty(BlockBase.HFACING, facing);
    }

    public void onBlockAdded(World world, BlockPos position, IBlockState state) {

        EnumFacing newFacing = this.suggestDefaultFacing(world, position, (EnumFacing)state.getValue(BlockBase.HFACING));

        world.setBlockState(position, state.withProperty(BlockBase.HFACING, newFacing), 2);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {BlockBase.HFACING, ASSEMBLED});
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        switch (this._myType) {
            case Power:
                tooltip.add("Furnace Power block");
                tooltip.add("");
                tooltip.add("Used to make a 3x3 multiblock.");
                break;
            case Fluid:
                tooltip.add("Furnace Fluid block");
                tooltip.add("");
                tooltip.add("Used to make a 3x3 multiblock.");
                break;
            case Input:
                tooltip.add("Furnace Item Input block");
                tooltip.add("");
                tooltip.add("Used to make a 3x3 multiblock.");
                break;
            case Output:
                tooltip.add("Furnace Item Output block");
                tooltip.add("");
                tooltip.add("Used to make a 3x3 multiblock.");
                break;
        }

    }

    @Override
    public void initBlock() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockBase.HFACING, EnumFacing.NORTH).withProperty(ASSEMBLED, false));

    }
    private MBFurnaceBlockType _myType;
}
