package JumpWatch.TheImmersiveTech.utils;

import JumpWatch.TheImmersiveTech.Ids;
import JumpWatch.TheImmersiveTech.Tile.TileEntitiyEletricFurnace;
import JumpWatch.TheImmersiveTech.Tile.TileEntityElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.Containers.ContainerElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.Containers.ContainerElectricFurnace;
import JumpWatch.TheImmersiveTech.blocks.guis.GuiElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.guis.GuiElectricFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Ids.GUI_ELECTRIC_FURNACE)
            return new ContainerElectricFurnace(player.inventory, (TileEntitiyEletricFurnace) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Ids.GUI_ELECTRIC_CRUSHER)
            return new ContainerElectricCrusher(player.inventory, (TileEntityElectricCrusher) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Ids.GUI_ELECTRIC_FURNACE)
            return new GuiElectricFurnace(player.inventory, (TileEntitiyEletricFurnace) world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Ids.GUI_ELECTRIC_CRUSHER)
            return new GuiElectricCrusher(player.inventory, (TileEntityElectricCrusher) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}
