package JumpWatch.TheImmersiveTech.blocks;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.model.ModelLoader;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

public class BlockBase extends Block {
    protected String internalName;
    public BlockBase(Material material, String name) {
        super(material);
        this.internalName = name;
        this.setRegistryName(this.internalName);
        this.setHardness(2);
        this.setResistance(5);
        this.setCreativeTab(TheImmersiveTech.TITBlocks);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}
