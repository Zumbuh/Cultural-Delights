package com.ncpbails.culturaldelights.world.feature.tree;

import com.ncpbails.culturaldelights.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.resources.ResourceKey;

import javax.annotation.Nullable;
import java.util.Random;

public class AvocadoPitGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean pLargeHive) {
        return ModConfiguredFeatures.AVOCADO_PIT;
    }
}