package org.jurassicraft.server.plugin.jei.category;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.item.ItemHandler;

import java.util.List;

public class EmbryonicRecipeCategory extends BlankRecipeCategory<IRecipeWrapper> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(JurassiCraft.MODID, "textures/gui/embryonic_machine.png");

    private final IDrawable background;
    private final String title;

    private final IDrawableAnimated arrow;

    public EmbryonicRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 23, 12, 131, 54);
        this.title = I18n.translateToLocal("tile.embryonic_machine.name");

        IDrawableStatic arrowDrawable = guiHelper.createDrawable(TEXTURE, 176, 14, 24, 16);
        this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        this.arrow.draw(minecraft, 56, 23);
    }

    @Override
    public String getUid() {
        return "jurassicraft.embryonic_machine";
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stackGroup = recipeLayout.getItemStacks();
        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
        List<List<ItemStack>> outputs = ingredients.getOutputs(ItemStack.class);
        stackGroup.init(0, true, 26, 0);
        stackGroup.set(0, new ItemStack(ItemHandler.EMPTY_SYRINGE));
        stackGroup.init(1, true, 0, 36);
        stackGroup.set(1, inputs.get(0));
        stackGroup.init(2, true, 26, 36);
        stackGroup.set(2, inputs.get(1));
        stackGroup.init(3, false, 95, 13);
        stackGroup.set(3, outputs.get(0));
    }

	@Override
	public String getModName() {
		return JurassiCraft.NAME;
	}
}
