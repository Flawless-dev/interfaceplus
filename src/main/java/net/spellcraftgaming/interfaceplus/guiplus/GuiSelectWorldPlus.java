package net.spellcraftgaming.interfaceplus.guiplus;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonNew;
import net.spellcraftgaming.interfaceplus.guiplus.GuiButtonTooltip;
import net.spellcraftgaming.interfaceplus.guiplus.GuiPanorama;
import net.spellcraftgaming.interfaceplus.guiplus.GuiSlotPlus;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;
import org.lwjgl.opengl.GL11;

public class GuiSelectWorldPlus {

   static GuiButton[] buttons;
   private static Minecraft mc = Minecraft.getMinecraft();
   private static GuiSelectWorldPlus.List field_146638_t;
   private static int width;
   private static int height;
   private static GuiSelectWorld gui;
   private static boolean field_146634_i;
   private static java.util.List field_146639_s;
   public static int field_146640_r;
   private static int z;
   private static int A;
   private static int y;
   private static int B;
   private static final DateFormat field_146633_h = new SimpleDateFormat();
   private static String[] field_146635_w = new String[3];


   public static void initGui(InitGuiEvent event, GameSettingsPlus settings) {
      buttons = new GuiButton[event.buttonList.size()];

      for(int isaveformat = 0; isaveformat < event.buttonList.size(); ++isaveformat) {
         GuiButton e = (GuiButton)event.buttonList.get(isaveformat);
         buttons[isaveformat] = e;
         GuiButtonNew buttonNew = new GuiButtonNew(buttons[isaveformat].id, buttons[isaveformat].xPosition, buttons[isaveformat].yPosition, buttons[isaveformat].width, buttons[isaveformat].height, buttons[isaveformat].displayString, false);
         event.buttonList.set(isaveformat, buttonNew);
         buttonNew.state = true;
         buttons[isaveformat] = buttonNew;
         switch(e.id) {
         case 1:
            z = isaveformat;
            break;
         case 2:
            y = isaveformat;
         case 3:
         case 4:
         case 5:
         default:
            break;
         case 6:
            A = isaveformat;
            break;
         case 7:
            B = isaveformat;
         }
      }

      width = event.gui.width;
      height = event.gui.height;
      gui = (GuiSelectWorld)event.gui;
      field_146638_t = new GuiSelectWorldPlus.List(event.buttonList);
      field_146638_t.registerScrollButtons(4, 5);
      ISaveFormat var6 = mc.getSaveLoader();

      try {
         field_146639_s = var6.getSaveList();
      } catch (AnvilConverterException var5) {
         var5.printStackTrace();
      }

      field_146635_w[GameType.SURVIVAL.getID()] = I18n.format("gameMode.survival", new Object[0]);
      field_146635_w[GameType.CREATIVE.getID()] = I18n.format("gameMode.creative", new Object[0]);
      field_146635_w[GameType.ADVENTURE.getID()] = I18n.format("gameMode.adventure", new Object[0]);
   }

   public static void drawScreen(DrawScreenEvent event, Minecraft mc, GameSettingsPlus settings) {
      GL11.glDisable(3008);
      GuiPanorama.renderSkybox(event.mouseX, event.mouseY, event.renderPartialTicks, event.gui);
      GL11.glEnable(3008);
      field_146638_t.drawScreen(event.mouseX, event.mouseY, event.renderPartialTicks, event.gui);
      GuiScreen var10000 = event.gui;
      GuiScreen.drawRect(0, 0, event.gui.width, 32, -1610612736);
      var10000 = event.gui;
      GuiScreen.drawRect(0, event.gui.height - 64, event.gui.width, event.gui.height, -1610612736);
      int size = buttons.length;
      boolean yOff = false;
      field_146640_r = field_146638_t.selectedElement;
      boolean flag1 = field_146640_r >= 0 && field_146640_r < field_146638_t.getSize();
      buttons[z].enabled = flag1;
      buttons[y].enabled = flag1;
      buttons[A].enabled = flag1;
      buttons[B].enabled = flag1;

      for(int id = 0; id < size; ++id) {
         buttons[id].drawButton(mc, event.mouseX, event.mouseY);
      }

      event.gui.drawCenteredString(mc.fontRenderer, I18n.format("selectWorld.title", new Object[0]), event.gui.width / 2, 20, 16777215);
      GuiButtonTooltip.drawTooltip(event.gui, buttons);
   }

   public static void func_146615_e(int p_146615_1_) {
      mc.displayGuiScreen((GuiScreen)null);
      if(!field_146634_i) {
         field_146634_i = true;
         String s = func_146621_a(p_146615_1_);
         if(s == null) {
            s = "World" + p_146615_1_;
         }

         String s1 = func_146614_d(p_146615_1_);
         if(s1 == null) {
            s1 = "World" + p_146615_1_;
         }

         if(mc.getSaveLoader().canLoadWorld(s)) {
            FMLClientHandler.instance().tryLoadExistingWorld(gui, s, s1);
         }
      }

   }

   protected static String func_146614_d(int p_146614_1_) {
      String s = ((SaveFormatComparator)field_146639_s.get(p_146614_1_)).getDisplayName();
      if(s == null || MathHelper.stringNullOrLengthZero(s)) {
         s = I18n.format("selectWorld.world", new Object[0]) + " " + (p_146614_1_ + 1);
      }

      return s;
   }

   protected static String func_146621_a(int p_146621_1_) {
      ISaveFormat isaveformat = mc.getSaveLoader();
      java.util.List field_146639_s = null;

      try {
         field_146639_s = isaveformat.getSaveList();
      } catch (AnvilConverterException var4) {
         var4.printStackTrace();
      }

      return ((SaveFormatComparator)field_146639_s.get(p_146621_1_)).getFileName();
   }


   @SideOnly(Side.CLIENT)
   static class List extends GuiSlotPlus {

      private static final String __OBFID = "CL_00000712";
      java.util.List buttonList;


      public List(java.util.List buttonList) {
         super(GuiSelectWorldPlus.mc, GuiSelectWorldPlus.width, GuiSelectWorldPlus.height, 32, GuiSelectWorldPlus.height - 64, 36);
         this.buttonList = buttonList;
      }

      protected int getSize() {
         return GuiSelectWorldPlus.field_146639_s.size();
      }

      protected void elementClicked(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_, GuiScreen gui) {
         GuiSelectWorldPlus.field_146640_r = p_148144_1_;
         boolean flag1 = GuiSelectWorldPlus.field_146640_r >= 0 && GuiSelectWorldPlus.field_146640_r < this.getSize();
         GuiSelectWorldPlus.buttons[GuiSelectWorldPlus.z].enabled = flag1;
         GuiSelectWorldPlus.buttons[GuiSelectWorldPlus.y].enabled = flag1;
         GuiSelectWorldPlus.buttons[GuiSelectWorldPlus.A].enabled = flag1;
         GuiSelectWorldPlus.buttons[GuiSelectWorldPlus.B].enabled = flag1;
         if(p_148144_2_ && flag1) {
            GuiSelectWorldPlus.func_146615_e(p_148144_1_);
         }

      }

      protected boolean isSelected(int p_148131_1_) {
         return p_148131_1_ == GuiSelectWorldPlus.field_146640_r;
      }

      protected int getContentHeight() {
         return GuiSelectWorldPlus.field_146639_s.size() * 36;
      }

      protected void drawBackground() {
         GuiSelectWorldPlus.gui.drawDefaultBackground();
      }

      protected void drawSlot(int p_148126_1_, int p_148126_2_, int p_148126_3_, int p_148126_4_, Tessellator p_148126_5_, int p_148126_6_, int p_148126_7_, GuiScreen gui) {
         SaveFormatComparator saveformatcomparator = (SaveFormatComparator)GuiSelectWorldPlus.field_146639_s.get(p_148126_1_);
         String s = saveformatcomparator.getDisplayName();
         if(s == null || MathHelper.stringNullOrLengthZero(s)) {
            s = I18n.format("selectWorld.world", new Object[0]) + " " + (p_148126_1_ + 1);
         }

         String s1 = saveformatcomparator.getFileName();
         s1 = s1 + " (" + GuiSelectWorldPlus.field_146633_h.format(new Date(saveformatcomparator.getLastTimePlayed()));
         s1 = s1 + ")";
         String s2 = "";
         if(saveformatcomparator.requiresConversion()) {
            s2 = I18n.format("selectWorld.conversion", new Object[0]) + " " + s2;
         } else {
            s2 = GuiSelectWorldPlus.field_146635_w[saveformatcomparator.getEnumGameType().getID()];
            if(saveformatcomparator.isHardcoreModeEnabled()) {
               s2 = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore", new Object[0]) + EnumChatFormatting.RESET;
            }

            if(saveformatcomparator.getCheatsEnabled()) {
               s2 = s2 + ", " + I18n.format("selectWorld.cheats", new Object[0]);
            }
         }

         GuiSelectWorldPlus.gui.drawString(GuiSelectWorldPlus.mc.fontRenderer, s, p_148126_2_ + 2, p_148126_3_ + 1, 16777215);
         GuiSelectWorldPlus.gui.drawString(GuiSelectWorldPlus.mc.fontRenderer, s1, p_148126_2_ + 2, p_148126_3_ + 12, 6710886);
         GuiSelectWorldPlus.gui.drawString(GuiSelectWorldPlus.mc.fontRenderer, s2, p_148126_2_ + 2, p_148126_3_ + 12 + 10, 6710886);
      }
   }
}
