package net.spellcraftgaming.interfaceplus.hudplus;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.spellcraftgaming.interfaceplus.hudplus.HudDefault;
import net.spellcraftgaming.interfaceplus.hudplus.HudExtendedWidget;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

public class HudFullTexture extends HudExtendedWidget {

   public HudFullTexture(Minecraft mc, GuiIngameForge ingameGui) {
      super(mc, ingameGui);
   }

   public void renderAirBar(GameSettingsPlus settings) {
      this.bind(HudDefault.INTERFACE);
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      int adjustedWidth = width / 2 - 91;
      int airAmount = super.mc.thePlayer.getAir();
      GL11.glEnable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      if(super.mc.thePlayer.isInsideOfMaterial(Material.water)) {
         this.drawTexturedModalRect(adjustedWidth + 21, height - 80, 0, 160, 141, 10);
         this.drawTexturedModalRect(adjustedWidth + 21, height - 80, 0, 140, (int)(141.0D * ((double)airAmount / 300.0D)), 10);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
   }

   public void renderExperience(GameSettingsPlus settings) {
      super.mc.mcProfiler.startSection("expBar");
      this.bind(HudDefault.INTERFACE);
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      int var2 = (int)((float)super.mc.thePlayer.xpBarCap() * super.mc.thePlayer.experience);
      String text1 = String.valueOf(super.mc.thePlayer.experienceLevel);
      double full = 100.0D / (double)super.mc.thePlayer.xpBarCap();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      this.drawTexturedModalRect(49, 35, 0, 132, (int)(88.0D * ((double)var2 / (double)super.mc.thePlayer.xpBarCap())), 8);
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD() && settings.show_numbers_experience) {
         super.fontrenderer.drawStringWithShadow(text1, 38 - super.fontrenderer.getStringWidth(text1) / 2, 38, 8453920);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }

   public void renderFood(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo) {
         super.mc.mcProfiler.startSection("food");
         this.bind(HudDefault.INTERFACE);
         int[] staminaColor = new int[]{super.colorGreen[0], super.colorGreen[1], super.colorGreen[0] + 4456448, super.colorGreen[1] + 4456448, super.colorGreen[0] + 6697779, super.colorGreen[1] + 6697779};
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

         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         this.drawTexturedModalRect(49, 22, 110, 100, (int)(110.0D * ((double)var12 / 20.0D)), 12);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

   }

   public void renderHealth(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo) {
         this.bind(HudDefault.INTERFACE);
         super.mc.mcProfiler.startSection("health");
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         int var10 = MathHelper.ceiling_float_int(super.mc.thePlayer.getHealth());
         IAttributeInstance attrMaxHealth = super.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
         int var4 = (int)attrMaxHealth.getAttributeValue();
         this.drawTexturedModalRect(49, 9, 0, 100, (int)(110.0D * ((double)var10 / (double)var4)), 12);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

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
            this.bind(HudDefault.INTERFACE);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            this.drawTexturedModalRect(53, 54, 0, 124, (int)(88.0D * ((double)health / (double)healthMax)), 8);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bind(Gui.icons);
            super.mc.mcProfiler.endSection();
         }
      }

   }

   public void renderJumpBar(GameSettingsPlus settings) {
      super.mc.mcProfiler.startSection("jumpBar");
      this.bind(HudDefault.INTERFACE);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      int var7 = width / 2 - 91;
      float var14 = super.mc.thePlayer.getHorseJumpPower();
      int color = (int)(var14 * 100.0F);
      if(super.mc.thePlayer.ridingEntity instanceof EntityLivingBase) {
         this.drawTexturedModalRect(var7 + 21, height - 80, 0, 160, 141, 10);
         this.drawTexturedModalRect(var7 + 21, height - 80, 0, 150, (int)(141.0D * ((double)color / 100.0D)), 10);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }
}
