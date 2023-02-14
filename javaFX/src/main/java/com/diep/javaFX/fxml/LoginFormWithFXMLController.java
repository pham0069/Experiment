package com.diep.javaFX.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * @FXML tag nonpublic controller member fields and handler methods for use by FXML markup
 */
public class LoginFormWithFXMLController {
    @FXML private Text actionTarget;

    /**
     * Note that this method is only wirable as FXML handler
     * if the method signature includes single event parameter of class javafx.event.ActionEvent
     * If mistakenly use java.awt.event.ActionEvent or having additional paramter, it wont work
     * @param event
     */
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actionTarget.setText("Sign in button pressed");
    }
}
