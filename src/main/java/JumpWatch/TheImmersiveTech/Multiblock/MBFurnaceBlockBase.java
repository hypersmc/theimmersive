package JumpWatch.TheImmersiveTech.Multiblock;

import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceFluidIOPortTileEntitiy;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceIOPortTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnacePowerTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceTileEntity;
import JumpWatch.TheImmersiveTech.blocks.BlockBase;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MBFurnaceBlockBase extends BlockBase implements ITileEntityProvider {
    public final static String INTERNAL_NAME = "mbfurnace";
    public MBFurnaceBlockBase(String name, MBFurnaceBlockType blockType) {
        super(Material.IRON, INTERNAL_NAME);
        this._myType = blockType;
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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking()) return false;
        MultiblockControllerBase controller = this.getMultiblockController(worldIn, pos);
        if ((null != controller) && (null == playerIn.getHeldEquipment())) {
            if (controller.getLastError() != null){
                playerIn.sendMessage(new TextComponentString(controller.getLastError().toString()));
                return true;
            }
        }
        return false;
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

        GameRegistry.registerTileEntity(MBFurnaceTileEntity.class, MBFurnaceBlockType.Wall.getName());
        GameRegistry.registerTileEntity(MBFurnacePowerTileEntity.class, MBFurnaceBlockType.Power.getName());
        GameRegistry.registerTileEntity(MBFurnaceIOPortTileEntity.class, MBFurnaceBlockType.Output.getName());
        GameRegistry.registerTileEntity(MBFurnaceFluidIOPortTileEntitiy.class, MBFurnaceBlockType.Fluid.getName());
    }

    private MBFurnaceBlockType _myType;

}
