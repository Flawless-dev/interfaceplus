package net.spellcraftgaming.interfaceplus.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.spellcraftgaming.interfaceplus.cache.Cache;
import net.spellcraftgaming.interfaceplus.event.GuiOpenHandler;
import net.spellcraftgaming.interfaceplus.event.GuiScreenHandler;
import net.spellcraftgaming.interfaceplus.event.ItemTooltipHandler;
import net.spellcraftgaming.interfaceplus.event.RenderGameOverlayHandler;
import net.spellcraftgaming.interfaceplus.event.RenderHealthbar;
import net.spellcraftgaming.interfaceplus.event.RenderTickHandler;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

@Mod(
   modid = "interfaceplus",
   version = "1.2.1",
   name = "InterfacePlus"
)
public class InterfacePlus {

   public static final String MODID = "interfaceplus";
   public static final String VERSION = "1.2.1";
   public static final String NAME = "InterfacePlus";
   @SideOnly(Side.CLIENT)
   public static GameSettingsPlus settings;
   @SideOnly(Side.CLIENT)
   public static Cache cache;
   @Instance
   public static InterfacePlus instance;


   @SideOnly(Side.CLIENT)
   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      System.out.println("preInit");
      settings = new GameSettingsPlus(Minecraft.getMinecraft(), Minecraft.getMinecraft().mcDataDir);
   }

   @SideOnly(Side.CLIENT)
   @EventHandler
   public void init(FMLInitializationEvent event) {
      System.out.println("Init");
      FMLCommonHandler.instance().bus().register(new RenderTickHandler(Minecraft.getMinecraft()));
      MinecraftForge.EVENT_BUS.register(new RenderGameOverlayHandler(Minecraft.getMinecraft(), settings));
      MinecraftForge.EVENT_BUS.register(new GuiScreenHandler(Minecraft.getMinecraft(), settings));
      MinecraftForge.EVENT_BUS.register(new GuiOpenHandler(Minecraft.getMinecraft(), settings));
      MinecraftForge.EVENT_BUS.register(new RenderHealthbar(settings));
      MinecraftForge.EVENT_BUS.register(new ItemTooltipHandler(settings));
   }

   @SideOnly(Side.CLIENT)
   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      System.out.println("postInit");
   }
}
