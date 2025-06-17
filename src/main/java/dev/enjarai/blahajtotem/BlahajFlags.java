package dev.enjarai.blahajtotem;

import dev.enjarai.blahajtotem.pond.BakedHuggableModel;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class BlahajFlags {
    public static boolean isHuggable(ItemStack itemStack, LivingEntity entity) {
        var model = Minecraft.getInstance().getItemRenderer().getModel(itemStack, entity.level(), entity, 0);
        return model instanceof BakedHuggableModel huggableModel && huggableModel.blahaj_totem$isHuggable();
    }
}
