package JumpWatch.TheImmersiveTech.blocks.machines;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import JumpWatch.TheImmersiveTech.Tile.TileEntitiyEletricFurnace;
import JumpWatch.TheImmersiveTech.blocks.BlockBase;
import JumpWatch.TheImmersiveTech.blocks.specialrender.MFurnace;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.world.IBlockAccess;
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
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Random;

public class BlockElectricFurnace extends BlockBase implements ITileEntityProvider {
    public static final IProperty<Boolean> IS_HANDLES = PropertyBool.create("is_handles");
    public final static String INTERNAL_NAME = "electric_furnace";

    public BlockElectricFurnace() {
        super(Material.IRON, INTERNAL_NAME);
        setUnlocalizedName(INTERNAL_NAME);
        setDefaultState(blockState.getBaseState().withProperty(IS_HANDLES, false));
    }
    @SideOnly(Side.CLIENT)
    public void initModel() {

    }
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(IS_HANDLES, false);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, IS_HANDLES);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitiyEletricFurnace();
    }

    /*@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);

        if (!worldIn.isAreaLoaded(pos, 1)) return;
        if (TileEntitiyEletricFurnace.cookTime > 0 && TileEntitiyEletricFurnace.cookTime < 101) {
            state = state.withProperty());
            worldIn.setBlockState(pos, , 2);
        }
    }*/
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileEntitiyEletricFurnace) {
                playerIn.openGui(TheImmersiveTech.MODID, 4, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
        TileEntitiyEletricFurnace tileentity = (TileEntitiyEletricFurnace) worldIn.getTileEntity(pos);
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(1)));
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(2)));
        super.breakBlock(worldIn, pos, state);
    }
}