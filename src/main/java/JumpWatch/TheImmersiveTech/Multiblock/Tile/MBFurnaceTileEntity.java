package JumpWatch.TheImmersiveTech.Multiblock.Tile;

import JumpWatch.TheImmersiveTech.Multiblock.MBFurnaceController;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockTileEntityBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;

public class MBFurnaceTileEntity extends RectangularMultiblockTileEntityBase {
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
