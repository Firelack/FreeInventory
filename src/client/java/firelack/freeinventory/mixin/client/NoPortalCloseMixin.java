package firelack.freeinventory.mixin.client;

import firelack.freeinventory.client.FreeInventoryClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class NoPortalCloseMixin {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    private void preventPortalClosing(Screen screen, CallbackInfo ci) {
        Minecraft mc = (Minecraft) (Object) this;

        if (screen == null) {
            if (mc.player != null && (mc.player.portalProcess != null || isInsideAnyPortal(mc.player))) {
                if (mc.screen instanceof AbstractContainerScreen) {
                    if (!FreeInventoryClient.isInputActive) {
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Unique
    private boolean isInsideAnyPortal(Player player) {
        BlockPos[] positions = {player.blockPosition(), player.blockPosition().above()};
        for (BlockPos pos : positions) {
            BlockState state = player.level().getBlockState(pos);
            String blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();
            if (blockId.contains("portal")) {
                return true;
            }
        }
        return false;
    }
}