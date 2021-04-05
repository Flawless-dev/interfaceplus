package net.spellcraftgaming.interfaceplus.hudplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.spellcraftgaming.interfaceplus.hudplus.HudDefault;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

public class HudExtendedWidget extends HudDefault {

   public HudExtendedWidget(Minecraft mc, GuiIngameForge ingameGui) {
      super(mc, ingameGui);
   }

   public void renderExperience(GameSettingsPlus settings) {
      super.mc.mcProfiler.startSection("expBar");
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      int[] experienceColor = new int[2];
      switch(settings.color_experience) {
      case 0:
         experienceColor[0] = super.colorRed[0];
         experienceColor[1] = super.colorRed[1];
         break;
      case 1:
         experienceColor[0] = super.colorBlue[0];
         experienceColor[1] = super.colorBlue[1];
         break;
      case 2:
         experienceColor[0] = super.colorGreen[0];
         experienceColor[1] = super.colorGreen[1];
         break;
      case 3:
         experienceColor[0] = super.colorYellow[0];
         experienceColor[1] = super.colorYellow[1];
         break;
      case 4:
         experienceColor[0] = super.colorWhite[0];
         experienceColor[1] = super.colorWhite[1];
         break;
      case 5:
         experienceColor[0] = super.colorGrey[0];
         experienceColor[1] = super.colorGrey[1];
      }

      int var2 = (int)((float)super.mc.thePlayer.xpBarCap() * super.mc.thePlayer.experience);
      String text1 = String.valueOf(super.mc.thePlayer.experienceLevel);
      double full = 100.0D / (double)super.mc.thePlayer.xpBarCap();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      drawCustomBar(true, this, 49, 35, 88, 8, (double)var2 * full, super.colorBlack, 0, 0, experienceColor[1], experienceColor[0]);
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD() && settings.show_numbers_experience) {
         super.fontrenderer.drawStringWithShadow(text1, 38 - super.fontrenderer.getStringWidth(text1) / 2, 38, 8453920);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }

   public void renderMountHealth(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo) {
         boolean isPlayerMounted = false;
         int health = 0;
         int healthMax = 0;
         if(super.mc.thePlayer.ridingEntity instanceof EntityLivingBase) {
            isPlayerMounted = true;
            EntityLivingBase mount = (EntityLivingBase)super.mc.thePlayer.ridingEntity;
            health = (int)Math.ceil((double)mount.getHealth());
            healthMax = (int)mount.getMaxHealth();
         }

         if(isPlayerMounted) {
            super.mc.mcProfiler.endStartSection("mountHealth");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            drawCustomBar(true, this, 53, 54, 88, 8, (double)health / (double)healthMax * 100.0D, super.colorBlack, 0, 0, -7667712, -4128768);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bind(Gui.icons);
            super.mc.mcProfiler.endSection();
         }
      }

   }

   public void renderHotbar(float partialTicks) {
      super.mc.mcProfiler.startSection("actionBar");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      super.mc.renderEngine.bindTexture(HudDefault.WIDGITS);
      InventoryPlayer inv = super.mc.thePlayer.inventory;
      this.drawTexturedModalRect(width / 2 - 91, height - 13 - 10, 0, 0, 182, 22);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawTexturedModalRect(width / 2 - 91 - 1 + inv.currentItem * 20, height - 22 - 2, 0, 22, 24, 24);
      GL11.glEnable('\u803a');
      RenderHelper.enableGUIStandardItemLighting();

      for(int i = 0; i < 9; ++i) {
         int x = width / 2 - 90 + i * 20 + 2;
         int z = height - 16 - 4;
         this.renderInventorySlot(i, x, z, partialTicks);
      }

      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }

   public void renderInventorySlot(int p_73832_1_, int p_73832_2_, int p_73832_3_, float p_73832_4_) {
      ItemStack itemstack = super.mc.thePlayer.inventory.mainInventory[p_73832_1_];
      if(itemstack != null) {
         float f1 = (float)itemstack.animationsToGo - p_73832_4_;
         if(f1 > 0.0F) {
            GL11.glPushMatrix();
            float f2 = 1.0F + f1 / 5.0F;
            GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
            GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
         }

         HudDefault.itemRenderer.renderItemAndEffectIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), itemstack, p_73832_2_, p_73832_3_);
         if(f1 > 0.0F) {
            GL11.glPopMatrix();
         }

         HudDefault.itemRenderer.renderItemOverlayIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), itemstack, p_73832_2_, p_73832_3_);
      }

   }

   public void renderFood(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo) {
         super.mc.mcProfiler.startSection("food");
         int[] staminaColor = new int[6];
         switch(settings.color_stamina) {
         case 0:
            staminaColor[0] = super.colorRed[0];
            staminaColor[1] = super.colorRed[1];
            staminaColor[2] = super.colorRed[0] + 17408;
            staminaColor[3] = super.colorRed[1] + 17408;
            staminaColor[4] = super.colorRed[0] + 3342336;
            staminaColor[5] = super.colorRed[1] + 2752512;
            break;
         case 1:
            staminaColor[0] = super.colorBlue[0];
            staminaColor[1] = super.colorBlue[1];
            staminaColor[2] = super.colorBlue[0] + 17408;
            staminaColor[3] = super.colorBlue[1] + 17408;
            staminaColor[4] = super.colorBlue[0] + 7485;
            staminaColor[5] = super.colorBlue[1] + 7485;
            break;
         case 2:
            staminaColor[0] = super.colorGreen[0];
            staminaColor[1] = super.colorGreen[1];
            staminaColor[2] = super.colorGreen[0] + 4456448;
            staminaColor[3] = super.colorGreen[1] + 4456448;
            staminaColor[4] = super.colorGreen[0] + 6697779;
            staminaColor[5] = super.colorGreen[1] + 6697779;
            break;
         case 3:
            staminaColor[0] = super.colorYellow[0];
            staminaColor[1] = super.colorYellow[1];
            staminaColor[2] = super.colorYellow[0] + 4352;
            staminaColor[3] = super.colorYellow[1] + 8704;
            staminaColor[4] = super.colorYellow[0] - 3355392;
            staminaColor[5] = super.colorYellow[1] - 4473856;
            break;
         case 4:
            staminaColor[0] = super.colorWhite[0];
            staminaColor[1] = super.colorWhite[1];
            staminaColor[2] = super.colorWhite[0] - 2236962;
            staminaColor[3] = super.colorWhite[1] - 2236962;
            staminaColor[4] = super.colorWhite[0] - 3355443;
            staminaColor[5] = super.colorWhite[1] - 5592405;
            break;
         case 5:
            staminaColor[0] = super.colorGrey[0];
            staminaColor[1] = super.colorGrey[1];
            staminaColor[2] = super.colorGrey[0] - 2236962;
            staminaColor[3] = super.colorGrey[1] - 2236962;
            staminaColor[4] = super.colorGrey[0] + 2236962;
            staminaColor[5] = super.colorGrey[1] + 2236962;
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         int var12 = super.mc.thePlayer.getFoodStats().getFoodLevel();
         ItemStack currentItem = super.mc.thePlayer.getCurrentEquippedItem();
         if(currentItem != null && currentItem.getItem() instanceof ItemFood && super.mc.thePlayer.getFoodStats().needFood() && settings.show_hunger_preview) {
            float value = (float)((ItemFood)super.mc.thePlayer.getCurrentEquippedItem().getItem()).func_150905_g(super.mc.thePlayer.getCurrentEquippedItem());
            int bonusHunger = (int)(value + (float)var12);
            if(bonusHunger > 20) {
               bonusHunger = 20;
            }

            drawCustomBar(true, this, 49, 22, 110, 12, (double)bonusHunger / 20.0D * 100.0D, super.colorBlack, 0, 0, staminaColor[5], staminaColor[4]);
         }

         if(!super.mc.thePlayer.isPotionActive(Potion.hunger)) {
            drawCustomBar(true, this, 49, 22, 110, 12, (double)var12 / 20.0D * 100.0D, super.colorBlack, 0, 0, staminaColor[1], staminaColor[0]);
         } else {
            drawCustomBar(true, this, 49, 22, 110, 12, (double)var12 / 20.0D * 100.0D, super.colorBlack, 0, 0, staminaColor[3], staminaColor[2]);
         }

         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

   }

   public void renderHealth(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo) {
         super.mc.mcProfiler.startSection("health");
         int[] healthColor = new int[4];
         switch(settings.color_health) {
         case 0:
            healthColor[0] = super.colorRed[0];
            healthColor[1] = super.colorRed[1];
            healthColor[2] = super.colorRed[0] + 17408;
            healthColor[3] = super.colorRed[1] + 17408;
            break;
         case 1:
            healthColor[0] = super.colorBlue[0];
            healthColor[1] = super.colorBlue[1];
            healthColor[2] = super.colorBlue[0] + 17408;
            healthColor[3] = super.colorBlue[1] + 17408;
            break;
         case 2:
            healthColor[0] = super.colorGreen[0];
            healthColor[1] = super.colorGreen[1];
            healthColor[2] = super.colorGreen[0] + 4456448;
            healthColor[3] = super.colorGreen[1] + 4456448;
            break;
         case 3:
            healthColor[0] = super.colorYellow[0];
            healthColor[1] = super.colorYellow[1];
            healthColor[2] = super.colorYellow[0] + 4352;
            healthColor[3] = super.colorYellow[1] + 8704;
            break;
         case 4:
            healthColor[0] = super.colorWhite[0];
            healthColor[1] = super.colorWhite[1];
            healthColor[2] = super.colorWhite[0] - 2236962;
            healthColor[3] = super.colorWhite[1] - 2236962;
            break;
         case 5:
            healthColor[0] = super.colorGrey[0];
            healthColor[1] = super.colorGrey[1];
            healthColor[2] = super.colorGrey[0] - 2236962;
            healthColor[3] = super.colorGrey[1] - 2236962;
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         int var10 = MathHelper.ceiling_float_int(super.mc.thePlayer.getHealth());
         IAttributeInstance attrMaxHealth = super.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
         int var4 = (int)attrMaxHealth.getAttributeValue();
         if(!super.mc.thePlayer.isPotionActive(Potion.poison)) {
            drawCustomBar(true, this, 49, 9, 110, 12, (double)var10 / (double)var4 * 100.0D, super.colorBlack, 0, 0, healthColor[1], healthColor[0]);
         } else {
            drawCustomBar(true, this, 49, 9, 110, 12, (double)var10 / (double)var4 * 100.0D, super.colorBlack, 0, 0, healthColor[3], healthColor[2]);
         }

         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

   }

   public void renderNumbers(GameSettingsPlus settings) {
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      int var10 = MathHelper.ceiling_float_int(super.mc.thePlayer.getHealth());
      IAttributeInstance attrMaxHealth = super.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
      int var4 = (int)attrMaxHealth.getAttributeValue();
      int var12 = super.mc.thePlayer.getFoodStats().getFoodLevel();
      String var141 = var10 + "/" + var4;
      String var151 = var12 + "/" + "20";
      int var2 = (int)((float)super.mc.thePlayer.xpBarCap() * super.mc.thePlayer.experience);
      String var9 = var2 + "/" + super.mc.thePlayer.xpBarCap();
      int var7 = width / 2 - 91;
      String var161 = "";
      if(super.mc.thePlayer.ridingEntity instanceof EntityLivingBase) {
         EntityLivingBase mount = (EntityLivingBase)super.mc.thePlayer.ridingEntity;
         var161 = (int)Math.ceil((double)mount.getHealth()) + "/" + (int)mount.getMaxHealth();
      }

      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         if(settings.show_numbers_health) {
            this.drawString(super.fontrenderer, var141, 90, 11, -1);
         }

         if(settings.show_numbers_stamina) {
            this.drawString(super.fontrenderer, var151, 90, 24, -1);
         }
      }

      GL11.glScaled(0.5D, 0.5D, 0.5D);
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD() && settings.show_numbers_health) {
         this.drawString(super.fontrenderer, var161, 183, 102, -1);
      }

      if(settings.show_numbers_experience) {
         this.drawString(super.fontrenderer, var9, 180, 74, -1);
      }

      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
   }

   public void renderWidget() {
      if(!super.mc.gameSettings.showDebugInfo) {
         this.bind(HudDefault.INTERFACE);
         this.drawTexturedModalRect(0, 0, 0, 50, 162, 50);
         if(super.mc.thePlayer.ridingEntity instanceof EntityLivingBase) {
            this.drawTexturedModalRect(51, 44, 163, 0, 92, 20);
         }

         this.bind(Gui.icons);
      }

   }
}
