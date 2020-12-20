package JumpWatch.TheImmersiveTech.Tile;

import JumpWatch.TheImmersiveTech.blocks.recipes.CrusherRecipes;
import JumpWatch.TheImmersiveTech.blocks.recipes.FurnaceRecipes;
import JumpWatch.TheImmersiveTech.utils.ModSettings;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.energy.IEnergyStorage;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ITickable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Random;

public class TileEntitiyEletricFurnace extends TileEntity implements ITickable, IEnergyStorage {
    int tick;
    public int energy = 0;
    public int storage = ModSettings.furnaceProperties.Furnacecapacity;
    public int forevery = ModSettings.furnaceProperties.foreach;
    public ItemStackHandler handler = new ItemStackHandler(3);
    private String customName;
    public static int cookTime;
    public int dontbother;
    private ItemStack smelting = ItemStack.EMPTY;
    @Override
    public void update() {
        tick++;
        if (tick > 20)
            tick = 0;
        if (tick == 0) {
            // System.out.println(Integer.toString(energy));
        }
        if (energy < forevery) return;

        if (canProcess()){
            processTick();
        }

    }

    public boolean canProcess(){
        // No input
        if (handler.getStackInSlot(0).isEmpty() || handler.getStackInSlot(1).isEmpty()){
            //Make the progress bar go down if slot is empty and there was progress.
            if (!(cookTime == 0)) cookTime--;
            return false;
        }

        FurnaceRecipes.FurnaceRecipe recipe = FurnaceRecipes.findRecipe(handler.getStackInSlot(0), handler.getStackInSlot(1));
        if (recipe == null) return false;

        ItemStack outputSlot = handler.getStackInSlot(2);
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
        if (cookTime == 100) {
            cookTime = 0;
            // Process finished
            doProcess();
        }
    }
    public void doProcess(){
        handler.insertItem(2, FurnaceRecipes.findRecipe(handler.getStackInSlot(0), handler.getStackInSlot(1)).getOutput(), false); //When i was trying on my own this was the line the system complained about after.
        handler.extractItem(0, 1, false);
        handler.extractItem(1, 1, false);
    }

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing) {
        if (capability != null && capability.getName() == "net.minecraftforge.energy.IEnergyStorage") {
            return true;
        }
        return super.hasCapability(capability, facing);
    }



    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        if (capability != null && capability.getName() == "net.minecraftforge.energy.IEnergyStorage") {
            return (T) this;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.handler.serializeNBT());
        compound.setInteger("CookTime", cookTime);
        compound.setInteger("GuiEnergy", energy);
        compound.setString("Name", getDisplayName().toString());
        compound.setInteger("EnergyStored", this.energy);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.cookTime = compound.getInteger("CookTime");
        this.energy = compound.getInteger("GuiEnergy");
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
        return new TextComponentTranslation("container.electric_furnace");
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
}