package main.engine.objects;

import main.engine.graphics.Mesh;
import main.maths.vectors.Vector3f;

public class GameObject {
    private Vector3f position, rotation, scale;
    private Mesh mesh;
    private double temp;

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh){
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    public void update(){
        position.setZ(position.z() - 0.01f);
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Mesh getMesh() {
        return mesh;
    }
}
