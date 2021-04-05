package net.spellcraftgaming.interfaceplus.guiplus;

import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.guiplus.GuiSmallButtonNew;
import net.spellcraftgaming.interfaceplus.guiplus.GuiSmallButtonPlus;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import net.spellcraftgaming.interfaceplus.settings.EnumOptionsPlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

public class GuiSettingsPlus extends GuiScreen {

   private static final EnumOptionsPlus[] generalOptions = new EnumOptionsPlus[]{EnumOptionsPlus.TEXTBOX_ENABLED, EnumOptionsPlus.NEW_GUI_ENABLED, EnumOptionsPlus.SHOW_HEALTHBAR, EnumOptionsPlus.COLOR_HEALTHBAR, EnumOptionsPlus.HEALTHBAR_TYPE, EnumOptionsPlus.BUTTON_TOOLTIP_ENABLED};
   private static final EnumOptionsPlus[] hudOptions = new EnumOptionsPlus[]{EnumOptionsPlus.HUD_ENABLED, EnumOptionsPlus.HUD_TYPE, EnumOptionsPlus.RENDER_PLAYER_FACE, EnumOptionsPlus.SIZE_WIDGET, EnumOptionsPlus.SHOW_NUMBERS_EXPERIENCE, EnumOptionsPlus.SHOW_NUMBERS_HEALTH, EnumOptionsPlus.SHOW_NUMBERS_STAMINA, EnumOptionsPlus.SHOW_ITEMDURABILITY, EnumOptionsPlus.SHOW_ARRROWCOUNT, EnumOptionsPlus.SHOW_ITEMCOUNT};
   private static final EnumOptionsPlus[] colorOptions = new EnumOptionsPlus[]{EnumOptionsPlus.COLOR_AIR, EnumOptionsPlus.COLOR_EXPERIENCE, EnumOptionsPlus.COLOR_HEALTH, EnumOptionsPlus.COLOR_JUMPBAR, EnumOptionsPlus.COLOR_STAMINA};
   private static final EnumOptionsPlus[] infoOptions = new EnumOptionsPlus[]{EnumOptionsPlus.ENABLE_INFORMATIONPLUS, EnumOptionsPlus.SHOW_DAMAGEVSENTITY, EnumOptionsPlus.SHOW_DURABILITY, EnumOptionsPlus.SHOW_HUNGERVALUE, EnumOptionsPlus.SHOW_WOLFFOOD, EnumOptionsPlus.SHOW_POTIONINGREDIENT, EnumOptionsPlus.SHOW_ARMORAMOUNT, EnumOptionsPlus.SHOW_ENCHANTABLE, EnumOptionsPlus.SHOW_HARDNESS, EnumOptionsPlus.SHOW_RESISTANCE, EnumOptionsPlus.SHOW_LIGHTVALUE};
   private static final EnumOptionsPlus[] hudExtraOptions = new EnumOptionsPlus[]{EnumOptionsPlus.SHOW_ARMOR, EnumOptionsPlus.SIZE_DETAIL, EnumOptionsPlus.ENABLE_STATUS, EnumOptionsPlus.ENABLE_STATUS_BLINK, EnumOptionsPlus.SIZE_STATUS, EnumOptionsPlus.SHOW_HUNGERPREVIEW, EnumOptionsPlus.ENABLE_CLOCK, EnumOptionsPlus.ENABLE_TIMECOLOR, EnumOptionsPlus.CLOCK_TIME_FORMAT, EnumOptionsPlus.ENABLE_IMMERSIVE_CLOCK};
   private final GuiScreen parent;
   private final GameSettingsPlus settings;
   private String title = "Interface+ Settings";
   public int type = 0;
   public static final int ROOT = 0;
   public static final int GENERAL = 1;
   public static final int HUD = 2;
   public static final int COLORS = 3;
   public static final int HUDEXTRA = 4;
   public static final int INFO = 5;


   public GuiSettingsPlus(GuiScreen screen, GameSettingsPlus settings, int type) {
      this.type = type;
      this.parent = screen;
      this.settings = settings;
   }

   public void func_73866_w_() {
      int i = 0;
      this.title = I18n.format("options.title", new Object[0]);
      EnumOptionsPlus[] aenumoptions = null;
      switch(this.type) {
      case 0:
         this.title = I18n.format("Interface+ Navigation Menu", new Object[0]);
         break;
      case 1:
         aenumoptions = generalOptions;
         this.title = I18n.format("Interface+ General Settings", new Object[0]);
         break;
      case 2:
         aenumoptions = hudOptions;
         this.title = I18n.format("Hud+ Settings", new Object[0]);
         break;
      case 3:
         aenumoptions = colorOptions;
         this.title = I18n.format("Hud+ Color Settings", new Object[0]);
         break;
      case 4:
         aenumoptions = hudExtraOptions;
         this.title = I18n.format("Hud+ Extra Settings", new Object[0]);
         break;
      case 5:
         aenumoptions = infoOptions;
         this.title = I18n.format("Information+ Settings", new Object[0]);
      }

      boolean j = false;
      if(this.type != 0) {
         int var7 = aenumoptions.length;

         for(int k = 0; k < var7; ++k) {
            EnumOptionsPlus enumoptions = aenumoptions[k];
            GuiSmallButtonPlus guismallbutton = new GuiSmallButtonPlus(enumoptions.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), enumoptions, this.settings.getKeyBinding(enumoptions));
            if(enumoptions.returnEnumOrdinal() == EnumOptionsPlus.SHOW_HARDNESS.ordinal()) {
               guismallbutton.enabled = false;
            }

            if(enumoptions.returnEnumOrdinal() == EnumOptionsPlus.SHOW_RESISTANCE.ordinal()) {
               guismallbutton.enabled = false;
            }

            if(enumoptions.returnEnumOrdinal() == EnumOptionsPlus.SIZE_WIDGET.ordinal()) {
               guismallbutton.enabled = false;
            }

            this.buttonList.add(guismallbutton);
            ++i;
         }
      } else {
         this.buttonList.add(new GuiButton(201, this.width / 2 - 100, this.height / 4 + 24 + -32, I18n.format("Interface+ Settings", new Object[0])));
         this.buttonList.add(new GuiButton(202, this.width / 2 - 100, this.height / 4 + 24 + -32 + 24, I18n.format("HUD+ Settings", new Object[0])));
         this.buttonList.add(new GuiButton(203, this.width / 2 - 100, this.height / 4 + 24 + -32 + 48, I18n.format("HUD+ Color Settings", new Object[0])));
         this.buttonList.add(new GuiButton(204, this.width / 2 - 100, this.height / 4 + 24 + -32 + 72, I18n.format("Hud+ Extra Settings", new Object[0])));
         this.buttonList.add(new GuiButton(205, this.width / 2 - 100, this.height / 4 + 24 + -32 + 96, I18n.format("Information+ Settings", new Object[0])));
      }

      this.buttonList.add(new GuiButton(150, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
   }

   protected void func_146284_a(GuiButton par1GuiButton) {
      if(par1GuiButton.enabled) {
         if(par1GuiButton.id < 100 && par1GuiButton instanceof GuiSmallButtonPlus) {
            this.settings.setOptionValue(((GuiSmallButtonPlus)par1GuiButton).returnEnumOptions(), 1);
            par1GuiButton.displayString = this.settings.getKeyBinding(EnumOptionsPlus.getEnumOptions(par1GuiButton.id));
         }

         if(par1GuiButton.id < 100 && par1GuiButton instanceof GuiSmallButtonNew) {
            this.settings.setOptionValue(((GuiSmallButtonNew)par1GuiButton).returnEnumOptions(), 1);
            par1GuiButton.displayString = this.settings.getKeyBinding(EnumOptionsPlus.getEnumOptions(par1GuiButton.id));
         }

         if(par1GuiButton.id == 150) {
            InterfacePlus.settings.saveOptions();
            this.mc.displayGuiScreen(this.parent);
         }

         if(par1GuiButton.id == 201) {
            this.mc.displayGuiScreen(new GuiSettingsPlus(this, InterfacePlus.settings, 1));
         }

         if(par1GuiButton.id == 202) {
            this.mc.displayGuiScreen(new GuiSettingsPlus(this, InterfacePlus.settings, 2));
         }

         if(par1GuiButton.id == 203) {
            this.mc.displayGuiScreen(new GuiSettingsPlus(this, InterfacePlus.settings, 3));
         }

         if(par1GuiButton.id == 204) {
            this.mc.displayGuiScreen(new GuiSettingsPlus(this, InterfacePlus.settings, 4));
         }

         if(par1GuiButton.id == 205) {
            this.mc.displayGuiScreen(new GuiSettingsPlus(this, InterfacePlus.settings, 5));
         }
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.drawDefaultBackground();
      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
      this.drawCenteredString(this.fontRendererObj, I18n.format(this.title, new Object[0]), this.width / 2, 12, 16777215);
      GuiButtonTooltip.drawTooltip(this, (ArrayList)this.buttonList);
   }

}
