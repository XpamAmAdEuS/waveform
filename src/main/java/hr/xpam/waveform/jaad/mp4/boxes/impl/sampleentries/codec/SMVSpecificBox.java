package hr.xpam.waveform.jaad.mp4.boxes.impl.sampleentries.codec;

import java.io.IOException;

import hr.xpam.waveform.jaad.mp4.MP4InputStream;

public class SMVSpecificBox extends CodecSpecificBox {

	private int framesPerSample;

	public SMVSpecificBox() {
		super("SMV Specific Structure");
	}

	@Override
	public void decode(MP4InputStream in) throws IOException {
		decodeCommon(in);

		framesPerSample = in.read();
	}

	public int getFramesPerSample() {
		return framesPerSample;
	}
}
