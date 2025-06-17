package dev.enjarai.blahajtotem.mixin;

import dev.enjarai.blahajtotem.BlahajFlags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
            method = "getArmPose",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void cuddleBlahaj(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> ci) {
        ItemStack lv = player.getItemInHand(hand);
        if (BlahajFlags.isHuggable(lv, player)) {
            ci.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            ci.cancel();
        }
    }
}
