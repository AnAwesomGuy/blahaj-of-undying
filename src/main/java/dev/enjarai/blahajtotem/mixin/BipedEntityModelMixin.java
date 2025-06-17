package dev.enjarai.blahajtotem.mixin;

import dev.enjarai.blahajtotem.BlahajFlags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class BipedEntityModelMixin {

    @Shadow
    public @Final ModelPart rightArm;

    @Shadow
    public @Final ModelPart leftArm;

    @Inject(
            method = {"poseRightArm", "poseLeftArm"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/AnimationUtils;animateCrossbowHold(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;Z)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void poseArms(LivingEntity entity, CallbackInfo ci) {
        if (BlahajFlags.isHuggable(entity.getMainHandItem(), entity) || BlahajFlags.isHuggable(entity.getOffhandItem(), entity)) {
            this.rightArm.xRot = -0.95F;
            this.rightArm.yRot = (float) (-Math.PI / 8);
            this.leftArm.xRot = -0.90F;
            this.leftArm.yRot = (float) (Math.PI / 8);
            ci.cancel();
        }
    }
}
