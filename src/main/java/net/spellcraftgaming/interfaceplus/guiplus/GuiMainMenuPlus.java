package net.spellcraftgaming.interfaceplus.guiplus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.spellcraftgaming.interfaceplus.event.RenderTickHandler;
import net.spellcraftgaming.interfaceplus.guiplus.GuiSettingsPlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

@SideOnly(Side.CLIENT)
public class GuiMainMenuPlus {

   private static final ResourceLocation TEXTBOX = new ResourceLocation("spellcraftgaming:interfaceplus/textbox.png");
   private static final ResourceLocation MC_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");
   static int heightY = 0;
   static boolean changelog = false;
   static GuiButton[] buttons;
   static int widthOffset = 15;


   public static void initGui(InitGuiEvent event, GameSettingsPlus settings) {
      int height = event.gui.height;
      RenderTickHandler.started = false;
      event.buttonList.add(new GuiButton(50, event.gui.width / 2 - 100, height / 4 + 48 + 72 - 10, 200, 20, I18n.format("Interface+ Settings", new Object[0])));

      for(int id = 0; id < event.buttonList.size(); ++id) {
         GuiButton button = (GuiButton)event.buttonList.get(id);
         switch(button.id) {
         case 1:
            button.yPosition -= 4;
            break;
         case 2:
            button.yPosition -= 6;
         case 3:
         case 4:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         default:
            break;
         case 5:
            if(settings.new_gui_enabled) {
               button.displayString = I18n.format("options.language", new Object[0]);
            }
            break;
         case 6:
            button.yPosition -= 8;
            break;
         case 14:
            button.yPosition -= 8;
         }
      }

   }

   public static void actionPerformed(ActionPerformedEvent event, Minecraft mc, GameSettingsPlus settings) {
      if(event.button.id == 50) {
         mc.displayGuiScreen(new GuiSettingsPlus(event.gui, settings, 0));
      }

   }

}
