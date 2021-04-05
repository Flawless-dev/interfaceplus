package net.spellcraftgaming.interfaceplus.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.spellcraftgaming.interfaceplus.guiplus.GuiFirstStart;
import net.spellcraftgaming.interfaceplus.guiplus.GuiLogo;
import net.spellcraftgaming.interfaceplus.guiplus.GuiMainMenuNew;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

public class GuiOpenHandler {

   private GameSettingsPlus settings;
   private Minecraft mc;
   private boolean showLogo = true;


   public GuiOpenHandler(Minecraft mc, GameSettingsPlus settings) {
      this.mc = mc;
      this.settings = settings;
   }

   @SubscribeEvent
   public void guiScreenOpen(GuiOpenEvent event) {
      if(event.gui instanceof GuiMainMenu && this.showLogo && !this.settings.first_start) {
         event.setCanceled(true);
         this.mc.displayGuiScreen(new GuiLogo());
         this.showLogo = false;
      } else if(event.gui instanceof GuiMainMenu && this.settings.first_start) {
         event.setCanceled(true);
         this.mc.displayGuiScreen(new GuiFirstStart(event.gui, this.settings));
      } else if(event.gui instanceof GuiMainMenu && !(event.gui instanceof GuiMainMenuNew) && !this.showLogo && !this.settings.first_start && (this.settings.new_gui_enabled || this.settings.textbox_enabled)) {
         event.setCanceled(true);
         this.mc.displayGuiScreen(new GuiMainMenuNew(this.settings));
      } else if(event.gui instanceof GuiMainMenuNew && !this.settings.new_gui_enabled && !this.settings.textbox_enabled) {
         event.setCanceled(true);
         this.mc.displayGuiScreen(new GuiMainMenu());
      }
   }
}
