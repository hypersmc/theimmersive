package JumpWatch.TheImmersiveTech.Tile;

import JumpWatch.TheImmersiveTech.blocks.FluidTank;
import JumpWatch.TheImmersiveTech.blocks.guis.GuiElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.machines.BlockElectricCrusher;
import JumpWatch.TheImmersiveTech.blocks.recipes.CrusherRecipes;
import JumpWatch.TheImmersiveTech.utils.ModSettings;

import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.energy.IEnergyStorage;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ITickable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

public class TileEntityElectricCrusher extends TileEntity implements ITickable, IEnergyStorage {
    int tick;
    public int energy = 0;
    public int storage = ModSettings.crusherProperties.Crushercapacity;
    public int forevery = ModSettings.crusherProperties.foreach;
    public int lube = ModSettings.crusherProperties.lube;
    public ItemStackHandler handler = new ItemStackHandler(2);
    private String customName;
    protected FluidStack fluid;
    public static int cookTime;
    protected int capacity = 0;
    private ItemStack smelting = ItemStack.EMPTY;
    public FluidTank tank = new FluidTank(this, 3000);
    private boolean needsUpdate = false;
    public int dontbother;
    private int updateTimer = 0;
    protected boolean canFill = true;
    CrusherRecipes.CrusherRecipe ch;

    @Override
    public void update() {
        // No energy, no work
        if(energy < forevery) return;
        // No fluid, no work
        if (tank.getFluidAmount() < lube) return;
        if(canProcess()){
            processTick();
        }
    }


    public boolean canProcess(){
        // No input
        if(handler.getStackInSlot(0).isEmpty()){
            //Make the progress bar go down if slot is empty and there was progress.
            if (!(cookTime == 0)) cookTime--;
            return false;
        }
        // No recipe
        CrusherRecipes.CrusherRecipe recipe = CrusherRecipes.findRecipe(handler.getStackInSlot(0));
        if(recipe == null) return false; //this line

        ItemStack outputSlot = handler.getStackInSlot(1);

        // it is missing
        // Check recipe output matches the content of the output slot
        if(!outputSlot.isEmpty() && !ItemStack.areItemsEqual(recipe.getOutput(), outputSlot)) return false;

        // No space
        if(outputSlot.getCount() + recipe.getOutput().getCount() > outputSlot.getMaxStackSize()) return false;
        // Passes all checks
        return true;
    }

    public void processTick(){
        cookTime++;
        // Spent resources
        energy -= forevery;
        tank.drain(lube, true);

        if (cookTime == 100) {
            cookTime = 0;
            // Process finished
            doProcess();
        }
    }

    public void doProcess(){
        handler.insertItem(1, CrusherRecipes.findRecipe(handler.getStackInSlot(0)).getOutput(), false); //When i was trying on my own this was the line the system complained about after.
        handler.extractItem(0, 1, false);

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability != null && capability.getName() == "net.minecraftforge.energy.IEnergyStorage")
            return (T) this;
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return (T) tank;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability != null && capability.getName() == "net.minecraftforge.energy.IEnergyStorage")
            return true;
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.handler.serializeNBT());
        compound.setInteger("CookTime", cookTime);
        compound.setInteger("GuiEnergy", energy);
        compound.setString("Name", getDisplayName().toString());
        compound.setInteger("EnergyStored", this.energy);
        tank.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.cookTime = compound.getInteger("CookTime");
        this.energy = compound.getInteger("GuiEnergy");
        tank.readFromNBT(compound);
        if (compound.hasKey("EnergyStored"))
            this.energy = compound.getInteger("EnergyStored");
        if (compound.hasKey("Name"))
            this.customName = compound.getString("Name");
    }


    public int getEnergyStored() {
        return this.energy;
    }

    public int getMaxEnergyStored() {
        return this.storage;
    }

    public boolean canExtract() {
        return false;
    }

    public boolean canReceive() {
        return true;
    }

    @Override
    public int extractEnergy(int extract, boolean simulate) {
        return 0;
    }

    @Override
    public int receiveEnergy(int receive, boolean sumulate) {
        // int amount = (receive <= this.energy) ? receive : this.energy;
        // this.energy += amount;
        // if (this.energy < 0)
        // this.energy = 0;
        int amount = this.energy += receive;
        if (this.energy > storage) {
            this.energy = storage;
        }
        return amount;
        // return this.energy += receive;
        // return ModSettings.blockProperties.inputRate;
    }

    public int getCurrentPower() {
        return energy;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("container.electric_crusher");
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this
                ? false
                : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public int getField(int id) {
        switch (id) {
            case 0 :
                return this.cookTime;
            case 1 :
                return this.energy;
            default :
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0 :
                this.cookTime = value;
                break;
            case 1 :
                this.energy = value;
        }
    }
    //////////////// FLUID PART/////////////////////
    /*
     * public boolean apply(FluidTank tank, IItemHandler inventory, boolean consume)
     * { }
     */
}