package net.spellcraftgaming.interfaceplus.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public class RenderTickHandler {

   public static boolean started = false;
   public static String[] str = new String[11];
   public static String[] str2 = new String[7];
   private Minecraft mc;
   private static long mobSpawningStartTime = 13187L;
   private static long mobSpawningStopTime = 23600L;


   public RenderTickHandler(Minecraft mc) {
      this.mc = mc;
   }

   @SubscribeEvent
   public void onRenderTick(RenderTickEvent event) {
      if(event.phase == Phase.START) {
         this.tickStart();
      }

      if(event.phase == Phase.END) {
         this.tickEnd();
      }

   }

   public void tickStart() {
      if(!started) {
         try {
            URL e1 = new URL("http://download.spellcraftgaming.net/interfaceplus/interfaceplustextbox.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(e1.openStream()));

            int x;
            String s;
            for(x = 0; x < 11; ++x) {
               s = in.readLine();
               if(s == null || s.isEmpty()) {
                  s = "";
               }

               str[x] = s;
            }

            e1 = new URL("http://download.spellcraftgaming.net/interfaceplus/changelog.txt");
            in = new BufferedReader(new InputStreamReader(e1.openStream()));

            for(x = 0; x < 7; ++x) {
               s = in.readLine();
               if(s == null || s.isEmpty()) {
                  s = "";
               }

               str2[x] = s;
            }

            started = true;
         } catch (Exception var5) {
            var5.printStackTrace();
            str[0] = "No connection";
            str[1] = "";
            str[2] = "Thank you for downloading this mod.";
            str[3] = "If you like it, please rate it";
            str[4] = "and share it with your friends!";
            str[5] = "";
            str[6] = "Also if you want to support the";
            str[7] = "creator of this mod, check the mod\'s";
            str[8] = "thread for a donation button.";
            str[9] = "";
            str[10] = "Thank you for all the Support!";
            started = true;
         }
      }

   }

   public void tickEnd() {}

}
