package page.langeweile.pause_music_on_pause.interfaces;

import net.minecraft.sounds.SoundSource;

public interface ModSoundDetails {
	void pmop_pauseOnly(SoundSource... soundSources);

	void pmop_resumeOnly(SoundSource... soundSources);
}
