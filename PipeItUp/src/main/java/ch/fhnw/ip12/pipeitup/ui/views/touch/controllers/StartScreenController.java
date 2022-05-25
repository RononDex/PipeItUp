package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class StartScreenController extends TouchUiController {

    @FXML
    Button mainmenu;

    public void handleMAIN_MENU() throws Exception{
		viewModel.currentScene.setValue(TouchScene.MAIN_MENU);
    }


}
