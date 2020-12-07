package JumpWatch.TheImmersiveTech.blocks;

import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.Fluid;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class FluidTank implements IFluidTank, IFluidHandler, IFluidTankProperties {
    /**
     * The contained fluid.
     */
    protected FluidStack fluid;
    /**
     * The tanks capacity.
     */
    protected int capacity;
    /**
     * The TileEntity containing this tank.
     */
    protected TileEntity containerTile;
    /**
     * Holds the wrapper providing tank properties for the IFluidHandler interface.
     */
    protected IFluidTankProperties[] tankProperties = new IFluidTankProperties[]{this};
    /**
     * Creates a new instance with the supplied capacity.
     *
     * @param tile
     *            The TileEntity holding the tank.
     * @param capacity
     *            The capacity of the tank.
     */
    public FluidTank(TileEntity tile, int capacity) {
        this(tile, null, capacity);
    }

    /**
     * Creates a new instance with the supplied capacity and fluid.
     *
     * @param tile
     *            The TileEntity holding the tank.
     * @param stack
     *            The fluid the tank should be initially filled with.
     * @param capacity
     *            The capacity of the tank.
     */
    public FluidTank(TileEntity tile, FluidStack stack, int capacity) {
        containerTile = tile;
        setCapacity(capacity);
        setFluid(stack);
    }

    /**
     * Creates a new instance with the supplied capacity, fluid type and amount.
     *
     * @param tile
     *            The TileEntity holding the tank.
     * @param fluid
     *            The type of fluid initially contained in the tank.
     * @param amount
     *            The initial amount of the contained fluid.
     * @param capacity
     *            The capacity of the tank.
     */
    public FluidTank(TileEntity tile, Fluid fluid, int amount, int capacity) {
        this(tile, new FluidStack(fluid, amount), capacity);
    }

    public FluidTank readFromNBT(NBTTagCompound nbt) {
        setCapacity(nbt.getInteger("Capacity"));
        FluidStack loadedFluid = (nbt.hasKey("Empty")) ? null : FluidStack.loadFluidStackFromNBT(nbt);
        setFluid(loadedFluid);
        return this;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Capacity", capacity);
        if (fluid != null) {
            fluid.writeToNBT(nbt);
        } else {
            nbt.setString("Empty", "");
        }
        return nbt;
    }

    /**
     * Sets the type and amount of fluid contained in the tank.
     *
     * @param fluid
     *            The fluid that should be contained in the tank.<br>
     *            Note that the amount will be limited to the tanks capacity.
     */
    public void setFluid(FluidStack fluid) {
        if (fluid != null) {
            // limit the stored fluid to the tanks capacity
            fluid.amount = Math.min(fluid.amount, capacity);
        }
        this.fluid = fluid;
    }

    /**
     * Sets the capacity of the tank.
     *
     * @param capacity
     *            The capacity the tank should have.<br>
     *            Values smaller than 0 are ignored.
     */
    public void setCapacity(int capacity) {
        // negative capacity makes no sense
        this.capacity = (capacity >= 0) ? capacity : 0;
        // limit the fluid amount to the new capacity
        if (fluid != null) {
            fluid.amount = Math.min(this.capacity, fluid.amount);
        }
    }

    /**
     * Gets the remaining capacity of the tank.
     *
     * @return The remaining amount of fluid the tank can take in until it is full.
     */
    public int getRemainingCapacity() {
        return getCapacity() - getFluidAmount();
    }

    /**
     * Checks if the tank is full.
     *
     * @return <code>true</code> if the tank is full, otherwise <code>false</code>.
     */
    public boolean isFull() {
        return getRemainingCapacity() == 0;
    }

    // region IFluidTank
    @Nullable
    @Override
    public FluidStack getFluid() {
        return fluid;
    }

    @Override
    public int getFluidAmount() {
        return (fluid != null) ? fluid.amount : 0;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public FluidTankInfo getInfo() {
        return new FluidTankInfo(this);
    }

    @Override
    public int fill(FluidStack fillFluid, boolean doFill) {
        if (fillFluid == null || fillFluid.amount <= 0)
            return 0;
        if (fluid == null) {
            if (!doFill) {
                return Math.min(capacity, fillFluid.amount);
            }
            fluid = new FluidStack(fillFluid, Math.min(capacity, fillFluid.amount));
            if (containerTile != null)
                FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, containerTile.getWorld(), containerTile.getPos(), this, fluid.amount));
            return fluid.amount;
        }
        if (!fluid.isFluidEqual(fillFluid)) {
            return 0;
        }
        int fillAmount = Math.min(capacity - fluid.amount, fillFluid.amount);
        if (doFill && fillAmount > 0) {
            fluid.amount += fillAmount;
            if (containerTile != null)
                FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, containerTile.getWorld(), containerTile.getPos(), this, fillAmount));
        }
        return fillAmount;
    }

    @Nullable
    @Override
    public FluidStack drain(int drainAmount, boolean doDrain) {
        if (fluid == null || drainAmount <= 0)
            return null;
        if (fluid.amount < drainAmount) {
            drainAmount = fluid.amount;
        }
        FluidStack drainedFluid = new FluidStack(fluid, drainAmount);
        if (doDrain) {
            fluid.amount -= drainAmount;
            if (fluid.amount == 0) {
                fluid = null;
            }
            if (containerTile != null)
                FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, containerTile.getWorld(), containerTile.getPos(), this, drainAmount));
        }
        return drainedFluid;
    }

    // endregion
    // region IFluidTankProperties
    @Nullable
    @Override
    public FluidStack getContents() {
        return (fluid == null) ? null : fluid.copy();
    }

    /*
     * These values have nothing to do with the current contents of the tank. They
     * are just filters for what could potentially go into the tank. We want to be
     * able to store any fluid, so true everywhere it is.
     */
    @Override
    public boolean canFill() {
        return true;
    }

    @Override
    public boolean canDrain() {
        return true;
    }

    @Override
    public boolean canFillFluidType(FluidStack fluidStack) {
        return true;
    }

    @Override
    public boolean canDrainFluidType(FluidStack fluidStack) {
        return true;
    }

    // endregion
    // region IFluidHandler
    @Override
    public IFluidTankProperties[] getTankProperties() {
        return tankProperties;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack fluidToDrain, boolean doDrain) {
        if (fluidToDrain == null || fluid == null || !fluidToDrain.isFluidEqual(fluid))
            return null;
        return drain(fluidToDrain.amount, doDrain);
    }
    // endregion
}