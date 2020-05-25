package main.engine.graphics;

import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

public class Material {
    private Texture texture;
    private String path;


    public Material(String filename){
        this.path = ("res/materials/" + filename);
    }

    public void loadTexture(){
        try{
            GL.createCapabilities();
            texture = new Texture(path);
            texture.bind();
            GL12.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            GL12.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);

        } catch(IOException e){
            System.err.println("Couldn't load Texture from path: " + path);
        }
        System.out.println(texture);
    }

    public Texture getTexture() {
        return texture;
    }

    public void remove(){
        GL13.glDeleteTextures(texture.id);
    }
}
