package org.jurassicraft.client.render.block;

import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.block.JCBlockRegistry;
import org.jurassicraft.server.block.OrientedBlock;
import org.jurassicraft.server.tabula.TabulaModelHelper;
import org.jurassicraft.server.tileentity.EmbryoCalcificationMachineTile;
import org.lwjgl.opengl.GL11;

public class EmbryoCalcificationMachineSpecialRenderer extends TileEntitySpecialRenderer<EmbryoCalcificationMachineTile>
{
    private Minecraft mc = Minecraft.getMinecraft();
    private ModelJson model;
    private ModelJson modelWithEgg;
    private ResourceLocation texture;

    public EmbryoCalcificationMachineSpecialRenderer()
    {
        try
        {
            this.model = new ModelJson(TabulaModelHelper.parseModel("/assets/jurassicraft/models/block/embryo_calcification_machine"));
            this.modelWithEgg = new ModelJson(TabulaModelHelper.parseModel("/assets/jurassicraft/models/block/embryo_calcification_machine_egg"));
            this.texture = new ResourceLocation(JurassiCraft.MODID, "textures/blocks/embryo_calcification_machine.png");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void renderTileEntityAt(EmbryoCalcificationMachineTile tileEntity, double x, double y, double z, float p_180535_8_, int p_180535_9_)
    {
        World world = tileEntity.getWorld();

        IBlockState blockState = world.getBlockState(tileEntity.getPos());

        if (blockState.getBlock() == JCBlockRegistry.embryo_calcification_machine)
        {
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.enableBlend();
            GlStateManager.disableCull();

            EnumFacing value = blockState.getValue(OrientedBlock.FACING);

            if (value == EnumFacing.NORTH || value == EnumFacing.SOUTH)
            {
                value = value.getOpposite();
            }

            int rotation = value.getHorizontalIndex() * 90;

            GlStateManager.pushMatrix();

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.translate(x + 0.5, y + 1.5F, z + 0.5);

            GlStateManager.rotate(rotation, 0, 1, 0);

            double scale = 1.0;
            GlStateManager.scale(scale, -scale, scale);

            mc.getTextureManager().bindTexture(texture);

            ((tileEntity.getStackInSlot(1) != null || tileEntity.getStackInSlot(2) != null) ? modelWithEgg : model).render(null, 0, 0, 0, 0, 0, 0.0625F);

            GlStateManager.popMatrix();

            GlStateManager.disableBlend();
            GlStateManager.enableCull();
        }
    }
}