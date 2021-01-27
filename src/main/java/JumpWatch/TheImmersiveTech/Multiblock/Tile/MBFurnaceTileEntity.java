package JumpWatch.TheImmersiveTech.Multiblock.Tile;

import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockBase;
import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockPort;
import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceBlockWall;
import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceController;
import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import JumpWatch.TheImmersiveTech.blocks.recipes.FurnaceRecipes;
import JumpWatch.TheImmersiveTech.utils.ModSettings;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockTileEntityBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.ItemStackHandler;

public class MBFurnaceTileEntity extends RectangularMultiblockTileEntityBase implements ITickable {
    public static int energy = 0;
    public static int cookTime;
    int tick;
    public static int storage = ModSettings.furnaceProperties.Furnacecapacity;
    public int forevery = ModSettings.furnaceProperties.foreach;
    public ItemStackHandler handler = new ItemStackHandler(3);
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this
                ? false
                : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }
    @Override
    public void update() {
        tick++;
        if (tick > 20)
            tick = 0;
        if (tick == 0) {

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
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("container.mbfurnace");
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
    public int getEnergyStored() {
        return this.energy;
    }
    public int getMaxEnergyStored() {
        return this.storage;
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



    @Override
    public boolean isGoodForFrame(IMultiblockValidator iMultiblockValidator){
        return true;
    }

    @Override
    public boolean isGoodForSides(IMultiblockValidator iMultiblockValidator) {
        return true;
    }

    @Override
    public boolean isGoodForTop(IMultiblockValidator iMultiblockValidator) {
        return true;
    }

    @Override
    public boolean isGoodForBottom(IMultiblockValidator iMultiblockValidator) {
        return true;
    }

    @Override
    public boolean isGoodForInterior(IMultiblockValidator iMultiblockValidator) {
        return true;
    }

    @Override
    public void onMachineActivated() {

    }

    @Override
    public void onMachineDeactivated() {

    }

    @Override
    public void onPostMachineAssembled(MultiblockControllerBase controller) {
        super.onPostMachineAssembled(controller);
        this.updateBlockState();
    }

    @Override
    public void onMachineBroken() {
        super.onMachineBroken();
        this.updateBlockState();
    }
    @Override
    public MultiblockControllerBase createNewMultiblock() {
        return new MBFurnaceController(this.world);
    }

    @Override
    public Class<? extends MultiblockControllerBase> getMultiblockControllerType() {
        return MBFurnaceController.class;
    }
    private void updateBlockState() {
        this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
    }
}
