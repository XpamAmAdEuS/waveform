package hr.xpam.waveform.jaad.mp4.api.codec;

import hr.xpam.waveform.jaad.mp4.api.DecoderInfo;
import hr.xpam.waveform.jaad.mp4.boxes.impl.sampleentries.codec.CodecSpecificBox;
import hr.xpam.waveform.jaad.mp4.boxes.impl.sampleentries.codec.H263SpecificBox;

public class H263DecoderInfo extends DecoderInfo {

	private H263SpecificBox box;

	public H263DecoderInfo(CodecSpecificBox box) {
		this.box = (H263SpecificBox) box;
	}

	public int getDecoderVersion() {
		return box.getDecoderVersion();
	}

	public long getVendor() {
		return box.getVendor();
	}

	public int getLevel() {
		return box.getLevel();
	}

	public int getProfile() {
		return box.getProfile();
	}
}
