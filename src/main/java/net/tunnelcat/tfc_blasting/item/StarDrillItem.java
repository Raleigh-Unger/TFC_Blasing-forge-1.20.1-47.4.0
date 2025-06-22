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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.tunnelcat.tfc_blasting.util.TFCBlastingTags;

import java.util.Random;

public class StarDrillItem extends Item {
    private BlockPos clickedBlock;
    private Player player;

    public StarDrillItem(Properties pProperties) {
        super(pProperties);
    }

    // Runs when item is right-clicked on a block
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            player = pContext.getPlayer();
            ItemStack itemMainhand = player.getMainHandItem();
            ItemStack itemOffhand = player.getOffhandItem();
            Direction face = pContext.getClickedFace();
            clickedBlock = pContext.getClickedPos();

            // Check if star drill in offhand and hammer in main hand then do the interaction
            if(itemOffhand.is(TFCBlastingTags.Items.STAR_DRILLS) && itemMainhand.is(TFCTags.Items.HAMMERS)) {
                // Begin using
                player.startUsingItem(InteractionHand.OFF_HAND);

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    // Runs when item use is completed
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLivingEntity.getOffhandItem().hurtAndBreak(1, pLivingEntity, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        pLevel.playSound(pLivingEntity, clickedBlock, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0f, 1.8f);
        pLevel.destroyBlock(clickedBlock, false);

        return pStack;
    }

    // Sets duration in ticks required to use item
    @Override
    public int getUseDuration(ItemStack pStack) {
        return 100;
    }

    // Runs every tick during item use
    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        HitResult block = pLivingEntity.pick(5, 1, false);
        BlockPos blockpos = ((BlockHitResult)block).getBlockPos();
        Random random = new Random();

        if(!blockpos.equals(clickedBlock)) {
            player.stopUsingItem();
        }

        // The -1 here is to stop the first sound from playing to stop players from spamming it
        if((pRemainingUseDuration - 1) % 20 == 0) {
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
}
