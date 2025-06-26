package net.tunnelcat.tfc_blasting.item;

import net.dries007.tfc.common.TFCTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.tunnelcat.tfc_blasting.block.TFCBlastingBlocks;
import net.tunnelcat.tfc_blasting.util.TFCBlastingTags;

import java.util.Random;

import static net.tunnelcat.tfc_blasting.util.TFCBlastingHelpers.getParticleOffsetToFace;

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

            // Check block is drillable, drill in offhand, hammer in main hand, and not underwater before beginning interaction
            if(block.is(TFCBlastingTags.BlockTags.CAN_STAR_DRILL)) {
                if(itemOffhand.is(TFCBlastingTags.ItemTags.STAR_DRILLS) && itemMainhand.is(TFCTags.Items.HAMMERS)) {
                    if(!player.isUnderWater()) {
                        player.startUsingItem(InteractionHand.OFF_HAND);

                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        return InteractionResult.FAIL;
    }

    // Runs every tick during item use
    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        HitResult block = pLivingEntity.pick(5, 1, false);
        BlockPos blockpos = ((BlockHitResult) block).getBlockPos();

        // Force player to stop using if they look away from the block they started using on or go underwater
        if(!pLevel.isClientSide()) {
            if (!blockpos.equals(clickedBlock) || pLivingEntity.isUnderWater()) {
                pLivingEntity.stopUsingItem();
            }
        }

        // The -1 here is to stop the first sound from playing so players can't spam it
        if ((pRemainingUseDuration - 1) % 20 == 0) {
            Random random = new Random();

            // Drill hit sound
            pLevel.playSound(
                    pLivingEntity,
                    clickedBlock,
                    SoundEvents.ANVIL_PLACE,
                    SoundSource.BLOCKS,
                    1.0f,
                    random.nextFloat(1.85f - 1.65f) + 1.65f
            );

            // Drill hit particles
            if(!pLevel.isClientSide()) {
                ((ServerLevel) pLevel).sendParticles(new BlockParticleOption(
                                ParticleTypes.BLOCK,
                                pLevel.getBlockState(clickedBlock).getBlock().defaultBlockState()
                        ),
                        clickedBlock.getX() + getParticleOffsetToFace('x', face),
                        clickedBlock.getY() + getParticleOffsetToFace('y', face),
                        clickedBlock.getZ() + getParticleOffsetToFace('z', face),
                        10, 0, 0, 0, 3
                );
            }
        }
    }

    // Runs when item use is completed
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLivingEntity.getOffhandItem().hurtAndBreak(1, pLivingEntity, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        pLevel.playSound(pLivingEntity, clickedBlock, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0f, 1.8f);
        pLevel.setBlock(clickedBlock, TFCBlastingBlocks.DRILL_HOLE_STONE.get().defaultBlockState(), 1);

        return pStack;
    }

    // Cancels all right-click events for the hammer while drill is being used to prevent making stone anvils
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock e) {
        if (e.getEntity().getOffhandItem().is(TFCBlastingTags.ItemTags.STAR_DRILLS) && e.getEntity().getMainHandItem().is(TFCTags.Items.HAMMERS)) {
            if (e.getHand() == InteractionHand.MAIN_HAND) {
                e.setCanceled(true);
            }
        }
    }
}
