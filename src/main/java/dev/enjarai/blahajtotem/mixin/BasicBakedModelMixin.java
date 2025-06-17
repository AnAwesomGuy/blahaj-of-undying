package dev.enjarai.blahajtotem.mixin;

import dev.enjarai.blahajtotem.pond.BakedHuggableModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SimpleBakedModel.class)
public class BasicBakedModelMixin implements BakedHuggableModel {
    @Unique
    private boolean blahaj_totem$huggable;

    @Override
    public void blahaj_totem$setHuggable(boolean huggable) {
        this.blahaj_totem$huggable = huggable;
    }

    @Override
    public boolean blahaj_totem$isHuggable() {
        return blahaj_totem$huggable;
    }
}
