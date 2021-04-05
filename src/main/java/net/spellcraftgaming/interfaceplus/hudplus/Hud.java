package net.spellcraftgaming.interfaceplus.hudplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

public abstract class Hud extends Gui {

   protected Minecraft mc;
   protected FontRenderer fontrenderer;
   protected int[] colorRed = new int[]{-4128768, -7667712};
   protected int[] colorBlue = new int[]{-16753726, -16760180};
   protected int[] colorYellow = new int[]{-1118720, -2434560};
   protected int[] colorGreen = new int[]{-12860928, -13923328};
   protected int[] colorWhite = new int[]{-855310, -1644826};
   protected int[] colorGrey = new int[]{-4210753, -8355712};
   protected int[] colorDefault = new int[]{-11776948, -12763843};
   protected int colorBlack = -16777216;


   public Hud(Minecraft mc, GuiIngameForge ingameGui) {
      this.mc = mc;
      this.fontrenderer = mc.fontRenderer;
   }

   protected static void drawOutline(Gui gui, int x, int y, int x2, int y2, int color) {
      Gui.drawRect(x, y, x + x2, y + 1, color);
      Gui.drawRect(x, y, x + 1, y + y2, color);
      Gui.drawRect(x + x2 - 1, y, x + x2, y + y2, color);
      Gui.drawRect(x, y + y2 - 1, x + x2, y + y2, color);
   }

   protected static void drawCustomBar(boolean outline, Gui gui, int x, int y, int x2, int y2, double par6, int outlineColor, int groundColor2, int groundColor1, int barColor2, int barColor1) {
      if(par6 < 0.0D) {
         par6 = 0.0D;
      }

      int var1 = (int)Math.round(par6 / 100.0D * (double)(x2 - 2));
      if(outline) {
         drawOutline(gui, x, y, x2, y2, outlineColor);
      }

      int var2 = x2 - 2;
      if(var2 < 0) {
         var2 = 0;
      }

      int var3 = y2 - 2;
      if(var3 < 0) {
         var3 = 0;
      }

      int var4 = (int)Math.round((double)var3 / 6.0D * 2.75D);
      Gui.drawRect(x + 1, y + 1, x + 1 + var1, y + var4 + 1, barColor1);
      Gui.drawRect(x + 1, y + 1 + var4, x + 1 + var1, y + var3 + 1, barColor2);
      if(var2 - var1 > 0) {
         Gui.drawRect(x + 1 + var1, y + 1, x + 1 + var2, y + var4 + 1, groundColor1);
         Gui.drawRect(x + 1 + var1, y + 1 + var4, x + 1 + var2, y + var3 + 1, groundColor2);
      }

   }

   public abstract void renderAirBar(GameSettingsPlus var1);

   public abstract void renderCrosshair();

   public abstract void renderArmor();

   public abstract void renderExperience(GameSettingsPlus var1);

   public abstract void renderFood(GameSettingsPlus var1);

   public abstract void renderHealth(GameSettingsPlus var1);

   public abstract void renderMountHealth(GameSettingsPlus var1);

   public abstract void renderJumpBar(GameSettingsPlus var1);

   public abstract void renderHotbar(float var1);

   public abstract void renderExtras(GameSettingsPlus var1);

   public abstract void renderInventorySlot(int var1, int var2, int var3, float var4);

   public abstract void renderPlayerFace();

   public abstract void renderNumbers(GameSettingsPlus var1);

   public abstract void renderStatusEffects(GameSettingsPlus var1);

   public abstract void renderArmorHelper(GameSettingsPlus var1);

   public abstract void renderClock(GameSettingsPlus var1);

   public abstract void renderWidget();

   protected void bind(ResourceLocation res) {
      this.mc.getTextureManager().bindTexture(res);
   }

   protected ResourceLocation playerSkin(AbstractClientPlayer par1AbstractClientPlayer) {
      return par1AbstractClientPlayer.getLocationSkin();
   }
}
