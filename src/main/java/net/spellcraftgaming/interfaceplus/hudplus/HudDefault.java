package net.spellcraftgaming.interfaceplus.hudplus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
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
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.ForgeHooks;
import net.spellcraftgaming.interfaceplus.cache.Cache;
import net.spellcraftgaming.interfaceplus.helper.TimeHelper;
import net.spellcraftgaming.interfaceplus.hudplus.Hud;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class HudDefault extends Hud {

   protected static final ResourceLocation WIDGITS = new ResourceLocation("textures/gui/widgets.png");
   protected static final ResourceLocation INTERFACE = new ResourceLocation("spellcraftgaming:interfaceplus/interface.png");
   protected static final ResourceLocation DAMAGE = new ResourceLocation("spellcraftgaming:interfaceplus/damage.png");
   protected static final ResourceLocation ACHIEVEMENTS = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   protected static final ResourceLocation INVENTORY = new ResourceLocation("textures/gui/container/inventory.png");
   protected static final RenderItem itemRenderer = new RenderItem();
   protected final GuiNewChat persistantChatGUI;


   public HudDefault(Minecraft mc, GuiIngameForge ingameGui) {
      super(mc, ingameGui);
      this.persistantChatGUI = new GuiNewChat(mc);
   }

   public void renderAirBar(GameSettingsPlus settings) {
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      int adjustedWidth = width / 2 - 91;
      int[] airColor = new int[2];
      switch(settings.color_air) {
      case 0:
         airColor[0] = super.colorRed[0];
         airColor[1] = super.colorRed[1];
         break;
      case 1:
         airColor[0] = super.colorBlue[0];
         airColor[1] = super.colorBlue[1];
         break;
      case 2:
         airColor[0] = super.colorGreen[0];
         airColor[1] = super.colorGreen[1];
         break;
      case 3:
         airColor[0] = super.colorYellow[0];
         airColor[1] = super.colorYellow[1];
         break;
      case 4:
         airColor[0] = super.colorWhite[0];
         airColor[1] = super.colorWhite[1];
         break;
      case 5:
         airColor[0] = super.colorGrey[0];
         airColor[1] = super.colorGrey[1];
      }

      int airAmount = super.mc.thePlayer.getAir();
      GL11.glEnable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      if(super.mc.thePlayer.isInsideOfMaterial(Material.water)) {
         drawCustomBar(true, this, adjustedWidth + 21, height - 80, 141, 10, (double)airAmount / 300.0D * 100.0D, super.colorBlack, super.colorDefault[1], super.colorDefault[0], airColor[1], airColor[0]);
      }

      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
   }

   public void renderCrosshair() {
      super.mc.mcProfiler.startSection("crosshair");
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      this.bind(Gui.icons);
      GL11.glEnable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      OpenGlHelper.glBlendFunc(775, 769, 1, 0);
      this.drawTexturedModalRect(width / 2 - 7, height / 2 - 7, 0, 0, 16, 16);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
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
      int left = width / 2 - 91;
      int top = height - 39;
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

            drawCustomBar(true, this, 49, 26, 110, 12, (double)bonusHunger / 20.0D * 100.0D, super.colorBlack, 0, 0, staminaColor[5], staminaColor[4]);
         }

         if(!super.mc.thePlayer.isPotionActive(Potion.hunger)) {
            drawCustomBar(true, this, 49, 26, 110, 12, (double)var12 / 20.0D * 100.0D, super.colorBlack, 0, 0, staminaColor[1], staminaColor[0]);
         } else {
            drawCustomBar(true, this, 49, 26, 110, 12, (double)var12 / 20.0D * 100.0D, super.colorBlack, 0, 0, staminaColor[3], staminaColor[2]);
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
            drawCustomBar(true, this, 49, 13, 110, 12, (double)var10 / (double)var4 * 100.0D, super.colorBlack, 0, 0, healthColor[1], healthColor[0]);
         } else {
            drawCustomBar(true, this, 49, 13, 110, 12, (double)var10 / (double)var4 * 100.0D, super.colorBlack, 0, 0, healthColor[3], healthColor[2]);
         }

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
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            drawCustomBar(true, this, 53, 49, 88, 8, (double)health / (double)healthMax * 100.0D, super.colorBlack, 0, 0, -7667712, -4128768);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bind(Gui.icons);
            super.mc.mcProfiler.endSection();
         }
      }

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

   public void renderHotbar(float partialTicks) {
      super.mc.mcProfiler.startSection("actionBar");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      ScaledResolution res = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int width = res.getScaledWidth();
      int height = res.getScaledHeight();
      super.mc.renderEngine.bindTexture(WIDGITS);
      InventoryPlayer inv = super.mc.thePlayer.inventory;
      this.drawTexturedModalRect(width / 2 - 91, height - 22 - 9, 0, 0, 182, 22);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawTexturedModalRect(width / 2 - 91 - 1 + inv.currentItem * 20, height - 22 - 1 - 9, 0, 22, 24, 22);
      GL11.glEnable('\u803a');
      RenderHelper.enableGUIStandardItemLighting();

      for(int i = 0; i < 9; ++i) {
         int x = width / 2 - 90 + i * 20 + 2;
         int z = height - 16 - 3;
         this.renderInventorySlot(i, x, z - 9, partialTicks);
      }

      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
      super.mc.mcProfiler.endSection();
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
      int var10004;
      int var10005;
      if(settings.show_itemdurability && item != null && item.getMaxDamage() > 0) {
         s = item.getMaxDamage() - item.getItemDamageForDisplay() + "/" + item.getMaxDamage();
         if(settings.size_detail == 0) {
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

         RenderHelper.enableGUIStandardItemLighting();
         var10004 = settings.size_detail == 1?2:6;
         var10005 = settings.size_detail == 1?62 + offset * 2:124 + offset * 2;
         itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, item, var10004, var10005);
         GL11.glDisable(2896);
         this.drawString(super.fontrenderer, s, settings.size_detail == 1?23:30, settings.size_detail == 1?66 + offset * 2:128 + offset * 2, -1);
         RenderHelper.disableStandardItemLighting();
         if(settings.size_detail == 0) {
            GL11.glScaled(2.0D, 2.0D, 2.0D);
         }

         var10000 = InterfacePlus.instance;
         InterfacePlus.cache.offset += 7;
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

         s = "x " + z;
         if(settings.size_detail == 0) {
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

         RenderHelper.enableGUIStandardItemLighting();
         itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(Items.arrow), settings.size_detail == 1?2:6, settings.size_detail == 1?62 + offset * 2:124 + offset * 2);
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         this.drawString(super.fontrenderer, s, settings.size_detail == 1?23:30, settings.size_detail == 1?66 + offset * 2:128 + offset * 2, -1);
         if(settings.size_detail == 0) {
            GL11.glScaled(2.0D, 2.0D, 2.0D);
         }

         var10000 = InterfacePlus.instance;
         InterfacePlus.cache.offset += 7;
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
         s = "x " + z;
         if(settings.size_detail == 0) {
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

         RenderHelper.enableGUIStandardItemLighting();
         var10004 = settings.size_detail == 1?2:6;
         var10005 = settings.size_detail == 1?62 + offset * 2:124 + offset * 2;
         itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, item, var10004, var10005);
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         this.drawString(super.fontrenderer, s, settings.size_detail == 1?23:30, settings.size_detail == 1?66 + offset * 2:128 + offset * 2, -1);
         if(settings.size_detail == 0) {
            GL11.glScaled(2.0D, 2.0D, 2.0D);
         }

         var10000 = InterfacePlus.instance;
         InterfacePlus.cache.offset += 7;
      }

      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
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

         itemRenderer.renderItemAndEffectIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), itemstack, p_73832_2_, p_73832_3_);
         if(f1 > 0.0F) {
            GL11.glPopMatrix();
         }

         itemRenderer.renderItemOverlayIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), itemstack, p_73832_2_, p_73832_3_);
      }

   }

   public void renderPlayerFace() {
      if(!super.mc.gameSettings.showDebugInfo) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         this.bind(this.playerSkin(super.mc.thePlayer));
         GL11.glScaled(0.5D, 0.25D, 0.5D);
         this.drawTexturedModalRect(34, 68, 32, 64, 32, 64);
         this.drawTexturedModalRect(34, 68, 160, 64, 32, 64);
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
      if(super.mc.thePlayer.ridingEntity instanceof EntityLivingBase) {
         EntityLivingBase mount = (EntityLivingBase)super.mc.thePlayer.ridingEntity;
         var161 = (int)Math.ceil((double)mount.getHealth()) + "/" + (int)mount.getMaxHealth();
      }

      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         if(settings.show_numbers_health) {
            this.drawString(super.fontrenderer, var141, 90, 15, -1);
         }

         if(settings.show_numbers_stamina) {
            this.drawString(super.fontrenderer, var151, 90, 28, -1);
         }
      }

      this.drawString(super.fontrenderer, var9, var7 + 80, height - 9, -1);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD() && settings.show_numbers_health) {
         this.drawString(super.fontrenderer, var161, 183, 102, -1);
      }

      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.bind(Gui.icons);
   }

   public void renderStatusEffects(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo) {
         ScaledResolution var1 = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int width = var1.getScaledWidth();
         int height = var1.getScaledHeight();
         int i = width - 28;
         byte j = 0;
         byte scale = 1;
         switch(settings.size_status) {
         case 0:
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            i = width * 2 - 28;
            scale = 2;
            break;
         case 1:
            GL11.glScaled(1.0D, 1.0D, 1.0D);
            i = width - 28;
            scale = 1;
         }

         Collection collection = super.mc.thePlayer.getActivePotionEffects();
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         if(!collection.isEmpty()) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            byte k = 24;

            for(Iterator iterator = super.mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext(); i -= k) {
               PotionEffect potioneffect = (PotionEffect)iterator.next();
               Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               String[] potionDuration = Potion.getDurationString(potioneffect).split(":");
               boolean shouldRender = true;

               try {
                  if(Integer.valueOf(potionDuration[0]).intValue() == 0 && (Integer.valueOf(potionDuration[1]).intValue() == 14 || Integer.valueOf(potionDuration[1]).intValue() == 12 || Integer.valueOf(potionDuration[1]).intValue() == 10 || Integer.valueOf(potionDuration[1]).intValue() == 8 || Integer.valueOf(potionDuration[1]).intValue() == 6 || Integer.valueOf(potionDuration[1]).intValue() == 4 || Integer.valueOf(potionDuration[1]).intValue() == 2)) {
                     shouldRender = false;
                  }
               } catch (Exception var16) {
                  var16.printStackTrace();
               }

               this.bind(ACHIEVEMENTS);
               if(!settings.enable_status_blink) {
                  shouldRender = true;
               }

               if(shouldRender) {
                  this.drawTexturedModalRect(i, settings.hudtype == 3?(height - 12) * scale - 24:j, 0, 202, 26, 26);
               }

               if(potion.hasStatusIcon()) {
                  this.bind(INVENTORY);
                  if(shouldRender) {
                     int m = potion.getStatusIconIndex();
                     this.drawTexturedModalRect(i + 4, (settings.hudtype == 3?(height - 12) * scale - 24:j) + 4, 0 + m % 8 * 18, 198 + m / 8 * 18, 18, 18);
                  }
               }
            }
         }

         switch(settings.size_status) {
         case 0:
            GL11.glScaled(2.0D, 2.0D, 2.0D);
            break;
         case 1:
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

         GL11.glDisable(3042);
      }

   }

   public void renderArmorHelper(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         super.mc.mcProfiler.startSection("armorhelper");
         ScaledResolution var1 = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
         int height = var1.getScaledHeight();
         this.bind(DAMAGE);
         boolean scale = true;

         for(int i11 = super.mc.thePlayer.inventory.armorInventory.length - 1; i11 >= 0; --i11) {
            if(super.mc.thePlayer.inventory.armorInventory[i11] != null && super.mc.thePlayer.inventory.armorInventory[i11].getItem() instanceof ItemArmor) {
               int i12 = super.mc.thePlayer.inventory.armorInventory[i11].getMaxDamage();
               ItemStack item = super.mc.thePlayer.inventory.armorInventory[i11];
               String s = item.getMaxDamage() - item.getItemDamageForDisplay() + "/" + item.getMaxDamage();
               if(settings.size_detail == 0) {
                  GL11.glScaled(0.5D, 0.5D, 0.5D);
               }

               int var10004 = settings.size_detail == 1?2:6;
               int var10005;
               InterfacePlus var10006;
               if(settings.size_detail == 1) {
                  var10006 = InterfacePlus.instance;
                  var10005 = 62 + InterfacePlus.cache.offset * 2;
               } else {
                  var10006 = InterfacePlus.instance;
                  var10005 = 124 + InterfacePlus.cache.offset * 2;
               }

               itemRenderer.renderItemIntoGUI(super.fontrenderer, Minecraft.getMinecraft().renderEngine, item, var10004, var10005);
               GL11.glDisable(2896);
               int var10003 = settings.size_detail == 1?23:30;
               InterfacePlus var9;
               if(settings.size_detail == 1) {
                  var9 = InterfacePlus.instance;
                  var10004 = 66 + InterfacePlus.cache.offset * 2;
               } else {
                  var9 = InterfacePlus.instance;
                  var10004 = 128 + InterfacePlus.cache.offset * 2;
               }

               this.drawString(super.fontrenderer, s, var10003, var10004, -1);
               if(settings.size_detail == 0) {
                  GL11.glScaled(2.0D, 2.0D, 2.0D);
               }

               InterfacePlus var10000 = InterfacePlus.instance;
               InterfacePlus.cache.offset += 7;
            }
         }

         this.bind(Gui.icons);
         super.mc.mcProfiler.endSection();
      }

   }

   public void renderWidget() {
      if(!super.mc.gameSettings.showDebugInfo) {
         this.bind(INTERFACE);
         this.drawTexturedModalRect(0, 0, 0, 0, 162, 50);
         if(super.mc.thePlayer.ridingEntity instanceof EntityLivingBase) {
            this.drawTexturedModalRect(51, 39, 163, 0, 92, 20);
         }

         this.bind(Gui.icons);
      }

   }

   public void renderClock(GameSettingsPlus settings) {
      if(!super.mc.gameSettings.showDebugInfo && super.mc.playerController.shouldDrawHUD()) {
         int clockColor = 16777215;
         if(settings.enable_clock_color) {
            clockColor = TimeHelper.getClockColor(super.mc, settings);
         }

         if(settings.enable_immersive_clock) {
            if(super.mc.thePlayer.inventory.hasItem(Items.clock)) {
               this.drawString(super.fontrenderer, TimeHelper.getTime(super.mc, settings), 4, 52, clockColor);
            }
         } else {
            this.drawString(super.fontrenderer, TimeHelper.getTime(super.mc, settings), 4, 52, clockColor);
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

}
