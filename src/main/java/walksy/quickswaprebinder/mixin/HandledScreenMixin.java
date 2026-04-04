package walksy.quickswaprebinder.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import org.spongepowered.asm.mixin.injection.Redirect;
import walksy.quickswaprebinder.RebindQuickSwapMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(AbstractContainerScreen.class)
public abstract class HandledScreenMixin {
    @Redirect(
            method = "mouseClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/input/MouseButtonEvent;hasShiftDown()Z"
            )
    )
    private boolean redirectClick(MouseButtonEvent instance) {
        return RebindQuickSwapMod.shouldQuickSwap();
    }

    @Redirect(
            method = "mouseReleased",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/input/MouseButtonEvent;hasShiftDown()Z"
            )
    )
    private boolean redirectRelease(MouseButtonEvent instance) {
        return RebindQuickSwapMod.shouldQuickSwap();
    }
}
