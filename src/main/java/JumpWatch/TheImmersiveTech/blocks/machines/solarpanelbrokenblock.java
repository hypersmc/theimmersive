package JumpWatch.TheImmersiveTech.blocks.machines;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class solarpanelbrokenblock extends Block {

    public static final ResourceLocation SPanelB = new ResourceLocation(TheImmersiveTech.MODID, "solarpanelbrokenblock");
    public solarpanelbrokenblock() {
        super(Material.IRON);
        setRegistryName(SPanelB);
        setUnlocalizedName(TheImmersiveTech.MODID + ".solarpanelbrokenblock");
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(TheImmersiveTech.TITBlocks);

    }
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }
}
