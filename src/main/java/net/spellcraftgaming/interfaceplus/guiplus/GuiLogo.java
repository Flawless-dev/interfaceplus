package net.spellcraftgaming.interfaceplus.guiplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.spellcraftgaming.interfaceplus.cache.Cache;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.guiplus.GuiMainMenuNew;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import org.lwjgl.opengl.GL11;

public class GuiLogo extends GuiScreen {

   private static final ResourceLocation SPCG_LOGO = new ResourceLocation("spellcraftgaming:interfaceplus/spellcraftgaming.png");
   private static final ResourceLocation BACKGROUND = new ResourceLocation("spellcraftgaming:interfaceplus/background.png");
   private int counter = 0;
   private long start = 0L;
   private boolean init = true;


   public void func_146278_c(int p_146278_1_) {
      GL11.glDisable(2896);
      GL11.glDisable(2912);
      Tessellator tessellator = Tessellator.instance;
      this.mc.getTextureManager().bindTexture(BACKGROUND);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float f = 32.0F;
      tessellator.startDrawingQuads();
      tessellator.setColorOpaque_I(4210752);
      tessellator.addVertexWithUV(0.0D, (double)this.height, 0.0D, 0.0D, (double)((float)this.height / f + (float)p_146278_1_));
      tessellator.addVertexWithUV((double)this.width, (double)this.height, 0.0D, (double)((float)this.width / f), (double)((float)this.height / f + (float)p_146278_1_));
      tessellator.addVertexWithUV((double)this.width, 0.0D, 0.0D, (double)((float)this.width / f), (double)p_146278_1_);
      tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, (double)p_146278_1_);
      tessellator.draw();
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
      this.func_146278_c(0);
      this.mc.getTextureManager().bindTexture(SPCG_LOGO);
      this.drawTexturedModalRect(this.width / 2 - 128, this.height / 2 - 128, 0, 0, 256, 256);
      if(this.init) {
         this.start = System.nanoTime();
         GuiButtonTooltip.initTooltips();
         InterfacePlus.cache = new Cache();
         this.init = false;
      }

      if(System.nanoTime() - this.start >= 1500000000L) {
         Minecraft var10000 = this.mc;
         GuiMainMenuNew var10001 = new GuiMainMenuNew(InterfacePlus.settings);
         InterfacePlus var10003 = InterfacePlus.instance;
         var10000.displayGuiScreen(var10001);
      }

   }

}
