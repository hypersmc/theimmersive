package JumpWatch.TheImmersiveTech;

import JumpWatch.TheImmersiveTech.items.cabels.FluidCable;
import JumpWatch.TheImmersiveTech.items.cabels.ItemFluidCable;
import JumpWatch.TheImmersiveTech.items.cabels.ItemOpticCable;
import JumpWatch.TheImmersiveTech.items.cabels.OpticCable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TheImmersiveTech.MODID, name = TheImmersiveTech.NAME, version = TheImmersiveTech.VERSION, dependencies = "required-after:hypercore;")
public class TheImmersiveTech {
    @SidedProxy(clientSide = "JumpWatch.TheImmersiveTech.ClientProxy", serverSide = "JumpWatch.TheImmersiveTech.ServerProxy")
    public static CommonProxy proxy;
    public static final String MODID = "theimmersivetech";
    public static final String NAME = "TheImmersiveTech";
    public static final String VERSION = "1.0";

    public static CreativeTabs TITBlocks = new CreativeTabs("TITBlocks") {
        @Override
        public ItemStack getTabIconItem() { return new ItemStack(BlockReg.furnaceBlock); } };

    static Block optic_cable = new OpticCable(Material.CLOTH)
            .setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
            .setUnlocalizedName("optic_cable")
            .setRegistryName("optic_cable");
    static Item ioptic_cable = new ItemOpticCable(optic_cable)
            .setRegistryName("optic_cable")
            .setUnlocalizedName("optic_cable");


    static Block fluid_cable = new FluidCable(Material.CLOTH)
            .setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
            .setUnlocalizedName("fluid_cable")
            .setRegistryName("fluid_cable");
    static Item ifluid_cable = new ItemFluidCable(fluid_cable)
            .setRegistryName("fluid_cable")
            .setUnlocalizedName("fluid_cable");
    /*static Item voltmeter = new ItemVoltmeter()
    *        .setRegistryName("voltmeter")
    *        .setUnlocalizedName("voltmeter")
    *        .setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
    *        .setMaxStackSize(1);
    */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preinit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

}