package com.diep.javaFX.css;

import javafx.scene.Scene;
import javafx.scene.text.Text;

/**
 * Using style from external css file
 * In this way, we can separate design from content
 */
public class FancyLoginFormWithCSSDemo extends LoginFormDemo {
    @Override
    protected Scene createScene() {
        Scene scene = super.createScene();
        scene.getStylesheets().add(this.getClass().getResource("login.css").toExternalForm());
        return scene;

    }

    @Override
    protected Text createWelcomeText() {
        Text welcome = new Text("Welcome");
        // Define id for this text component for styling in CSS
        welcome.setId("welcome-text");
        return welcome;
    }

    @Override
    protected Text createActionTargetText() {
        Text actionTarget = new Text();
        actionTarget.setId("actionTarget");
        return actionTarget;
    }

}

