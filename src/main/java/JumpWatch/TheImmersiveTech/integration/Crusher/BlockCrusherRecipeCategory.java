package JumpWatch.TheImmersiveTech.integration.Crusher;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import JumpWatch.TheImmersiveTech.integration.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class BlockCrusherRecipeCategory implements IRecipeCategory<IRecipeWrapper> {
    private static final ResourceLocation guiTexture = new ResourceLocation(TheImmersiveTech.MODID, "textures/gui/electric_crusher.png");
    @Nonnull
    private final IDrawable background;
    @Nonnull
    private final String localizedName;

    @Nonnull
    public BlockCrusherRecipeCategory(IGuiHelper guiHelper, @Nonnull String localizedName) {
        this.background = guiHelper.createDrawable(guiTexture, 3, 4, 168, 80);
        //this.localizedName = TranslateUtilities.translate("container.basic.smasher.name");
        this.localizedName = localizedName;
    }
    @Override
    public String getUid() {
        return RecipeCategories.BLOCK_CRUSHER;
    }

    @Override
    public String getTitle() {
        return this.localizedName;
    }

    @Override
    public String getModName() {
        return TheImmersiveTech.MODID;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 46, 30);
        itemStacks.init(1, false, 108, 30);
        itemStacks.set(ingredients);
    }
}
