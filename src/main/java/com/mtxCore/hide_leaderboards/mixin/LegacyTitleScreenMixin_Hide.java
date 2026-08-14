package com.mtxCore.hide_leaderboards.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(targets = "wily.legacy.client.screen.RenderableVList", remap = false)
public class LegacyTitleScreenMixin_Hide {

    @Inject(method = "addRenderable", at = @At("HEAD"), cancellable = true, remap = false)
    private void hideLeaderboards(Renderable renderable, CallbackInfoReturnable<Object> cir) {
        if (renderable instanceof AbstractWidget widget) {
            String text = widget.getMessage().getString();
            boolean isLeaderboardKey = widget.getMessage().getContents() instanceof TranslatableContents tc
                    && tc.getKey().contains("leaderboard");
            if (text.contains("Leaderboard") || isLeaderboardKey) {
                cir.setReturnValue(this);
            }
        }
    }
}
