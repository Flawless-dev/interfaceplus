package net.spellcraftgaming.interfaceplus.guiplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

public class GuiPanorama {

   private static Minecraft mc = Minecraft.getMinecraft();
   private static int panoramaTimer = 0;
   private static int panoramaTimer2 = 0;
   private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[]{new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png")};
   private static ResourceLocation field_110351_G = mc.getTextureManager().getDynamicTextureLocation("background", new DynamicTexture(256, 256));


   private static void drawPanorama() {
      Tessellator tessellator = Tessellator.instance;
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glDisable(3008);
      GL11.glDisable(2884);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      byte b0 = 8;

      for(int k = 0; k < b0 * b0; ++k) {
         GL11.glPushMatrix();
         float f1 = ((float)(k % b0) / (float)b0 - 0.5F) / 64.0F;
         float f2 = ((float)(k / b0) / (float)b0 - 0.5F) / 64.0F;
         float f3 = 0.0F;
         GL11.glTranslatef(f1, f2, f3);
         GL11.glRotatef(MathHelper.sin((float)panoramaTimer / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-((float)panoramaTimer) * 0.1F, 0.0F, 1.0F, 0.0F);

         for(int l = 0; l < 6; ++l) {
            GL11.glPushMatrix();
            if(l == 1) {
               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if(l == 2) {
               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if(l == 3) {
               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if(l == 4) {
               GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if(l == 5) {
               GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            mc.getTextureManager().bindTexture(titlePanoramaPaths[l]);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_I(16777215, 255 / (k + 1));
            float f4 = 0.0F;
            tessellator.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double)(0.0F + f4), (double)(0.0F + f4));
            tessellator.addVertexWithUV(1.0D, -1.0D, 1.0D, (double)(1.0F - f4), (double)(0.0F + f4));
            tessellator.addVertexWithUV(1.0D, 1.0D, 1.0D, (double)(1.0F - f4), (double)(1.0F - f4));
            tessellator.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double)(0.0F + f4), (double)(1.0F - f4));
            tessellator.draw();
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
         GL11.glColorMask(true, true, true, false);
      }

      tessellator.setTranslation(0.0D, 0.0D, 0.0D);
      GL11.glColorMask(true, true, true, true);
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GL11.glDepthMask(true);
      GL11.glEnable(2884);
      GL11.glEnable(2929);
   }

   private static void rotateAndBlurSkybox(float p_73968_1_, GuiScreen gui) {
      mc.getTextureManager().bindTexture(field_110351_G);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColorMask(true, true, true, false);
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      GL11.glDisable(3008);
      byte b0 = 3;

      for(int i = 0; i < b0; ++i) {
         tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float)(i + 1));
         int j = gui.width;
         int k = gui.height;
         float f1 = 0.0F;
         tessellator.addVertexWithUV((double)j, (double)k, 0.0D, (double)(0.0F + f1), 1.0D);
         tessellator.addVertexWithUV((double)j, 0.0D, 0.0D, (double)(1.0F + f1), 1.0D);
         tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)(1.0F + f1), 0.0D);
         tessellator.addVertexWithUV(0.0D, (double)k, 0.0D, (double)(0.0F + f1), 0.0D);
      }

      tessellator.draw();
      GL11.glEnable(3008);
      GL11.glColorMask(true, true, true, true);
   }

   public static void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_, GuiScreen gui) {
      mc.getFramebuffer().unbindFramebuffer();
      GL11.glViewport(0, 0, 256, 256);
      drawPanorama();
      rotateAndBlurSkybox(p_73971_3_, gui);
      rotateAndBlurSkybox(p_73971_3_, gui);
      rotateAndBlurSkybox(p_73971_3_, gui);
      rotateAndBlurSkybox(p_73971_3_, gui);
      rotateAndBlurSkybox(p_73971_3_, gui);
      rotateAndBlurSkybox(p_73971_3_, gui);
      rotateAndBlurSkybox(p_73971_3_, gui);
      mc.getFramebuffer().bindFramebuffer(true);
      GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      float f1 = gui.width > gui.height?120.0F / (float)gui.width:120.0F / (float)gui.height;
      float f2 = (float)gui.height * f1 / 256.0F;
      float f3 = (float)gui.width * f1 / 256.0F;
      tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
      int k = gui.width;
      int l = gui.height;
      tessellator.addVertexWithUV(0.0D, (double)l, 0.0D, (double)(0.5F - f2), (double)(0.5F + f3));
      tessellator.addVertexWithUV((double)k, (double)l, 0.0D, (double)(0.5F - f2), (double)(0.5F - f3));
      tessellator.addVertexWithUV((double)k, 0.0D, 0.0D, (double)(0.5F + f2), (double)(0.5F - f3));
      tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)(0.5F + f2), (double)(0.5F + f3));
      tessellator.draw();
   }

   public static void update() {
      ++panoramaTimer2;
      if(panoramaTimer2 > 1) {
         panoramaTimer2 = 0;
         ++panoramaTimer;
      }

   }

}
