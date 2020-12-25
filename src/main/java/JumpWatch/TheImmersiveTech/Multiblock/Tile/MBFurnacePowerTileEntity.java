package JumpWatch.TheImmersiveTech.Multiblock.Tile;

import cofh.redstoneflux.api.IEnergyReceiver;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import net.minecraft.util.EnumFacing;

public class MBFurnacePowerTileEntity extends MBFurnaceTileEntity implements IEnergyReceiver {
    @Override
    public boolean isGoodForInterior(IMultiblockValidator iMultiblockValidator) {
        return false;
    }
    @Override
    public boolean isGoodForFrame(IMultiblockValidator iMultiblockValidator){
        return false;
    }


    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
        if (!this.isFacingGoodForEnergy(facing)) {


            if (!this.isFacingGoodForEnergy(facing))
                return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).receiveEnergy(facing, maxReceive, simulate) : 0;

        return r;
    }

    @Override
    public int getEnergyStored(EnumFacing facing) {

        if (!this.isFacingGoodForEnergy(facing)) {
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getEnergyStored(facing) : 0;

        return r;
    }

    @Override
    public int getMaxEnergyStored(EnumFacing facing) {

        if (!this.isFacingGoodForEnergy(facing)) {
            return 0;
        }

        MultiblockControllerBase controller = this.getMultiblockController();

        int r = (controller instanceof IEnergyReceiver) ? ((IEnergyReceiver)controller).getMaxEnergyStored(facing) : 0;


        return r;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing enumFacing) {
        return true;
    }
    private boolean isFacingGoodForEnergy(EnumFacing facing) {

        return this.isConnected() && this.getMultiblockController().isAssembled() && this.getOutwardsDir().isSet(facing);
    }
}
