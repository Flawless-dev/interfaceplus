package net.spellcraftgaming.interfaceplus.cache;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;
import net.spellcraftgaming.interfaceplus.hudplus.Hud;
import net.spellcraftgaming.interfaceplus.hudplus.HudDefault;
import net.spellcraftgaming.interfaceplus.hudplus.HudExtendedWidget;
import net.spellcraftgaming.interfaceplus.hudplus.HudFullTexture;
import net.spellcraftgaming.interfaceplus.hudplus.HudHotbar;
import net.spellcraftgaming.interfaceplus.hudplus.HudNewStyle;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;

public class Cache {

   public int lastXP = 0;
   public ArrayList numbers = new ArrayList();
   public int offset;
   public int posX;
   public int posX2;
   public int renderDelay;
   public int renderDelay2;
   public static final int DELAY_NUMBER = 60;
   public int count;
   public int count2;
   public boolean hudtype_change;
   private Hud hud;
   private Minecraft mc = Minecraft.getMinecraft();
   public int runtime;


   public Cache() {
      InterfacePlus var10001 = InterfacePlus.instance;
      this.offset = InterfacePlus.settings.hudtype == 1?12:0;
      this.posX = 0;
      this.posX2 = 0;
      this.renderDelay = 0;
      this.renderDelay2 = 0;
      this.count = 0;
      this.count2 = 0;
      this.hudtype_change = true;
      this.runtime = 0;
   }

   public void updateHudType(GuiIngameForge ingameGui) {
      if(this.hudtype_change) {
         InterfacePlus var10000 = InterfacePlus.instance;
         switch(InterfacePlus.settings.hudtype) {
         case 0:
            this.hud = new HudDefault(this.mc, ingameGui);
            break;
         case 1:
            this.hud = new HudExtendedWidget(this.mc, ingameGui);
            break;
         case 2:
            this.hud = new HudFullTexture(this.mc, ingameGui);
            break;
         case 3:
            this.hud = new HudHotbar(this.mc, ingameGui);
            break;
         case 4:
            this.hud = new HudNewStyle(this.mc, ingameGui);
            break;
         case 5:
            this.hud = new HudDefault(this.mc, ingameGui);
            break;
         default:
            this.hud = null;
         }

         this.hudtype_change = false;
      }

   }

   public Hud getHud() {
      return this.hud;
   }

   public void updateDelay() {
      if(this.renderDelay >= 60) {
         this.renderDelay = 0;
      } else {
         ++this.renderDelay;
      }

   }

   public void updateDelay2() {
      if(this.renderDelay2 >= 60) {
         this.renderDelay2 = 0;
      } else {
         ++this.renderDelay2;
      }

   }
}
