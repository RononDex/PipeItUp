package ch.fhnw.ip12.pipeitup.ui;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.HardwareGameBoardUi;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.HardwareGameBoardUiImpl;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.SoftwareGameBoardUi;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.SoftwareGameBoardUiImpl;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchUi;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchUiImpl;
import com.google.inject.AbstractModule;

/**
* DependencyInjectionConfig
*/
@ExcludeTypeFromJacocoGeneratedReport
public class DependencyInjectionConfigUi extends AbstractModule {

	@Override
    protected void configure() {
        bind(GameModeService.class).to(GameModeServiceImpl.class);
        bind(PipeItUpGameEntryPoint.class).to(PipeItUpGame.class);
        bind(TouchUi.class).to(TouchUiImpl.class);
        bind(SoftwareGameBoardUi.class).to(SoftwareGameBoardUiImpl.class);
        bind(HardwareGameBoardUi.class).to(HardwareGameBoardUiImpl.class);
    }

}
