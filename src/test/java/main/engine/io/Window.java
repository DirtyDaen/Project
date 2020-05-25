package main.engine.io;

import static org.lwjgl.glfw.GLFW.*;


import main.maths.util.Matrix4f;
import main.maths.vectors.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
    private int width;
    private int height;
    private String title;
    private int windowPosX[] = new int[1];
    private int windowPosY[] = new int[1];

    private long window;
    private Input input;
    private Vector3f backgroundColor = new Vector3f();
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized = false;
    private boolean isFullscreen = false;

    private Matrix4f projection;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        projection = Matrix4f.projection(70.0f, (float) width / (float) height, 0.1f, 1000.0f);

        windowPosX[0] = 0;
        windowPosY[0] = 0;
    }

    public void create(){
        if (!glfwInit()){
            System.err.println("ERROR: Window not initialized");
            return;
        }

        window = glfwCreateWindow(width, height, title, isFullscreen ? glfwGetPrimaryMonitor() : 0, 0);

        if(window == 0){

            System.err.println("ERROR: Window not created");
        }

        GLFWVidMode videomode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        windowPosX[0] = (videomode.width() - width) /2;
        windowPosY[0] = (videomode.height() - height) /2;

        glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        GL11.glEnable(GL11.GL_DEPTH_TEST);

        input = new Input();

        createCallbacks();

        glfwShowWindow(window);

        glfwSwapInterval(1);
    }

    public void update(){
        if (isResized == true) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }
        GL11.glClearColor(backgroundColor.x(),backgroundColor.y(),backgroundColor.z(),1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        glfwPollEvents();
    }

    public void swapBuffers(){
        glfwSwapBuffers(window);
    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(window);
    }

    public void removeWindow(){
        input.removeCallback();
        removeCallbacks();
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void closeWindow(){
        glfwSetWindowShouldClose(window, true);
    }

    private void removeCallbacks(){
        sizeCallback.free();
    }

    private void createCallbacks(){
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };

        glfwSetKeyCallback(window,input.getKeyboardCallback());
        glfwSetMouseButtonCallback(window, input.getMouseButtonCallback());
        glfwSetCursorPosCallback(window, input.getCursorPosCallback());
        glfwSetScrollCallback(window,input.getMouseScrollCallback());
        glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public Input getInput(){
        return input;
    }

    public long getWindow(){
        return window;
    }

    public void setBackgroundColor(float r, float g, float b){
        backgroundColor.set(r,b,g);
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void switchFullscreen() {
        this.isFullscreen = !isFullscreen;
        isResized = true;
        if (isFullscreen){
            glfwGetWindowPos(window, windowPosX, windowPosY);
            glfwSetWindowMonitor(window,glfwGetPrimaryMonitor(),0,0,width,height,0);
        }else{
            glfwSetWindowMonitor(window,0,windowPosX[0],windowPosY[0],width,height,0);
        }
    }

    public void mouseState(boolean lock){
        glfwSetInputMode(window, GLFW_CURSOR, lock ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public Matrix4f getProjectionMatrix() {
        return projection;
    }
}
