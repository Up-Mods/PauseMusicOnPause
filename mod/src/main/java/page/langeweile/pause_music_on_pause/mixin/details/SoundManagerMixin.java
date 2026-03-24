package page.langeweile.pause_music_on_pause.mixin.details;

import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import page.langeweile.pause_music_on_pause.interfaces.ModSoundDetails;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin implements ModSoundDetails {
	@Shadow
	@Final
	private SoundEngine soundEngine;

	@Override
	public void pmop_pauseOnly(SoundSource... soundSources) {
		((ModSoundDetails) this.soundEngine).pmop_pauseOnly(soundSources);
	}

	@Override
	public void pmop_resumeOnly(SoundSource... soundSources) {
		((ModSoundDetails) this.soundEngine).pmop_resumeOnly(soundSources);
	}
}
