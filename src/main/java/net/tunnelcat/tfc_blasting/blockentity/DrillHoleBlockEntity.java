package net.tunnelcat.tfc_blasting.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrillHoleBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public DrillHoleBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(TFCBlastingBlockEntities.DRILL_HOLE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    // Used to drop the inventory contents when block is broken
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());

        inventory.setItem(0, itemHandler.getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }





//-------------might not need these---------------------
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
}
