package net.spellcraftgaming.interfaceplus.guiplus;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.spellcraftgaming.interfaceplus.guiplus.GuiMainMenuNew;
import net.spellcraftgaming.interfaceplus.guiplus.GuiSettingsPlus;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import net.spellcraftgaming.interfaceplus.settings.EnumOptionsPlus;
import org.lwjgl.input.Mouse;

public class GuiButtonTooltip {

   public static HashMap button = new HashMap();
   static String[] stringsToDraw = null;
   static int buttonId;
   static GuiScreen screen;


   public static void drawTooltip(GuiScreen gui, GuiButton[] buttons) {
      InterfacePlus var10000 = InterfacePlus.instance;
      if(InterfacePlus.settings.button_tooltip_enabled) {
         Minecraft mc = Minecraft.getMinecraft();
         int mx = Mouse.getEventX() * gui.width / gui.mc.displayWidth;
         int my = gui.height - Mouse.getEventY() * gui.height / gui.mc.displayHeight - 1;
         int i = mx + 5;
         int j = my + 5;
         String[] strings = stringsToDraw;
         int maxY = 0;
         boolean should = false;
         boolean reverse = false;

         int counter;
         for(counter = 0; counter < buttons.length; ++counter) {
            boolean id = mx >= buttons[counter].xPosition && my >= buttons[counter].yPosition && mx < buttons[counter].xPosition + buttons[counter].width && my < buttons[counter].yPosition + buttons[counter].height;
            if(buttons[counter].id == buttonId && screen == gui && buttons[counter].getHoverState(id) == 2) {
               should = true;
            }
         }

         if(strings != null && should) {
            counter = 0;

            int var14;
            for(var14 = 0; var14 < strings.length; ++var14) {
               int x = mc.fontRenderer.getStringWidth(strings[var14]);
               if(maxY < x) {
                  maxY = mc.fontRenderer.getStringWidth(strings[var14]);
               }

               ++counter;
            }

            if(j + 5 + 12 * counter + 10 > gui.height) {
               reverse = true;
            }

            if(reverse) {
               GuiScreen.drawRect(mx, my - 3 - strings.length * 12 - 2, mx + maxY + 10, my, -1610612736);
            } else {
               GuiScreen.drawRect(i, j, i + maxY + 10, j + 3 + strings.length * 12 + 2, -1610612736);
            }

            for(var14 = 0; var14 < strings.length; ++var14) {
               if(!strings[var14].isEmpty()) {
                  if(reverse) {
                     gui.drawString(mc.fontRenderer, strings[var14], i, my - 2 - 12 * (counter - var14 - 1) - 10, 12303291);
                  } else {
                     gui.drawString(mc.fontRenderer, strings[var14], i + 5, j + 5 + 12 * var14, 12303291);
                  }
               }
            }
         }
      }

   }

   public static void drawTooltip(GuiScreen gui, ArrayList buttons) {
      InterfacePlus var10000 = InterfacePlus.instance;
      if(InterfacePlus.settings.button_tooltip_enabled) {
         Minecraft mc = Minecraft.getMinecraft();
         int mx = Mouse.getEventX() * gui.width / gui.mc.displayWidth;
         int my = gui.height - Mouse.getEventY() * gui.height / gui.mc.displayHeight - 1;
         int i = mx + 5;
         int j = my + 5;
         String[] strings = stringsToDraw;
         int maxY = 0;
         boolean should = false;
         boolean reverse = false;

         int counter;
         for(counter = 0; counter < buttons.size(); ++counter) {
            GuiButton button = (GuiButton)buttons.get(counter);
            boolean id = mx >= button.xPosition && my >= button.yPosition && mx < button.xPosition + button.width && my < button.yPosition + button.height;
            if(button.id == buttonId && screen == gui && button.getHoverState(id) == 2) {
               should = true;
            }
         }

         if(strings != null && should) {
            counter = 0;

            int var15;
            for(var15 = 0; var15 < strings.length; ++var15) {
               int x = mc.fontRenderer.getStringWidth(strings[var15]);
               if(maxY < x) {
                  maxY = mc.fontRenderer.getStringWidth(strings[var15]);
               }

               ++counter;
            }

            if(j + 5 + 12 * counter + 10 > gui.height) {
               reverse = true;
            }

            if(reverse) {
               GuiScreen.drawRect(mx, my - 3 - strings.length * 12 - 2, mx + maxY + 10, my, -1610612736);
            } else {
               GuiScreen.drawRect(i, j, i + maxY + 10, j + 3 + strings.length * 12 + 2, -1610612736);
            }

            for(var15 = 0; var15 < strings.length; ++var15) {
               if(!strings[var15].isEmpty()) {
                  if(reverse) {
                     gui.drawString(mc.fontRenderer, strings[var15], i, my - 2 - 12 * (counter - var15 - 1) - 10, 12303291);
                  } else {
                     gui.drawString(mc.fontRenderer, strings[var15], i + 5, j + 5 + 12 * var15, 12303291);
                  }
               }
            }
         }
      }

   }

   public static void setTooltip(GuiScreen gui, GuiButton button) {
      stringsToDraw = getToolTip(gui, button.id);
      buttonId = button.id;
      screen = gui;
   }

   private static String[] getToolTip(GuiScreen gui, int buttonId) {
      return (String[])button.get(gui.getClass().getSimpleName() + "." + buttonId);
   }

   public static void initTooltips() {
      String[] strings = new String[]{"Play in singleplayer mode"};
      button.put(GuiMainMenu.class.getSimpleName() + ".1", strings);
      strings = new String[]{"Connect to a server"};
      button.put(GuiMainMenu.class.getSimpleName() + ".2", strings);
      strings = new String[]{"Use Minecraft Realms"};
      button.put(GuiMainMenu.class.getSimpleName() + ".14", strings);
      strings = new String[]{"View and manage the installed mods"};
      button.put(GuiMainMenu.class.getSimpleName() + ".6", strings);
      strings = new String[]{"Access the Minecraft settings"};
      button.put(GuiMainMenu.class.getSimpleName() + ".0", strings);
      strings = new String[]{"Change the language of the", "Minecraft client"};
      button.put(GuiMainMenu.class.getSimpleName() + ".5", strings);
      strings = new String[]{"Access the Interface+ settings"};
      button.put(GuiMainMenu.class.getSimpleName() + ".50", strings);
      strings = new String[]{"Exit the game"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".4", strings);
      strings = new String[]{"Play in singleplayer mode"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".1", strings);
      strings = new String[]{"Connect to a server"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".2", strings);
      strings = new String[]{"Use Minecraft Realms"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".14", strings);
      strings = new String[]{"View and manage the installed mods"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".6", strings);
      strings = new String[]{"Access the Minecraft settings"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".0", strings);
      strings = new String[]{"Change the language of the", "Minecraft client"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".5", strings);
      strings = new String[]{"Access the Interface+ settings"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".50", strings);
      strings = new String[]{"Exit the game"};
      button.put(GuiMainMenuNew.class.getSimpleName() + ".4", strings);
      strings = new String[]{"Play the selected world"};
      button.put(GuiSelectWorld.class.getSimpleName() + ".1", strings);
      strings = new String[]{"Create a new world"};
      button.put(GuiSelectWorld.class.getSimpleName() + ".3", strings);
      strings = new String[]{"Rename the selected world"};
      button.put(GuiSelectWorld.class.getSimpleName() + ".6", strings);
      strings = new String[]{"Delete the selected world"};
      button.put(GuiSelectWorld.class.getSimpleName() + ".2", strings);
      strings = new String[]{"Create a new world with the same", "worldseed as the selected world"};
      button.put(GuiSelectWorld.class.getSimpleName() + ".7", strings);
      strings = new String[]{"Return to the", "main menu"};
      button.put(GuiSelectWorld.class.getSimpleName() + ".0", strings);
      strings = new String[]{"Return to the main menu"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".1", strings);
      strings = new String[]{"Continue to play"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".4", strings);
      strings = new String[]{"Access the Minecraft settings"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".0", strings);
      strings = new String[]{"Access the mod settings"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".12", strings);
      strings = new String[]{"Host the game world on the", "LAN network"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".7", strings);
      strings = new String[]{"Look at the achievements", "for the current world"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".5", strings);
      strings = new String[]{"Look at the statistics", "for the current world"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".6", strings);
      strings = new String[]{"Access the Interface+ settings"};
      button.put(GuiIngameMenu.class.getSimpleName() + ".50", strings);
      strings = new String[]{"Join the selected Server"};
      button.put(GuiMultiplayer.class.getSimpleName() + ".1", strings);
      strings = new String[]{"Connect to a server which", "isn\'t in the server list"};
      button.put(GuiMultiplayer.class.getSimpleName() + ".4", strings);
      strings = new String[]{"Add a new server", "to the server list"};
      button.put(GuiMultiplayer.class.getSimpleName() + ".3", strings);
      strings = new String[]{"Edit the selected server"};
      button.put(GuiMultiplayer.class.getSimpleName() + ".7", strings);
      strings = new String[]{"Delete the selected server"};
      button.put(GuiMultiplayer.class.getSimpleName() + ".2", strings);
      strings = new String[]{"Refresh the server list"};
      button.put(GuiMultiplayer.class.getSimpleName() + ".8", strings);
      strings = new String[]{"Return to the", "main menu"};
      button.put(GuiMultiplayer.class.getSimpleName() + ".0", strings);
      strings = new String[]{"General Interface+ Settings", "includes gui changing ", "and healthbar settings"};
      button.put(GuiSettingsPlus.class.getSimpleName() + ".201", strings);
      strings = new String[]{"Hud changing settings"};
      button.put(GuiSettingsPlus.class.getSimpleName() + ".202", strings);
      strings = new String[]{"Hud color changing settings"};
      button.put(GuiSettingsPlus.class.getSimpleName() + ".203", strings);
      strings = new String[]{"Hud addon settings", "includes settings for the", "armor helper, status", "and hunger"};
      button.put(GuiSettingsPlus.class.getSimpleName() + ".204", strings);
      strings = new String[]{"Item Tooltip settings"};
      button.put(GuiSettingsPlus.class.getSimpleName() + ".205", strings);
      strings = new String[]{"Enable/disable the Textbox"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.TEXTBOX_ENABLED.ordinal(), strings);
      strings = new String[]{"Enable/disable the new gui style"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.NEW_GUI_ENABLED.ordinal(), strings);
      strings = new String[]{"Healthbar above heads:", "ï¿½nallï¿½r: enabled within 16 blocks", "ï¿½nat look atï¿½r: only when looking", "at the entity (64 blocks range)"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_HEALTHBAR.ordinal(), strings);
      strings = new String[]{"Set the color of the healthbars"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.COLOR_HEALTHBAR.ordinal(), strings);
      strings = new String[]{"Set the style of the healthbars", "ï¿½nDefaultï¿½r, ï¿½nSmallï¿½r or ï¿½nSlim"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.HEALTHBAR_TYPE.ordinal(), strings);
      strings = new String[]{"Enable/Disable Button Tooltips"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.BUTTON_TOOLTIP_ENABLED.ordinal(), strings);
      strings = new String[]{"Enable/Disable Hud changes"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.HUD_ENABLED.ordinal(), strings);
      strings = new String[]{"Change the Hud style:", "ï¿½nDefaultï¿½r, ï¿½nExtended Widgetï¿½r,", "ï¿½nFull Textureï¿½r, ï¿½nHotbar Widgetï¿½r and", "ï¿½6ï¿½nNew Style (complete rework)"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.HUD_TYPE.ordinal(), strings);
      strings = new String[]{"Enabled/Disable player face", "If disabled Steve\'s face will", "be rendered instead.", "Except if you use the new style"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.RENDER_PLAYER_FACE.ordinal(), strings);
      strings = new String[]{"Enable/Disable Experience Numbers"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_NUMBERS_EXPERIENCE.ordinal(), strings);
      strings = new String[]{"Enable/Disable Health Numbers"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_NUMBERS_HEALTH.ordinal(), strings);
      strings = new String[]{"Enable/Disable Stamina Numbers"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_NUMBERS_STAMINA.ordinal(), strings);
      strings = new String[]{"Enable/Disable onscreen durability", "display of the equipped item"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_ITEMDURABILITY.ordinal(), strings);
      strings = new String[]{"Enable/Disable onscreen display", "of the arrow amount in the", "inventory when a bow is equipped"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_ARRROWCOUNT.ordinal(), strings);
      strings = new String[]{"Enable/Disable onscreen display", "of the amount of equipped block", "in the inventory"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_ITEMCOUNT.ordinal(), strings);
      strings = new String[]{"Set the color of the", "breath bar"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.COLOR_AIR.ordinal(), strings);
      strings = new String[]{"Set the color of the", "experience bar"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.COLOR_EXPERIENCE.ordinal(), strings);
      strings = new String[]{"Set the color of the", "health bar"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.COLOR_HEALTH.ordinal(), strings);
      strings = new String[]{"Set the color of the", "jump bar"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.COLOR_JUMPBAR.ordinal(), strings);
      strings = new String[]{"Set the color of the", "stamina/food bar"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.COLOR_STAMINA.ordinal(), strings);
      strings = new String[]{"Enable/Disable armor", "durability informations"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_ARMOR.ordinal(), strings);
      strings = new String[]{"Set the size for all extra", "details"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SIZE_DETAIL.ordinal(), strings);
      strings = new String[]{"Enable/Disable status", "effect informations"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.ENABLE_STATUS.ordinal(), strings);
      strings = new String[]{"Enable/Disable the blinking", "feature of the status", "effect informations"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.ENABLE_STATUS_BLINK.ordinal(), strings);
      strings = new String[]{"Set the size for the status", "effect informations"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SIZE_STATUS.ordinal(), strings);
      strings = new String[]{"Enable/Disable the hunger", "preview of items"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.SHOW_HUNGERPREVIEW.ordinal(), strings);
      strings = new String[]{"Enable/Disable the ingame", "time clock"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.ENABLE_CLOCK.ordinal(), strings);
      strings = new String[]{"Set if you want the", "clock to be colored"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.ENABLE_TIMECOLOR.ordinal(), strings);
      strings = new String[]{"Set the time format", "for the ingame clock"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.CLOCK_TIME_FORMAT.ordinal(), strings);
      strings = new String[]{"Set if you only want the", "ingame clock to be visible", "if you have a clock item", "in your inventory"};
      button.put(GuiSettingsPlus.class.getSimpleName() + "." + EnumOptionsPlus.ENABLE_IMMERSIVE_CLOCK.ordinal(), strings);
   }

}
