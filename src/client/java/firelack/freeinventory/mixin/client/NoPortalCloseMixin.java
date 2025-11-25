package firelack.freeinventory.mixin.client;

import firelack.freeinventory.client.FreeInventoryClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class NoPortalCloseMixin {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void preventPortalClosing(Screen screen, CallbackInfo ci) {
        Minecraft mc = (Minecraft) (Object) this;

        if (screen == null) {
            if (mc.player != null && mc.player.portalProcess != null) {
                if (FreeInventoryClient.isPlayerTicking) {
                    if (mc.screen instanceof AbstractContainerScreen) {
                        ci.cancel();
                    }
                }
            }
        }
    }
}