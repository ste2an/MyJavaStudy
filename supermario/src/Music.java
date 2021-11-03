import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music {

    public Music() throws FileNotFoundException, JavaLayerException {
        Player player;
        String str = System.getProperty("user.dir") + "/supermario/src/music/music.wav";

        // FileNotFoundException
        BufferedInputStream name = new BufferedInputStream(new FileInputStream(str));
        //JavaLayerException
        player = new Player(name);
        player.play();
    }
}
