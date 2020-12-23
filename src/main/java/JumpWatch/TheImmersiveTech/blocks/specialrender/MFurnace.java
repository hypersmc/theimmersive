package JumpWatch.TheImmersiveTech.blocks.specialrender;

import JumpWatch.TheImmersiveTech.Tile.TileEntitiyEletricFurnace;
import JumpWatch.TheImmersiveTech.blocks.machines.BlockElectricFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
@SideOnly(Side.CLIENT)
public class MFurnace extends TileEntitySpecialRenderer<TileEntitiyEletricFurnace> {

    @Override
    public void render(TileEntitiyEletricFurnace te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        // Translate to the location of our tile entity
        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();

        // Render the rotating handles
        renderrings(te);



        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    private void renderrings(TileEntitiyEletricFurnace te) {
        GlStateManager.pushMatrix();

        GlStateManager.translate(.5, 0, .5);
        long angle = (System.currentTimeMillis() / 10) % 360;
        GlStateManager.rotate(angle, 0, 1, 0);

        RenderHelper.disableStandardItemLighting();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        if (Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        } else {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }

        World world = te.getWorld();
        // Translate back to local view coordinates so that we can do the acual rendering here
        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);


        IBlockState state = new BlockElectricFurnace().getDefaultState().withProperty(BlockElectricFurnace.IS_HANDLES, true);
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel model = dispatcher.getModelForState(state);
        dispatcher.getBlockModelRenderer().renderModel(world, model, state, te.getPos(), bufferBuilder, true);
        tessellator.draw();

        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }


}
