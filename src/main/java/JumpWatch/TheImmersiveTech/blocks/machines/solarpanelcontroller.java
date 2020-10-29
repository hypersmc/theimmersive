package JumpWatch.TheImmersiveTech.blocks.machines;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class solarpanelcontroller extends Block {

    public static final ResourceLocation solarpanelcontroller = new ResourceLocation(TheImmersiveTech.MODID, "solarpanelcontroller");
    public solarpanelcontroller() {
        super(Material.IRON);
        setRegistryName(solarpanelcontroller);
        setUnlocalizedName(TheImmersiveTech.MODID + ".solarpanelcontroller");
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(TheImmersiveTech.TITBlocks);

    }
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
