package JumpWatch.TheImmersiveTech.blocks.Containers;

import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceTileEntity;
import JumpWatch.TheImmersiveTech.Tile.TileEntitiyEletricFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMBElectricFurnace extends Container {
    private final MBFurnaceTileEntity tileentity;
    private int cookTime, energy;
    public ContainerMBElectricFurnace(InventoryPlayer player, MBFurnaceTileEntity tileentitiy) {
        this.tileentity = tileentitiy;
        IItemHandler handler = tileentitiy.handler;
        this.addSlotToContainer(new SlotItemHandler(handler, 0, 44, 21));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 44, 50));
        this.addSlotToContainer(new SlotItemHandler(handler, 2, 97, 36));
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileentity.isUsableByPlayer(playerIn);
    }

    @Override
    public void updateProgressBar(int id, int data) {
        this.tileentity.setField(id, data);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener listener = (IContainerListener) this.listeners.get(i);
            if (this.cookTime != this.tileentity.getField(0))
                listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
            if (this.energy != this.tileentity.getField(1))
                listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
        }
        this.cookTime = this.tileentity.getField(0);
        this.energy = this.tileentity.getField(1);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 3) {
                if (!this.mergeItemStack(itemstack1, 3, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (!this.mergeItemStack(itemstack1, 0, 3, false)) {
                if (index < 3 + 27) {
                    if (!this.mergeItemStack(itemstack1, 3 + 27, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.mergeItemStack(itemstack1, 3, 3 + 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }
}