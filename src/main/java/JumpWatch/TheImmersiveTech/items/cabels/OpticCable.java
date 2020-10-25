package JumpWatch.TheImmersiveTech.items.cabels;

import JumpWatch.hypercore.cabels.BaseCableBlock;
import JumpWatch.hypercore.cabels.tileentities.CableTileEntity;
import JumpWatch.hypercore.utils.EnumCableType;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class OpticCable extends BaseCableBlock implements ITileEntityProvider {

    public OpticCable(Material materialIn) {
        super(materialIn);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        // TODO Auto-generated method stub
        return new CableTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        CableTileEntity tileEntity = (CableTileEntity) world.getTileEntity(pos);
        if (tileEntity != null) {
            tileEntity.init(EnumCableType.C20, false);
        }
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }
}
