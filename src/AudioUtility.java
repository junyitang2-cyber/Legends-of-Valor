import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Scanner;

/**
 *
 *
 * A utility for audios and sounds
 *
 * reference: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
 *
 */
public class AudioUtility {
    private static final String WORKING_DIRECTORY=System.getProperty("user.dir");
    private static final String PATH_TO_AUDIOS_FROM_WORKING_DIRECTORY =WORKING_DIRECTORY+"/src/ConfigFiles/";


    public static final String BGM = "Legends-Never-Die.wav";
    public static final String DRAGON_ROAR ="dragon_roar.wav";
    public static final String GAMEOVER = "deep-voice-game-over-SBA-300055796.wav";
    public static final String LIGHT_HIT="light_hit.wav";
    public static final String EPIC_DAMAGE="epic_damage-compressed.wav";
    public static final String LEVEL_UP="mixkit-completion-of-a-level-2063.wav";
    public static final String WIN="win.wav";
    public static final String LOSE="mixkit-player-losing-or-failing-2042.wav";
    public static final String NOTIFICATION ="mixkit-unlock-game-notification-253.wav";// played when showing the information/status
    public static final String ERROR="error.wav";
    public static final String GETTING_HIT="mixkit-boxer-getting-hit-2055.wav";
    /**
     * Background music
     */
    public static final String BGM_THE_GREAT_BATTLE="alexander-nakarada-the-great-battle-compressed.wav";


    /**
     * Demo entry for this utility
     *
     * @param args
     */

    /**
     *
     * Play a sound
     * @param audioFileName Name of the audio file e.g. "dragon_roar.wav".
     * @param loop if you want to loop infinitely, set loop as true, otherwise false as default.
     *
     */
    public synchronized static void playSound(String audioFileName, boolean loop ,float volumeOffset) {
        Thread thread=new Thread(new Runnable() {
            public void run() {
                try
                {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(PATH_TO_AUDIOS_FROM_WORKING_DIRECTORY + audioFileName));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    if(loop) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }

                    FloatControl volume= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(volumeOffset);

                    clip.start();

                    // If you want the playSound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY);
                    // If you want to stop the playSound, then use clip.stop();

                } catch (
                        Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        thread.start();

    }

    public static void playSound(String audioFileName){
        playSound(audioFileName, false,0);
    }

}
