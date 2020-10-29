package JumpWatch.TheImmersiveTech.blocks.ores;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CopperOre extends Block {

    public static final ResourceLocation COre = new ResourceLocation(TheImmersiveTech.MODID, "CopperOre");
    public CopperOre() {
        super(Material.IRON);
        setRegistryName(COre);
        setUnlocalizedName(TheImmersiveTech.MODID + ".CopperOre");
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(TheImmersiveTech.TITBlocks);

    }
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
