package net.tunnelcat.tfc_blasting.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.tunnelcat.tfc_blasting.TFCBlasting;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class TFCBlastingTags {
    public static class ItemTags {
        public static final TagKey<Item> FUSE_CAPS = itemTag("fuse_caps");
        public static final TagKey<Item> FUSE_CAPS_EMPTY = itemTag("fuse_caps_empty");
        public static final TagKey<Item> STAR_DRILLS = itemTag("star_drills");

        private static TagKey<Item> itemTag(String name) {
            return net.minecraft.tags.ItemTags.create(fromNamespaceAndPath(TFCBlasting.MOD_ID, name));
        }
    }

    public static class BlockTags {
        public static final TagKey<Block> CAN_STAR_DRILL = blockTag("can_star_drill");

        private static TagKey<Block> blockTag(String name) {
            return net.minecraft.tags.BlockTags.create(fromNamespaceAndPath(TFCBlasting.MOD_ID, name));
        }
    }

    public static class FluidTags {
        public static final TagKey<Fluid> ACIDS = fluidTag("acids");
        public static final TagKey<Fluid> FLUIDS = fluidTag("fluids");

        private static TagKey<Fluid> fluidTag(String name) {
            return net.minecraft.tags.FluidTags.create(fromNamespaceAndPath(TFCBlasting.MOD_ID, name));
        }
    }
}
