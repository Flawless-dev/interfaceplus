package net.spellcraftgaming.interfaceplus.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.spellcraftgaming.interfaceplus.settings.GameSettingsPlus;

@SideOnly(Side.CLIENT)
public class ItemTooltipHandler {

   GameSettingsPlus settings;


   public ItemTooltipHandler(GameSettingsPlus settingsGeneral) {
      this.settings = settingsGeneral;
   }

   @SubscribeEvent
   public void itemToolTip(ItemTooltipEvent event) {
      int id = Item.getIdFromItem(event.itemStack.getItem());
      boolean addDurability = event.itemStack.getItem().isDamageable();
      boolean addEnchantable = event.itemStack.isItemEnchantable();
      if(addEnchantable && event.itemStack.getItem().getItemEnchantability() > 0) {
         addEnchantable = true;
      } else {
         addEnchantable = false;
      }

      boolean addHungerValue = false;
      boolean addWolfFood = false;
      if(event.itemStack.getItem() instanceof ItemFood) {
         addHungerValue = true;
         addWolfFood = ((ItemFood)event.itemStack.getItem()).isWolfsFavoriteMeat();
      }

      boolean addPotionIngredient = false;
      if(event.itemStack.getItem().isPotionIngredient(event.itemStack)) {
         addPotionIngredient = true;
      }

      boolean addFuel = false;
      boolean addBurnTime = false;
      if(TileEntityFurnace.isItemFuel(event.itemStack)) {
         addFuel = true;
         addBurnTime = true;
      }

      boolean addArmorAmount = false;
      if(event.itemStack.getItem() instanceof ItemArmor) {
         addArmorAmount = true;
      }

      boolean addHardness = false;
      boolean addResistance = false;
      boolean addLightValue = false;
      if(event.itemStack.getItem() instanceof ItemBlock) {
         addResistance = true;
         if(Block.getBlockById(id).getLightValue() > 0) {
            addLightValue = true;
         }
      }

      int damage = 0;
      boolean addDamageVsEntity = false;

      for(int should = 0; should < event.toolTip.size(); ++should) {
         if(((String)event.toolTip.get(should)).startsWith("ï¿½9+")) {
            StringBuilder s = new StringBuilder();
            boolean next = true;
            int index = 3;

            while(next) {
               char y = ((String)event.toolTip.get(should)).charAt(index);
               if(Character.isDigit(y)) {
                  s.append(y);
                  ++index;
               } else {
                  next = false;
               }
            }

            damage = Integer.valueOf(s.toString()).intValue();
            if(damage > 1) {
               addDamageVsEntity = true;
            }

            event.toolTip.remove(should);
            event.toolTip.remove(should - 1);
         }
      }

      boolean var21 = this.settings.showInformationPlus;
      if(addDamageVsEntity && var21 && this.settings.showDamageVsEntity) {
         event.toolTip.add("Damage: " + damage);
      }

      if(addDurability && var21 && this.settings.showDurability) {
         event.toolTip.add("Durability: " + (event.itemStack.getItem().getMaxDamage() - event.itemStack.getItemDamage() + 1) + "/" + (event.itemStack.getItem().getMaxDamage() + 1));
      }

      if(addHungerValue && var21 && this.settings.showHungerValue) {
         event.toolTip.add("Hunger Value: " + ((ItemFood)event.itemStack.getItem()).func_150905_g(event.itemStack));
      }

      if(addWolfFood && var21 && this.settings.showWolfFood) {
         event.toolTip.add("Wolf Food");
      }

      if(addPotionIngredient && var21 && this.settings.showPotionIngredient) {
         event.toolTip.add("Brewing Ingredient");
      }

      if(addArmorAmount && var21 && this.settings.showArmorAmount) {
         event.toolTip.add("Armor Value: " + ((ItemArmor)event.itemStack.getItem()).damageReduceAmount);
      }

      if(addEnchantable && var21 && this.settings.showEnchantable) {
         event.toolTip.add("Enchantablity: " + event.itemStack.getItem().getItemEnchantability());
      }

      if(addLightValue && var21 && this.settings.showLightValue) {
         event.toolTip.add("Light Value: " + Block.getBlockById(id).getLightValue());
      }

      String s = GameRegistry.findUniqueIdentifierFor(event.itemStack.getItem()).modId + ":" + GameRegistry.findUniqueIdentifierFor(event.itemStack.getItem()).name;
   }
}
