package firelack.freeinventory.mixin.client;

import firelack.freeinventory.client.FreeInventoryClient;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseProtectionMixin {

    @Inject(method = "onPress", at = @At("HEAD"))
    private void onMousePressStart(long window, int button, int action, int mods, CallbackInfo ci) {
        FreeInventoryClient.isInputActive = true;
    }

    @Inject(method = "onPress", at = @At("RETURN"))
    private void onMousePressEnd(long window, int button, int action, int mods, CallbackInfo ci) {
        FreeInventoryClient.isInputActive = false;
    }
}