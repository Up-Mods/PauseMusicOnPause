package page.langeweile.pause_music_on_pause.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import page.langeweile.pause_music_on_pause.ModOptions;
import page.langeweile.pause_music_on_pause.interfaces.ModOptionsAccess;

@Mixin(Options.class)
public abstract class OptionsMixin implements ModOptionsAccess {
	@Unique
	private final ModOptions modOptions = new ModOptions();

	@Inject(method = "processOptions", at = @At("TAIL"))
	private void addModOptions(Options.FieldAccess accessor, CallbackInfo ci) {
		accessor.process("pauseMusicOnPause", this.modOptions.pauseMusicOnPause());
	}

	@Override
	public OptionInstance<Boolean> pmop_pauseMusicOnPause() {
		return this.modOptions.pauseMusicOnPause();
	}
}
