package hr.xpam.waveform.jaad.aac.syntax;

import hr.xpam.waveform.jaad.aac.AACException;
import hr.xpam.waveform.jaad.aac.DecoderConfig;

class SCE_LFE extends Element {

	private final ICStream ics;

	SCE_LFE(int frameLength) {
		super();
		ics = new ICStream(frameLength);
	}

	void decode(BitStream in, DecoderConfig conf) throws AACException {
		readElementInstanceTag(in);
		ics.decode(in, false, conf);
	}

	public ICStream getICStream() {
		return ics;
	}
}
