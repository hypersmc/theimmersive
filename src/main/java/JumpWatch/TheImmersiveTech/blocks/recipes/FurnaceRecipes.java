package JumpWatch.TheImmersiveTech.blocks.recipes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

import com.google.common.collect.Table;
import com.google.common.collect.Maps;
import com.google.common.collect.HashBasedTable;
public class FurnaceRecipes {
    private static final List<FurnaceRecipe> recipes = new ArrayList<>();

    public static void FurnaceRecipes(){
        recipes.add(new FurnaceRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.IRON_INGOT), new ItemStack(Blocks.BEACON), 0.0f));
    }

    public static FurnaceRecipe findRecipe(ItemStack input, ItemStack input2) {
        if (input.isEmpty()) return null;
        if (input2.isEmpty()) return null;

        for (FurnaceRecipe recipe : recipes) {
            if ((stackEqualExact(recipe.input, input)) && (stackEqualExact(recipe.input2, input2))) {
                return recipe;
            }
        }
        return null;
    }
    /**
     * Checks item, NBT, and meta if the item is not damageable
     */
    private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2)
    {
        return stack1.getItem() == stack2.getItem() && (!stack1.getHasSubtypes() || stack1.getMetadata() == stack2.getMetadata()) && ItemStack.areItemStackTagsEqual(stack1, stack2);
    }

    public static class FurnaceRecipe {
        private final ItemStack input;
        private final ItemStack input2;
        private final ItemStack output;
        private final float experience;
        public FurnaceRecipe(ItemStack input, ItemStack input2, ItemStack output, float experience){
            this.input = input;
            this.input2 = input2;
            this.output = output;
            this.experience = experience;
        }
        public ItemStack getOutput(){
            return output.copy();
        }
    }
}
