package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import JumpWatch.TheImmersiveTech.blocks.machines.solarpanelblock;
import JumpWatch.TheImmersiveTech.blocks.machines.solarpanelbrokenblock;
import JumpWatch.TheImmersiveTech.blocks.machines.solarpanelcontrollerbrokenblock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockReg {
    //working
    @GameRegistry.ObjectHolder("theimmersivetech:efurnace")
    public static FurnaceBlock furnaceBlock;
    @GameRegistry.ObjectHolder("theimmersivetech:solarpanelblock")
    public static solarpanelblock Solarpanel;

    //broken blocks
    @GameRegistry.ObjectHolder("theimmersivetech:solarpanelbrokenblock")
    public static solarpanelbrokenblock spbb;
    @GameRegistry.ObjectHolder("theimmersivetech:solarpanelcontrollerbrokenblock")
    public static solarpanelcontrollerbrokenblock spcbb;


    @SideOnly(Side.CLIENT)
    public static void initModels(){
        //working blocks
        Solarpanel.initModel();
        furnaceBlock.initModel();
        //brocken blocks
        spbb.initModel();
        spcbb.initModel();
    }
}