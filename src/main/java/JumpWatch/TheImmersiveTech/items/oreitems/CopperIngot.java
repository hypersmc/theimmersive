package JumpWatch.TheImmersiveTech.items.oreitems;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CopperIngot extends Item {
    public static final ResourceLocation CItem = new ResourceLocation(TheImmersiveTech.MODID, "CopperItem");
    public CopperIngot(){
        setRegistryName(CItem);
        setUnlocalizedName(TheImmersiveTech.MODID + ".CopperItem");
        setCreativeTab(TheImmersiveTech.TITITems);
    }
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
