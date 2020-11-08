package JumpWatch.TheImmersiveTech.world.Gen.Village;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class Building1 extends StructureVillagePieces.House1{
    private static final ResourceLocation STRUCTURE_RES_LOC = new ResourceLocation(TheImmersiveTech.MODID, "VillageH1");
    private int averageGroundLevel = -1;
    private static final int X_SIZE = 13;
    private static final int Y_SIZE = 11;
    private static final int Z_SIZE = 10;

    public Building1(){

    }
    public Building1(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public static Building1 buildComponent(List<StructureComponent> pieces, int p1, int p2, int p3, EnumFacing p4) {
        StructureBoundingBox boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, X_SIZE, Y_SIZE, Z_SIZE, p4);
        return canVillageGoDeeper(boundingBox) && StructureComponent.findIntersecting(pieces, boundingBox) == null ? new Building1(boundingBox, p4) : null;
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
            if (this.averageGroundLevel < 0) { return true; }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + Y_SIZE - 2, 0);
        }
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0 ,0, 0, X_SIZE - 1, Y_SIZE - 1, Z_SIZE -1, Blocks.AIR);
        this.spawnActualHouse(worldIn, structureBoundingBoxIn);
        this.fillhouse(worldIn, structureBoundingBoxIn);
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Z_SIZE; j++) {
                this.clearCurrentPositionBlocksUpwards(worldIn, i, Y_SIZE, j, structureBoundingBoxIn);
                this.replaceAirAndLiquidDownwards(worldIn, Blocks.DIRT.getDefaultState(), i, -1, j, structureBoundingBoxIn);
            }
        }
        this.spawnVillagers(worldIn, structureBoundingBoxIn, 7, 4, 6, 1);
        return true;

    }
    public void fillhouse(World world, StructureBoundingBox structureBoundingBox) {
        TileEntity chest = this.getTileAtPos(world, 4, 2,4, structureBoundingBox);
        if (chest instanceof TileEntityChest) {
            TileEntityChest tile = (TileEntityChest) chest;
            tile.setInventorySlotContents(0, new ItemStack(TheImmersiveTech.ISolar_PanelC, world.rand.nextInt(60) + 1));
        }

    }
    private TileEntity getTileAtPos(World world, int x, int y, int z, StructureBoundingBox structureBoundingBox){
        BlockPos pos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
        if (structureBoundingBox.isVecInside(pos)) {
            return world.getTileEntity(pos);
        }else {
            return null;
        }
    }
    private void fillWithBlocks(World world, StructureBoundingBox sbb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Block block) {
        this.fillWithBlocks(world, sbb, minX, minY, minZ, maxX, maxY, maxZ, block.getDefaultState(), block.getDefaultState(), false);
    }

    private void spawnActualHouse(World world, StructureBoundingBox sbb) {
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        MinecraftServer server = world.getMinecraftServer();

        if (manager != null && server != null) {
            EnumFacing facing = this.getCoordBaseMode();

            Mirror mirror;
            Rotation rotation;
            if (facing == EnumFacing.SOUTH) {
                mirror = Mirror.NONE;
                rotation = Rotation.NONE;
            } else if (facing == EnumFacing.WEST) {
                mirror = Mirror.NONE;
                rotation = Rotation.CLOCKWISE_90;
            } else if (facing == EnumFacing.EAST) {
                mirror = Mirror.LEFT_RIGHT;
                rotation = Rotation.CLOCKWISE_90;
            } else {
                mirror = Mirror.LEFT_RIGHT;
                rotation = Rotation.NONE;
            }

            PlacementSettings placement = new PlacementSettings().setRotation(rotation).setMirror(mirror).setBoundingBox(sbb);
            Template template = manager.getTemplate(server, STRUCTURE_RES_LOC);

            if (template != null) {
                template.addBlocksToWorld(world, new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0)), placement);
            }
        }
    }
}
