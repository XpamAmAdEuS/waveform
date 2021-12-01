package hr.xpam.waveform.jaad.mp4.boxes.impl.meta;

import java.io.IOException;

import hr.xpam.waveform.jaad.mp4.MP4InputStream;
import hr.xpam.waveform.jaad.mp4.boxes.FullBox;

public class ThreeGPPRecordingYearBox extends FullBox {

	private int year;

	public ThreeGPPRecordingYearBox() {
		super("3GPP Recording Year Box");
	}

	@Override
	public void decode(MP4InputStream in) throws IOException {
		super.decode(in);

		year = (int) in.readBytes(2);
	}

	public int getYear() {
		return year;
	}
}
