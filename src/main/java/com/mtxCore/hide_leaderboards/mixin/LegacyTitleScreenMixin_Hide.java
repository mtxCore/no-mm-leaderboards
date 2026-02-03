package com.mtxCore.hide_leaderboards.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wily.legacy.client.screen.RenderableVList;

// Mixin directly into the list class from Legacy4J
@Mixin(RenderableVList.class)
public class LegacyTitleScreenMixin_Hide {

    @Inject(method = "addRenderable", at = @At("HEAD"), cancellable = true)
    private void hideLeaderboards(Renderable renderable, CallbackInfoReturnable<RenderableVList> cir) {
        if (renderable instanceof AbstractWidget widget) {


            String text = widget.getMessage().getString();


            boolean isLeaderboardKey = false;
            if (widget.getMessage().getContents() instanceof TranslatableContents tc) {
                if (tc.getKey().contains("leaderboard")) {
                    isLeaderboardKey = true;
                }
            }

            // Combine checks: If it says "Leaderboard" OR has a leaderboard key
            if (text.contains("Leaderboard") || isLeaderboardKey) {
                cir.setReturnValue((RenderableVList) (Object) this);
            }
        }
    }
}