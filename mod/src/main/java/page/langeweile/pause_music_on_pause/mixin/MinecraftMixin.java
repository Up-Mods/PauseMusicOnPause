package page.langeweile.pause_music_on_pause.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import page.langeweile.pause_music_on_pause.interfaces.ModOptionsAccess;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	@Shadow
	@Final
	public Options options;

	@WrapOperation(
		method = "runTick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/sounds/SoundManager;pauseAllExcept([Lnet/minecraft/sounds/SoundSource;)V"
		)
	)
	private void pauseMusicOnPause(SoundManager instance, SoundSource[] soundSources, Operation<Void> original) {
		if (((ModOptionsAccess) this.options).pmop_pauseMusicOnPause().get()) {
			// Dear god, I hope nobody uses enum expansion and forces me to do something much hackier here
			original.call(instance, new SoundSource[] {});
		} else {
			original.call(instance, soundSources);
		}
	}
}
