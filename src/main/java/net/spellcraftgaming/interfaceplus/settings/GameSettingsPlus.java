package net.spellcraftgaming.interfaceplus.settings;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings.Options;
import net.spellcraftgaming.interfaceplus.event.RenderHealthbar;
import net.spellcraftgaming.interfaceplus.main.InterfacePlus;
import net.spellcraftgaming.interfaceplus.settings.EnumOptionsPlus;

@SideOnly(Side.CLIENT)
public class GameSettingsPlus {

   protected Minecraft mc;
   private File optionsFile;
   private static final String[] COLOR = new String[]{"Red", "Blue", "Green", "Yellow", "White", "Grey"};
   private static final String[] SIZE = new String[]{"Small", "Default", "Large"};
   private static final String[] HUD_TYPE = new String[]{"Default", "Extended Widget", "Full Texture", "Hotbar Widget", "New Style"};
   private static final String[] HEALTHBAR_TYPE = new String[]{"Default", "Small", "Slim"};
   private static final String[] SHOW_HEALTHBAR = new String[]{"All", "At look at", "Disabled"};
   private static final String[] TIME_FORMAT = new String[]{"24 Hours", "12 Hours"};
   public boolean textbox_enabled = true;
   public boolean new_gui_enabled = false;
   public boolean button_tooltip_enabled = true;
   public boolean hud_enabled = true;
   public boolean show_armor = true;
   public boolean show_arrowcount = true;
   public boolean show_itemdurability = true;
   public boolean show_blockcount = true;
   public boolean show_numbers_health = true;
   public boolean show_numbers_stamina = true;
   public boolean show_numbers_experience = true;
   public boolean enable_status = true;
   public boolean enable_status_blink = true;
   public boolean enable_clock = true;
   public boolean enable_clock_color = true;
   public boolean enable_immersive_clock = false;
   public boolean render_player_face = true;
   public boolean showInformationPlus = true;
   public boolean showDurability = true;
   public boolean showDamageVsEntity = true;
   public boolean showHungerValue = true;
   public boolean showWolfFood = true;
   public boolean showPotionIngredient = true;
   public boolean showArmorAmount = true;
   public boolean showEnchantable = true;
   public boolean showHardness = true;
   public boolean showResistance = true;
   public boolean showLightValue = true;
   public boolean show_hunger_preview = true;
   public boolean first_start = true;
   public int hudtype = 0;
   public int healthbartype = 0;
   public int show_healthbar = 0;
   public int size_widget = 1;
   public int size_detail = 1;
   public int size_status = 1;
   public int color_health = 0;
   public int color_healthbar = 0;
   public int color_stamina = 2;
   public int color_air = 1;
   public int color_experience = 3;
   public int color_jumpbar = 5;
   public int clock_time_format = 0;


   public GameSettingsPlus(Minecraft par1Minecraft, File file) {
      this.mc = par1Minecraft;
      this.optionsFile = new File(file, "optionInterfacePlus.txt");
      this.loadOptions();
   }

   public void setOptionValue(EnumOptionsPlus options, int value) {
      if(options == EnumOptionsPlus.TEXTBOX_ENABLED) {
         this.textbox_enabled = !this.textbox_enabled;
         System.out.println(options + ":" + this.textbox_enabled);
      }

      if(options == EnumOptionsPlus.NEW_GUI_ENABLED) {
         this.new_gui_enabled = !this.new_gui_enabled;
         System.out.println(options + ":" + this.new_gui_enabled);
      }

      if(options == EnumOptionsPlus.BUTTON_TOOLTIP_ENABLED) {
         this.button_tooltip_enabled = !this.button_tooltip_enabled;
         System.out.println(options + ":" + this.button_tooltip_enabled);
      }

      if(options == EnumOptionsPlus.HUD_ENABLED) {
         this.hud_enabled = !this.hud_enabled;
         System.out.println(options + ":" + this.hud_enabled);
      }

      if(options == EnumOptionsPlus.SHOW_ARMOR) {
         this.show_armor = !this.show_armor;
         System.out.println(options + ":" + this.show_armor);
      }

      if(options == EnumOptionsPlus.SHOW_ARRROWCOUNT) {
         this.show_arrowcount = !this.show_arrowcount;
         System.out.println(options + ":" + this.show_arrowcount);
      }

      if(options == EnumOptionsPlus.SHOW_ITEMDURABILITY) {
         this.show_itemdurability = !this.show_itemdurability;
         System.out.println(options + ":" + this.show_itemdurability);
      }

      if(options == EnumOptionsPlus.SHOW_ITEMCOUNT) {
         this.show_blockcount = !this.show_blockcount;
         System.out.println(options + ":" + this.show_blockcount);
      }

      if(options == EnumOptionsPlus.SHOW_NUMBERS_HEALTH) {
         this.show_numbers_health = !this.show_numbers_health;
         System.out.println(options + ":" + this.show_numbers_health);
      }

      if(options == EnumOptionsPlus.SHOW_NUMBERS_STAMINA) {
         this.show_numbers_stamina = !this.show_numbers_stamina;
         System.out.println(options + ":" + this.show_numbers_stamina);
      }

      if(options == EnumOptionsPlus.SHOW_NUMBERS_EXPERIENCE) {
         this.show_numbers_experience = !this.show_numbers_experience;
         System.out.println(options + ":" + this.show_numbers_experience);
      }

      if(options == EnumOptionsPlus.ENABLE_STATUS) {
         this.enable_status = !this.enable_status;
         System.out.println(options + ":" + this.enable_status);
      }

      if(options == EnumOptionsPlus.ENABLE_STATUS_BLINK) {
         this.enable_status_blink = !this.enable_status_blink;
         System.out.println(options + ":" + this.enable_status_blink);
      }

      if(options == EnumOptionsPlus.ENABLE_CLOCK) {
         this.enable_clock = !this.enable_clock;
         System.out.println(options + ":" + this.enable_clock);
      }

      if(options == EnumOptionsPlus.ENABLE_TIMECOLOR) {
         this.enable_clock_color = !this.enable_clock_color;
         System.out.println(options + ":" + this.enable_clock_color);
      }

      if(options == EnumOptionsPlus.ENABLE_IMMERSIVE_CLOCK) {
         this.enable_immersive_clock = !this.enable_immersive_clock;
         System.out.println(options + ":" + this.enable_immersive_clock);
      }

      if(options == EnumOptionsPlus.RENDER_PLAYER_FACE) {
         this.render_player_face = !this.render_player_face;
         System.out.println(options + ":" + this.render_player_face);
      }

      if(options == EnumOptionsPlus.ENABLE_INFORMATIONPLUS) {
         this.showInformationPlus = !this.showInformationPlus;
         System.out.println(options + ":" + this.showInformationPlus);
      }

      if(options == EnumOptionsPlus.SHOW_DAMAGEVSENTITY) {
         this.showDamageVsEntity = !this.showDamageVsEntity;
         System.out.println(options + ":" + this.showDamageVsEntity);
      }

      if(options == EnumOptionsPlus.SHOW_DURABILITY) {
         this.showDurability = !this.showDurability;
         System.out.println(options + ":" + this.showDurability);
      }

      if(options == EnumOptionsPlus.SHOW_HUNGERVALUE) {
         this.showHungerValue = !this.showHungerValue;
         System.out.println(options + ":" + this.showHungerValue);
      }

      if(options == EnumOptionsPlus.SHOW_WOLFFOOD) {
         this.showWolfFood = !this.showWolfFood;
         System.out.println(options + ":" + this.showWolfFood);
      }

      if(options == EnumOptionsPlus.SHOW_RESISTANCE) {
         this.showPotionIngredient = !this.showPotionIngredient;
         System.out.println(options + ":" + this.showPotionIngredient);
      }

      if(options == EnumOptionsPlus.SHOW_ARMORAMOUNT) {
         this.showArmorAmount = !this.showArmorAmount;
         System.out.println(options + ":" + this.showArmorAmount);
      }

      if(options == EnumOptionsPlus.SHOW_ENCHANTABLE) {
         this.showEnchantable = !this.showEnchantable;
         System.out.println(options + ":" + this.showEnchantable);
      }

      if(options == EnumOptionsPlus.SHOW_HARDNESS) {
         this.showHardness = !this.showHardness;
         System.out.println(options + ":" + this.showHardness);
      }

      if(options == EnumOptionsPlus.SHOW_RESISTANCE) {
         this.showResistance = !this.showResistance;
         System.out.println(options + ":" + this.showResistance);
      }

      if(options == EnumOptionsPlus.SHOW_LIGHTVALUE) {
         this.showLightValue = !this.showLightValue;
         System.out.println(options + ":" + this.showLightValue);
      }

      if(options == EnumOptionsPlus.SHOW_HUNGERPREVIEW) {
         this.show_hunger_preview = !this.show_hunger_preview;
         System.out.println(options + ":" + this.show_hunger_preview);
      }

      if(options == EnumOptionsPlus.FIRST_START) {
         this.first_start = !this.first_start;
         System.out.println(options + ":" + this.first_start);
      }

      if(options == EnumOptionsPlus.COLOR_HEALTH) {
         if(this.color_health >= 5) {
            this.color_health = 0;
         } else {
            this.color_health += value;
         }

         System.out.println(options + ":" + this.color_health);
      }

      if(options == EnumOptionsPlus.COLOR_HEALTHBAR) {
         if(this.color_healthbar >= 5) {
            this.color_healthbar = 0;
         } else {
            this.color_healthbar += value;
         }

         System.out.println(options + ":" + this.color_healthbar);
      }

      if(options == EnumOptionsPlus.COLOR_STAMINA) {
         if(this.color_stamina >= 5) {
            this.color_stamina = 0;
         } else {
            this.color_stamina += value;
         }

         System.out.println(options + ":" + this.color_stamina);
      }

      if(options == EnumOptionsPlus.COLOR_AIR) {
         if(this.color_air >= 5) {
            this.color_air = 0;
         } else {
            this.color_air += value;
         }

         System.out.println(options + ":" + this.color_air);
      }

      if(options == EnumOptionsPlus.COLOR_EXPERIENCE) {
         if(this.color_experience >= 5) {
            this.color_experience = 0;
         } else {
            this.color_experience += value;
         }

         System.out.println(options + ":" + this.color_experience);
      }

      if(options == EnumOptionsPlus.COLOR_JUMPBAR) {
         if(this.color_jumpbar >= 5) {
            this.color_jumpbar = 0;
         } else {
            this.color_jumpbar += value;
         }

         System.out.println(options + ":" + this.color_jumpbar);
      }

      if(options == EnumOptionsPlus.CLOCK_TIME_FORMAT) {
         if(this.clock_time_format >= 1) {
            this.clock_time_format = 0;
         } else {
            this.clock_time_format += value;
         }

         System.out.println(options + ":" + this.clock_time_format);
      }

      if(options == EnumOptionsPlus.HUD_TYPE) {
         if(this.hudtype >= 4) {
            this.hudtype = 0;
         } else {
            this.hudtype += value;
         }

         InterfacePlus var10000 = InterfacePlus.instance;
         InterfacePlus.cache.hudtype_change = true;
         System.out.println(options + ":" + this.hudtype);
      }

      if(options == EnumOptionsPlus.HEALTHBAR_TYPE) {
         if(this.healthbartype >= 2) {
            this.healthbartype = 0;
         } else {
            this.healthbartype += value;
         }

         System.out.println(options + ":" + this.healthbartype);
      }

      if(options == EnumOptionsPlus.SHOW_HEALTHBAR) {
         if(this.show_healthbar >= 2) {
            this.show_healthbar = 0;
         } else {
            this.show_healthbar += value;
         }

         System.out.println(options + ":" + this.show_healthbar);
      }

      if(options == EnumOptionsPlus.SIZE_DETAIL) {
         if(this.size_detail >= 1) {
            this.size_detail = 0;
         } else {
            this.size_detail += value;
         }

         System.out.println(options + ":" + this.size_detail);
      }

      if(options == EnumOptionsPlus.SIZE_STATUS) {
         if(this.size_status >= 1) {
            this.size_status = 0;
         } else {
            this.size_status += value;
         }

         System.out.println(options + ":" + this.size_status);
      }

      this.saveOptions();
   }

   public boolean getOptionOrdinalValue(EnumOptionsPlus options) {
      switch(GameSettingsPlus.SwitchOptions.optionIds[options.ordinal()]) {
      case 1:
         return this.textbox_enabled;
      case 2:
         return this.show_armor;
      case 3:
         return this.show_numbers_health;
      case 4:
         return this.show_numbers_stamina;
      case 5:
         return this.show_numbers_experience;
      case 6:
         return this.enable_status;
      case 7:
         return this.enable_status_blink;
      case 8:
         return this.render_player_face;
      case 9:
         return this.hud_enabled;
      case 10:
         return this.showInformationPlus;
      case 11:
         return this.showDamageVsEntity;
      case 12:
         return this.showDurability;
      case 13:
         return this.showHungerValue;
      case 14:
         return this.showWolfFood;
      case 15:
         return this.showPotionIngredient;
      case 16:
         return this.showArmorAmount;
      case 17:
         return this.showEnchantable;
      case 18:
         return this.showHardness;
      case 19:
         return this.showResistance;
      case 20:
         return this.showLightValue;
      case 21:
         return this.show_hunger_preview;
      case 22:
         return this.enable_clock;
      case 23:
         return this.enable_clock_color;
      case 24:
         return this.enable_immersive_clock;
      case 25:
         return this.first_start;
      case 26:
         return this.new_gui_enabled;
      case 27:
         return this.button_tooltip_enabled;
      case 28:
         return this.show_itemdurability;
      case 29:
         return this.show_arrowcount;
      case 30:
         return this.show_blockcount;
      default:
         return false;
      }
   }

   public void loadOptions() {
      try {
         if(!this.optionsFile.exists()) {
            return;
         }

         BufferedReader e = new BufferedReader(new FileReader(this.optionsFile));
         String s = "";

         while((s = e.readLine()) != null) {
            try {
               String[] e1 = s.split(":");
               if(e1[0].equals("textbox_enabled")) {
                  this.textbox_enabled = e1[1].equals("true");
               }

               if(e1[0].equals("new_hud_enabled")) {
                  this.new_gui_enabled = e1[1].equals("true");
               }

               if(e1[0].equals("button_tooltip_enabled")) {
                  this.button_tooltip_enabled = e1[1].equals("true");
               }

               if(e1[0].equals("hud_enabled")) {
                  this.hud_enabled = e1[1].equals("true");
               }

               if(e1[0].equals("size_widget")) {
                  this.size_widget = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("size_detail")) {
                  this.size_detail = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("size_status")) {
                  this.size_status = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("color_health")) {
                  this.color_health = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("color_healthbar")) {
                  this.color_healthbar = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("color_stamina")) {
                  this.color_stamina = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("color_air")) {
                  this.color_air = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("color_experience")) {
                  this.color_experience = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("color_jumpbar")) {
                  this.color_jumpbar = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("clock_time_format")) {
                  this.clock_time_format = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("hudtype")) {
                  this.hudtype = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("healthbartype")) {
                  this.healthbartype = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("show_healthbar")) {
                  this.show_healthbar = Integer.parseInt(e1[1]);
               }

               if(e1[0].equals("show_armor")) {
                  this.show_armor = e1[1].equals("true");
               }

               if(e1[0].equals("show_blockcount")) {
                  this.show_blockcount = e1[1].equals("true");
               }

               if(e1[0].equals("show_arrowcount")) {
                  this.show_arrowcount = e1[1].equals("true");
               }

               if(e1[0].equals("show_itemdurability")) {
                  this.show_itemdurability = e1[1].equals("true");
               }

               if(e1[0].equals("enable_status")) {
                  this.enable_status = e1[1].equals("true");
               }

               if(e1[0].equals("enable_status_blink")) {
                  this.enable_status_blink = e1[1].equals("true");
               }

               if(e1[0].equals("enable_clock")) {
                  this.enable_clock = e1[1].equals("true");
               }

               if(e1[0].equals("enable_clock_color")) {
                  this.enable_clock_color = e1[1].equals("true");
               }

               if(e1[0].equals("enable_immersive_clock")) {
                  this.enable_immersive_clock = e1[1].equals("true");
               }

               if(e1[0].equals("render_player_face")) {
                  this.render_player_face = e1[1].equals("true");
               }

               if(e1[0].equals("show_numbers_health")) {
                  this.show_numbers_health = e1[1].equals("true");
               }

               if(e1[0].equals("show_numbers_stamina")) {
                  this.show_numbers_stamina = e1[1].equals("true");
               }

               if(e1[0].equals("show_numbers_experience")) {
                  this.show_numbers_experience = e1[1].equals("true");
               }

               if(e1[0].equals("showInformationPlus")) {
                  this.showInformationPlus = e1[1].equals("true");
               }

               if(e1[0].equals("showDamageVsEntity")) {
                  this.showDamageVsEntity = e1[1].equals("true");
               }

               if(e1[0].equals("showDurability")) {
                  this.showDurability = e1[1].equals("true");
               }

               if(e1[0].equals("showHungerValue")) {
                  this.showHungerValue = e1[1].equals("true");
               }

               if(e1[0].equals("showWolfFood")) {
                  this.showWolfFood = e1[1].equals("true");
               }

               if(e1[0].equals("showPotionIngredient")) {
                  this.showPotionIngredient = e1[1].equals("true");
               }

               if(e1[0].equals("showArmorAmount")) {
                  this.showArmorAmount = e1[1].equals("true");
               }

               if(e1[0].equals("showEnchantable")) {
                  this.showEnchantable = e1[1].equals("true");
               }

               if(e1[0].equals("showHardness")) {
                  this.showHardness = e1[1].equals("true");
               }

               if(e1[0].equals("showResistance")) {
                  this.showResistance = e1[1].equals("true");
               }

               if(e1[0].equals("showLightValue")) {
                  this.showLightValue = e1[1].equals("true");
               }

               if(e1[0].equals("show_hunger_preview")) {
                  this.show_hunger_preview = e1[1].equals("true");
               }

               if(e1[0].equals("first_start")) {
                  this.first_start = e1[1].equals("true");
               }
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }

         e.close();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private float parseFloat(String par1Str) {
      return par1Str.equals("false")?0.0F:(par1Str.equals("true")?1.0F:Float.parseFloat(par1Str));
   }

   public String getKeyBinding(EnumOptionsPlus par1EnumOptions) {
      String s = I18n.format(par1EnumOptions.getEnumString(), new Object[0]) + ": ";
      if(par1EnumOptions.getEnumBoolean()) {
         boolean flag = this.getOptionOrdinalValue(par1EnumOptions);
         return flag?s + I18n.format("options.on", new Object[0]):s + I18n.format("options.off", new Object[0]);
      } else {
         return par1EnumOptions == EnumOptionsPlus.SIZE_WIDGET?s + getTranslation(SIZE, this.size_widget):(par1EnumOptions == EnumOptionsPlus.SIZE_STATUS?s + getTranslation(SIZE, this.size_status):(par1EnumOptions == EnumOptionsPlus.SIZE_DETAIL?s + getTranslation(SIZE, this.size_detail):(par1EnumOptions == EnumOptionsPlus.HUD_TYPE?s + getTranslation(HUD_TYPE, this.hudtype):(par1EnumOptions == EnumOptionsPlus.HEALTHBAR_TYPE?s + getTranslation(HEALTHBAR_TYPE, this.healthbartype):(par1EnumOptions == EnumOptionsPlus.SHOW_HEALTHBAR?s + getTranslation(SHOW_HEALTHBAR, this.show_healthbar):(par1EnumOptions == EnumOptionsPlus.COLOR_JUMPBAR?s + getTranslation(COLOR, this.color_jumpbar):(par1EnumOptions == EnumOptionsPlus.COLOR_EXPERIENCE?s + getTranslation(COLOR, this.color_experience):(par1EnumOptions == EnumOptionsPlus.COLOR_STAMINA?s + getTranslation(COLOR, this.color_stamina):(par1EnumOptions == EnumOptionsPlus.COLOR_HEALTH?s + getTranslation(COLOR, this.color_health):(par1EnumOptions == EnumOptionsPlus.COLOR_HEALTHBAR?s + getTranslation(COLOR, this.color_healthbar):(par1EnumOptions == EnumOptionsPlus.COLOR_AIR?s + getTranslation(COLOR, this.color_air):(par1EnumOptions == EnumOptionsPlus.CLOCK_TIME_FORMAT?s + getTranslation(TIME_FORMAT, this.clock_time_format):s))))))))))));
      }
   }

   private static String getTranslation(String[] par0ArrayOfStr, int par1) {
      if(par1 < 0 || par1 >= par0ArrayOfStr.length) {
         par1 = 0;
      }

      return I18n.format(par0ArrayOfStr[par1], new Object[0]);
   }

   public void saveOptions() {
      if(!FMLClientHandler.instance().isLoading()) {
         try {
            PrintWriter var2 = new PrintWriter(new FileWriter(this.optionsFile));
            var2.println("textbox_enabled:" + this.textbox_enabled);
            var2.println("new_hud_enabled:" + this.new_gui_enabled);
            var2.println("button_tooltip_enabled:" + this.button_tooltip_enabled);
            var2.println("show_healthbar:" + this.show_healthbar);
            var2.println("size_detail:" + this.size_detail);
            var2.println("size_status:" + this.size_status);
            var2.println("size_widget:" + this.size_widget);
            var2.println("color_health:" + this.color_health);
            var2.println("color_healthbar:" + this.color_healthbar);
            var2.println("color_air:" + this.color_air);
            var2.println("color_stamina:" + this.color_stamina);
            var2.println("color_experience:" + this.color_experience);
            var2.println("color_jumpbar:" + this.color_jumpbar);
            var2.println("clock_time_format:" + this.clock_time_format);
            var2.println("hudtype:" + this.hudtype);
            var2.println("healthbartype:" + this.healthbartype);
            var2.println("hud_enabled:" + this.hud_enabled);
            var2.println("show_armor:" + this.show_armor);
            var2.println("show_blockcount:" + this.show_blockcount);
            var2.println("show_arrowcount:" + this.show_arrowcount);
            var2.println("show_itemdurability:" + this.show_itemdurability);
            var2.println("show_numbers_health:" + this.show_numbers_health);
            var2.println("show_numbers_stamina:" + this.show_numbers_stamina);
            var2.println("show_numbers_experience:" + this.show_numbers_experience);
            var2.println("enable_status:" + this.enable_status);
            var2.println("enable_status_blink:" + this.enable_status_blink);
            var2.println("enable_clock:" + this.enable_clock);
            var2.println("enable_clock_color:" + this.enable_clock_color);
            var2.println("enable_immersive_clock:" + this.enable_immersive_clock);
            var2.println("render_player_face:" + this.render_player_face);
            var2.println("showInformationPlus:" + this.showInformationPlus);
            var2.println("showDamageVsEntity:" + this.showDamageVsEntity);
            var2.println("showDurability:" + this.showDurability);
            var2.println("showHungerValue:" + this.showHungerValue);
            var2.println("showWolfFood:" + this.showWolfFood);
            var2.println("showPotionIngredient:" + this.showPotionIngredient);
            var2.println("showArmorAmount:" + this.showArmorAmount);
            var2.println("showEnchantable:" + this.showEnchantable);
            var2.println("showHardness:" + this.showHardness);
            var2.println("showResistance:" + this.showResistance);
            var2.println("showLightValue:" + this.showLightValue);
            var2.println("show_hunger_preview:" + this.show_hunger_preview);
            var2.println("first_start:" + this.first_start);
            var2.close();
         } catch (Exception var21) {
            var21.printStackTrace();
         }

         RenderHealthbar.setNewColor(this);
      }

   }


   static final class SwitchOptions {

      static final int[] optionIds = new int[Options.values().length];


      static {
         try {
            optionIds[EnumOptionsPlus.TEXTBOX_ENABLED.ordinal()] = 1;
         } catch (NoSuchFieldError var30) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_ARMOR.ordinal()] = 2;
         } catch (NoSuchFieldError var29) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_NUMBERS_HEALTH.ordinal()] = 3;
         } catch (NoSuchFieldError var28) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_NUMBERS_STAMINA.ordinal()] = 4;
         } catch (NoSuchFieldError var27) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_NUMBERS_EXPERIENCE.ordinal()] = 5;
         } catch (NoSuchFieldError var26) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.ENABLE_STATUS.ordinal()] = 6;
         } catch (NoSuchFieldError var25) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.ENABLE_STATUS_BLINK.ordinal()] = 7;
         } catch (NoSuchFieldError var24) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.RENDER_PLAYER_FACE.ordinal()] = 8;
         } catch (NoSuchFieldError var23) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.HUD_ENABLED.ordinal()] = 9;
         } catch (NoSuchFieldError var22) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.ENABLE_INFORMATIONPLUS.ordinal()] = 10;
         } catch (NoSuchFieldError var21) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_DAMAGEVSENTITY.ordinal()] = 11;
         } catch (NoSuchFieldError var20) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_DURABILITY.ordinal()] = 12;
         } catch (NoSuchFieldError var19) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_HUNGERVALUE.ordinal()] = 13;
         } catch (NoSuchFieldError var18) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_WOLFFOOD.ordinal()] = 14;
         } catch (NoSuchFieldError var17) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_POTIONINGREDIENT.ordinal()] = 15;
         } catch (NoSuchFieldError var16) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_ARMORAMOUNT.ordinal()] = 16;
         } catch (NoSuchFieldError var15) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_ENCHANTABLE.ordinal()] = 17;
         } catch (NoSuchFieldError var14) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_HARDNESS.ordinal()] = 18;
         } catch (NoSuchFieldError var13) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_RESISTANCE.ordinal()] = 19;
         } catch (NoSuchFieldError var12) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_LIGHTVALUE.ordinal()] = 20;
         } catch (NoSuchFieldError var11) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_HUNGERPREVIEW.ordinal()] = 21;
         } catch (NoSuchFieldError var10) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.ENABLE_CLOCK.ordinal()] = 22;
         } catch (NoSuchFieldError var9) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.ENABLE_TIMECOLOR.ordinal()] = 23;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.ENABLE_IMMERSIVE_CLOCK.ordinal()] = 24;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.FIRST_START.ordinal()] = 25;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.NEW_GUI_ENABLED.ordinal()] = 26;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.BUTTON_TOOLTIP_ENABLED.ordinal()] = 27;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_ITEMDURABILITY.ordinal()] = 28;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_ARRROWCOUNT.ordinal()] = 29;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            optionIds[EnumOptionsPlus.SHOW_ITEMCOUNT.ordinal()] = 30;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
