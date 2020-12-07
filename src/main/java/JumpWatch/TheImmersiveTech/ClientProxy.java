package JumpWatch.TheImmersiveTech;

import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        super.preinit(event);
        helplogger.info("this worked2");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.optic_cable), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":optic_cable", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.fluid_cable), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":fluid_cable", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.item_cable), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":item_cable", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.Solar_PanelC), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":solarpanelcontroller", "inventory"));
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(TheImmersiveTech.voltmeter, 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":voltmeter", "inventory"));
    }
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        BlockReg.initModels();
        //ItemReg.initModels();
    }
    @Override
    public void postinit(FMLPostInitializationEvent event) {
        super.postinit(event);
    }
}
