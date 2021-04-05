package net.spellcraftgaming.interfaceplus.guiplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonNew;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

public class GuiMultiplayerPlus {

   private static GuiButton[] buttons;


   public static void initGui(InitGuiEvent event, GameSettingsPlus settings) {
      buttons = new GuiButton[event.buttonList.size()];

      for(int id = 0; id < event.buttonList.size(); ++id) {
         GuiButton button = (GuiButton)event.buttonList.get(id);
         buttons[id] = button;
         GuiButtonNew buttonNew = new GuiButtonNew(buttons[id].id, buttons[id].xPosition, buttons[id].yPosition, buttons[id].width, buttons[id].height, buttons[id].displayString, false);
         event.buttonList.set(id, buttonNew);
         buttonNew.state = true;
         buttons[id] = buttonNew;
      }

   }

   public static void drawScreen(DrawScreenEvent event, Minecraft mc, GameSettingsPlus settings) {
      GuiButtonTooltip.drawTooltip(event.gui, buttons);
   }
}
