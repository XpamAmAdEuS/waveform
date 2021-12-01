package hr.xpam.waveform.json;

import hr.xpam.waveform.Generator;
import hr.xpam.waveform.Sample;
import hr.xpam.waveform.SampleRange;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class WaveformGenerator 
{

	private static FileWriter file;
	Sample wave;

	public WaveformGenerator(String input,String output,int resolution) throws UnsupportedAudioFileException, IOException {
		File soundFile = new File(input);
		Generator generator = new Generator();
		Sample wave = generator.loadStandardizedSample(soundFile);
		setWave(wave);
		draw(output,resolution);

	}

	public void draw(String output,int resolution) {

		JSONObject obj = new JSONObject();
		JSONArray data = new JSONArray();

		if (wave != null)
		{

			ArrayList<SampleRange> ranges = SampleRange.getList(wave, resolution);
			for (SampleRange range : ranges) {
				int avg = (int) range.getAverage(1);
				// int min = (int) range.getMinimum(1);
				// int max = (int) range.getMaximum(1);

				data.add(avg);

			}

			obj.put("data",data);

			try {

				file = new FileWriter(output+".json");
				file.write(obj.toJSONString());

			} catch (IOException e) {
				e.printStackTrace();

			} finally {

				try {
					file.flush();
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void setWave(Sample wave)
	{
		this.wave = wave;
	}

	public Sample getWave()
	{
		return wave;
	}

	public void setSample(Sample sample) {
		this.wave = sample;
		
	}
}
