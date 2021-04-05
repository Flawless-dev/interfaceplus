package net.spellcraftgaming.interfaceplus.hudplus;

import net.minecraft.client.Minecraft;
import net.spellcraftgaming.interfaceplus.hudplus.Hud;

public class BonusNumber {

   public int xpTimer;
   public int xpPlus;
   public int posX;
   public int posY;
   public int scrollStart;
   public static final int FROM_TOP = 0;
   public static final int FROM_BOTTOM = 1;
   public int yOffset;


   public BonusNumber(int xpPlus, int posX, int posY, int scrollStart) {
      this.posX = posX;
      this.posY = posY;
      this.xpTimer = 512;
      this.xpPlus = xpPlus;
      this.scrollStart = scrollStart;
   }

   public String getDisplayString() {
      return "+" + this.xpPlus;
   }

   public void update() {
      if(this.xpTimer > 496) {
         this.yOffset = this.posY - (int)((double)(this.xpTimer - 496) / 16.0D * (double)this.posY);
      }

      --this.xpTimer;
   }

   public void renderNumber(Hud hud) {
      switch(this.scrollStart) {
      case 0:
         hud.drawString(Minecraft.getMinecraft().fontRenderer, this.getDisplayString(), this.posX - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.getDisplayString()), 0 + this.yOffset, -1);
         break;
      case 1:
         hud.drawString(Minecraft.getMinecraft().fontRenderer, this.getDisplayString(), this.posX - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.getDisplayString()), Minecraft.getMinecraft().displayHeight - this.yOffset, -1);
      }

   }
}
