package page.langeweile.pause_music_on_pause;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.sounds.SoundSource;
import page.langeweile.pause_music_on_pause.interfaces.ModSoundDetails;

public class ModOptions {
	private final OptionInstance<Boolean> pauseMusicOnPause = OptionInstance.createBoolean(
		"options.pause_music_on_pause",
		true,
		bool -> {
			if (Minecraft.getInstance().isPaused()) {
				var soundManager = Minecraft.getInstance().getSoundManager();
				if (bool) {
					((ModSoundDetails) soundManager).pmop_pauseOnly(SoundSource.MUSIC);
				} else {
					((ModSoundDetails) soundManager).pmop_resumeOnly(SoundSource.MUSIC);
				}
			}
		}
	);

	public OptionInstance<Boolean> pauseMusicOnPause() {
		return this.pauseMusicOnPause;
	}
}
