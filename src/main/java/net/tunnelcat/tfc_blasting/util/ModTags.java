package net.tunnelcat.tfc_blasting.util;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.tunnelcat.tfc_blasting.TFCBlasting;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> FUSE_CAPS = tag("fuse_caps");
        public static final TagKey<Item> FUSE_CAPS_EMPTY = tag("fuse_caps_empty");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(fromNamespaceAndPath(TFCBlasting.MOD_ID, name));
        }
    }
}
