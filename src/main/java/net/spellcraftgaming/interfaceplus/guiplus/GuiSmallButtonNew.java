package net.spellcraftgaming.interfaceplus.guiplus;

import net.minecraft.client.Minecraft;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonNew;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.settings.EnumOptionsPlus;

public class GuiSmallButtonNew extends GuiButtonNew {

   private final EnumOptionsPlus enumOptions;


   public GuiSmallButtonNew(int par1, int par2, int par3, String par4Str) {
      this(par1, par2, par3, (EnumOptionsPlus)null, par4Str);
   }

   public GuiSmallButtonNew(int par1, int par2, int par3, int par4, int par5, String par6Str) {
      super(par1, par2, par3, par4, par5, par6Str, true);
      this.enumOptions = null;
   }

   public GuiSmallButtonNew(int par1, int par2, int par3, EnumOptionsPlus par4EnumOptions, String par5Str) {
      super(par1, par2, par3, 150, 14, par5Str, true);
      this.enumOptions = par4EnumOptions;
   }

   public EnumOptionsPlus returnEnumOptions() {
      return this.enumOptions;
   }

   public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
      super.func_146112_a(p_146112_1_, p_146112_2_, p_146112_3_);
      super.field_146123_n = p_146112_2_ >= super.field_146128_h && p_146112_3_ >= super.field_146129_i && p_146112_2_ < super.field_146128_h + super.field_146120_f && p_146112_3_ < super.field_146129_i + super.field_146121_g;
      int k = this.func_146114_a(super.field_146123_n);
      if(k == 2) {
         GuiButtonTooltip.setTooltip(Minecraft.getMinecraft().currentScreen, this);
      }

   }
}
