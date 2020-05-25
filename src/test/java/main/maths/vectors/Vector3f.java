package main.maths.vectors;

import java.util.Objects;

public class Vector3f {
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(){}

    public void set(Vector3f vector3f){
        this.x = vector3f.x();
        this.y = vector3f.y();
        this.z = vector3f.z();
    }

    public void set(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f add(Vector3f vector1, Vector3f vector2){
        return new Vector3f(vector1.x() + vector2.x(), vector1.y() + vector2.y(),vector1.z() + vector2.z());
    }

    public static Vector3f subtract(Vector3f vector1, Vector3f vector2){
        return new Vector3f(vector1.x() - vector2.x(), vector1.y() - vector2.y(),vector1.z() - vector2.z());
    }

    public static Vector3f multiply(Vector3f vector1, Vector3f vector2){
        return new Vector3f(vector1.x() * vector2.x(), vector1.y() * vector2.y(),vector1.z() * vector2.z());
    }

    public static Vector3f divide(Vector3f vector1, Vector3f vector2){
        return new Vector3f(vector1.x() / vector2.x(), vector1.y() / vector2.y(),vector1.z() / vector2.z());
    }

    public static float length(Vector3f vector){
        return (float) Math.sqrt(vector.x() * vector.x() + vector.y() * vector.y() + vector.z() * vector.z());
    }

    public static Vector3f normalize(Vector3f vector){
        float len = Vector3f.length(vector);
        return Vector3f.divide(vector, new Vector3f(len, len, len));
    }

    public static  float dot(Vector3f vector1, Vector3f vector2){
        return vector1.x() * vector2.x() + vector1.y() * vector2.y() +vector1.z() * vector2.z();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return Float.compare(vector3f.x, x) == 0 &&
                Float.compare(vector3f.y, y) == 0 &&
                Float.compare(vector3f.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public float z() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
