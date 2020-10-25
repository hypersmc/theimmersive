package JumpWatch.TheImmersiveTech.blocks.furnace;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FurnaceBlock extends Block {

    public static final ResourceLocation EFURNACE = new ResourceLocation(TheImmersiveTech.MODID, "efurnace");
    public FurnaceBlock() {
        super(Material.IRON);
        setRegistryName(EFURNACE);
        setUnlocalizedName(TheImmersiveTech.MODID + ".efurnace");
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(TheImmersiveTech.TITBlocks);

    }
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
