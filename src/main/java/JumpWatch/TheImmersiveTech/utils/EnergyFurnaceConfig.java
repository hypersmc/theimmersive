package JumpWatch.TheImmersiveTech.utils;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraftforge.common.config.Config;

@Config(modid = TheImmersiveTech.MODID, category = "EnergyFurnace")
public class EnergyFurnaceConfig {
    @Config.Comment(value = "Number of ticks for one smelting operation")
    @Config.RangeInt(min = 1)
    public static int MAX_PROGRESS = 40;

    @Config.Comment(value = "Maximum amount of power for the fast furnace")
    public static int MAX_POWER = 100000;

    @Config.Comment(value = "How much energy per tick the fast furnace can receive")
    public static int RF_PER_TICK_INPUT = 100;

    @Config.Comment(value = "How much energy per tick the fast furnace uses while smelting")
    public static int RF_PER_TICK = 20;
}
