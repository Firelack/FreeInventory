package firelack.freeinventory.mixin.client;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class InPortalClientMixin {
    /**
     * Empêche la fermeture automatique du screen côté client
     * par la méthode shouldCloseOnDimChange()
     */
    @Inject(
        method = "shouldCloseOnDimChange",
        at = @At("HEAD"),
        cancellable = true
    )
    private void freeinv$keepScreenOpenOnDimChange(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false); // Empêche la fermeture lors d'un changement de dimension
    }
}
