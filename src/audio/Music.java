package audio;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class Music  {
	public ArrayList<String> musicFiles;
	public int currentSongIndex;
	public static Clip clip;
	boolean musicState=true;
	FloatControl gainControl;
	long clipTime;
	
	//GESTIONE DELLA MUSICA
	public void playSound(String fileName) {
		try {
			File soundFile=new File(fileName);
			AudioInputStream ais=AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format=ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip=(Clip)AudioSystem.getLine(info);
			clip.open(ais);
			gainControl= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isMusicState() {
		return musicState;
	}

	public void setMusicState(boolean musicState) {
		this.musicState = musicState;
	}
	
	//METODO CHE FA RIPARIRE LA MUSICA SE BLOCCATA
	public void startMusic() {
		musicState=true;
		clipTime=clip.getMicrosecondPosition();	
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	//METODO CHE PERMETTE DI BLOCCARE LA MUSICA
	public void stopMusic() {
		musicState=false;
		clipTime=clip.getMicrosecondPosition();
		clip.stop();
	}
}
