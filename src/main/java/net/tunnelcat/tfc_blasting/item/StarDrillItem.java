package net.tunnelcat.tfc_blasting.item;

import net.dries007.tfc.common.TFCTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.tunnelcat.tfc_blasting.util.TFCBlastingTags;

public class StarDrillItem extends Item {
    public StarDrillItem(Properties pProperties) {
        super(pProperties);
    }

    /**
     * Called when this item is used when targeting a Block
     */
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            Player player = pContext.getPlayer();
            ItemStack itemMainhand = player.getMainHandItem();
            ItemStack itemOffhand = player.getOffhandItem();
            BlockPos block = pContext.getClickedPos();
            Direction face = pContext.getClickedFace();

            player.sendSystemMessage(Component.literal(block.getX() + ", " + block.getY() + ", " + block.getZ() + " : " + face));

            if(itemOffhand.is(TFCBlastingTags.Items.STAR_DRILLS) && itemMainhand.is(TFCTags.Items.HAMMERS)) {
                itemOffhand.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
                pContext.getLevel().explode(null, block.getX(), block.getY(), block.getZ(), 0.1F, Level.ExplosionInteraction.BLOCK);

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }
}
