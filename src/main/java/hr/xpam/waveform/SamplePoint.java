package hr.xpam.waveform;

public class SamplePoint {
	
	long[] channel;

	int nBitsPerSample;
	int nChannel;
	
	public SamplePoint(Sample wave, int offset) {
		
		nBitsPerSample = wave.getNBitsPerSample();
		nChannel = wave.getNChannel();
		
		// the offset we will get the point
		int byteOffset = (nBitsPerSample * nChannel) * offset ;
		
		channel = new long[nChannel];
		for(int c = 1; c <= nChannel; c++)
		{
			switch (nBitsPerSample) {
				case 8 -> // 8 bits
						channel[c - 1] = wave.getData().get8bitPoint(byteOffset);
				case 16 -> // 16 bits
						channel[c - 1] = wave.getData().get16bitPoint(byteOffset);
				case 24 -> // 24 bits
						channel[c - 1] = wave.getData().get24bitPoint(byteOffset);
				case 32 -> // 32 bits
						channel[c - 1] = wave.getData().get32bitPoint(byteOffset);
				default -> {
				}
			}

		}
	}

	/**
	 * Get value
	 * @param channel int
	 * @return []
	 */
	public long getValue(int channel) {
		if (channel < 1 || channel > this.channel.length)
			throw new ArrayIndexOutOfBoundsException();
		
		return this.channel[channel-1];
	}

}
