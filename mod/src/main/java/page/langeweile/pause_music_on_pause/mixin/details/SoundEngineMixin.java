package page.langeweile.pause_music_on_pause.mixin.details;

import com.mojang.blaze3d.audio.Channel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import page.langeweile.pause_music_on_pause.interfaces.ModSoundDetails;

import java.util.List;
import java.util.Map;

@Mixin(SoundEngine.class)
public class SoundEngineMixin implements ModSoundDetails {
	@Shadow
	private boolean loaded;

	@Shadow
	@Final
	private Map<SoundInstance, ChannelAccess.ChannelHandle> instanceToChannel;

	@Override
	public void pmop_pauseOnly(SoundSource... soundSources) {
		if (this.loaded) {
			for (var entry : this.instanceToChannel.entrySet()) {
				if (List.of(soundSources).contains(entry.getKey().getSource())) {
					entry.getValue().execute(Channel::pause);
				}
			}
		}
	}

	@Override
	public void pmop_resumeOnly(SoundSource... soundSources) {
		if (this.loaded) {
			for (var entry : this.instanceToChannel.entrySet()) {
				if (List.of(soundSources).contains(entry.getKey().getSource())) {
					entry.getValue().execute(Channel::unpause);
				}
			}
		}
	}
}
