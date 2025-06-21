package net.tunnelcat.tfc_blasting.util;

import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.tunnelcat.tfc_blasting.TFCBlasting;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class TFCBlastingTags {
    public static class Items {
        public static final TagKey<Item> FUSE_CAPS = itemTag("fuse_caps");
        public static final TagKey<Item> FUSE_CAPS_EMPTY = itemTag("fuse_caps_empty");
        public static final TagKey<Item> STAR_DRILLS = itemTag("star_drills");
        public static final TagKey<Fluid> ACIDS = fluidTag("acids");

        private static TagKey<Item> itemTag(String name) {
            return ItemTags.create(fromNamespaceAndPath(TFCBlasting.MOD_ID, name));
        }

        private static TagKey<Fluid> fluidTag(String name) {
            return FluidTags.create(fromNamespaceAndPath(TFCBlasting.MOD_ID, name));
        }
    }
}
