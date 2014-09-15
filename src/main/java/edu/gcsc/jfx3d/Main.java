package edu.gcsc.jfx3d;

import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = createContent(24,8);
        
        // Create camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(300);

        // and position it
        camera.getTransforms().addAll(
                new Rotate(0, Rotate.Y_AXIS),
                new Rotate(0, Rotate.X_AXIS),
                new Translate(0, 0, -200));

        // add camera as node to scene graph
        root.getChildren().add(camera);

        // Setup a scene
        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.DARKGRAY.darker().darker().darker().darker());
        scene.setCamera(camera);

        //Add the scene to the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        System.exit(0);
    }
    
    /**
     * Creates a sphere with the specified resolution.
     *
     * @param radius sphere radius
     * @param resolution resolution (high resolution results in high number of
     * triangles)
     * @return groupcontaining the sphere.
     */
    public Group createContent(double radius, int resolution) {

        Group root = new Group();

        // Create a sphere
        Cylinder sphere;

        sphere = new Cylinder(radius, radius*2, resolution);

        PointLight light2 = new PointLight(Color.LIGHTGRAY);
        root.getChildren().add(light2);
        light2.getTransforms().add(new Translate(-50, 10, -520));

        AmbientLight light3 = new AmbientLight(new Color(0.35,0.35,0.35,1.0));
        root.getChildren().add(light3);

        PhongMaterial m = new PhongMaterial(Color.RED);

        sphere.setMaterial(m);

        sphere.setDrawMode(DrawMode.FILL);
        sphere.setCullFace(CullFace.NONE);

        VFX3DUtil.addMouseBehavior(sphere, sphere, MouseButton.PRIMARY,
                Rotate.X_AXIS, Rotate.Y_AXIS);

        // Add sphere to group
        root.getChildren().add(sphere);

        return root;
    }
}
