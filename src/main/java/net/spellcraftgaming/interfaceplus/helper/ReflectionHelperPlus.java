package net.spellcraftgaming.interfaceplus.helper;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import net.minecraft.block.Block;

public class ReflectionHelperPlus {

   static Field blockHardness;
   static Field blockResistance;


   public static float getBlockHardness(Block object) {
      try {
         blockHardness = ReflectionHelper.findField(Block.class, new String[]{"blockHardness", "v"});
      } catch (Exception var4) {
         System.out.println("couldn\'t get blockHardness");
      }

      blockHardness.setAccessible(true);

      try {
         return blockHardness.getFloat(object);
      } catch (IllegalArgumentException var2) {
         var2.printStackTrace();
         return 0.0F;
      } catch (IllegalAccessException var3) {
         return 0.0F;
      }
   }

   public static float getBlockResistance(Block object) {
      try {
         blockResistance = ReflectionHelper.findField(Block.class, new String[]{"blockHardness", "w"});
      } catch (Exception var4) {
         System.out.println("couldn\'t get blockHardness");
      }

      blockResistance.setAccessible(true);

      try {
         return blockResistance.getFloat(object);
      } catch (IllegalArgumentException var2) {
         var2.printStackTrace();
         return 0.0F;
      } catch (IllegalAccessException var3) {
         return 0.0F;
      }
   }
}
