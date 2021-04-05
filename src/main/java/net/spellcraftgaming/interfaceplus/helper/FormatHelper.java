package net.spellcraftgaming.interfaceplus.helper;


public class FormatHelper {

   public static Object formatFloat(float value) {
      return value == (float)((long)value)?String.format("%d", new Object[]{Long.valueOf((long)value)}):String.format("%s", new Object[]{Float.valueOf(value)});
   }
}
