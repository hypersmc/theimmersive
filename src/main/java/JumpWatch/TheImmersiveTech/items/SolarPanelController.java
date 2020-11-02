package JumpWatch.TheImmersiveTech.items;

import JumpWatch.TheImmersiveTech.Tile.SolarControllerTileEntity;
import JumpWatch.TheImmersiveTech.blocks.machines.solarpanelcontroller;
import JumpWatch.hypercore.cabels.tileentities.FluidCableTileEntiity;
import JumpWatch.hypercore.utils.EnumCableType;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SolarPanelController extends solarpanelcontroller implements ITileEntityProvider {

    public SolarPanelController(Material materialIn) {
        super(materialIn);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        // TODO Auto-generated method stub
        return new SolarControllerTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        SolarControllerTileEntity tileEntity = (SolarControllerTileEntity) world.getTileEntity(pos);
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }

}
