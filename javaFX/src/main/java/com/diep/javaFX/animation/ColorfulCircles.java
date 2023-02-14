package com.diep.javaFX.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class ColorfulCircles extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        Rectangle colors = createColors();
        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());
        colors.setBlendMode(BlendMode.OVERLAY);

        Group circles = createCircleGroup();

        Group blendModeGroup = new Group(new Group(new Rectangle(WIDTH, HEIGHT, Color.BLACK), circles), colors);

        root.getChildren().add(blendModeGroup);
        setAnimation(circles);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Group createCircleGroup() {
        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            // Each circle has radius of 150, fill color of white, opacity of 5% (almost transparent)
            Circle circle = new Circle(150, Color.web("white", 0.05));
            // Set boundary around the circle
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            // Apply box blur effect to the circles
            circles.setEffect(new BoxBlur(10, 10, 3));
            circles.getChildren().add(circle);
        }
        return circles;
    }

    /**
     * Create rectangle with gradient colors
     * @return
     */
    private Rectangle createColors() {
        Rectangle colors = new Rectangle(WIDTH, HEIGHT,
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE,
                        new Stop[]{
                        new Stop(0, Color.web("#f8bd55")),
                        new Stop(0.14, Color.web("#c0fe56")),
                        new Stop(0.28, Color.web("#5dfbc1")),
                        new Stop(0.43, Color.web("#64c2f8")),
                        new Stop(0.57, Color.web("#be4af7")),
                        new Stop(0.71, Color.web("#ed5fc2")),
                        new Stop(0.85, Color.web("#ef504c")),
                        new Stop(1, Color.web("#f2660f")),}));
        return colors;
    }

    private void setAnimation(Group circles) {
        Timeline timeline = new Timeline();
        for (Node circle: circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                            new KeyValue(circle.translateXProperty(), random() * WIDTH),
                            new KeyValue(circle.translateYProperty(), random() * HEIGHT)
                    ),
                    new KeyFrame(new Duration(40000), // set end position at 40s
                            new KeyValue(circle.translateXProperty(), random() * WIDTH),
                            new KeyValue(circle.translateYProperty(), random() * HEIGHT)
                    )
            );
        }
        // play 40s of animation
        timeline.play();
    }

    private double random() {
        return RANDOM.nextDouble();
    }
}
