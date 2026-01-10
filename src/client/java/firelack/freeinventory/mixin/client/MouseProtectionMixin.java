package firelack.freeinventory.mixin.client;

import firelack.freeinventory.client.FreeInventoryClient;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseProtectionMixin {

    @Inject(method = "onButton", at = @At("HEAD"))
    private void onMousePressStart(CallbackInfo ci) {
        FreeInventoryClient.isInputActive = true;
    }

    @Inject(method = "onButton", at = @At("RETURN"))
    private void onMousePressEnd(CallbackInfo ci) {
        FreeInventoryClient.isInputActive = false;
    }
}