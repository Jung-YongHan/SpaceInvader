package org.newdawn.spaceinvaders;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGM {
    public BGM(){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();

            //소리 설정
            FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

            //볼륨 조정 -> 그냥하면 소리 커용
            gainControl.setValue(-30.0f);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
