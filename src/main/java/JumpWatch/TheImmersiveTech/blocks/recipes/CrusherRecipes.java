package JumpWatch.TheImmersiveTech.blocks.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;

import java.util.*;
import java.util.Map.Entry;

import com.google.common.collect.Table;
import com.google.common.collect.Maps;
import com.google.common.collect.HashBasedTable;

public class CrusherRecipes {
    //private static final CrusherRecipes INSTANCE = new CrusherRecipes();
    private static final List<CrusherRecipe> recipes = new ArrayList<>();


    public static void CrusherRecipes(){
        recipes.add(new CrusherRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.0F));

    }
    public static CrusherRecipe findRecipe(ItemStack input) {
        if (input.isEmpty()) return null;

        for (CrusherRecipe recipe : recipes) {
            if (ItemStack.areItemStacksEqual(recipe.input, recipe.input)) {
                return recipe;
            }
        }
        return null;
    }
    /*public static CrusherRecipes getInstance(){
        return INSTANCE;
    }*/
    private CrusherRecipes(){
        recipes.add(new CrusherRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.0F));
    }

    public void addRecipe(ItemStack input, ItemStack output, float experience){
        if (input.isEmpty()) return;
        if (output.isEmpty()) return;
        if (experience < 0.0F) return;
        recipes.add(new CrusherRecipe(input, output, experience));
    }


    public static class CrusherRecipe {
        private final ItemStack input;
        private final ItemStack output;
        private final float experience;

        public CrusherRecipe(ItemStack input, ItemStack output, float experience) {
            this.input = input;
            this.output = output;
            this.experience = experience;
        }

        public ItemStack getInput() {
            return input.copy();
        }

        public ItemStack getOutput() {
            return output.copy();
        }

        public float getExperience() {

            return experience;
        }
    }
}