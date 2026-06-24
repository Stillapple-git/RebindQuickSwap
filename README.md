wasnt working 1.21.11 viafabric plus so i fixed it (only works for 1.21.11 btw)


------

code that changed:
walksy/rebindquickswap/mixin/HandledScreenMixin.class
package walksy.rebindquickswap.mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import walksy.rebindquickswap.RebindQuickSwap;
@Mixin(value={class_465.class})
public abstract class HandledScreenMixin {
    @ModifyExpressionValue(method={"method_25402"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_11909;method_74239()Z"))
    private boolean redirectClick(boolean original) {
        // Double pipe || means OR. This overrides vanilla/ViaFabricPlus smoothly!
        return original || RebindQuickSwap.shouldQuickSwap();
    }
    @ModifyExpressionValue(method={"method_25406"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_11909;method_74239()Z"))
    private boolean redirectRelease(boolean original) {
        return original || RebindQuickSwap.shouldQuickSwap();
    }
}
