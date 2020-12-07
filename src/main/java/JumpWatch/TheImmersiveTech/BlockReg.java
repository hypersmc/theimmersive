package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.Tile.TileEntitiyEletricFurnace;
import JumpWatch.TheImmersiveTech.Tile.TileEntityElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import JumpWatch.TheImmersiveTech.blocks.machines.*;
import JumpWatch.TheImmersiveTech.utils.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(TheImmersiveTech.MODID)
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


    //Others
    @GameRegistry.ObjectHolder(BlockElectricCrusher.INTERNAL_NAME)
    private static BlockElectricCrusher blockElectricCrusher;
    @GameRegistry.ObjectHolder(BlockElectricFurnace.INTERNAL_NAME)
    private static BlockElectricFurnace blockElectricFurnace;

    //regs
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockElectricFurnace());
        event.getRegistry().register(new BlockElectricCrusher());

    }

    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerTileEntity(TileEntitiyEletricFurnace.class, new ResourceLocation(TheImmersiveTech.MODID, "electric_furnace"));
        GameRegistry.registerTileEntity(TileEntityElectricCrusher.class, new ResourceLocation(TheImmersiveTech.MODID, "electric_crusher"));
    }
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(blockElectricFurnace.createItemBlock());
        event.getRegistry().register(blockElectricCrusher.createItemBlock());
    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModels(ModelRegistryEvent event) {
        blockElectricFurnace.registerItemModel(Item.getItemFromBlock(blockElectricFurnace));
        blockElectricCrusher.registerItemModel(Item.getItemFromBlock(blockElectricCrusher));

    }


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