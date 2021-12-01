package hr.xpam.waveform.jaad.mp4.api.codec;

import hr.xpam.waveform.jaad.mp4.api.DecoderInfo;
import hr.xpam.waveform.jaad.mp4.boxes.impl.sampleentries.codec.CodecSpecificBox;
import hr.xpam.waveform.jaad.mp4.boxes.impl.sampleentries.codec.QCELPSpecificBox;

public class QCELPDecoderInfo extends DecoderInfo {

	private QCELPSpecificBox box;

	public QCELPDecoderInfo(CodecSpecificBox box) {
		this.box = (QCELPSpecificBox) box;
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
