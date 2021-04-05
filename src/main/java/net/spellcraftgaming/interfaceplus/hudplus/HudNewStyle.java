package net.spellcraftgaming.interfaceplus.hudplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.ForgeHooks;
import net.spellcraftgaming.interfaceplus.cache.Cache;
import net.spellcraftgaming.interfaceplus.helper.TimeHelper;
import net.spellcraftgaming.interfaceplus.hudplus.HudDefault;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

public class HudNewStyle extends HudDefault {

   public HudNewStyle(Minecraft mc, GuiIngameForge ingameGui) {
      super(mc, ingameGui);
   }

   public void renderWidget() {}

   public void renderPlayerFace() {
      if(!super.mc.gameSettings.showDebugInfo) {
         drawRect(2, 2, 22, 22, -1610612736);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         this.bind(this.playerSkin(super.mc.thePlayer));
         GL11.glScaled(0.5D, 0.25D, 0.5D);
         this.drawTexturedModalRect(8, 16, 32, 64, 32, 64);
         this.drawTexturedModalRect(8, 16, 160, 64, 32, 64);
         GL11.glScaled(2.0D, 4.0D, 2.0D);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
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
         int var10000 = settings.render_player_face?24:2;
         int var7;
         if(settings.show_numbers_health) {
            InterfacePlus var10001 = InterfacePlus.instance;
            var7 = InterfacePlus.cache.posX - 1;
         } else {
            var7 = 0;
         }

         int posX = var10000 + var7;
         this.drawSpikedRect(posX, posX, 3, 3, 97, 83, 10, 10, -1610612736);
         this.drawSpikedRect(posX + 2, posX + 2, 5, 5, 89, 79, 6, 6, 553648127);
         if(!super.mc.thePlayer.isPotionActive(Potion.poison)) {
            this.drawSpikedRect(posX + 2, posX + 2, 5, 5, (int)(89.0D * ((double)var10 / (double)var4)), (int)(89.0D * ((double)var10 / (double)var4)) - 10, 6, 6, healthColor[0]);
         } else {
            this.drawSpikedRect(posX + 2, posX + 2, 5, 5, (int)(89.0D * ((double)var10 / (double)var4)), (int)(89.0D * ((double)var10 / (double)var4)) - 10, 6, 6, healthColor[2]);
         }

         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
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
         GL11.glBlendFunc(770, 771);
         int var10000 = settings.render_player_face?24:2;
         int var8;
         if(settings.show_numbers_stamina) {
            InterfacePlus var10001 = InterfacePlus.instance;
            var8 = InterfacePlus.cache.posX2 - 1;
         } else {
            var8 = 0;
         }

         int posX = var10000 + var8;
         this.drawSpikedRect(posX, posX, 13, 13, 70, 58, 8, 8, -1610612736);
         this.drawSpikedRect(posX + 2, posX + 2, 13, 13, 64, 54, 6, 6, 553648127);
         int var12 = super.mc.thePlayer.getFoodStats().getFoodLevel();
         ItemStack currentItem = super.mc.thePlayer.getCurrentEquippedItem();
         if(currentItem != null && currentItem.getItem() instanceof ItemFood && super.mc.thePlayer.getFoodStats().needFood() && settings.show_hunger_preview) {
            float value = (float)((ItemFood)super.mc.thePlayer.getCurrentEquippedItem().getItem()).func_150905_g(super.mc.thePlayer.getCurrentEquippedItem());
            int bonusHunger = (int)(value + (float)var12);
            if(bonusHunger > 20) {
               bonusHunger = 20;
            }

            this.drawSpikedRect(posX + 2, posX + 2, 13, 13, (int)(64.0D * ((double)bonusHunger / 20.0D)), (int)(63.0D * ((double)bonusHunger / 20.0D)) - 10, 6, 6, staminaColor[4]);
         }

         if(!super.mc.thePlayer.isPotionActive(Potion.hunger)) {
            this.drawSpikedRect(posX + 2, posX + 2, 13, 13, (int)(64.0D * ((double)var12 / 20.0D)), (int)(64.0D * ((double)var12 / 20.0D)) - 10, 6, 6, staminaColor[0]);
         } else {
            this.drawSpikedRect(posX + 2, posX + 2, 13, 13, (int)(64.0D * ((double)var12 / 20.0D)), (int)(64.0D * ((double)var12 / 20.0D)) - 10, 6, 6, staminaColor[2]);
         }

         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

   }

   public void drawSpikedRect(int posX1, int posX2, int posY1, int posY2, int width1, int width2, int height1, int height2, int color) {
      float f3 = (float)(color >> 24 & 255) / 255.0F;
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      Tessellator tessellator = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColor4f(f, f1, f2, f3);
      GL11.glPushMatrix();
      tessellator.startDrawingQuads();
      tessellator.addVertex((double)posX1, (double)posY1 + (double)height1, 0.0D);
      tessellator.addVertex((double)posX2 + (double)width2, (double)posY2 + (double)height2, 0.0D);
      tessellator.addVertex((double)posX1 + (double)width1, (double)posY2, 0.0D);
      tessellator.addVertex((double)posX2, (double)posY1, 0.0D);
      tessellator.draw();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public void renderNumbers(GameSettingsPlus settings) {
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
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
         EntityLivingBase posX = (EntityLivingBase)super.mc.thePlayer.ridingEntity;
         var161 = (int)Math.ceil((double)posX.getHealth()) + "/" + (int)posX.getMaxHealth();
      }

      int posX1;
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         InterfacePlus var10000;
         if(settings.show_numbers_health) {
            posX1 = super.fontrenderer.getStringWidth(var4 + "/" + var4) / 2;
            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.posX = posX1 + 4;
            this.drawSpikedRect(settings.render_player_face?23:2, settings.render_player_face?23:2, 4, 4, posX1 + 4, posX1 + 4, 8, 8, -1610612736);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            this.drawCenteredString(super.fontrenderer, var141, 66, 12, -1);
            GL11.glScaled(2.0D, 2.0D, 2.0D);
         }

         if(settings.show_numbers_stamina) {
            posX1 = super.fontrenderer.getStringWidth("20/20") / 2;
            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.posX2 = posX1 + 4;
            this.drawSpikedRect(settings.render_player_face?23:2, settings.render_player_face?23:2, 12, 12, posX1 + 4, posX1 + 4, 8, 8, -1610612736);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            this.drawCenteredString(super.fontrenderer, var151, 66, 28, -1);
            GL11.glScaled(2.0D, 2.0D, 2.0D);
         }
      }

      if(settings.show_numbers_experience) {
         posX1 = super.fontrenderer.getStringWidth(var9) / 2;
         this.drawSpikedRect(1, 1, height - 14, height - 14, posX1 + 4, posX1 + 4, 8, 8, -1610612736);
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         this.drawString(super.fontrenderer, var9, 6, (height - 12) * 2 + 1, -1);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
      }

      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD() && settings.show_numbers_health && super.mc.thePlayer.isRiding()) {
         int var18 = settings.render_player_face?24:2;
         int var19;
         if(settings.show_numbers_stamina) {
            InterfacePlus var10001 = InterfacePlus.instance;
            var19 = InterfacePlus.cache.posX - 1;
         } else {
            var19 = 0;
         }

         posX1 = var18 + var19;
         int width2 = super.fontrenderer.getStringWidth(var161) / 2;
         this.drawSpikedRect(posX1, posX1, 24, 24, width2 + 4, width2 + 4, 5, 5, -1610612736);
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         this.drawString(super.fontrenderer, var161, posX1 * 2 + 4, 48, -1);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
   }

   public void renderMountHealth(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo) {
         boolean isPlayerMounted = false;
         int health = 0;
         int healthMax = 0;
         if(super.mc.thePlayer.ridingEntity instanceof EntityLivingBase) {
            isPlayerMounted = true;
            EntityLivingBase posX = (EntityLivingBase)super.mc.thePlayer.ridingEntity;
            health = (int)Math.ceil((double)posX.getHealth());
            healthMax = (int)posX.getMaxHealth();
         }

         if(isPlayerMounted) {
            super.mc.mcProfiler.endStartSection("mountHealth");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            int var10000 = settings.render_player_face?24:2;
            int var7;
            if(settings.show_numbers_stamina) {
               InterfacePlus var10001 = InterfacePlus.instance;
               var7 = InterfacePlus.cache.posX - 1;
            } else {
               var7 = 0;
            }

            int posX1 = var10000 + var7;
            this.drawSpikedRect(posX1, posX1, 21, 21, 58, 54, 3, 3, -1610612736);
            this.drawSpikedRect(posX1 + 2, posX1 + 2, 21, 21, (int)((double)health / (double)healthMax * 53.0D), (int)((double)health / (double)healthMax * 53.0D - 2.0D), 1, 1, -4128768);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bind(Gui.icons);
            super.mc.mcProfiler.endSection();
         }
      }

   }

   public void renderClock(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3042);
         GL11.glEnable(3008);
         GL11.glBlendFunc(770, 771);
         int clockColor = 16777215;
         if(settings.enable_clock_color) {
            clockColor = TimeHelper.getClockColor(super.mc, settings);
         }

         int width2 = super.fontrenderer.getStringWidth(TimeHelper.getTime(super.mc, settings)) / 2;
         this.drawSpikedRect(2, 2, 23, 23, 20, 20, 6, 6, -1610612736);
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         if(settings.enable_immersive_clock) {
            if(super.mc.thePlayer.inventory.hasItem(Items.clock)) {
               this.drawCenteredString(super.fontrenderer, TimeHelper.getTime(super.mc, settings), 24, 48, clockColor);
            }
         } else {
            this.drawCenteredString(super.fontrenderer, TimeHelper.getTime(super.mc, settings), 24, 48, clockColor);
         }

         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public void renderExtras(GameSettingsPlus settings) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      InterfacePlus var10000 = InterfacePlus.instance;
      InterfacePlus.cache.offset = 0;
      var10000 = InterfacePlus.instance;
      int offset = InterfacePlus.cache.offset;
      ItemStack item = Minecraft.getMinecraft().thePlayer.getHeldItem();
      String s = "";
      if(settings.show_itemdurability && item != null && item.getMaxDamage() > 0) {
         this.drawSpikedRect(2, 2, 30 + offset, 30 + offset, 39, 39, 10, 10, -1610612736);
         s = item.getMaxDamage() - item.getItemDamageForDisplay() + "/" + item.getMaxDamage();
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         RenderHelper.enableGUIStandardItemLighting();
         HudDefault.itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, item, 6, 62 + offset * 2);
         GL11.glDisable(2896);
         this.drawCenteredString(super.fontrenderer, s, 52, 66 + offset * 2, -1);
         RenderHelper.disableStandardItemLighting();
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         var10000 = InterfacePlus.instance;
         InterfacePlus.cache.offset += 10;
      }

      var10000 = InterfacePlus.instance;
      offset = InterfacePlus.cache.offset;
      int x;
      int z;
      int y;
      Cache var11;
      InterfacePlus var10001;
      if(settings.show_arrowcount && item != null && super.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow) {
         x = Minecraft.getMinecraft().thePlayer.inventory.getSizeInventory();
         z = 0;
         var10000 = InterfacePlus.instance;
         var10001 = InterfacePlus.instance;
         var11 = InterfacePlus.cache;
         if(InterfacePlus.cache.renderDelay2 >= 60) {
            for(y = 0; y < x; ++y) {
               item = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(y);
               if(item != null && Item.getIdFromItem(item.getItem()) == Item.getIdFromItem(Items.arrow)) {
                  z += item.stackSize;
               }
            }

            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.count2 = z;
            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.updateDelay2();
         } else {
            var10000 = InterfacePlus.instance;
            z = InterfacePlus.cache.count2;
            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.updateDelay2();
         }

         this.drawSpikedRect(2, 2, 30 + offset, 30 + offset, 39, 39, 10, 10, -1610612736);
         s = "x " + z;
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         RenderHelper.enableGUIStandardItemLighting();
         HudDefault.itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(Items.arrow), 6, 62 + offset * 2);
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         this.drawCenteredString(super.fontrenderer, s, 52, 66 + offset * 2, -1);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         var10000 = InterfacePlus.instance;
         InterfacePlus.cache.offset += 10;
      }

      var10000 = InterfacePlus.instance;
      offset = InterfacePlus.cache.offset;
      if(settings.show_blockcount && item != null && super.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
         x = Minecraft.getMinecraft().thePlayer.inventory.getSizeInventory();
         z = 0;
         var10000 = InterfacePlus.instance;
         var10001 = InterfacePlus.instance;
         var11 = InterfacePlus.cache;
         if(InterfacePlus.cache.renderDelay >= 60) {
            for(y = 0; y < x; ++y) {
               item = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(y);
               if(item != null && Item.getIdFromItem(item.getItem()) == Item.getIdFromItem(super.mc.thePlayer.getCurrentEquippedItem().getItem())) {
                  z += item.stackSize;
               }
            }

            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.count = z;
            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.updateDelay();
         } else {
            var10000 = InterfacePlus.instance;
            z = InterfacePlus.cache.count;
            var10000 = InterfacePlus.instance;
            InterfacePlus.cache.updateDelay();
         }

         item = Minecraft.getMinecraft().thePlayer.getHeldItem();
         this.drawSpikedRect(2, 2, 30 + offset, 30 + offset, 39, 39, 10, 10, -1610612736);
         s = "x " + z;
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         RenderHelper.enableGUIStandardItemLighting();
         HudDefault.itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, item, 6, 62 + offset * 2);
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         this.drawCenteredString(super.fontrenderer, s, 52, 66 + offset * 2, -1);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         var10000 = InterfacePlus.instance;
         InterfacePlus.cache.offset += 10;
      }

      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void renderArmor() {
      super.mc.mcProfiler.startSection("armor");
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      this.bind(Gui.icons);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int left = width / 2 - 91;
      int top = height - 36;
      int level = ForgeHooks.getTotalArmorValue(super.mc.thePlayer);
      if(level > 0) {
         int width2 = 12 + super.fontrenderer.getStringWidth(String.valueOf(level)) + 2;
         GL11.glDisable(2896);
         this.drawSpikedRect(left, left, top, top, width2, width2, 10, 10, -1610612736);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3042);
         this.drawTexturedModalRect(left + 1, top + 1, 34, 9, 9, 9);
         super.fontrenderer.drawString(String.valueOf(level), left + 12, top + 2, -1);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }

      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }

   public void renderArmorHelper(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         super.mc.mcProfiler.startSection("armorhelper");
         ScaledResolution var1 = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int height = var1.getScaledHeight();
         this.bind(HudDefault.DAMAGE);
         boolean scale = true;

         for(int i11 = super.mc.thePlayer.inventory.armorInventory.length - 1; i11 >= 0; --i11) {
            if(super.mc.thePlayer.inventory.armorInventory[i11] != null && super.mc.thePlayer.inventory.armorInventory[i11].getItem() instanceof ItemArmor) {
               int i12 = super.mc.thePlayer.inventory.armorInventory[i11].getMaxDamage();
               ItemStack item = super.mc.thePlayer.inventory.armorInventory[i11];
               InterfacePlus var10004 = InterfacePlus.instance;
               int var10003 = 30 + InterfacePlus.cache.offset;
               InterfacePlus var10005 = InterfacePlus.instance;
               this.drawSpikedRect(2, 2, var10003, 30 + InterfacePlus.cache.offset, 39, 39, 10, 10, -1610612736);
               String s = item.getMaxDamage() - item.getItemDamageForDisplay() + "/" + item.getMaxDamage();
               GL11.glScaled(0.5D, 0.5D, 0.5D);
               InterfacePlus var10006 = InterfacePlus.instance;
               HudDefault.itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, item, 6, 62 + InterfacePlus.cache.offset * 2);
               GL11.glDisable(2896);
               var10005 = InterfacePlus.instance;
               this.drawCenteredString(super.fontrenderer, s, 52, 66 + InterfacePlus.cache.offset * 2, -1);
               GL11.glScaled(2.0D, 2.0D, 2.0D);
               InterfacePlus var10000 = InterfacePlus.instance;
               InterfacePlus.cache.offset += 10;
            }
         }

         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

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
      this.drawSpikedRect(23, 23, 22, 22, 18, 18, 7, 7, -1610612736);
      String text1 = String.valueOf(super.mc.thePlayer.experienceLevel);
      double full = (double)((width - 2) / super.mc.thePlayer.xpBarCap());
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      this.drawSpikedRect(0, 0, height - 6, height - 6, width, width, 6, 6, -1610612736);
      this.drawSpikedRect(1, 1, height - 5, height - 5, (int)((double)var2 * full), (int)((double)var2 * full), 4, 4, experienceColor[0]);
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD() && settings.show_numbers_experience) {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         this.drawCenteredString(super.fontrenderer, text1, 64, 47, 8453920);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
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
      this.drawSpikedRect(width / 2 - 91, width / 2 - 91, height - 22 - 4, height - 22 - 4, 182, 182, 2, 2, -1610612736);

      int i;
      for(i = 0; i < 10; ++i) {
         this.drawSpikedRect(width / 2 - 91 + i * 20, width / 2 - 91 + i * 20, height - 22 - 2, height - 22 - 2, 2, 2, 18, 18, -1610612736);
         if(i < 9) {
            this.drawSpikedRect(width / 2 - 91 + 2 + i * 20, width / 2 - 91 + 2 + i * 20, height - 22 - 2, height - 22 - 2, 18, 18, 18, 18, 1610612736);
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawSpikedRect(width / 2 - 91 + 2 + inv.currentItem * 20, width / 2 - 91 + 2 + inv.currentItem * 20, height - 22 - 2, height - 22 - 2, 18, 18, 18, 18, 687865855);
      GL11.glEnable('\u803a');
      RenderHelper.enableGUIStandardItemLighting();

      for(i = 0; i < 9; ++i) {
         int x = width / 2 - 90 + i * 20 + 2;
         int z = height - 16 - 3;
         this.renderInventorySlot(i, x, z - 4, partialTicks);
      }

      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }

   public void renderJumpBar(GameSettingsPlus settings) {
      super.mc.mcProfiler.startSection("jumpBar");
      int[] jumpColor = new int[2];
      switch(settings.color_jumpbar) {
      case 0:
         jumpColor[0] = super.colorRed[0];
         jumpColor[1] = super.colorRed[1];
         break;
      case 1:
         jumpColor[0] = super.colorBlue[0];
         jumpColor[1] = super.colorBlue[1];
         break;
      case 2:
         jumpColor[0] = super.colorGreen[0];
         jumpColor[1] = super.colorGreen[1];
         break;
      case 3:
         jumpColor[0] = super.colorYellow[0];
         jumpColor[1] = super.colorYellow[1];
         break;
      case 4:
         jumpColor[0] = super.colorWhite[0];
         jumpColor[1] = super.colorWhite[1];
         break;
      case 5:
         jumpColor[0] = super.colorGrey[0];
         jumpColor[1] = super.colorGrey[1];
      }

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
         drawCustomBar(true, this, var7 + 21, height - 80, 141, 10, (double)color / 100.0D * 100.0D, super.colorBlack, super.colorDefault[1], super.colorDefault[0], jumpColor[1], jumpColor[0]);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
   }
}
