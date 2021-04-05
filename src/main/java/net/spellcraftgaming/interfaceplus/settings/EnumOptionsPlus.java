package net.spellcraftgaming.interfaceplus.settings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public enum EnumOptionsPlus {

   TEXTBOX_ENABLED("TEXTBOX_ENABLED", 0, "Textbox", false, true),
   NEW_GUI_ENABLED("NEW_GUI_ENABLED", 1, "New Gui (Experimental)", false, true),
   BUTTON_TOOLTIP_ENABLED("BUTTON_TOOLTIP_ENABLED", 2, "Button Tooltip", false, true),
   SHOW_ARMOR("SHOW_ARMOR", 3, "Show Armor", false, true),
   SHOW_ITEMDURABILITY("SHOW_ITEMDURABILITY", 4, "Show Item Durabiliy", false, true),
   SHOW_ITEMCOUNT("SHOW_ITEMCOUNT", 5, "Show Block count", false, true),
   SHOW_ARRROWCOUNT("SHOW_ARRROWCOUNT", 6, "Show Arrow count", false, true),
   SHOW_HEALTHBAR("SHOW_HEALTHBAR", 7, "Healthbar", false, false),
   SHOW_NUMBERS_HEALTH("SHOW_NUMBERS_HEALTH", 8, "Show Health Value", false, true),
   SHOW_NUMBERS_STAMINA("SHOW_NUMBERS_STAMINA", 9, "Show Stamina Value", false, true),
   SHOW_NUMBERS_EXPERIENCE("SHOW_NUMBERS_EXPERIENCE", 10, "Show Experience Value", false, true),
   ENABLE_STATUS("ENABLE_STATUS", 11, "Enable Status", false, true),
   ENABLE_STATUS_BLINK("ENABLE_STATUS_BLINK", 12, "Enable Status Blink", false, true),
   ENABLE_CLOCK("ENABLE_CLOCK", 13, "Enable Clock", false, true),
   ENABLE_IMMERSIVE_CLOCK("ENABLE_IMMERSIVE_CLOCK", 14, "Enable Immersive Clock", false, true),
   ENABLE_TIMECOLOR("ENABLE_TIMECOLOR", 15, "Enable Colored Clock", false, true),
   RENDER_PLAYER_FACE("RENDER_PLAYER_FACE", 16, "Render Player Face", false, true),
   HUD_TYPE("HUD_TYPE", 17, "Hud Type", false, false),
   HEALTHBAR_TYPE("HEALTHBAR_TYPE", 18, "Healthbar Type", false, false),
   SIZE_DETAIL("SIZE_DETAIL", 19, "Detail Size", false, false),
   SIZE_STATUS("SIZE_STATUS", 20, "Status Size", false, false),
   SIZE_WIDGET("SIZE_WIDGET", 21, "Widget Size", false, false),
   COLOR_HEALTH("COLOR_HEALTH", 22, "Health Color", false, false),
   COLOR_HEALTHBAR("COLOR_HEALTHBAR", 23, "Healthbar Color", false, false),
   COLOR_STAMINA("COLOR_STAMINA", 24, "Stamina Color", false, false),
   COLOR_AIR("COLOR_AIR", 25, "Air Color", false, false),
   COLOR_EXPERIENCE("COLOR_EXPERIENCE", 26, "Experience Color", false, false),
   COLOR_JUMPBAR("COLOR_JUMPBAR", 27, "Jumpbar Color", false, false),
   HUD_ENABLED("HUD_ENABLED", 28, "Hud", false, true),
   ENABLE_INFORMATIONPLUS("ENABLE_INFORMATIONPLUS", 29, "Enable Information+", false, true),
   SHOW_DURABILITY("SHOW_DURABILITY", 30, "Show Durability", false, true),
   SHOW_DAMAGEVSENTITY("SHOW_DAMAGEVSENTITY", 31, "Show Damage Vs Entity", false, true),
   SHOW_HUNGERVALUE("SHOW_HUNGERVALUE", 32, "Show Hunger Value", false, true),
   SHOW_WOLFFOOD("SHOW_WOLFFOOD", 33, "Show Wolf Food", false, true),
   SHOW_POTIONINGREDIENT("SHOW_POTIONINGREDIENT", 34, "Show Potion Ingredient", false, true),
   SHOW_ARMORAMOUNT("SHOW_ARMORAMOUNT", 35, "Show Armor Amount", false, true),
   SHOW_ENCHANTABLE("SHOW_ENCHANTABLE", 36, "Show Enchantability", false, true),
   SHOW_HARDNESS("SHOW_HARDNESS", 37, "Show Block Hardness", false, true),
   SHOW_RESISTANCE("SHOW_RESISTANCE", 38, "Show Block Resistance", false, true),
   SHOW_LIGHTVALUE("SHOW_LIGHTVALUE", 39, "Show Block Light Value", false, true),
   SHOW_HUNGERPREVIEW("SHOW_HUNGERPREVIEW", 40, "Show Hunger Preview", false, true),
   CLOCK_TIME_FORMAT("CLOCK_TIME_FORMAT", 41, "Clock Time Format", false, false),
   FIRST_START("FIRST_START", 42, "First Start", false, true);
   private final boolean enumFloat;
   private final boolean enumBoolean;
   private final String enumString;
   // $FF: synthetic field
   private static final EnumOptionsPlus[] $VALUES = new EnumOptionsPlus[]{TEXTBOX_ENABLED, NEW_GUI_ENABLED, BUTTON_TOOLTIP_ENABLED, SHOW_ARMOR, SHOW_ITEMDURABILITY, SHOW_ITEMCOUNT, SHOW_ARRROWCOUNT, SHOW_HEALTHBAR, SHOW_NUMBERS_HEALTH, SHOW_NUMBERS_STAMINA, SHOW_NUMBERS_EXPERIENCE, ENABLE_STATUS, ENABLE_STATUS_BLINK, ENABLE_CLOCK, ENABLE_IMMERSIVE_CLOCK, ENABLE_TIMECOLOR, RENDER_PLAYER_FACE, HUD_TYPE, HEALTHBAR_TYPE, SIZE_DETAIL, SIZE_STATUS, SIZE_WIDGET, COLOR_HEALTH, COLOR_HEALTHBAR, COLOR_STAMINA, COLOR_AIR, COLOR_EXPERIENCE, COLOR_JUMPBAR, HUD_ENABLED, ENABLE_INFORMATIONPLUS, SHOW_DURABILITY, SHOW_DAMAGEVSENTITY, SHOW_HUNGERVALUE, SHOW_WOLFFOOD, SHOW_POTIONINGREDIENT, SHOW_ARMORAMOUNT, SHOW_ENCHANTABLE, SHOW_HARDNESS, SHOW_RESISTANCE, SHOW_LIGHTVALUE, SHOW_HUNGERPREVIEW, CLOCK_TIME_FORMAT, FIRST_START};


   public static EnumOptionsPlus getEnumOptions(int par0) {
      EnumOptionsPlus[] aenumoptions = values();
      int j = aenumoptions.length;

      for(int k = 0; k < j; ++k) {
         EnumOptionsPlus enumoptions = aenumoptions[k];
         if(enumoptions.returnEnumOrdinal() == par0) {
            return enumoptions;
         }
      }

      return null;
   }

   private EnumOptionsPlus(String var1, int var2, String par3Str, boolean par4, boolean par5) {
      this.enumString = par3Str;
      this.enumFloat = par4;
      this.enumBoolean = par5;
   }

   public boolean getEnumFloat() {
      return this.enumFloat;
   }

   public boolean getEnumBoolean() {
      return this.enumBoolean;
   }

   public int returnEnumOrdinal() {
      return this.ordinal();
   }

   public String getEnumString() {
      return this.enumString;
   }

}
