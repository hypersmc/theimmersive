package JumpWatch.TheImmersiveTech.Multiblock;

import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceFluidIOPortTileEntitiy;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceIOPortTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnacePowerTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceTileEntity;
import JumpWatch.TheImmersiveTech.blocks.BlockBase;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MBFurnaceBlockBase extends BlockBase implements ITileEntityProvider {
    public MBFurnaceBlockBase(String name, MBFurnaceBlockType blockType) {
        super(Material.WOOD, name);
        this._myType = blockType;

    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        switch (this._myType) {
            default:
                return new MBFurnaceTileEntity();
            case Power:
                return new MBFurnacePowerTileEntity();
            case Fluid:
                return new MBFurnaceFluidIOPortTileEntitiy();
            case Input:
                return new MBFurnaceIOPortTileEntity(true);
            case Output:
                return new MBFurnaceIOPortTileEntity(false);
        }
    }

    protected IMultiblockPart getMultiblockPartAt(IBlockAccess world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        return te instanceof IMultiblockPart ? (IMultiblockPart)te : null;
    }
    protected MultiblockControllerBase getMultiblockController(IBlockAccess world, BlockPos pos) {
        IMultiblockPart part = this.getMultiblockPartAt(world, pos);
        return part.getMultiblockController();
    }

    protected void initBlock() {
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
    }

    private MBFurnaceBlockType _myType;

}
