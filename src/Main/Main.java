package Main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import pannableFeatures.MovableCanvas;
import pannableFeatures.NodeActions;
import pannableFeatures.SceneActions;

import java.util.ArrayList;

public class Main extends Application {

    public static final double WIDTH = 1024;
    public static final double HEIGHT = 768;

    public static void main(String[] args) {
        FileNavigator FN = new FileNavigator();
        ArrayList<String> paths = new ArrayList<String>();
        FN.getPaths(args[0], paths);
        ASTProcessor astp = new ASTProcessor();
        astp.process(paths);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        MovableCanvas canvas = new MovableCanvas();

        // create sample nodes that can be dragged
        NodeActions nodeActions = new NodeActions(canvas);

        drawShapes(canvas, nodeActions, root);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        SceneActions sceneActions = new SceneActions(canvas);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, sceneActions.getOnMousePressedEventHandler());
        scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, sceneActions.getOnMouseDraggedEventHandler());
        scene.addEventFilter(ScrollEvent.ANY, sceneActions.getOnScrollEventHandler());

        stage.setTitle("Project 2");
        stage.setScene(scene);
        stage.show();

//        canvas.createGrid();
    }

    public void drawShapes(Pane canvas, NodeActions nodeActions, Group root) {
        Circle circle1 = createCircle(100, 100, 100, Color.VIOLET);

        circle1.addEventFilter(MouseEvent.MOUSE_PRESSED, nodeActions.getOnMousePressedEventHandler());
        circle1.addEventFilter(MouseEvent.MOUSE_DRAGGED, nodeActions.getOnMouseDraggedEventHandler());

        Circle circle2 = createCircle(500, 100, 100, Color.AQUA);
        circle2.addEventFilter(MouseEvent.MOUSE_PRESSED, nodeActions.getOnMousePressedEventHandler());
        circle2.addEventFilter(MouseEvent.MOUSE_DRAGGED, nodeActions.getOnMouseDraggedEventHandler());

        Circle circle3 = createCircle(900, 100, 100, Color.CRIMSON);
        circle3.addEventFilter(MouseEvent.MOUSE_PRESSED, nodeActions.getOnMousePressedEventHandler());
        circle3.addEventFilter(MouseEvent.MOUSE_DRAGGED, nodeActions.getOnMouseDraggedEventHandler());

        canvas.getChildren().addAll(circle1, circle2, circle3);
    }

    private Circle createCircle(double x, double y, double radius, Color c) {
        final Circle circle = new Circle(x, y, radius, c);
        circle.setStroke(Color.GRAY);
        circle.setStrokeWidth(5);
        circle.setStrokeType(StrokeType.INSIDE);
        return circle;
    }
}
