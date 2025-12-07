package firelack.freeinventory.mixin.client;

import firelack.freeinventory.client.FreeInventoryClient;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.phys.AABB;

@Mixin(LocalPlayer.class)
public class PortalLockMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTickStart(CallbackInfo ci) {
        FreeInventoryClient.isPlayerTicking = true;
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void onTickEnd(CallbackInfo ci) {
        FreeInventoryClient.isPlayerTicking = false;
    }

@Inject(method = "closeContainer", at = @At("HEAD"), cancellable = true)
    private void preventLogicalClose(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;

        if (FreeInventoryClient.isInputActive) return;

        if (FreeInventoryClient.isPlayerTicking || isInsideAnyPortal(player)) {
            ci.cancel();
        }
    }

    @Unique
    private boolean isInsideAnyPortal(Player player) {
        AABB box = player.getBoundingBox();
        BlockPos min = BlockPos.containing(box.minX, box.minY, box.minZ);
        BlockPos max = BlockPos.containing(box.maxX, box.maxY, box.maxZ);

        for (BlockPos pos : BlockPos.betweenClosed(min, max)) {
             String id = BuiltInRegistries.BLOCK.getKey(player.level().getBlockState(pos).getBlock()).toString();
             if (id.contains("portal")) return true;
        }
        return false;
    }
}