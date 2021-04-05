package net.spellcraftgaming.interfaceplus.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Pre;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;
import net.spellcraftgaming.interfaceplus.guiplus.GuiIngameMenuPlus;
import net.spellcraftgaming.interfaceplus.guiplus.GuiMainMenuPlus;
import net.spellcraftgaming.interfaceplus.guiplus.GuiPanorama;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiScreenHandler {

   private GameSettingsPlus settings;
   private Minecraft mc;


   public GuiScreenHandler(Minecraft mc, GameSettingsPlus settings) {
      this.mc = mc;
      this.settings = settings;
   }

   @SubscribeEvent
   public void guiScreenInit(Post event) {
      if(event.gui instanceof GuiIngameMenu) {
         GuiIngameMenuPlus.initGui(event, this.settings);
      }

      if(event.gui instanceof GuiMainMenu && !this.settings.new_gui_enabled && !this.settings.textbox_enabled) {
         GuiMainMenuPlus.initGui(event, this.settings);
      }

   }

   @SubscribeEvent
   public void guiScreenDraw(DrawScreenEvent event) {
      GuiPanorama.update();
   }

   @SubscribeEvent
   public void guiScreenDraw(net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Post event) {
      if(event.gui instanceof GuiIngameMenu && this.settings.new_gui_enabled) {
         GL11.glScalef(100000.0F, 100000.0F, 100000.0F);
         GuiIngameMenuPlus.drawScreen(event, this.mc);
      }

   }

   @SubscribeEvent
   public void guiScreenDraw(Pre event) {
      if(event.gui instanceof GuiIngameMenu && this.settings.new_gui_enabled) {
         GL11.glScalef(1.0E-5F, 1.0E-5F, 1.0E-5F);
      }

   }

   @SubscribeEvent
   public void guiScreenAction(ActionPerformedEvent event) {
      if(event.gui instanceof GuiIngameMenu) {
         GuiIngameMenuPlus.actionPerformed(event, this.mc);
      }

      if(event.gui instanceof GuiMainMenu && !this.settings.new_gui_enabled && !this.settings.textbox_enabled) {
         GuiMainMenuPlus.actionPerformed(event, this.mc, this.settings);
      }

   }
}
