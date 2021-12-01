package hr.xpam.waveform;

import java.util.ArrayList;
import java.util.List;

public class SampleRange {

	/**
	 * Sample Point list
	 */
	private List<SamplePoint> points;

	/**
	 * Average point
	 */
	private final long[] average;
	
	/**
	 * Minimum point
	 */
	private final long[] min;
	
	/**
	 * Maximum point
	 */
	private final long[] max;

	/**
	 * Nb of channel
	 */
	private final int nChannel;

	/**
	 * bit per sample
	 */
	private final int nBitsPerSample;

	/**
	 * 
	 * @param wave Sample
	 * @param offset int
	 * @param length int
	 */
	public SampleRange(Sample wave, int offset, int length) {
		if (length < 1)
			throw new IllegalArgumentException("length >= 1");
	
		if (wave == null)
			throw new NullPointerException();

		nChannel = wave.getNChannel();
		nBitsPerSample = wave.getNBitsPerSample();
		
		average = new long[nChannel];
		min = new long[nChannel];
		max = new long[nChannel];

		points = new ArrayList<>();
		
		int count = 0;
		for(int i = 0; i < length; i++)
		{
			try {
				SamplePoint point = new SamplePoint(wave, offset+i);
				for(int c = 1; c <= nChannel; c++) 
				{
					long value = point.getValue(c);
					average[c-1] += value;
					if (count == 0)
						min[c-1] = max[c-1] = value;
					if (value < min[c-1] )
						min[c-1] = value;
					if (value > max[c-1])
						max[c-1] = value;
				}
				points.add(point);
				count++;

			} catch(ArrayIndexOutOfBoundsException e)
			{
				break;
			}
		
		}

		if (count > 0)
			for(int c = 1; c <= nChannel; c++) 
				average[c-1] = average[c-1] / count;
		
	}
	
	
	/**
	 * Get Average point for a channel
	 * @param channel int
	 * @return int
	 */
	public long getAverage(int channel)
	{
		if (channel < 1 || channel > average.length)
			throw new ArrayIndexOutOfBoundsException();
		
		return average[channel-1];
	}

	/**
	 * Get minimum point for a channel
	 * @param channel int
	 * @return int
	 */
	public long getMinimum(int channel)
	{
		if (channel < 1 || channel > average.length)
			throw new ArrayIndexOutOfBoundsException();
		
		return min[channel-1];
	}

	/**
	 * get Maximum point for a channel
	 * @param channel int
	 * @return int
	 */
	public long getMaximum(int channel)
	{
		if (channel < 1 || channel > average.length)
			throw new ArrayIndexOutOfBoundsException();
		
		return max[channel-1];
	}


	/**
	 * Get a list of SampleRange
	 * @param wave Sample
	 * @param resolution (point per range)
	 * @return list
	 */
	public static ArrayList<SampleRange> getList(Sample wave, int resolution) {
		ArrayList<SampleRange> list = new ArrayList<>();
		int len = wave.getNbOfPoints();		
		for(int offset = 0; offset < len; offset += resolution)
		{
			list.add(new SampleRange(wave, offset, resolution));
		}
		return list;
	}

}
