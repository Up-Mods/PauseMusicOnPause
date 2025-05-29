package page.langeweile.pause_music_on_pause.mixin.details;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.MusicInfo;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import page.langeweile.pause_music_on_pause.interfaces.ModOptionsAccess;

@Mixin(MusicManager.class)
public abstract class MusicManagerMixin {
	@Shadow
	@Final
	private Minecraft minecraft;

	@WrapOperation(
		method = "tick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/sounds/MusicManager;startPlaying(Lnet/minecraft/client/sounds/MusicInfo;)V"
		)
	)
	private void pauseMusicOnStart(MusicManager instance, MusicInfo music, Operation<Void> original) {
		if (this.minecraft.isPaused()) {
			if (!((ModOptionsAccess) this.minecraft.options).pmop_pauseMusicOnPause().get()) {
				original.call(instance, music);
			}
		} else {
			original.call(instance, music);
		}
	}
}
