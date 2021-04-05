package net.spellcraftgaming.interfaceplus.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.spellcraftgaming.interfaceplus.hudplus.Hud;
import net.spellcraftgaming.interfaceplus.hudplus.HudNewStyle;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

@SideOnly(Side.CLIENT)
public class RenderGameOverlayHandler {

   private Minecraft mc;
   public static boolean renderAirPlus = false;
   public static boolean renderArmorPlus = false;
   public static boolean renderCrosshairPlus = false;
   public static boolean renderExperiencePlus = false;
   public static boolean renderFoodPlus = false;
   public static boolean renderHealthPlus = false;
   public static boolean renderHealthMountPlus = false;
   public static boolean renderHotbarPlus = false;
   public static boolean renderJumpBarPlus = false;
   private GameSettingsPlus settings;
   private GuiIngameForge ingameGui;


   public RenderGameOverlayHandler(Minecraft mc, GameSettingsPlus settings) {
      this.mc = mc;
      this.ingameGui = (GuiIngameForge)mc.ingameGUI;
      this.settings = settings;
      this.setRenderPermissions();
   }

   @SubscribeEvent
   public void renderOverlay(Post event) {}

   @SubscribeEvent
   public void renderOverlay(Pre event) {
      InterfacePlus var10000 = InterfacePlus.instance;
      InterfacePlus.cache.updateHudType(this.ingameGui);
      var10000 = InterfacePlus.instance;
      Hud hud = InterfacePlus.cache.getHud();
      if(this.settings.hud_enabled && this.mc.playerController.shouldDrawHUD()) {
         if(renderAirPlus && event.type == ElementType.AIR) {
            hud.renderAirBar(this.settings);
            event.setCanceled(true);
         }

         if(renderArmorPlus && event.type == ElementType.ARMOR) {
            hud.renderArmor();
            event.setCanceled(true);
         }

         if(renderCrosshairPlus && event.type == ElementType.CROSSHAIRS) {
            hud.renderCrosshair();
            event.setCanceled(true);
         }

         if(renderExperiencePlus && event.type == ElementType.EXPERIENCE) {
            if(!(hud instanceof HudNewStyle)) {
               hud.renderExperience(this.settings);
            }

            event.setCanceled(true);
         }

         if(renderFoodPlus && event.type == ElementType.FOOD) {
            hud.renderFood(this.settings);
            event.setCanceled(true);
         }

         if(renderHealthPlus && event.type == ElementType.HEALTH) {
            hud.renderWidget();
            if(this.settings.render_player_face) {
               hud.renderPlayerFace();
            }

            hud.renderHealth(this.settings);
            event.setCanceled(true);
         }

         if(renderHealthMountPlus && event.type == ElementType.HEALTHMOUNT) {
            hud.renderMountHealth(this.settings);
            hud.renderFood(this.settings);
            hud.renderExperience(this.settings);
            event.setCanceled(true);
         }

         if(renderJumpBarPlus && event.type == ElementType.JUMPBAR) {
            hud.renderJumpBar(this.settings);
            event.setCanceled(true);
         }

         if(renderHotbarPlus && event.type == ElementType.HOTBAR) {
            if(hud instanceof HudNewStyle) {
               hud.renderExperience(this.settings);
            }

            hud.renderHotbar(event.partialTicks);
            hud.renderExtras(this.settings);
            event.setCanceled(true);
         }

         if(event.type == ElementType.CHAT) {
            hud.renderNumbers(this.settings);
            if(this.settings.show_armor) {
               hud.renderArmorHelper(this.settings);
            }

            if(this.settings.enable_status) {
               hud.renderStatusEffects(this.settings);
            }

            if(this.settings.enable_clock) {
               hud.renderClock(this.settings);
            }

            var10000 = InterfacePlus.instance;
            ++InterfacePlus.cache.runtime;
         }
      }

   }

   @SubscribeEvent
   public void renderOverlayChat(Chat event) {
      if(this.settings.hudtype == 3) {
         event.posY -= 46;
      }

   }

   public void setRenderPermissions() {
      renderAirPlus = true;
      renderArmorPlus = true;
      renderCrosshairPlus = true;
      renderExperiencePlus = true;
      renderFoodPlus = true;
      renderHealthPlus = true;
      renderHealthMountPlus = true;
      renderHotbarPlus = true;
      renderJumpBarPlus = true;
   }

}
