package main.maths.vectors;

import java.util.Objects;

public class Vector2f {
    private float x, y;

    public Vector2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public static Vector2f add(Vector2f vector1, Vector2f vector2){
        return new Vector2f(vector1.x() + vector2.x(), vector1.y() + vector2.y());
    }

    public static Vector2f subtract(Vector2f vector1, Vector2f vector2){
        return new Vector2f(vector1.x() - vector2.x(), vector1.y() - vector2.y());
    }

    public static Vector2f multiply(Vector2f vector1, Vector2f vector2){
        return new Vector2f(vector1.x() * vector2.x(), vector1.y() * vector2.y());
    }

    public static Vector2f divide(Vector2f vector1, Vector2f vector2){
        return new Vector2f(vector1.x() / vector2.x(), vector1.y() / vector2.y());
    }

    public static float length(Vector2f vector){
        return (float) Math.sqrt(vector.x() * vector.x() + vector.y() * vector.y());
    }

    public static Vector2f normalize(Vector2f vector){
        float len = Vector2f.length(vector);
        return Vector2f.divide(vector, new Vector2f(len, len));
    }

    public static  float dot(Vector2f vector1, Vector2f vector2){
        return vector1.x() * vector2.x() + vector1.y() * vector2.y();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(vector2f.x, x) == 0 &&
                Float.compare(vector2f.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public float x() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float y() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
