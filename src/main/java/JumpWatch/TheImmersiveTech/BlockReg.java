package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import JumpWatch.TheImmersiveTech.blocks.machines.solarpanel;
import JumpWatch.TheImmersiveTech.blocks.machines.solarpanelcontroller;
import JumpWatch.TheImmersiveTech.blocks.ores.CopperOre;
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
    @GameRegistry.ObjectHolder("theimmersivetech:copperore")
    public static CopperOre copperOre;
    @GameRegistry.ObjectHolder("theimmersivetech:solarpanelcontroller")
    public static solarpanelcontroller solarpanelcontroller;
    @GameRegistry.ObjectHolder("theimmersivetech:solarpanel")
    public static solarpanel solarpanel;
    @SideOnly(Side.CLIENT)
    public static void initModels(){
        furnaceBlock.initModel();
        solarpanelcontroller.initModel();
        solarpanel.initModel();
    }
}