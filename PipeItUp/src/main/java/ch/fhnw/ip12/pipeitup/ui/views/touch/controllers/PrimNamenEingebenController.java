package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;


import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@ExcludeTypeFromJacocoGeneratedReport
public class PrimNamenEingebenController extends TouchUiController{

	@FXML
	TextField textField;

	@FXML
	Button
	button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
	buttonQ, buttonW, buttonE, buttonR, buttonT, buttonZ, buttonU, buttonI, buttonO, buttonP,
	buttonA, buttonS, buttonD, buttonF, buttonG, buttonH, buttonJ, buttonK, buttonL,
	buttonY, buttonX, buttonC, buttonV, buttonB, buttonN, buttonM,
	backspace,
	enter, backarrow;

	@Override
	public void initializeController() {
		viewModel.userName.setValue("");
	}

	public void handleBackarrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.GAMEMODE_PRIM);
	}

	public StringProperty getUserNme() {
		return this.viewModel.userName;
	}

	public void handleButton1() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "1");
	}

	public void handleButton2() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "2");
	}
	public void handleButton3() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "3");
	}
	public void handleButton4() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "4");
	}
	public void handleButton5() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "5");
	}
	public void handleButton6() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "6");
	}
	public void handleButton7() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "7");
	}
	public void handleButton8() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "8");
	}
	public void handleButton9() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "9");
	}
	public void handleButtonQ() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "Q");
	}
	public void handleButtonW() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "W");
	}
	public void handleButtonE() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "E");
	}
	public void handleButtonR() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "R");
	}
	public void handleButtonT() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "T");
	}
	public void handleButtonZ() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "Z");
	}
	public void handleButtonU() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "U");
	}
	public void handleButtonI() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "I");
	}
	public void handleButtonO() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "O");
	}
	public void handleButtonP() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "P");
	}
	public void handleButtonA() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "A");
	}
	public void handleButtonS() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "S");
	}
	public void handleButtonD() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "D");
	}
	public void handleButtonF() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "F");
	}
	public void handleButtonG() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "G");
	}
	public void handleButtonH() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "H");
	}
	public void handleButtonJ() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "J");
	}
	public void handleButtonK() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "K");
	}
	public void handleButtonL() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "L");
	}
	public void handleButtonY() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "Y");
	}
	public void handleButtonX() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "X");
	}
	public void handleButtonC() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "C");
	}
	public void handleButtonV() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "V");
	}
	public void handleButtonB() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "B");
	}
	public void handleButtonN() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "N");
	}
	public void handleButtonM() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "M");
	}

	public void handleButton0() {
		ensureTextFieldIsBound();
		this.viewModel.userName.setValue(this.viewModel.userName.getValue() + "0");
	}

	public void handleEnter() throws Exception{
		viewModel.currentScene.setValue(TouchScene.PAULINE1);
	}

	private boolean textFieldAlreadyBound = false;
	private void ensureTextFieldIsBound() {
		if (!textFieldAlreadyBound) {
			textField.textProperty().bindBidirectional(this.viewModel.userName);
		}
	}
}
