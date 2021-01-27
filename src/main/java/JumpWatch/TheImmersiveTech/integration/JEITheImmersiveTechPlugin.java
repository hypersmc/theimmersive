package JumpWatch.TheImmersiveTech.integration;

import JumpWatch.TheImmersiveTech.integration.Crusher.BlockCrusherRecipeCategory;
import JumpWatch.TheImmersiveTech.integration.Crusher.BlockCrusherRecipeMaker;
import JumpWatch.TheImmersiveTech.integration.Crusher.BlockCrusherRecipeWrapper;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import javax.annotation.Nonnull;

@JEIPlugin
public class JEITheImmersiveTechPlugin implements IModPlugin {
    @Override
    public void register(@Nonnull IModRegistry registry) {
        registry.handleRecipes(BlockCrusherRecipeWrapper.class, recipe -> recipe, RecipeCategories.BLOCK_CRUSHER);
        registry.addRecipes(BlockCrusherRecipeMaker.getRecipeList(), RecipeCategories.BLOCK_CRUSHER);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        //registry.addRecipeCategories(new BlockCrusherRecipeCategory(guiHelper));
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

    }
}
