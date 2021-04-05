package net.spellcraftgaming.interfaceplus.guiplus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.settings.EnumOptionsPlus;

@SideOnly(Side.CLIENT)
public class GuiSmallButtonPlus extends GuiButton {

   private final EnumOptionsPlus enumOptions;


   public GuiSmallButtonPlus(int par1, int par2, int par3, String par4Str) {
      this(par1, par2, par3, (EnumOptionsPlus)null, par4Str);
   }

   public GuiSmallButtonPlus(int par1, int par2, int par3, int par4, int par5, String par6Str) {
      super(par1, par2, par3, par4, par5, par6Str);
      this.enumOptions = null;
   }

   public GuiSmallButtonPlus(int par1, int par2, int par3, EnumOptionsPlus par4EnumOptions, String par5Str) {
      super(par1, par2, par3, 150, 20, par5Str);
      this.enumOptions = par4EnumOptions;
   }

   public EnumOptionsPlus returnEnumOptions() {
      return this.enumOptions;
   }

   public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
      super.drawButton(p_146112_1_, p_146112_2_, p_146112_3_);
      this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
      int k = this.getHoverState(this.field_146123_n);
      if(k == 2) {
         GuiButtonTooltip.setTooltip(Minecraft.getMinecraft().currentScreen, this);
      }

   }
}
