package JumpWatch.TheImmersiveTech.blocks.machines;


import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import JumpWatch.TheImmersiveTech.Tile.TileEntityElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.BlockBase;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.ITileEntityProvider;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Random;

public class BlockElectricCrusher extends BlockBase implements ITileEntityProvider {
    public final static String INTERNAL_NAME = "electric_crusher";
    public BlockElectricCrusher() {
        super(Material.IRON, INTERNAL_NAME);
        setUnlocalizedName(INTERNAL_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityElectricCrusher();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileEntityElectricCrusher) {
                playerIn.openGui(TheImmersiveTech.MODID, 5, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }



    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityElectricCrusher tileentity = (TileEntityElectricCrusher) worldIn.getTileEntity(pos);
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(1)));
        super.breakBlock(worldIn, pos, state);
    }
}