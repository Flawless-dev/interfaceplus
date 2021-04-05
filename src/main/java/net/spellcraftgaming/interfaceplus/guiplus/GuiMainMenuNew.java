package net.spellcraftgaming.interfaceplus.guiplus;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.spellcraftgaming.interfaceplus.event.RenderTickHandler;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonNew;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.guiplus.GuiPanorama;
import net.spellcraftgaming.interfaceplus.guiplus.GuiSettingsPlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

public class GuiMainMenuNew extends GuiMainMenu {

   protected GameSettingsPlus settings;
   protected static int heightY = 0;
   protected static int widthOffset = 15;
   private static final ResourceLocation TEXTBOX = new ResourceLocation("spellcraftgaming:interfaceplus/textbox.png");
   private static final ResourceLocation MC_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");
   static boolean changelog = false;


   public GuiMainMenuNew(GameSettingsPlus settings) {
      this.settings = settings;
   }

   public void func_73866_w_() {
      super.initGui();
      int id;
      GuiButton button;
      GuiButtonNew buttonNew;
      if(this.settings.textbox_enabled) {
         this.buttonList.add(new GuiButton(50, this.width / 2 - 200, this.height / 4 + 48 + 72 + 12 - 49, 200, 20, I18n.format("Interface+ Settings", new Object[0])));

         for(id = 0; id < this.buttonList.size(); ++id) {
            button = (GuiButton)this.buttonList.get(id);
            switch(button.id) {
            case 0:
               button.xPosition = this.width / 2 - button.width * 2 - 4;
               button.yPosition -= 26;
               break;
            case 1:
               button.xPosition = this.width / 2 - button.width;
               button.yPosition -= 32;
               break;
            case 2:
               button.xPosition = this.width / 2 - button.width;
               button.yPosition -= 34;
               heightY = button.yPosition;
            case 3:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            default:
               break;
            case 4:
               button.xPosition = this.width / 2 - button.width;
               button.yPosition -= 26;
               break;
            case 5:
               button.xPosition = this.width / 2 - button.width;
               button.yPosition -= 2;
               if(this.settings.new_gui_enabled) {
                  button.displayString = I18n.format("options.language", new Object[0]);
               }
               break;
            case 6:
               button.xPosition = this.width / 2 - button.width;
               button.yPosition -= 36;
               break;
            case 14:
               button.xPosition = this.width / 2 - button.width * 2 - 4;
               button.yPosition -= 36;
            }

            if(this.settings.new_gui_enabled) {
               if(id == 5) {
                  buttonNew = new GuiButtonNew(button.id, 10 + widthOffset, this.height / 2 - 80 + 20 * (this.buttonList.size() - 1), 125, 20, button.displayString, true);
                  this.buttonList.set(id, buttonNew);
               } else if(id > 5) {
                  buttonNew = new GuiButtonNew(button.id, 10 + widthOffset, this.height / 2 - 80 + 20 * (id - 1), 125, 20, button.displayString, true);
                  this.buttonList.set(id, buttonNew);
               } else {
                  int a = button.id;
                  int b = 10 + widthOffset;
                  int c = this.height / 2 - 80 + 20 * id;
                  String d = button.displayString;
                  buttonNew = new GuiButtonNew(a, b, c, 125, 20, d, true);
                  this.buttonList.set(id, buttonNew);
               }
            }
         }
      } else {
         this.buttonList.add(new GuiButton(50, this.width / 2 - 100, this.height / 4 + 48 + 72 - 10, 200, 20, I18n.format("Interface+ Settings", new Object[0])));

         for(id = 0; id < this.buttonList.size(); ++id) {
            button = (GuiButton)this.buttonList.get(id);
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
               if(this.settings.new_gui_enabled) {
                  button.displayString = I18n.format("options.language", new Object[0]);
               }
               break;
            case 6:
               button.yPosition -= 8;
               break;
            case 14:
               button.yPosition -= 8;
            }

            if(this.settings.new_gui_enabled) {
               if(id == 5) {
                  buttonNew = new GuiButtonNew(button.id, 10 + widthOffset, this.height / 2 - 80 + 20 * (this.buttonList.size() - 1), 125, 20, button.displayString, true);
                  this.buttonList.set(id, buttonNew);
               } else if(id > 5) {
                  buttonNew = new GuiButtonNew(button.id, 10 + widthOffset, this.height / 2 - 80 + 20 * (id - 1), 125, 20, button.displayString, true);
                  this.buttonList.set(id, buttonNew);
               } else {
                  buttonNew = new GuiButtonNew(button.id, 10 + widthOffset, this.height / 2 - 80 + 20 * id, 125, 20, button.displayString, true);
                  this.buttonList.set(id, buttonNew);
               }
            }
         }
      }

   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      FontRenderer fontRendererObj = this.mc.fontRenderer;
      int x;
      int stringId;
      if(this.settings.new_gui_enabled) {
         GL11.glDisable(3008);
         GuiPanorama.renderSkybox(p_73863_1_, p_73863_2_, p_73863_3_, this);
         GL11.glEnable(3008);
         short s1 = 274;
         x = this.width / 2 - s1 / 2;
         boolean color = true;
         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         drawRect(widthOffset, 0, widthOffset + 150, 36, -1610612736);
         drawRect(widthOffset, 38, widthOffset + 150, this.height, -1610612736);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

         for(stringId = 0; stringId < this.buttonList.size(); ++stringId) {
            ((GuiButton)this.buttonList.get(stringId)).drawButton(this.mc, p_73863_1_, p_73863_2_);
         }

         for(stringId = 0; stringId < this.labelList.size(); ++stringId) {
            ((GuiLabel)this.labelList.get(stringId)).func_146159_a(this.mc, p_73863_1_, p_73863_2_);
         }

         this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/minecraft.png"));
         byte var19 = 15;
         byte var20 = 15;
         GL11.glScalef(0.5F, 0.5F, 0.5F);
         drawTexturedModalRect(var19 + 0 + widthOffset * 2, var20 + 0, 0, 0, 155, 44);
         drawTexturedModalRect(var19 + 155 + widthOffset * 2, var20 + 0, 0, 45, 155, 44);
         List<String> brandings = Lists.reverse(FMLCommonHandler.instance().getBrandings(true));
         GL11.glScalef(2.0F, 2.0F, 2.0F);
         String s2 = "Copyright Mojang AB. Do not distribute!";
         drawString(fontRendererObj, s2, this.width - fontRendererObj.getStringWidth(s2) - 2, this.height - 10, -1);
         for (int i = 0; i < brandings.size(); i++)
         {
           String brd = (String)brandings.get(i);
           if (!Strings.isNullOrEmpty(brd)) {
             drawString(fontRendererObj, brd, this.width - fontRendererObj.getStringWidth(brd) - 2, this.height - 10 - (10 + i * (fontRendererObj.FONT_HEIGHT + 1)), 16777215);
           }
         }
         if(this.settings.textbox_enabled) {
            this.mc.getTextureManager().bindTexture(TEXTBOX);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            width = this.width - 200;
            byte var23 = 0;
            drawRect(width + 200, var23 + 104, width, var23, -1610612736);
            String s11 = "Interface+ v. 1.2.1 by KurodaAkira";
            this.drawString(fontRendererObj, s11, width + 5, var23 + 5, 15114036);
            int offset = 0;
            int color1 = -1;

            int stringId1;
            String s;
            for(stringId1 = 0; stringId1 < RenderTickHandler.str.length; ++stringId1) {
               s = RenderTickHandler.str[stringId1];
               if(stringId1 == 0) {
                  if(s.contains("No connection")) {
                     s = "Could not connect to Server";
                  } else if(s.equals("1.2.1")) {
                     s = "The latest version is: " + s;
                  } else {
                     s = "Outdated! New version is: " + s;
                     color1 = 13631488;
                     stringId1 = RenderTickHandler.str.length - 1;
                     changelog = true;
                     offset += 2;
                  }
               }

               if(s.isEmpty()) {
                  offset += 2;
               } else {
                  this.drawString(fontRendererObj, s, width + 5, var23 + 5 + 10 + offset, color1);
                  offset += 10;
                  color1 = -1;
                  if(changelog) {
                     offset += 2;
                  }
               }
            }

            if(changelog) {
               for(stringId1 = 0; stringId1 < RenderTickHandler.str2.length; ++stringId1) {
                  s = RenderTickHandler.str2[stringId1];
                  if(s.isEmpty()) {
                     offset += 2;
                  } else {
                     this.drawString(fontRendererObj, s, width + 5, var23 + 5 + 10 + offset, -1);
                     offset += 10;
                  }
               }
            }
         }

         GuiButtonTooltip.drawTooltip(this, (ArrayList)this.buttonList);
         GL11.glPopMatrix();
      } else if(this.settings.textbox_enabled) {
         super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
         this.mc.getTextureManager().bindTexture(TEXTBOX);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPushMatrix();
         this.drawTexturedModalRect(this.width / 2 + 8, heightY, 0, 0, 200, 104);
         String var18 = "Interface+ v. 1.2.1 by KurodaAkira";
         this.drawString(fontRendererObj, var18, this.width / 2 + 8 + 5, heightY + 5, 15114036);
         x = 0;
         int var21 = -1;

         String var22;
         for(stringId = 0; stringId < RenderTickHandler.str.length; ++stringId) {
            var22 = RenderTickHandler.str[stringId];
            if(stringId == 0) {
               if(var22.contains("No connection")) {
                  var22 = "Could not connect to Server";
               } else if(var22.equals("1.2.1")) {
                  var22 = "The latest version is: " + var22;
               } else {
                  var22 = "Outdated! New version is: " + var22;
                  var21 = 13631488;
                  stringId = RenderTickHandler.str.length - 1;
                  changelog = true;
                  x += 2;
               }
            }

            if(var22.isEmpty()) {
               x += 2;
            } else {
               this.drawString(fontRendererObj, var22, this.width / 2 + 8 + 5, heightY + 5 + 10 + x, var21);
               x += 10;
               var21 = -1;
               if(changelog) {
                  x += 2;
               }
            }
         }

         if(changelog) {
            for(stringId = 0; stringId < RenderTickHandler.str2.length; ++stringId) {
               var22 = RenderTickHandler.str2[stringId];
               if(var22.isEmpty()) {
                  x += 2;
               } else {
                  this.drawString(fontRendererObj, var22, this.width / 2 + 8 + 5, heightY + 5 + 10 + x, -1);
                  x += 10;
               }
            }
         }

         GL11.glPopMatrix();
      } else {
         super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
      }

   }

   protected void func_146284_a(GuiButton p_146284_1_) {
      super.actionPerformed(p_146284_1_);
      if(p_146284_1_.id == 50) {
         this.mc.displayGuiScreen(new GuiSettingsPlus(this, this.settings, 0));
      }

   }

}
