package dev.enjarai.blahajtotem.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.enjarai.blahajtotem.pond.BakedHuggableModel;
import dev.enjarai.blahajtotem.pond.UnbakedHuggableModel;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BakedModel;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockModel.class)
public class JsonUnbakedModelMixin implements UnbakedHuggableModel {
    @Shadow
    @Nullable
    public BlockModel parent;

    @Unique
    @Nullable
    private Boolean blahaj_totem$huggable;

    @Override
    public void blahaj_totem$setHuggable(boolean huggable) {
        this.blahaj_totem$huggable = huggable;
    }

    @Override
    public boolean blahaj_totem$isHuggable() {
        if (blahaj_totem$huggable != null) {
            return blahaj_totem$huggable;
        } else if (parent != null) {
            return ((UnbakedHuggableModel) parent).blahaj_totem$isHuggable();
        } else {
            return false;
        }
    }

    @ModifyReturnValue(
            method = "bake(Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/renderer/block/model/BlockModel;Ljava/util/function/Function;Lnet/minecraft/client/resources/model/ModelState;Z)Lnet/minecraft/client/resources/model/BakedModel;",
            at = @At("RETURN")
    )
    private BakedModel addFieldToBakedModel(BakedModel original) {
        if (original instanceof BakedHuggableModel huggableModel) {
            huggableModel.blahaj_totem$setHuggable(blahaj_totem$isHuggable());
        }
        return original;
    }
}
