package firelack.freeinventory.mixin.client;

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

        // If the new screen to set is null (meaning the current screen is being closed)
        if (screen == null) {
            // If the player is in a portal
            if (mc.player != null && mc.player.portalProcess != null) {
                
                // If the current screen is an inventory screen
                if (mc.screen instanceof AbstractContainerScreen) {
                    
                    // Cancel the screen closing
                    ci.cancel();
                }
            }
        }
    }
}