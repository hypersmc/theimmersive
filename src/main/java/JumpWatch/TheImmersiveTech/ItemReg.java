package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.items.oreitems.CopperIngot;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemReg {

    @GameRegistry.ItemStackHolder("theimmersivetech:copperingot")
    public static CopperIngot copperingot;
    @SideOnly(Side.CLIENT)
    public static void initModels(){
        copperingot.initModel();

    }
}
