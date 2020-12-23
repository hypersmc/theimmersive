package JumpWatch.TheImmersiveTech.Multiblock.Tile;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class MBFurnaceFluidIOPortTileEntitiy extends MBFurnaceTileEntity implements IFluidTank, IFluidHandler {
    @Override
    public FluidStack getFluid() {
        return null;
    }

    @Override
    public int getFluidAmount() {
        return 0;
    }

    @Override
    public int getCapacity() {
        return 10000;
    }

    @Override
    public FluidTankInfo getInfo() {
        return null;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return fill(resource, doFill);
    }

    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return drain(resource, doDrain);
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return drain(maxDrain, doDrain);
    }
}
