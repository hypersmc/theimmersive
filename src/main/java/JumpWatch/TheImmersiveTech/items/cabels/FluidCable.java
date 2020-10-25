package JumpWatch.TheImmersiveTech.items.cabels;

import JumpWatch.hypercore.cabels.BaseCableBlock;
import JumpWatch.hypercore.cabels.FluidBaseCableBlock;
import JumpWatch.hypercore.cabels.tileentities.CableTileEntity;
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

public class FluidCable extends FluidBaseCableBlock implements ITileEntityProvider {

    public FluidCable(Material materialIn) {
        super(materialIn);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        // TODO Auto-generated method stub
        return new FluidCableTileEntiity();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        FluidCableTileEntiity tileEntity = (FluidCableTileEntiity) world.getTileEntity(pos);
        if (tileEntity != null) {
            tileEntity.init(EnumCableType.C20, false);
        }
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }
}