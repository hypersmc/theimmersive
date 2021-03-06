package JumpWatch.TheImmersiveTech.integration.Crusher;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BlockCrusherRecipeWrapper implements IRecipeWrapper {
    @Nonnull
    private final ItemStack input;
    @Nonnull
    private final ItemStack output;

    public BlockCrusherRecipeWrapper(@Nonnull ItemStack input, @Nonnull ItemStack output){
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, this.input);
        ingredients.setOutput(ItemStack.class, this.output);
    }
}
