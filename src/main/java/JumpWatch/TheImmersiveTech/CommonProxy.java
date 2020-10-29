package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import JumpWatch.TheImmersiveTech.blocks.ores.CopperOre;
import JumpWatch.hypercore.cabels.tileentities.CableTileEntity;
import JumpWatch.hypercore.cabels.tileentities.FluidCableTileEntiity;
import JumpWatch.hypercore.cabels.tileentities.ItemCableTileEntitiy;
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
        GameRegistry.registerTileEntity(ItemCableTileEntitiy.class, "item_cable");


    }
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(TheImmersiveTech.optic_cable);
        event.getRegistry().registerAll(TheImmersiveTech.fluid_cable);
        event.getRegistry().registerAll(TheImmersiveTech.item_cable);
        event.getRegistry().register(new FurnaceBlock());
        GameRegistry.registerTileEntity(CableTileEntity.class, "optic_cable");
        GameRegistry.registerTileEntity(FluidCableTileEntiity.class, "fluid_cable");
        GameRegistry.registerTileEntity(ItemCableTileEntitiy.class, "item_cable");
        helplogger.info("This worked!");
    }
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TheImmersiveTech.ioptic_cable);
        event.getRegistry().registerAll(TheImmersiveTech.ifluid_cable);
        event.getRegistry().registerAll(TheImmersiveTech.iitem_cable);
        event.getRegistry().register(new ItemBlock(BlockReg.furnaceBlock).setRegistryName(FurnaceBlock.EFURNACE));
        event.getRegistry().register(new ItemBlock(BlockReg.copperOre).setRegistryName(CopperOre.COre));
        event.getRegistry().registerAll(TheImmersiveTech.iCopper);
    }
    public void init(FMLInitializationEvent event) {

    }

    public void postinit(FMLPostInitializationEvent event) {

    }
}
