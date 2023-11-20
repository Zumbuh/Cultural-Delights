package com.ncpbails.culturaldelights.world.feature;

import com.ncpbails.culturaldelights.CulturalDelights;
import com.ncpbails.culturaldelights.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> AVOCADO_PLACED = createKey("avocado_placed");
	public static final ResourceKey<PlacedFeature> AVOCADO_PIT_PLACED = createKey("avocado_pit_placed");
	public static final ResourceKey<PlacedFeature> WILD_CORN_PLACED = createKey("wild_corn_placed");
	public static final ResourceKey<PlacedFeature> WILD_CUCUMBERS_PLACED = createKey("wild_cucumbers_placed");
	public static final ResourceKey<PlacedFeature> WILD_EGGPLANTS_PLACED = createKey("wild_eggplants_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, AVOCADO_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.AVOCADO_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.AVOCADO_SAPLING.get()));
				
		register(context, AVOCADO_PIT_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.AVOCADO_PIT),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.AVOCADO_PIT.get()));		
				
		register(context, WILD_CORN_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_CORN), 
		        List.of(RarityFilter.onAverageOnceEvery(25),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        ));	

        register(context, WILD_EGGPLANTS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_EGGPLANTS), 
		        List.of(RarityFilter.onAverageOnceEvery(25),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        ));

        register(context, WILD_CUCUMBERS_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILD_CUCUMBERS), 
		        List.of(RarityFilter.onAverageOnceEvery(25),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        ));
		
    }


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(CulturalDelights.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

}