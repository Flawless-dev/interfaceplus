package net.spellcraftgaming.interfaceplus.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

public class RenderHealthbar {

   protected static final int[] colorRed = new int[]{12648448, 9109504};
   protected static final int[] colorBlue = new int[]{23490, 17036};
   protected static final int[] colorYellow = new int[]{15658496, 14342656};
   protected static final int[] colorGreen = new int[]{3916288, 2853888};
   protected static final int[] colorWhite = new int[]{15921906, 15132390};
   protected static final int[] colorGrey = new int[]{12566463, 8421504};
   protected static final int HEALTHBAR_WIDTH = 25;
   protected static final double HEALTHBAR_HEIGHT = 6.0D;
   protected static int[] colorHealth = new int[2];
   protected static GameSettingsPlus settings;


   public RenderHealthbar(GameSettingsPlus settings) {
      settings = settings;
      setNewColor(settings);
   }

   public static void setNewColor(GameSettingsPlus settings) {
      switch(settings.color_healthbar) {
      case 0:
         colorHealth[0] = colorRed[0];
         colorHealth[1] = colorRed[1];
         break;
      case 1:
         colorHealth[0] = colorBlue[0];
         colorHealth[1] = colorBlue[1];
         break;
      case 2:
         colorHealth[0] = colorGreen[0];
         colorHealth[1] = colorGreen[1];
         break;
      case 3:
         colorHealth[0] = colorYellow[0];
         colorHealth[1] = colorYellow[1];
         break;
      case 4:
         colorHealth[0] = colorWhite[0];
         colorHealth[1] = colorWhite[1];
         break;
      case 5:
         colorHealth[0] = colorGrey[0];
         colorHealth[1] = colorGrey[1];
      }

   }

   @SubscribeEvent
   public void renderHealthbar(Post event) {
      if(settings.show_healthbar < 2) {
         float offset = 0.5F;
         if(event.entity instanceof EntityChicken) {
            offset += 0.15F;
         }

         if(event.entity instanceof EntityPlayer) {
            offset += 0.25F;
         }

         if(event.entity instanceof EntityHorse) {
            offset += 0.2F;
         }

         if(event.entity instanceof EntityHorse && ((EntityHorse)event.entity).riddenByEntity != null) {
            return;
         }

         if(event.entity == Minecraft.getMinecraft().thePlayer && Minecraft.getMinecraft().currentScreen != null) {
            return;
         }

         Vec3 vec3 = Minecraft.getMinecraft().thePlayer.getLook(1.0F).normalize();
         Vec3 vec31 = Vec3.createVectorHelper(event.entity.posX - Minecraft.getMinecraft().thePlayer.posX, event.entity.boundingBox.minY + (double)(event.entity.height / 2.0F) - (event.entity.posY + (double)event.entity.getEyeHeight()), event.entity.posZ - Minecraft.getMinecraft().thePlayer.posZ);
         double d0 = vec31.lengthVector();
         vec31 = vec31.normalize();
         double d1 = vec3.dotProduct(vec31);
         boolean lookAt = d1 > 1.0D - 0.025D / d0 && Minecraft.getMinecraft().thePlayer.canEntityBeSeen(event.entity);
         if(settings.show_healthbar != 1) {
            lookAt = true;
         }

         if(lookAt) {
            byte distance = 16;
            if(settings.show_healthbar == 1) {
               distance = 64;
            }

            double d3 = event.entity.getDistanceSqToEntity(Minecraft.getMinecraft().thePlayer);
            //String p_147906_2_ = event.entity.getHealth() + "/" + event.entity.getMaxHealth();
            if (d3 <= distance * distance)
            {
               FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
               float f = 1.6F;
               float f1 = 0.016666668F * f;
               GL11.glPushMatrix();
               GL11.glTranslatef((float)event.x + 0.0F, (float)event.y + event.entity.height + offset, (float)event.z);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);
               GL11.glScalef(-f1, -f1, f1);
               GL11.glDisable(2896);
               GL11.glEnable(3042);
               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
               GL11.glDisable(3553);
               GL11.glDepthMask(true);
               switch(settings.healthbartype) {
               case 0:
                  typeDefault(event);
                  break;
               case 1:
                  typeSmall(event);
                  break;
               case 2:
                  typeSlim(event);
               }

               GL11.glEnable(3553);
               GL11.glDisable(3042);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glPopMatrix();
            }
         }
      }

   }

   protected static void typeDefault(Post event) {
      float width = 25.0F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_I(0, 160);
      tessellator.addVertex((double)(-width) - 0.5D, -1.5D, 0.01D);
      tessellator.addVertex((double)(-width) - 0.5D, 5.5D, 0.01D);
      tessellator.addVertex((double)width + 0.5D, 5.5D, 0.01D);
      tessellator.addVertex((double)width + 0.5D, -1.5D, 0.01D);
      tessellator.draw();
      tessellator.startDrawingQuads();
      width = event.entity.getHealth() / event.entity.getMaxHealth() * 25.0F;
      tessellator.setColorRGBA_I(colorHealth[0], 255);
      tessellator.addVertex((double)(-width), -1.0D, 0.005D);
      tessellator.addVertex((double)(-width), 2.0D, 0.005D);
      tessellator.addVertex((double)width, 2.0D, 0.005D);
      tessellator.addVertex((double)width, -1.0D, 0.005D);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_I(colorHealth[1], 255);
      tessellator.addVertex((double)(-width), 2.0D, 0.005D);
      tessellator.addVertex((double)(-width), 5.0D, 0.005D);
      tessellator.addVertex((double)width, 5.0D, 0.005D);
      tessellator.addVertex((double)width, 2.0D, 0.005D);
      tessellator.draw();
      if(isHurt(event.entity)) {
         tessellator.startDrawingQuads();
         tessellator.setColorRGBA_I(16777215, 68);
         tessellator.addVertex((double)(-width), -1.0D, 0.0D);
         tessellator.addVertex((double)(-width), 5.0D, 0.0D);
         tessellator.addVertex((double)width, 5.0D, 0.0D);
         tessellator.addVertex((double)width, -1.0D, 0.0D);
         tessellator.draw();
      }

   }

   protected static void typeSmall(Post event) {
      float width = 20.0F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_I(0, 160);
      tessellator.addVertex((double)(-width) - 0.5D, -1.5D, 0.01D);
      tessellator.addVertex((double)(-width) - 0.5D, 3.5D, 0.01D);
      tessellator.addVertex((double)width + 0.5D, 3.5D, 0.01D);
      tessellator.addVertex((double)width + 0.5D, -1.5D, 0.01D);
      tessellator.draw();
      tessellator.startDrawingQuads();
      width = event.entity.getHealth() / event.entity.getMaxHealth() * 20.0F;
      tessellator.setColorRGBA_I(colorHealth[0], 255);
      tessellator.addVertex((double)(-width), -1.0D, 0.005D);
      tessellator.addVertex((double)(-width), 1.0D, 0.005D);
      tessellator.addVertex((double)width, 1.0D, 0.005D);
      tessellator.addVertex((double)width, -1.0D, 0.005D);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_I(colorHealth[1], 255);
      tessellator.addVertex((double)(-width), 1.0D, 0.005D);
      tessellator.addVertex((double)(-width), 3.0D, 0.005D);
      tessellator.addVertex((double)width, 3.0D, 0.005D);
      tessellator.addVertex((double)width, 1.0D, 0.005D);
      tessellator.draw();
      if(isHurt(event.entity)) {
         tessellator.startDrawingQuads();
         tessellator.setColorRGBA_I(16777215, 68);
         tessellator.addVertex((double)(-width), -1.0D, 0.0D);
         tessellator.addVertex((double)(-width), 3.0D, 0.0D);
         tessellator.addVertex((double)width, 3.0D, 0.0D);
         tessellator.addVertex((double)width, -1.0D, 0.0D);
         tessellator.draw();
      }

   }

   protected static void typeSlim(Post event) {
      float width = 16.0F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_I(0, 160);
      tessellator.addVertex((double)(-width) - 0.5D, -1.5D, 0.01D);
      tessellator.addVertex((double)(-width) - 0.5D, 1.0D, 0.01D);
      tessellator.addVertex((double)width + 0.5D, 1.0D, 0.01D);
      tessellator.addVertex((double)width + 0.5D, -1.5D, 0.01D);
      tessellator.draw();
      tessellator.startDrawingQuads();
      width = event.entity.getHealth() / event.entity.getMaxHealth() * 16.0F;
      tessellator.setColorRGBA_I(colorHealth[0], 255);
      tessellator.addVertex((double)(-width), -1.0D, 0.005D);
      tessellator.addVertex((double)(-width), 0.5D, 0.005D);
      tessellator.addVertex((double)width, 0.5D, 0.005D);
      tessellator.addVertex((double)width, -1.0D, 0.005D);
      tessellator.draw();
      if(isHurt(event.entity)) {
         tessellator.startDrawingQuads();
         tessellator.setColorRGBA_I(16777215, 68);
         tessellator.addVertex((double)(-width), -1.0D, 0.0D);
         tessellator.addVertex((double)(-width), 0.5D, 0.0D);
         tessellator.addVertex((double)width, 0.5D, 0.0D);
         tessellator.addVertex((double)width, -1.0D, 0.0D);
         tessellator.draw();
      }

   }

   protected static boolean isHurt(EntityLivingBase entity) {
      return entity.hurtTime > 0;
   }

}
