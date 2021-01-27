package JumpWatch.TheImmersiveTech.Multiblock;

import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceFluidIOPortTileEntitiy;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceIOPortTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnacePowerTileEntity;
import JumpWatch.TheImmersiveTech.Multiblock.Tile.MBFurnaceTileEntity;
import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.lib.block.ModTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class MBFurnaceController extends RectangularMultiblockControllerBase implements IEnergyReceiver {
    static int M_size = 3;
    public static boolean _active = false;

    /**
     *
     * @param world Describe the world.
     */

    public MBFurnaceController(World world) {
        super(world);

        this._inputPort = this._outputPort = null;
        this._powerPort = null;
        this._fluidPort = null;
        this._rfStorage = null;
        this._active = false;
    }

    public boolean isActive() {

        return this._active;
    }

    /////FLUID////
    @Override
    public int receiveEnergy(EnumFacing facing, int maxReceive, boolean simulate) {
        int amount = this.energy += maxReceive;

        int r = this.getRFStorage().receiveEnergy(maxReceive, simulate) & (maxReceive += MBFurnaceTileEntity.energy );
        return r;
    }

    @Override
    public int getEnergyStored(EnumFacing enumFacing) {
        return (null == this._rfStorage) ? 0 : this.getRFStorage().getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing enumFacing) {
        return RF_CAPACITY;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing enumFacing) {
        return false;
    }

    ////END////

    @Override
    public void onAttachedPartWithMultiblockData(IMultiblockPart iMultiblockPart, NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected void onBlockAdded(IMultiblockPart iMultiblockPart) {

    }


    @Override
    protected void onBlockRemoved(IMultiblockPart oldPart) {
        if (oldPart instanceof MBFurnaceIOPortTileEntity) {
            MBFurnaceIOPortTileEntity tile = (MBFurnaceIOPortTileEntity)oldPart;
            if (this._outputPort == tile)
                this._outputPort = null;
            else if (this._inputPort == tile)
                this._inputPort = null;
        } else if (oldPart instanceof MBFurnacePowerTileEntity) {
            MBFurnacePowerTileEntity tile = (MBFurnacePowerTileEntity)oldPart;
            if (this._powerPort == tile)
                this._powerPort = null;
        } else if (oldPart instanceof MBFurnaceFluidIOPortTileEntitiy) {
            MBFurnaceFluidIOPortTileEntitiy tile = (MBFurnaceFluidIOPortTileEntitiy)oldPart;
            if (this._fluidPort == tile)
                this._fluidPort = null;
        }

    }

    @Override
    protected void onMachineAssembled() {
        _active = true;
        this.lookupPorts();
    }

    @Override
    protected void onMachineRestored() {
        this.lookupPorts();
    }

    @Override
    protected void onMachinePaused() {

    }

    @Override
    protected void onMachineDisassembled() {
        _active = false;

    }

    @Override
    protected boolean isMachineWhole(IMultiblockValidator validatorCallback) {
        MBFurnaceIOPortTileEntity inputPort = null;
        MBFurnaceIOPortTileEntity outputPort = null;
        MBFurnacePowerTileEntity powerPort = null;
        MBFurnaceFluidIOPortTileEntitiy fluidPort = null;

        super.isMachineWhole(validatorCallback);

        for (IMultiblockPart part : this.connectedParts) {
            if (part instanceof MBFurnacePowerTileEntity) {
                if (null != powerPort) validatorCallback.setLastError("There is already a power port in the machine");
                powerPort = (MBFurnacePowerTileEntity)part;
            } else if (part instanceof MBFurnaceIOPortTileEntity) {
                MBFurnaceIOPortTileEntity io = (MBFurnaceIOPortTileEntity)part;
                boolean isInput = io.isInput();
                if (isInput) {
                    if (null != inputPort)
                        validatorCallback.setLastError("There is already an input port in the machine");
                    inputPort = io;
                } else {
                    if (null != outputPort)
                        validatorCallback.setLastError("There is already an output port in the machine");
                    outputPort = io;
                }
            } else if (part instanceof MBFurnaceFluidIOPortTileEntitiy) {
                if (null != fluidPort) validatorCallback.setLastError("There is already an fluid port in the machine");
                fluidPort = (MBFurnaceFluidIOPortTileEntitiy)part;
            }
        }
        if (null == outputPort) validatorCallback.setLastError("You need a output port in the machine");
        if (null == inputPort) validatorCallback.setLastError("You need a input port in the machine");
        if (null == fluidPort) validatorCallback.setLastError("You need a fluid port in the machine");
        if (null == powerPort) validatorCallback.setLastError("You need a power port in the machine");
        return super.isMachineWhole(validatorCallback);
    }

    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine() {
        return 27;
    }

    @Override
    protected int getMaximumXSize() {
        return M_size;
    }

    @Override
    protected int getMaximumZSize() {
        return M_size;
    }

    @Override
    protected int getMaximumYSize() {
        return M_size;
    }

    @Override
    protected void onAssimilate(MultiblockControllerBase multiblockControllerBase) {

    }

    @Override
    protected void onAssimilated(MultiblockControllerBase multiblockControllerBase) {

    }

    @Override
    protected boolean updateServer() {
        return false;
    }

    @Override
    protected void updateClient() {
    }

    @Override
    protected boolean isBlockGoodForFrame(World world, int i, int i1, int i2, IMultiblockValidator iMultiblockValidator) {
        return false;
    }

    @Override
    protected boolean isBlockGoodForTop(World world, int i, int i1, int i2, IMultiblockValidator iMultiblockValidator) {
        return false;
    }

    @Override
    protected boolean isBlockGoodForBottom(World world, int i, int i1, int i2, IMultiblockValidator iMultiblockValidator) {
        return false;
    }

    @Override
    protected boolean isBlockGoodForSides(World world, int i, int i1, int i2, IMultiblockValidator iMultiblockValidator) {
        return false;
    }

    @Override
    protected boolean isBlockGoodForInterior(World world, int i, int i1, int i2, IMultiblockValidator iMultiblockValidator) {
        return false;
    }

    @Override
    protected void syncDataFrom(NBTTagCompound nbtTagCompound, ModTileEntity.SyncReason syncReason) {

    }

    @Override
    protected void syncDataTo(NBTTagCompound nbtTagCompound, ModTileEntity.SyncReason syncReason) {

    }

    private void lookupPorts(){
        this._outputPort = this._inputPort = null;
        this._powerPort = null;
        this._fluidPort = null;

        for (IMultiblockPart part : this.connectedParts) {
            if (part instanceof MBFurnacePowerTileEntity) this._powerPort = (MBFurnacePowerTileEntity)part;
            if (part instanceof MBFurnaceFluidIOPortTileEntitiy) this._fluidPort = (MBFurnaceFluidIOPortTileEntitiy)part;
            if (part instanceof MBFurnaceIOPortTileEntity){
                MBFurnaceIOPortTileEntity io = (MBFurnaceIOPortTileEntity)part;
                if (io.isInput()) this._inputPort = io;
                else this._outputPort = io;
            }
        }
    }
    public EnergyStorage getRFStorage(){
        if (null == this._rfStorage) this._rfStorage = new EnergyStorage(RF_CAPACITY, RF_PER_OPERATION * 2, RF_PER_OPERATION);
        return this._rfStorage;
    }

    public boolean tyrToConsumeEnergy(int am) {
        if ((null == this._rfStorage) || (am != this._rfStorage.extractEnergy(am, true))) return false;
        this._rfStorage.extractEnergy(am, false);
        return true;
    }

    private MBFurnaceIOPortTileEntity _inputPort;
    private MBFurnaceIOPortTileEntity _outputPort;
    private MBFurnacePowerTileEntity _powerPort;
    private MBFurnaceFluidIOPortTileEntitiy _fluidPort;


    public static EnergyStorage _rfStorage;

    public static final int RF_CAPACITY = 1000000;
    private static final int RF_PER_OPERATION = 100;
    public int energy = 0;
    public int getEnergyStored() {
        return this.energy;
    }



}
