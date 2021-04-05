package net.spellcraftgaming.interfaceplus.guiplus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiButtonNew extends GuiButton {

   protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
   public int field_146120_f;
   public int field_146121_g;
   public int field_146128_h;
   public int field_146129_i;
   public String field_146126_j;
   public int field_146127_k;
   public boolean field_146124_l;
   public boolean field_146125_m;
   protected boolean field_146123_n;
   private static final String __OBFID = "CL_00000668";
   public int packedFGColour;
   public int alpha;
   public boolean state;


   public GuiButtonNew(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_, String p_i1020_4_, boolean state) {
      this(p_i1020_1_, p_i1020_2_, p_i1020_3_, 200, 20, p_i1020_4_, state);
   }

   public GuiButtonNew(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_, String p_i1020_4_) {
      this(p_i1020_1_, p_i1020_2_, p_i1020_3_, 200, 20, p_i1020_4_, true);
   }

   public GuiButtonNew(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_, boolean state) {
      super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
      this.field_146120_f = 200;
      this.field_146121_g = 20;
      this.field_146124_l = true;
      this.field_146125_m = true;
      this.field_146127_k = p_i1021_1_;
      this.field_146128_h = p_i1021_2_;
      this.field_146129_i = p_i1021_3_;
      this.field_146120_f = p_i1021_4_;
      this.field_146121_g = p_i1021_5_;
      this.field_146126_j = p_i1021_6_;
      this.alpha = 0;
      this.state = state;
   }

   public int func_146114_a(boolean p_146114_1_) {
      byte b0 = 1;
      if(!this.field_146124_l) {
         b0 = 0;
      } else if(p_146114_1_) {
         b0 = 2;
      }

      return b0;
   }

   public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
      if(this.field_146125_m) {
         FontRenderer fontrenderer = p_146112_1_.fontRenderer;
         p_146112_1_.getTextureManager().bindTexture(buttonTextures);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
         int k = this.func_146114_a(this.field_146123_n);
         this.func_146119_b(p_146112_1_, p_146112_2_, p_146112_3_);
         int l = 14737632;
         if(this.packedFGColour != 0) {
            l = this.packedFGColour;
         } else if(!this.field_146124_l) {
            l = 10526880;
         } else if(this.field_146123_n) {
            l = 16777120;
         }

         String textMod = this.field_146126_j;
         if(this.field_146123_n && this.field_146124_l) {
            this.alpha = 822083583;
            textMod = "> " + textMod + " <";
         } else {
            this.alpha = 16777215;
         }

         if(this.state) {
            drawRect(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, this.alpha);
         }

         if(this.state) {
            this.drawCenteredString(fontrenderer, textMod, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, -1);
         }

         if(k == 2) {
            GuiButtonTooltip.setTooltip(Minecraft.getMinecraft().currentScreen, this);
         }
      }

   }

   protected void func_146119_b(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {}

   public void func_146118_a(int p_146118_1_, int p_146118_2_) {}

   public boolean func_146116_c(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
      return this.field_146124_l && this.field_146125_m && p_146116_2_ >= this.field_146128_h && p_146116_3_ >= this.field_146129_i && p_146116_2_ < this.field_146128_h + this.field_146120_f && p_146116_3_ < this.field_146129_i + this.field_146121_g;
   }

   public boolean func_146115_a() {
      return this.field_146123_n;
   }

   public void func_146111_b(int p_146111_1_, int p_146111_2_) {}

   public void func_146113_a(SoundHandler p_146113_1_) {
      p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
   }

   public int func_146117_b() {
      return this.field_146120_f;
   }

   public int func_154310_c() {
      return this.field_146121_g;
   }

}
