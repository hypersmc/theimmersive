package JumpWatch.TheImmersiveTech.blocks.machines;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class solarpanelcontroller extends Block {

    public solarpanelcontroller(Material materialIn) {
        super(Material.LEAVES);
        this.setSoundType(SoundType.METAL);
        this.fullBlock = true;
        this.setLightOpacity(0);
        this.setHardness(0.2f);
        this.setHarvestLevel("shears", 0);
    }
}
