package net.spellcraftgaming.interfaceplus.guiplus;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import net.spellcraftgaming.interfaceplus.settings.EnumOptionsPlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

public class GuiFirstStart extends GuiScreen {

   private final GuiScreen parent;
   private final GameSettingsPlus settings;


   public GuiFirstStart(GuiScreen screen, GameSettingsPlus settings) {
      this.parent = screen;
      this.settings = settings;
   }

   public void func_73866_w_() {
      this.buttonList.add(new GuiButton(202, this.width / 2 - 100, this.height / 4 + 24 + -32 + 48, I18n.format(this.settings.getKeyBinding(EnumOptionsPlus.TEXTBOX_ENABLED), new Object[0])));
      this.buttonList.add(new GuiButton(150, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
   }

   protected void func_146284_a(GuiButton p_146284_1_) {
      if(p_146284_1_.id == 150) {
         this.settings.setOptionValue(EnumOptionsPlus.FIRST_START, 0);
         InterfacePlus.settings.saveOptions();
         this.mc.displayGuiScreen(this.parent);
      }

      if(p_146284_1_.id == 202) {
         this.settings.setOptionValue(EnumOptionsPlus.TEXTBOX_ENABLED, 0);
         p_146284_1_.displayString = this.settings.getKeyBinding(EnumOptionsPlus.TEXTBOX_ENABLED);
      }

   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
   {
	   drawDefaultBackground();
	    drawCenteredString(this.fontRendererObj, "Initial Settings", this.width / 2, 12, 16777215);
	    drawCenteredString(this.fontRendererObj, "Do you want to enable the textbox?", this.width / 2, this.height / 4 + 24 + -32, 16777215);
	    drawCenteredString(this.fontRendererObj, "If the textbox is enabled you will see", this.width / 2, this.height / 4 + 24 + -32 + 10, 16777215);
	    drawCenteredString(this.fontRendererObj, "if a new update is available!", this.width / 2, this.height / 4 + 24 + -32 + 20, 16777215);
	    drawCenteredString(this.fontRendererObj, "You can always change this option in the Interface+ Settings!", this.width / 2, this.height / 4 + 24 + -32 + 30, 16777215);
	    super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	  }
}