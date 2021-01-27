package JumpWatch.TheImmersiveTech.Multiblock;

import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnacePowerTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceTileEntity;
import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import com.google.common.collect.Lists;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.lib.BlockFacings;
import it.zerono.mods.zerocore.lib.PropertyBlockFacings;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MBFurnaceBlockWall extends MBFurnaceBlockBase{
    public final static String INTERNAL_NAME = "mbfurnacewall";
    public static final ResourceLocation MBF = new ResourceLocation(TheImmersiveTech.MODID, "mb_furnace");

    public MBFurnaceBlockWall(String name) {
        super(name, MBFurnaceBlockType.Wall);
        setUnlocalizedName("mbfurnacewall");
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        tooltip.add("Furnace Wall block");
        tooltip.add("");
        tooltip.add("Used to make a 3x3 multiblock.");
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
                playerIn.openGui(TheImmersiveTech.MODID, 6, worldIn, pos.getX(), pos.getY(), pos.getZ());

                //playerIn.openGui(TheImmersiveTech.MODID, 6, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
            if (part.getPartPosition().getType().equals(MBFurnaceBlockType.Wall)){
                playerIn.sendMessage(new TextComponentString("test"));
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
        return new BlockStateContainer(this, FACES);
    }
    @Override
    public void initBlock(){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":mbfurnacewall", "inventory"));
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
