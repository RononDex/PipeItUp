package ch.fhnw.ip12.pipeitup.app;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.fhnw.ip12.pipeitup.data.DependencyInjectionConfigData;
import ch.fhnw.ip12.pipeitup.logic.DependencyInjectionConfigLogic;
import ch.fhnw.ip12.pipeitup.ui.DependencyInjectionConfigUi;
import ch.fhnw.ip12.pipeitup.ui.PipeItUpGameEntryPoint;
import ch.fhnw.ip12.pipeitup.ui.UiMode;


/**
 * PipeItUp
 */
@ExcludeTypeFromJacocoGeneratedReport
public class PipeItUp {

	static final Logger log = LogManager.getLogger(PipeItUp.class.getName());
	static Injector injector;

	public static void main(String[] args) {
		try {
			log.info("---------------------------------------------------");
			log.info("----- Launching Pipe-It-Up!");
			log.info("---------------------------------------------------");
			log.info("Parameters:");
			if (args.length > 0) System.out.println(args[1]);

			List<String> argsList = Arrays.asList(args);
			UiMode uiMode = UiMode.HARDWARE;

			if (argsList.contains("--softwareUi"))
				uiMode = UiMode.SOFTWARE;

			log.info(" - UiMode: " + uiMode.name());
			log.info("");

			SetupDependencyInjection();

			PipeItUpGameEntryPoint game = injector.getInstance(PipeItUpGameEntryPoint.class);
			game.setUiMode(uiMode);
			game.start();
		} catch (Exception e) {
			log.fatal(String.format("FATAL Error encountered, shutting down: %s", e.getMessage()));
		}
	}

	public static void SetupDependencyInjection() {
		injector = Guice.createInjector(new DependencyInjectionConfigUi(), new DependencyInjectionConfigData(),
				new DependencyInjectionConfigLogic());

		log.info("Dependency Injection config loaded");
	}
}
