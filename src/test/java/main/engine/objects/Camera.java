package main.engine.objects;

import main.engine.io.Input;
import main.maths.vectors.Vector3f;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;


public class Camera {
    private Vector3f position, rotation;
    private float movespeed = 0.05f, mouseSens = 0.15f, distance = 2.0f, angle = 0;
    private float horizAngle = 0, vertAngle = 0;
    private double oldMouseX = 0, oldMouseY = 0, newMouseX, newMouseY;

    public Camera(Vector3f position, Vector3f rotation){
        this.position = position;
        this.rotation = rotation;

    }

    public void update(){
        newMouseX = Input.getMouseX();
        newMouseY = Input.getMouseY();

        float x = (float) Math.sin(Math.toRadians(rotation.y())) * movespeed;
        float z = (float) Math.cos(Math.toRadians(rotation.y())) * movespeed;

        if(Input.isKeyDown(GLFW_KEY_A)) position = Vector3f.add(position, new Vector3f(-z, 0, x));
        if(Input.isKeyDown(GLFW_KEY_D)) position = Vector3f.add(position, new Vector3f(z, 0, -x));
        if(Input.isKeyDown(GLFW_KEY_W)) position = Vector3f.add(position, new Vector3f(-x, 0, -z));
        if(Input.isKeyDown(GLFW_KEY_S)) position = Vector3f.add(position, new Vector3f(x, 0, z));
        if(Input.isKeyDown(GLFW_KEY_SPACE)) position = Vector3f.add(position, new Vector3f(0, movespeed, 0));
        if(Input.isKeyDown(GLFW_KEY_LEFT_SHIFT)) position = Vector3f.add(position, new Vector3f(0, -movespeed, 0));

        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSens, -dx * mouseSens, 0));

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }

    public void update(GameObject object){
        newMouseX = Input.getMouseX();
        newMouseY = Input.getMouseY();

        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            vertAngle -= dy * mouseSens;
            horizAngle += dx * mouseSens;
        }

        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)){
            if(distance > 0){
                distance += dy * mouseSens /4;
            } else{
                distance = 0.1f;
            }
        }

        float horizDist = (float) (distance * Math.cos(Math.toRadians(vertAngle)));
        float vertDist = (float) (distance * Math.sin(Math.toRadians(vertAngle)));

        float xOffset = (float) (horizDist * Math.sin(Math.toRadians(-horizAngle)));
        float zOffset = (float) (horizDist * Math.cos(Math.toRadians(-horizAngle)));

        position.set(object.getPosition().x() + xOffset, object.getPosition().y() - vertDist, object.getPosition().z() + zOffset);

        rotation.set(vertAngle, -horizAngle, 0);

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
