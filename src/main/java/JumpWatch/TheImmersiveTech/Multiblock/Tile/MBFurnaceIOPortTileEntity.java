package JumpWatch.TheImmersiveTech.Multiblock.Tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class MBFurnaceIOPortTileEntity extends MBFurnaceTileEntity implements IInventory, ISidedInventory {
    protected boolean _isInput;
    protected ItemStack _inventory;
    // save/load state

    public MBFurnaceIOPortTileEntity(boolean isInput){
        this._isInput = isInput;
        this._inventory = null;
    }


    @Override
    public void readFromNBT(NBTTagCompound data) {

        super.readFromNBT(data);

        if (data.hasKey("mfIOdir"))
            this._isInput = data.getBoolean("mfIOdir");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {

        super.writeToNBT(data);
        data.setBoolean("mfIOdir", this._isInput);
        return data;
    }
    public boolean isInput() {

        return this._isInput;
    }



    // IInventory

    @Override
    public void clear() {

        this._inventory = null;
    }

    @Override
    public int getSizeInventory() {

        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        return 0 == index ? this._inventory : null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }


    public ItemStack getStackInSlotOnClosing(int index) {

        ItemStack itemStack = this.getStackInSlot(index);

        if (itemStack != null)
            this.setInventorySlotContents(index, null);

        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if (0 == index)
            this._inventory = stack;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return true;
    }

    @Override
    public int getField(int id) {

        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public String getName() {

        return "te.mightyFurnaceIO";
    }

    @Override
    public boolean hasCustomName() {

        return false;
    }


    // ISidedInventory

    @Override
    public int[] getSlotsForFace(EnumFacing side) {

        return new int[0];
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return false;
    }
}
