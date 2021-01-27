package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockPort;
import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockType;
import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockWall;
import JumpWatch.TheImmersiveTech.blocks.furnace.FurnaceBlock;
import JumpWatch.TheImmersiveTech.blocks.machines.*;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

    @GameRegistry.ObjectHolder(MBFurnaceBlockWall.INTERNAL_NAME)
    public static MBFurnaceBlockWall mbFurnaceBlockWall;
    @GameRegistry.ObjectHolder(MBFurnaceBlockPort.INTERNAL_NAME)
    public static MBFurnaceBlockPort mbFurnaceBlockPort;
    @GameRegistry.ObjectHolder(MBFurnaceBlockPort.INTERNAL_NAME2)
    public static MBFurnaceBlockPort mbFurnaceBlockPort2;
    @GameRegistry.ObjectHolder(MBFurnaceBlockPort.INTERNAL_NAME3)
    public static MBFurnaceBlockPort mbFurnaceBlockPort3;
    @GameRegistry.ObjectHolder(MBFurnaceBlockPort.INTERNAL_NAME4)
    public static MBFurnaceBlockPort mbFurnaceBlockPort4;

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        helplogger.info("this worked11");
        event.getRegistry().register(blockElectricFurnace.createItemBlock());
        event.getRegistry().register(blockElectricCrusher.createItemBlock());
    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModels(ModelRegistryEvent event) {
        helplogger.info("this worked12");
        blockElectricFurnace.registerItemModel(Item.getItemFromBlock(blockElectricFurnace));
        blockElectricCrusher.registerItemModel(Item.getItemFromBlock(blockElectricCrusher));

    }

    public void init(FMLInitializationEvent event){
        helplogger.info("This worked9!");
    }

    public static final MBFurnaceBlockWall MB_FURNACE_BLOCK_WALL = new MBFurnaceBlockWall("mbfurnaceWall");
    public static final MBFurnaceBlockPort MB_FURNACE_BLOCK_PowerPORT = new MBFurnaceBlockPort("mbfurnacepowerport", MBFurnaceBlockType.Power);
    public static final MBFurnaceBlockPort MB_FURNACE_BLOCK_InputPORT = new MBFurnaceBlockPort("mbfurnaceoutputport", MBFurnaceBlockType.Output);
    public static final MBFurnaceBlockPort MB_FURNACE_BLOCK_OutputPORT = new MBFurnaceBlockPort("mbfurnaceinputport", MBFurnaceBlockType.Input);
    public static final MBFurnaceBlockPort MB_FURNACE_BLOCK_FluidPORT = new MBFurnaceBlockPort("mbfurnacefluidport", MBFurnaceBlockType.Fluid);

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