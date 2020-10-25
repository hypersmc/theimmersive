package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import JumpWatch.hypercore.cabels.tileentities.CableTileEntity;
import JumpWatch.hypercore.cabels.tileentities.FluidCableTileEntiity;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
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


    }
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(TheImmersiveTech.optic_cable);
        event.getRegistry().registerAll(TheImmersiveTech.fluid_cable);
        event.getRegistry().register(new FurnaceBlock());
        GameRegistry.registerTileEntity(CableTileEntity.class, "optic_cable");
        GameRegistry.registerTileEntity(FluidCableTileEntiity.class, "fluid_cable");
        helplogger.info("This worked!");
    }
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TheImmersiveTech.ioptic_cable);
        event.getRegistry().registerAll(TheImmersiveTech.ifluid_cable);
        event.getRegistry().register(new ItemBlock(BlockReg.furnaceBlock).setRegistryName(FurnaceBlock.EFURNACE));
    }
    public void init(FMLInitializationEvent event) {

    }

    public void postinit(FMLPostInitializationEvent event) {

    }
}
