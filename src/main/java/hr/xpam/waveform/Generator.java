package hr.xpam.waveform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import hr.xpam.waveform.aac.AacAudioDecoder;
import hr.xpam.waveform.flac.FlacAudioDecoder;
import hr.xpam.waveform.mp3.MP3AudioDecoder;
import hr.xpam.waveform.ogg.OggAudioDecoder;
import hr.xpam.waveform.wave.WaveAudioDecoder;

public class Generator {

    private List<AudioDecoder> decoders;

    private final AudioStandardizer audioStandardizer = new SampleHelper();

    public Generator(){
        prepareAudioDecoders();
    }


    /**
     * Generate a standardized sample for a sound
     * @param soundFile File
     * @return UnsupportedAudioFileException
     */
    public Sample loadStandardizedSample(File soundFile) throws UnsupportedAudioFileException, IOException {
        AudioDecoder decoder = getDecoderFor(soundFile);
        if (decoder == null)
            throw new Error("No decoder found for file " + soundFile.getAbsolutePath());
        
        AudioInputStream ais = decoder.decodeAndStandardize(soundFile);
        return new Sample(ais);
    }


    /**
     * Find decoder for a sound
     * @param soundFile File
     * @return decoder
     */
    private AudioDecoder getDecoderFor(File soundFile) {
        for (AudioDecoder decoder : decoders) {
            if (decoder.isAbleToDecode(soundFile))
                return decoder;
        }
        return null;
    }


    /**
     * Prepare decoders
     */
    private void prepareAudioDecoders() {
        decoders = new ArrayList<>();
        decoders.add(new AacAudioDecoder(audioStandardizer));
        decoders.add(new WaveAudioDecoder(audioStandardizer));
        decoders.add(new FlacAudioDecoder(audioStandardizer));
        decoders.add(new MP3AudioDecoder(audioStandardizer));
        decoders.add(new OggAudioDecoder(audioStandardizer));

    } 
}
