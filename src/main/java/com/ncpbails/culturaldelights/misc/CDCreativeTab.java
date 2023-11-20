package com.ncpbails.culturaldelights.misc;

import com.ncpbails.culturaldelights.CulturalDelights;
import com.ncpbails.culturaldelights.item.ModItems;
import com.ncpbails.culturaldelights.item.ModFoods;
import com.ncpbails.culturaldelights.item.CustomTabBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class CDCreativeTab {


    public static final DeferredRegister<CreativeModeTab> ITEMS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CulturalDelights.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = ITEMS.register(CulturalDelights.MOD_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + "culturalTab"))
            .icon(() -> new ItemStack(ModItems.CHICKEN_TACO.get()))
            .displayItems((enabledFeatures, output) -> {
                for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
                    if(item.get() instanceof CustomTabBehavior customTabBehavior){
                        customTabBehavior.fillItemCategory(output);
                    }else{
                        output.accept(item.get());
                    }
                }
            })
            .build());

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
	
    public static void registerCreativeTab(IEventBus modEventBus) {
        register(modEventBus);
    }
}