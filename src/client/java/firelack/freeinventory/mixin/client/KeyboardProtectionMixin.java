package firelack.freeinventory.mixin.client;

import firelack.freeinventory.client.FreeInventoryClient;
import net.minecraft.client.KeyboardHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardProtectionMixin {

    @Inject(method = "keyPress", at = @At("HEAD"))
    private void onKeyPressStart(CallbackInfo ci) {
        FreeInventoryClient.isInputActive = true;
    }

    @Inject(method = "keyPress", at = @At("RETURN"))
    private void onKeyPressEnd(CallbackInfo ci) {
        FreeInventoryClient.isInputActive = false;
    }
}