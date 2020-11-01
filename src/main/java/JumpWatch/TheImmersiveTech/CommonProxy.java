package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.Tile.SolarControllerTileEntity;
import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import JumpWatch.TheImmersiveTech.blocks.machines.solarpanelblock;
import JumpWatch.hypercore.cabels.tileentities.CableTileEntity;
import JumpWatch.hypercore.cabels.tileentities.FluidCableTileEntiity;
import JumpWatch.hypercore.cabels.tileentities.ItemCableTileEntitiy;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
@Mod.EventBusSubscriber
public class CommonProxy {
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerTileEntity(CableTileEntity.class, "optic_cable");
        GameRegistry.registerTileEntity(FluidCableTileEntiity.class, "fluid_cable");
        GameRegistry.registerTileEntity(ItemCableTileEntitiy.class, "item_cable");
        GameRegistry.registerTileEntity(SolarControllerTileEntity.class, "solarpanelcontroller");


    }
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(TheImmersiveTech.optic_cable);
        event.getRegistry().registerAll(TheImmersiveTech.fluid_cable);
        event.getRegistry().registerAll(TheImmersiveTech.item_cable);
        event.getRegistry().registerAll(TheImmersiveTech.Solar_PanelC);
        event.getRegistry().register(new FurnaceBlock());
        event.getRegistry().register(new solarpanelblock());
        GameRegistry.registerTileEntity(CableTileEntity.class, "optic_cable");
        GameRegistry.registerTileEntity(FluidCableTileEntiity.class, "fluid_cable");
        GameRegistry.registerTileEntity(ItemCableTileEntitiy.class, "item_cable");
        GameRegistry.registerTileEntity(SolarControllerTileEntity.class, "solarpanelcontroller");
        helplogger.info("This worked!");
    }
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TheImmersiveTech.ioptic_cable);
        event.getRegistry().registerAll(TheImmersiveTech.ifluid_cable);
        event.getRegistry().registerAll(TheImmersiveTech.iitem_cable);
        event.getRegistry().registerAll(TheImmersiveTech.ISolar_PanelC);
        event.getRegistry().register(new ItemBlock(BlockReg.furnaceBlock).setRegistryName(FurnaceBlock.EFURNACE));
        event.getRegistry().register(new ItemBlock(BlockReg.Solarpanel).setRegistryName(solarpanelblock.SPanel));
        //event.getRegistry().registerAll(TheImmersiveTech.iCopper);
    }
    public void init(FMLInitializationEvent event) {

    }

    public void postinit(FMLPostInitializationEvent event) {

    }
}
