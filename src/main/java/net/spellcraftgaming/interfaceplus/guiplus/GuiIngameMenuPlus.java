package net.spellcraftgaming.interfaceplus.guiplus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Post;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonNew;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.guiplus.GuiSettingsPlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiIngameMenuPlus {

   private static GameSettingsPlus settings;
   static int widthOffset = 15;
   static GuiButton[] buttons;


   public static void initGui(InitGuiEvent event, GameSettingsPlus settingsPlus) {
      settings = settingsPlus;
      event.buttonList.add(new GuiButton(50, event.gui.width / 2 - 100, event.gui.height / 4 + 120 - 16, I18n.format("Interface+ Settings", new Object[0])));
      buttons = new GuiButton[event.buttonList.size()];

      for(int id = 0; id < event.buttonList.size(); ++id) {
         GuiButton button = (GuiButton)event.buttonList.get(id);
         button.yPosition -= 12;
         if(button.id == 1) {
            button.yPosition += 24;
         }

         if(settings.new_gui_enabled) {
            buttons[id] = button;
            GuiButtonNew buttonNew = new GuiButtonNew(buttons[id].id, 10 + widthOffset, event.gui.height / 2 - 90 + 20 * id, 125, 20, buttons[id].displayString, false);
            event.buttonList.set(id, buttonNew);
            buttonNew.state = true;
            buttons[id] = buttonNew;
            buttons[id].enabled = false;
            event.buttonList.set(id, buttons[id]);
         }
      }

   }

   public static void drawScreen(Post event, Minecraft mc) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GuiScreen var10000 = event.gui;
      GuiScreen.drawRect(widthOffset, 0, widthOffset + 150, 24, -1610612736);
      var10000 = event.gui;
      GuiScreen.drawRect(widthOffset, 26, widthOffset + 150, event.gui.height, -1610612736);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int size = buttons.length;
      boolean yOff = false;

      for(int id = 0; id < size; ++id) {
         buttons[id].drawButton(mc, event.mouseX, event.mouseY);
      }

      event.gui.drawCenteredString(mc.fontRenderer, I18n.format("menu.game", new Object[0]), 91, 8, -4473925);
      GuiButtonTooltip.drawTooltip(event.gui, buttons);
   }

   public static void actionPerformed(ActionPerformedEvent event, Minecraft mc) {
      if(event.button.id == 50) {
         mc.displayGuiScreen(new GuiSettingsPlus(event.gui, settings, 0));
      }

   }

}
