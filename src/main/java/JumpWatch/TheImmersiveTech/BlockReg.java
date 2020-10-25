package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockReg {

    @GameRegistry.ObjectHolder("theimmersivetech:efurnace")
    public static FurnaceBlock furnaceBlock;

    @SideOnly(Side.CLIENT)
    public static void initModels(){
        furnaceBlock.initModel();
    }
}