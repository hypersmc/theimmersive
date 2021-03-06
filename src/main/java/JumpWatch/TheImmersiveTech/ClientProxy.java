package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockPort;
import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockType;
import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockWall;
import JumpWatch.TheImmersiveTech.Tile.TileEntitiyEletricFurnace;
import JumpWatch.TheImmersiveTech.Tile.TileEntityElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.machines.BlockElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.machines.BlockElectricFurnace;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerTileEntity(TileEntitiyEletricFurnace.class, new ResourceLocation(TheImmersiveTech.MODID, "electric_furnace"));
        GameRegistry.registerTileEntity(TileEntityElectricCrusher.class, new ResourceLocation(TheImmersiveTech.MODID, "electric_crusher"));
        //GameRegistry.registerTileEntity(MBFurnaceTileEntity.class, new ResourceLocation(TheImmersiveTech.MODID, "mb_furnace"));
        helplogger.info("this worked2");
        OBJLoader.INSTANCE.addDomain(TheImmersiveTech.MODID);
    }
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockElectricFurnace());
        event.getRegistry().register(new BlockElectricCrusher());
        event.getRegistry().register(new MBFurnaceBlockWall("mbfurnaceWall"));
        event.getRegistry().register(new MBFurnaceBlockPort("mbfurnacepowerport", MBFurnaceBlockType.Power));
        event.getRegistry().register(new MBFurnaceBlockPort("mbfurnaceoutputport", MBFurnaceBlockType.Output));
        event.getRegistry().register(new MBFurnaceBlockPort("mbfurnaceinputport", MBFurnaceBlockType.Input));
        event.getRegistry().register(new MBFurnaceBlockPort("mbfurnacefluidport", MBFurnaceBlockType.Fluid));
        helplogger.info("this worked6");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.optic_cable), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":optic_cable", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.fluid_cable), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":fluid_cable", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.item_cable), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":item_cable", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(TheImmersiveTech.Solar_PanelC), 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":solarpanelcontroller", "inventory"));
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(TheImmersiveTech.voltmeter, 0, new ModelResourceLocation(TheImmersiveTech.MODID + ":voltmeter", "inventory"));
        BlockReg.initModels();
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
