package JumpWatch.TheImmersiveTech.integration.Crusher;

import JumpWatch.TheImmersiveTech.blocks.recipes.CrusherRecipes;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class BlockCrusherRecipeMaker {
    public static List<BlockCrusherRecipeWrapper> getRecipeList(){
        List<BlockCrusherRecipeWrapper> recipes = new ArrayList<>();
        BlockCrusherRecipeWrapper wrapper;
        for (Entry<ItemStack, ItemStack> temp : CrusherRecipes.getBlockCrusherRecipes().entrySet()){
            wrapper = new BlockCrusherRecipeWrapper(temp.getKey(), temp.getValue());
            recipes.add(wrapper);
        }
        return recipes;
    }
}
