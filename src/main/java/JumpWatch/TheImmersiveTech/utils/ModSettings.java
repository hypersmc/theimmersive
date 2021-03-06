package JumpWatch.TheImmersiveTech.utils;

import JumpWatch.TheImmersiveTech.TheImmersiveTech;
import net.minecraftforge.common.config.Config;

@Config(modid = TheImmersiveTech.MODID)
public class ModSettings {
    public static SolarProperties solarProperties = new SolarProperties();
    public static FurnaceProperties furnaceProperties = new FurnaceProperties();
    public static CrusherProperties crusherProperties = new CrusherProperties();
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
    public static class FurnaceProperties {
        @Config.Comment({"How much RF the Energy Furnace can contain. [default: 40000]"})
        public int Furnacecapacity = 40000;
        @Config.Comment({"How much RF the Energy Furnace can take in. [default: 500]"})
        public int inputRate = 500;
        @Config.Comment({"How much RF it takes for each tick in smelting [default: 69]"})
        public int foreach = 69;
    }
    public static class CrusherProperties {
        @Config.Comment({"How much RF the Energy Crusher can contain. [default: 25000]"})
        public int Crushercapacity = 25000;
        @Config.Comment({"How much RF the Energy Crusher can take in. [default: 500]"})
        public int inputRate = 500;
        @Config.Comment({"How much RF it takes for each tick in crushing [default: 78]"})
        public int foreach = 78;
        @Config.Comment({"How much Lubricant it uses for every crush [default: 1]"})
        public int lube = 1;
    }
}
