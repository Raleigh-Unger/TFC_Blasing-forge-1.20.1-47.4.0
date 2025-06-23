package net.tunnelcat.tfc_blasting.item;

import net.dries007.tfc.common.TFCTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.tunnelcat.tfc_blasting.util.TFCBlastingTags;

import java.util.Random;

public class StarDrillItem extends Item {
    private BlockPos clickedBlock;
    private Direction face;

    public StarDrillItem(Properties pProperties) {super(pProperties);}

    @Override public int getUseDuration(ItemStack pStack) {return 100;}
    @Override public UseAnim getUseAnimation(ItemStack pStack) {return UseAnim.BOW;}

    // Runs when item is right-clicked on a block
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            clickedBlock = pContext.getClickedPos();
            face = pContext.getClickedFace();

            Player player = pContext.getPlayer();
            ItemStack itemMainhand = player.getMainHandItem();
            ItemStack itemOffhand = player.getOffhandItem();
            BlockState block = pContext.getLevel().getBlockState(clickedBlock);

            // Check if star drill in offhand and hammer in main hand and block is drillable then do the interaction
            if(itemOffhand.is(TFCBlastingTags.ItemTags.STAR_DRILLS) && itemMainhand.is(TFCTags.Items.HAMMERS)) {
                if(block.is(TFCBlastingTags.BlockTags.CAN_STAR_DRILL)) {
                    player.startUsingItem(InteractionHand.OFF_HAND);

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.FAIL;
    }

    // Runs every tick during item use
    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        HitResult block = pLivingEntity.pick(5, 1, false);
        BlockPos blockpos = ((BlockHitResult)block).getBlockPos();

        // Force player to stop using if they look away from the block they started using on
        if(!blockpos.equals(clickedBlock)) {
            pLivingEntity.stopUsingItem();
        }

        // The -1 here is to stop the first sound from playing to stop players from spamming it
        if((pRemainingUseDuration - 1) % 20 == 0) {
            Random random = new Random();

            pLevel.playSound(
                    pLivingEntity,
                    clickedBlock,
                    SoundEvents.ANVIL_PLACE,
                    SoundSource.BLOCKS,
                    1.0f,
                    random.nextFloat(1.85f - 1.65f) + 1.65f
            );
        }
    }

    // Runs when item use is completed
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLivingEntity.getOffhandItem().hurtAndBreak(1, pLivingEntity, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        pLevel.playSound(pLivingEntity, clickedBlock, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0f, 1.8f);
        pLevel.destroyBlock(clickedBlock, false);

        return pStack;
    }
}
