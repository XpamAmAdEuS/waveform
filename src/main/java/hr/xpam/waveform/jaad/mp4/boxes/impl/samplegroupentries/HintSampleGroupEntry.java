package hr.xpam.waveform.jaad.mp4.boxes.impl.samplegroupentries;

import java.io.IOException;

import hr.xpam.waveform.jaad.mp4.MP4InputStream;

public class HintSampleGroupEntry extends SampleGroupDescriptionEntry {

	public HintSampleGroupEntry() {
		super("Hint Sample Group Entry");
	}

	@Override
	public void decode(MP4InputStream in) throws IOException {
	}
}
