package JumpWatch.TheImmersiveTech.items.cabels;

import JumpWatch.hypercore.cabels.FluidBaseCableBlock;
import JumpWatch.hypercore.cabels.ItemBaseCableBlock;
import JumpWatch.hypercore.cabels.tileentities.FluidCableTileEntiity;
import JumpWatch.hypercore.cabels.tileentities.ItemCableTileEntitiy;
import JumpWatch.hypercore.utils.EnumCableType;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCable extends ItemBaseCableBlock implements ITileEntityProvider {

    public ItemCable(Material materialIn) {
        super(materialIn);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        // TODO Auto-generated method stub
        return new ItemCableTileEntitiy();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        ItemCableTileEntitiy tileEntity = (ItemCableTileEntitiy) world.getTileEntity(pos);
        if (tileEntity != null) {
            tileEntity.init(EnumCableType.C20, false);
        }
        super.onBlockPlacedBy(world, pos, state, placer, stack);
    }
}