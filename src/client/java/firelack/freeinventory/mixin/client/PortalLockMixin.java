package firelack.freeinventory.mixin.client;

import firelack.freeinventory.client.FreeInventoryClient;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
        if (FreeInventoryClient.isPlayerTicking) {
            ci.cancel();
        }
    }
}