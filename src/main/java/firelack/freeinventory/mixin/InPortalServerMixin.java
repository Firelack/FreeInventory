package firelack.freeinventory.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class InPortalServerMixin {
    /**
     * Empêche la fermeture de l'inventaire côté serveur
     */
    @Inject(
        method = "closeHandledScreen",
        at = @At("HEAD"),
        cancellable = true
    )
    private void freeinv$keepInventoryOpenServer(CallbackInfo ci) {
        ci.cancel();
    }
}
