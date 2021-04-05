package net.spellcraftgaming.interfaceplus.hudplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.ForgeHooks;
import net.spellcraftgaming.interfaceplus.helper.TimeHelper;
import net.spellcraftgaming.interfaceplus.hudplus.HudDefault;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

public class HudHotbar extends HudDefault {

   public HudHotbar(Minecraft mc, GuiIngameForge ingameGui) {
      super(mc, ingameGui);
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
         ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int width = res.getScaledWidth();
         int height = res.getScaledHeight();
         int var12 = super.mc.thePlayer.getFoodStats().getFoodLevel();
         ItemStack currentItem = super.mc.thePlayer.getCurrentEquippedItem();
         if(currentItem != null && currentItem.getItem() instanceof ItemFood && super.mc.thePlayer.getFoodStats().needFood() && settings.show_hunger_preview) {
            float value = (float)((ItemFood)super.mc.thePlayer.getCurrentEquippedItem().getItem()).func_150905_g(super.mc.thePlayer.getCurrentEquippedItem());
            int bonusHunger = (int)(value + (float)var12);
            if(bonusHunger > 20) {
               bonusHunger = 20;
            }

            drawCustomBar(true, this, 49, height - 16 - 10, 180, 10, (double)bonusHunger / 20.0D * 100.0D, super.colorBlack, 0, 0, staminaColor[5], staminaColor[4]);
         }

         if(!super.mc.thePlayer.isPotionActive(Potion.poison)) {
            drawCustomBar(true, this, 49, height - 16 - 10, 180, 10, (double)var12 / 20.0D * 100.0D, -16777216, 0, 0, staminaColor[1], staminaColor[0]);
         } else {
            drawCustomBar(true, this, 49, height - 16 - 10, 180, 10, (double)var12 / 20.0D * 100.0D, -16777216, 0, 0, staminaColor[3], staminaColor[2]);
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
         ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
         int width = res.getScaledWidth();
         int height = res.getScaledHeight();
         int var10 = MathHelper.ceiling_float_int(this.mc.thePlayer.getHealth());
         IAttributeInstance attrMaxHealth = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
         int var4 = (int)attrMaxHealth.getAttributeValue();
         if (!(this.mc.thePlayer.ridingEntity instanceof EntityLivingBase)) {
           if (!this.mc.thePlayer.isPotionActive(Potion.poison)) {
             drawCustomBar(true, this, 49, height - 16 - 40, 180, 10, var10 / var4 * 100.0D, -16777216, 0, 0, healthColor[1], healthColor[0]);
           } else {
             drawCustomBar(true, this, 49, height - 16 - 40, 180, 10, var10 / var4 * 100.0D, -16777216, 0, 0, healthColor[3], healthColor[2]);
           }
         }

         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

   }

   public void renderMountHealth(GameSettingsPlus settings)
   {
     if (!this.mc.gameSettings.showDebugInfo)
     {
       boolean isPlayerMounted = false;
       int health = 0;
       int healthMax = 0;
       if ((this.mc.thePlayer.ridingEntity instanceof EntityLivingBase))
       {
         isPlayerMounted = true;
         EntityLivingBase mount = (EntityLivingBase)this.mc.thePlayer.ridingEntity;
         health = (int)Math.ceil(mount.getHealth());
         healthMax = (int)mount.getMaxHealth();
       }
       if (isPlayerMounted)
       {
         this.mc.mcProfiler.endStartSection("mountHealth");
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
         int width = res.getScaledWidth();
         int height = res.getScaledHeight();
         drawCustomBar(false, this, 49, height - 32 - 24, 180, 10, health / healthMax * 100.0D, -16777216, 0, 0, -7667712, -4128768);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         bind(Gui.icons);
         this.mc.mcProfiler.endSection();
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
      this.drawTexturedModalRect(49, height + 7 - 22 - 23 - 9, 0, 0, 182, 22);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawTexturedModalRect(48 + inv.currentItem * 20, height - 22 + 7 - 23 - 1 - 9, 0, 22, 24, 22);
      GL11.glEnable('\u803a');
      RenderHelper.enableGUIStandardItemLighting();

      for(int i = 0; i < 9; ++i) {
         int x = 50 + i * 20 + 2;
         int z = height - 16 - 19;
         this.renderInventorySlot(i, x, z - 9, partialTicks);
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

   public void renderPlayerFace() {
      if(!super.mc.gameSettings.showDebugInfo) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int width = res.getScaledWidth();
         int height = res.getScaledHeight();
         this.bind(this.playerSkin(super.mc.thePlayer));
         GL11.glScaled(0.5D, 0.25D, 0.5D);
         this.drawTexturedModalRect(34, (height - 51 + 7) * 4, 32, 64, 32, 64);
         this.drawTexturedModalRect(34, (height - 51 + 7) * 4, 160, 64, 32, 64);
         GL11.glScaled(2.0D, 4.0D, 2.0D);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
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
      if ((this.mc.thePlayer.ridingEntity instanceof EntityLivingBase))
      {
        EntityLivingBase mount = (EntityLivingBase)this.mc.thePlayer.ridingEntity;
        var161 = (int)Math.ceil(mount.getHealth()) + "/" + (int)mount.getMaxHealth();
        drawString(this.fontrenderer, var161, 120, height - 32 - 30 + 7, -1);
        boolean isPlayerMounted = false;
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         if(settings.show_numbers_health) {
            if(isPlayerMounted) {
               this.drawString(super.fontrenderer, var161, 120, height - 32 - 30 + 7, -1);
            } else {
               this.drawString(super.fontrenderer, var141, 120, height - 32 - 30 + 7, -1);
            }
         }

         if(settings.show_numbers_stamina) {
            this.drawString(super.fontrenderer, var151, 120, height - 32 + 7, -1);
         }
      }

      this.drawString(super.fontrenderer, var9, var7 + 80, height - 9, -1);
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      }
   }

   public void renderWidget() {
      if(!super.mc.gameSettings.showDebugInfo) {
         this.bind(HudDefault.INTERFACE);
         ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int width = res.getScaledWidth();
         int height = res.getScaledHeight();
         this.drawTexturedModalRect(0, height - 16 - 52 + 7, 0, 170, 231, 52);
         this.bind(Gui.icons);
      }

   }

   public void renderClock(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         int clockColor = 16777215;
         ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int width = res.getScaledWidth();
         int height = res.getScaledHeight();
         if(settings.enable_clock_color) {
            clockColor = TimeHelper.getClockColor(super.mc, settings);
         }

         if(settings.enable_immersive_clock) {
            if(super.mc.thePlayer.inventory.hasItem(Items.clock)) {
               this.drawString(super.fontrenderer, TimeHelper.getTime(super.mc, settings), 4, height - 16 - 54, clockColor);
            }
         } else {
            this.drawString(super.fontrenderer, TimeHelper.getTime(super.mc, settings), 4, height - 16 - 54, clockColor);
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
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
      drawCustomBar(true, this, 0, height - 10, width, 10, (double)var2 * full, super.colorBlack, super.colorDefault[1], super.colorDefault[0], experienceColor[1], experienceColor[0]);
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD() && settings.show_numbers_experience) {
         super.fontrenderer.drawStringWithShadow(text1, 25 - super.fontrenderer.getStringWidth(text1) / 2, height - 22, 8453920);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }

   public void renderArmor() {
      super.mc.mcProfiler.startSection("armor");
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      this.bind(Gui.icons);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      int left = 46;
      int top = height - 64;
      int level = ForgeHooks.getTotalArmorValue(super.mc.thePlayer);

      for(int i = 1; level > 0 && i < 20; i += 2) {
         if(i < level) {
            this.drawTexturedModalRect(left + 48, top - 2, 34, 9, 9, 9);
         } else if(i == level) {
            this.drawTexturedModalRect(left + 48, top - 2, 25, 9, 9, 9);
         } else if(i > level) {
            this.drawTexturedModalRect(left + 48, top - 2, 16, 9, 9, 9);
         }

         left += 8;
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }
}
