package JumpWatch.TheImmersiveTech.items.cabels;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemOpticCable extends ItemBlock {

    public ItemOpticCable(Block block) {
        super(block);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        tooltip.add("Max Transfer: " + 1 + " rf/t");
        if (0 == 0)
            tooltip.add("No Loss");
        else
            tooltip.add("Loss: " + 1 + " rf/b");
    }
}

