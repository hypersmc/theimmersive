package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.blocks.recipes.CrusherRecipes;
import JumpWatch.TheImmersiveTech.blocks.recipes.FurnaceRecipes;
import JumpWatch.TheImmersiveTech.items.SolarPanelController;
import JumpWatch.TheImmersiveTech.items.cabels.*;
import JumpWatch.TheImmersiveTech.items.itemsolarpanelcontroller;
import JumpWatch.TheImmersiveTech.utils.GuiHandler;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = TheImmersiveTech.MODID, name = TheImmersiveTech.NAME, version = TheImmersiveTech.VERSION, dependencies = "required-after:hypercore;required-after:zerocore;")
public class TheImmersiveTech {
    @SidedProxy(clientSide = "JumpWatch.TheImmersiveTech.ClientProxy", serverSide = "JumpWatch.TheImmersiveTech.ServerProxy")
    public static CommonProxy proxy;
    public static final String MODID = "theimmersivetech";
    public static final String NAME = "theimmersivetech";
    public static final String VERSION = "1.0";
    public static TheImmersiveTech instance;
    public static CreativeTabs TITBlocks = new CreativeTabs("TITBlocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Solar_PanelC);
        }
    };
    /*public static CreativeTabs TITITems = new CreativeTabs("TITItems") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemReg.copperingot);
        }
    };*/

    static Block optic_cable = new OpticCable(Material.CLOTH)
            .setCreativeTab(TheImmersiveTech.TITBlocks)
            .setUnlocalizedName("optic_cable")
            .setRegistryName("optic_cable");
    static Item ioptic_cable = new ItemOpticCable(optic_cable)
            .setRegistryName("optic_cable")
            .setUnlocalizedName("optic_cable");

    public static Block Solar_PanelC = new SolarPanelController(Material.IRON)
            .setUnlocalizedName("solarpanelcontroller")
            .setRegistryName("solarpanelcontroller");
    public static Item ISolar_PanelC = new itemsolarpanelcontroller(Solar_PanelC)
            .setRegistryName("solarpanelcontroller")
            .setUnlocalizedName("solarpanelcontroller");


    static Block fluid_cable = new FluidCable(Material.CLOTH)
            .setCreativeTab(TheImmersiveTech.TITBlocks)
            .setUnlocalizedName("fluid_cable")
            .setRegistryName("fluid_cable");
    static Item ifluid_cable = new ItemFluidCable(fluid_cable)
            .setRegistryName("fluid_cable")
            .setUnlocalizedName("fluid_cable");

    static Block item_cable = new ItemCable(Material.CLOTH)
            .setCreativeTab(TheImmersiveTech.TITBlocks)
            .setUnlocalizedName("item_cable")
            .setRegistryName("item_cable");
    static Item iitem_cable = new ItemItemCable(item_cable)
            .setRegistryName("item_cable")
            .setUnlocalizedName("item_cable");
    /*static Item iCopper = new CopperIngot()
            .setUnlocalizedName("copperingot");

    /*static Item voltmeter = new ItemVoltmeter()
    *        .setRegistryName("voltmeter")
    *        .setUnlocalizedName("voltmeter")
    *        .setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
    *        .setMaxStackSize(1);
    */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        CrusherRecipes.CrusherRecipes();
        FurnaceRecipes.FurnaceRecipes();
        proxy.preinit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        helplogger.info("This worked9!");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        helplogger.info("This worked10!");
        proxy.init(event);

    }

}
