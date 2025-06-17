package dev.enjarai.blahajtotem.particle;

import com.mojang.serialization.MapCodec;
import dev.enjarai.blahajtotem.BlahajTotem;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ModParticles {
    public static final ParticleType<BlahajParticleEffect> BLAHAJ_OF_UNDYING = createComplex(BlahajParticleEffect::createCodec, BlahajParticleEffect::createPacketCodec);

    @ApiStatus.Internal
    public static void register(RegisterEvent event) {
        event.register(Registries.PARTICLE_TYPE, BlahajTotem.BLAHAJ_OF_UNDYING, () -> BLAHAJ_OF_UNDYING);
    }

    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(BLAHAJ_OF_UNDYING, BlahajParticle.Factory::new);
    }

    public static <T extends ParticleOptions> ParticleType<T> createComplex(Function<ParticleType<T>, MapCodec<T>> codecGetter, Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> streamCodecGetter) {
        return new ParticleType<>(false) {
            @Override
            public @NotNull MapCodec<T> codec() {
                return codecGetter.apply(this);
            }

            @Override
            public @NotNull StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodecGetter.apply(this);
            }
        };
    }
}
