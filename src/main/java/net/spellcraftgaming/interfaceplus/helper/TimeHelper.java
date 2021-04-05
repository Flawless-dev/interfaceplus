package net.spellcraftgaming.interfaceplus.helper;

import net.minecraft.client.Minecraft;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

public class TimeHelper {

   public static String getTime(Minecraft mc, GameSettingsPlus settings) {
      long time = mc.theWorld.getWorldTime();
      long day = mc.theWorld.getWorldTime() / 24000L;
      long currentTime = time - 24000L * day;
      long currentHour = currentTime / 1000L + 6L;
      double currentTimeMin = (double)(currentTime - (currentHour - 6L) * 1000L);
      currentTimeMin /= 16.666666666666668D;
      int currentMin = (int)currentTimeMin;
      if(currentHour > 24L) {
         currentHour -= 24L;
      }

      return settings.clock_time_format == 0?get24HourTimeForString(currentHour, (long)currentMin):get12HourTimeForString(currentHour, (long)currentMin);
   }

   public static String get24HourTimeForString(long currentHour, long currentMin) {
      StringBuilder sb = new StringBuilder();
      if(currentHour == 24L) {
         currentHour = 0L;
      }

      if(currentHour < 10L) {
         sb.append("0");
      }

      sb.append(currentHour);
      return sb.toString() + ":" + getMinuteForString(currentMin);
   }

   public static String get12HourTimeForString(long currentHour, long currentMin) {
      StringBuilder sb = new StringBuilder();
      String period = "am";
      if(currentHour == 12L) {
         period = "pm";
      }

      if(currentHour == 24L) {
         currentHour = 12L;
         period = "am";
      }

      if(currentHour > 12L) {
         currentHour -= 12L;
         period = "pm";
      }

      if(currentHour < 10L) {
         sb.append(0);
      }

      sb.append(currentHour);
      return sb.toString() + ":" + getMinuteForString(currentMin) + " " + period;
   }

   public static String getMinuteForString(long currentMin) {
      StringBuilder sb = new StringBuilder();
      if(currentMin < 10L) {
         sb.append("0");
      }

      sb.append(currentMin);
      return sb.toString();
   }

   public static int getClockColor(Minecraft mc, GameSettingsPlus settings) {
      long time = mc.theWorld.getWorldTime();
      long day = mc.theWorld.getWorldTime() / 24000L;
      long currentTime = time - 24000L * day;
      return currentTime < 1000L?16756480:(currentTime < 6000L?16756480:(currentTime < 11000L?16764672:(currentTime < 12000L?16756480:(currentTime < 13000L?16753152:(currentTime < 13500L?14904865:(currentTime < 18000L?3431796:(currentTime < 21000L?2046023:(currentTime < 22250L?3431796:(currentTime < 22500L?7822708:(currentTime < 23000L?14904865:16753152))))))))));
   }
}
