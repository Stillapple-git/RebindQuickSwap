package walksy.quickswaprebinder;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.mixin.client.keymapping.KeyMappingAccessor;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class RebindQuickSwapMod implements ModInitializer {

    public static KeyMapping keyBinding = KeyMappingHelper.registerKeyMapping(
            new KeyMapping("Quick Swap Keybind", GLFW.GLFW_KEY_LEFT_SHIFT, new KeyMapping.Category(Identifier.fromNamespaceAndPath("walksy", "bind"))));

    @Override
    public void onInitialize() {

    }

    @SuppressWarnings("all")
    public static boolean shouldQuickSwap() {
        final int code = InputConstants.getKey(keyBinding.saveString()).getValue();
        final long handle = Minecraft.getInstance().getWindow().handle();
        boolean bl2 = ((KeyMappingAccessor)keyBinding).fabric_getBoundKey().getType() == InputConstants.Type.MOUSE;

        return (bl2 ? isMouseButtonPressed(handle, code)
                : isKeyPressed(handle, code));
    }

    private static boolean isKeyPressed(long handle, int code) {
        return GLFW.glfwGetKey(handle, code) == 1;
    }

    private static boolean isMouseButtonPressed(long handle, int code) {
        return GLFW.glfwGetMouseButton(handle, code) == 1;
    }
}
