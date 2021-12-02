package ch.fhnw.ip12.pipeitup.app;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.fhnw.ip12.pipeitup.ui.PipeItUpGame;
import ch.fhnw.ip12.pipeitup.ui.UiMode;

/**
 * PipeItUp
 */
public class PipeItUp {

    static Logger log = Logger.getLogger(PipeItUp.class.getName());

    @ExcludeFromJacocoGeneratedReport
    public static void main(String[] args) {
        try {
            System.out.println("funktoiniert");
            System.out.println(args[1]);
            List<String> argsList = Arrays.asList(args);
            UiMode uiMode = UiMode.HARDWARE;

            if (argsList.contains("--softwareUi"))
                uiMode = UiMode.SOFTWARE;

            PipeItUpGame game = new PipeItUpGame(uiMode);

            game.start();
        } catch (Exception e) {
            log.log(Level.SEVERE, String.format("FATAL Error encountered, shutting down: %s", e.getMessage()));
        }
    }
}
