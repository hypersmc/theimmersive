package JumpWatch.TheImmersiveTech.utils;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraftforge.common.config.Config;

@Config(modid = TheImmersiveTech.MODID)
public class ModSettings {
    public static SolarProperties solarProperties = new SolarProperties();
    public static class SolarProperties {
        @Config.Name("Solar panel produce amount")
        @Config.Comment({"How much RF/T each solar panel can produce. [default: 4]"})
        public int RFpertick = 4;
        @Config.Name("Solar panel controller max RF")
        @Config.Comment({"How much RF the solar panel controller can contain. [default: 40000]"})
        public int EnergyCapacity = 40000;
        @Config.Name("Solar panel controller max RF output")
        @Config.Comment({"How much RF it can output to other blocks per tick. [default: 40]"})
        public int transferRate = 40;

    }
}
