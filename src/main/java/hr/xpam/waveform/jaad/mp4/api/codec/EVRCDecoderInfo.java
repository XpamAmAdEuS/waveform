package hr.xpam.waveform.jaad.mp4.api.codec;

import hr.xpam.waveform.jaad.mp4.api.DecoderInfo;
import hr.xpam.waveform.jaad.mp4.boxes.impl.sampleentries.codec.CodecSpecificBox;
import hr.xpam.waveform.jaad.mp4.boxes.impl.sampleentries.codec.EVRCSpecificBox;

public class EVRCDecoderInfo extends DecoderInfo {

	private EVRCSpecificBox box;

	public EVRCDecoderInfo(CodecSpecificBox box) {
		this.box = (EVRCSpecificBox) box;
	}

	public int getDecoderVersion() {
		return box.getDecoderVersion();
	}

	public long getVendor() {
		return box.getVendor();
	}

	public int getFramesPerSample() {
		return box.getFramesPerSample();
	}
}
