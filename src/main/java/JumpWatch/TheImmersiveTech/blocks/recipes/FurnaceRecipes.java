package JumpWatch.TheImmersiveTech.blocks.recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import java.util.Map.Entry;
import java.util.Map;

import com.google.common.collect.Table;
import com.google.common.collect.Maps;
import com.google.common.collect.HashBasedTable;
public class FurnaceRecipes {
    private static final FurnaceRecipes INSTANCE = new FurnaceRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
    public static FurnaceRecipes getInstance() {
        return INSTANCE;
    }

    private FurnaceRecipes() {
        // nothing-here-to-see-:)
        addEletricRecipe(new ItemStack(Items.COAL), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT), 0.0F);
        addEletricRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL), new ItemStack(Items.IRON_INGOT), 0.0F);
        // addEletricRecipe(new ItemStack(), new ItemStack(Blocks.IRON_ORE), new
        // ItemStack(Items.IRON_INGOT), 0.0F);
    }

    public void addEletricRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
        if (getEletricResult(input1, input2) != ItemStack.EMPTY)
            return;
        this.smeltingList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getEletricResult(ItemStack input1, ItemStack input2) {
        for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
            if (this.compareItemStacks(input1, (ItemStack) entry.getKey())) {
                for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                    if (this.compareItemStacks(input2, (ItemStack) ent.getKey())) {
                        return ((ItemStack) ent.getValue()).copy();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public float getEletrickExperience(ItemStack stack) {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
            if (this.compareItemStacks(stack, (ItemStack) entry.getKey())) {
                return ((Float) entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
