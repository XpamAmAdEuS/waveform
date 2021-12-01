package hr.xpam.waveform.jaad.aac.syntax;

import hr.xpam.waveform.jaad.aac.AACException;
import hr.xpam.waveform.jaad.aac.SampleFrequency;
import hr.xpam.waveform.jaad.aac.sbr.SBR;

public abstract class Element implements Constants {

	private int elementInstanceTag;
	private SBR sbr;

	protected void readElementInstanceTag(BitStream in) throws AACException {
		elementInstanceTag = in.readBits(4);
	}

	public int getElementInstanceTag() {
		return elementInstanceTag;
	}

	void decodeSBR(BitStream in, SampleFrequency sf, int count, boolean stereo, boolean crc, boolean downSampled, boolean smallFrames) throws AACException {
		if(sbr==null) sbr = new SBR(smallFrames,elementInstanceTag==ELEMENT_CPE,sf,downSampled);
		sbr.decode(in, count);
	}

	boolean isSBRPresent() {
		return sbr!=null;
	}

	SBR getSBR() {
		return sbr;
	}
}
