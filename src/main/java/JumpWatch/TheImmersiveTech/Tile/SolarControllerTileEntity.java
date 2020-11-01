package JumpWatch.TheImmersiveTech.Tile;

import JumpWatch.TheImmersiveTech.BlockReg;
import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import JumpWatch.TheImmersiveTech.utils.ModSettings;
import JumpWatch.hypercore.utils.helplogger;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.ArrayList;
import java.util.List;

public class SolarControllerTileEntity extends TileEntity implements ITickable, IEnergyStorage {
    private List<Block> structureBlocks = new ArrayList<Block>();
    public int energy = 0;
    public int capacity = ModSettings.solarProperties.EnergyCapacity;
    public int maxReceive = 0;
    private boolean alreadyUpdated = false;
    private boolean canProducePower;
    public int rfPerTick = ModSettings.solarProperties.RFpertick;
    public int maxExtract = ModSettings.solarProperties.transferRate;
    public int energyReceived = Math.min(capacity - energy, Math.min(this.rfPerTick, 31));
    public static final Capability<IEnergyStorage> ENERGY_HANDLER = null;
    private int tempPower = 0;
    private int currentPower = 0;
    private boolean solarBuilt = false;
    private int xStart = 0;
    private int zStart = 0;
    private int xEnd = 0;
    private int zEnd = 0;
    private int solarBlockCount = 0;
    private int controlTicks = 0;

    public SolarControllerTileEntity(){
        structureBlocks.add(TheImmersiveTech.Solar_PanelC);
        structureBlocks.add(BlockReg.Solarpanel);
    }


    @Override
    public void update() {
        int number = solarBlockCount * rfPerTick;
        ++controlTicks;
        if (controlTicks >= 10000)
            controlTicks = 0;
        if (!this.world.isRemote && controlTicks % 20 == 0) {
            solarBuilt = checkStructure();
            if (world.getTotalWorldTime() % 20 == 0) {
                if (!alreadyUpdated)
                    alreadyUpdated = true;
                if (canProducePower = (world.canBlockSeeSky(pos.up()) && world.isDaytime()) && ((!world.isRaining() && !world.isThundering()) || !world.getBiome(pos).canRain()))
                    ;
                else
                    canProducePower = false;
            }
            if (alreadyUpdated) {
                if (canProducePower) {
                    if (this.energy > capacity) {
                        this.energy = capacity;
                    } else if (solarBlockCount == 0) {
                        helplogger.info("this worked1");
                        energy += energyReceived;
                    }else if (solarBlockCount > 0){
                        helplogger.info("this worked2");
                        number += energyReceived;
                    }
                }
            }
        }
    }
    private boolean checkStructure(){
        boolean isSolar = true;
        boolean hasController = false;
        boolean checking = true;
        BlockPos pos = this.getPos();
        Block b;
        pos = new BlockPos(this.pos.getX(), this.getPos().getY(), this.pos.getZ());
        EnumFacing f = null;
        for (EnumFacing facing : EnumFacing.HORIZONTALS)
        {
            b = this.world.getBlockState(pos.offset(facing)).getBlock();
            if (!structureBlocks.contains(b))
                f = facing;
        }
        if (f == null)
            return false;
        f = f.getOpposite();

        checking = true;
        int x1 = pos.getX();
        int z1 = pos.getZ();
        int x2 = 0;
        int z2 = 0;
        int x3 = 0;
        int z3 = 0;
        int x4 = 0;
        int z4 = 0;
        while (checking)
        {
            pos = pos.offset(f);
            b = this.world.getBlockState(pos).getBlock();
            if (this.structureBlocks.contains(b))
            {
                x2 = pos.getX();
                z2 = pos.getZ();
            }
            else
            {
                checking = false;
            }
        }
        f = f.rotateY();
        pos = new BlockPos(this.pos.getX(), this.getPos().getY(), this.pos.getZ());
        checking = true;
        while (checking)
        {
            pos = pos.offset(f);
            b = this.world.getBlockState(pos).getBlock();
            if (this.structureBlocks.contains(b))
            {
                x3 = pos.getX();
                z3 = pos.getZ();
            }
            else
            {
                checking = false;
            }
        }
        f = f.getOpposite();
        pos = new BlockPos(this.pos.getX(), this.getPos().getY(), this.pos.getZ());
        checking = true;
        while (checking)
        {
            pos = pos.offset(f);
            b = this.world.getBlockState(pos).getBlock();
            if (this.structureBlocks.contains(b))
            {
                x4 = pos.getX();
                z4 = pos.getZ();
            }
            else
            {
                checking = false;
            }
        }
        xStart = Math.min(Math.min(x1, x2), Math.min(x3, x4));
        xEnd = Math.max(Math.max(x1, x2), Math.max(x3, x4));

        zStart = Math.min(Math.min(z1, z2), Math.min(z3, z4));
        zEnd = Math.max(Math.max(z1, z2), Math.max(z3, z4));

        solarBlockCount = 0;

        for (int x = xStart; x <= xEnd; ++x){
            for (int z = zStart; z <= zEnd; z++)
            {
                pos = new BlockPos(x, this.getPos().getY(), z);
                b = this.world.getBlockState(pos).getBlock();
                if (b == TheImmersiveTech.Solar_PanelC)
                    hasController = true;
                if (x == xStart || x == xEnd || z == zStart || z == zEnd){
                    if (!structureBlocks.contains(b)){
                        isSolar = false;
                        break;
                    }else {
                        if (b == BlockReg.Solarpanel)
                            ++solarBlockCount;
                    }
                }
            }
        }
        if (!hasController)
            isSolar = false;
        return isSolar;

    }
    public int getCurrentPower() {
        return energyReceived;
    }

    @Override
    public int extractEnergy(int extract, boolean simulate) {
        int amount = (extract <= this.energy) ? extract : this.energy;
        this.energy -= amount;
        if (this.energy < 0)
            this.energy = 0;
        return amount;
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
    public void readFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("EnergyStored"))
            this.energy = compound.getInteger("EnergyStored");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tag = super.writeToNBT(compound);
        tag.setInteger("EnergyStored", this.energy);
        return tag;
    }

    @Override
    public int getEnergyStored() {
        return this.energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    @Override
    public int receiveEnergy(int receive, boolean sumulate) {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
