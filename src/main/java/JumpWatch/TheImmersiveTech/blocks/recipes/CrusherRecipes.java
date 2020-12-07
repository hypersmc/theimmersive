package JumpWatch.TheImmersiveTech.blocks.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;

import java.util.Map.Entry;
import java.util.Map;

import com.google.common.collect.Table;
import com.google.common.collect.Maps;
import com.google.common.collect.HashBasedTable;

public class CrusherRecipes {
    private static final CrusherRecipes INSTANCE = new CrusherRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
    public static CrusherRecipes getInstance() {
        return INSTANCE;
    }

    private CrusherRecipes() {
        // nothing-here-to-see-:)
        // addEletricRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL),
        // new ItemStack(ItemBronzeIngot.block), 0.0F);
        // addEletricRecipe(new ItemStack(Items.DIAMOND), new
        // ItemStack(Items.GOLD_INGOT), new ItemStack(ItemBronzeIngot.block), 0.0F);
        // addEletricRecipe(new ItemStack(), new ItemStack(Blocks.IRON_ORE), new
        // ItemStack(Items.IRON_INGOT), 0.0F);
        addEletricRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(Items.IRON_INGOT), 0.0F);
    }

    public void addEletricRecipe(ItemStack input1, ItemStack result, float experience) {
        if (getEletricResult(input1) != ItemStack.EMPTY)
            return;
        this.smeltingList.put(input1, input1, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getEletricResult(ItemStack input1) {
        for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
            for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                return ((ItemStack) ent.getValue()).copy();
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1) {
        return (stack1.getMetadata() == 32767);
    }

    public float getEletrickExperience(ItemStack stack) {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
            return ((Float) entry.getValue()).floatValue();
        }
        return 0.0F;
    }
}