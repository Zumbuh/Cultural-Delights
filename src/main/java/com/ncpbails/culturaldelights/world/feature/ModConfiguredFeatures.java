package com.ncpbails.culturaldelights.world.feature;

import com.google.common.collect.ImmutableList;
import com.ncpbails.culturaldelights.CulturalDelights;
import com.ncpbails.culturaldelights.block.ModBlocks;
import com.ncpbails.culturaldelights.block.custom.FruitingLeaves;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.Random;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_TREE = registerKey("avocado");
	public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_PIT = registerKey("avocado_pit");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_CORN = registerKey("wild_corn");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_EGGPLANTS = registerKey("wild_eggplants");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_CUCUMBERS = registerKey("wild_cucumbers");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {


        register(context, AVOCADO_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.AVOCADO_LOG.get()),
                new StraightTrunkPlacer(3, 2, 0),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
				        .add(ModBlocks.AVOCADO_LEAVES.get().defaultBlockState(), 3)
						.add(ModBlocks.FRUITING_AVOCADO_LEAVES.get().defaultBlockState().setValue(FruitingLeaves.AGE, 4), 1)
			    ),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
				
		register(context, AVOCADO_PIT, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.AVOCADO_SAPLING.get()),
                new StraightTrunkPlacer(3, 2, 0),
                BlockStateProvider.simple(ModBlocks.AVOCADO_SAPLING.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());	

        register(context, WILD_CORN, Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_CORN.get())))));
		
		register(context, WILD_EGGPLANTS, Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_EGGPLANTS.get())))));
		
		register(context, WILD_CUCUMBERS, Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_CUCUMBERS.get())))));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(CulturalDelights.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
	
}