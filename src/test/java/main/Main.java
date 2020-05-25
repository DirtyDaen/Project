package main;


import main.engine.graphics.*;
import main.engine.io.ModelLoader;
import main.engine.io.Window;
import main.engine.objects.Camera;
import main.engine.objects.GameObject;
import main.maths.vectors.Vector2f;
import main.maths.vectors.Vector3f;
import org.lwjgl.opengl.WGLARBRenderTexture;

public class Main implements Runnable{

    public Thread game;

    public Window window;
    public Renderer renderer;
    public Shader shader;

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public Mesh mesh = initMesh();

    private Mesh initMesh(){
        return mesh = ModelLoader.loadModel("res/models/blob_normal_2_0.obj", "stars.png");
    }

    public GameObject object = new GameObject(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));

    public static void main(String[] args) {
        new Main().start();
    }

    public void start(){
        game = new Thread(this,"Game");
        game.start();
    }

    public void  run(){
        init();
        while(!window.shouldClose()){
            update();
            render();
        }
        mesh.delete();
    }

    private void render() {
        renderer.renderMesh(object, camera);
        window.swapBuffers();
    }

    private void update() {
        window.update();
        window.getInput().update(window);
        camera.update(object);
    }

    public void init(){
        System.out.println(System.getProperty("user.dir"));
        window = new Window(WIDTH,HEIGHT,"Game");
        shader = new Shader("res/shaders/mainVertex.glsl","res/shaders/mainFragment.glsl");
        renderer = new Renderer(window, shader);

        window.setBackgroundColor(0.5f,0.5f,0);

        window.create();
        mesh.create();
        shader.create();
    }

    private void close(){
        window.removeWindow();
        shader.deleteProgram();
    }
}
