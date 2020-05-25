package main.engine.io;


import org.lwjgl.glfw.*;

public class Input  {

    private GLFWKeyCallback keyboard;
    private GLFWMouseButtonCallback mouseButton;
    private GLFWCursorPosCallback cursorPos;
    private GLFWScrollCallback mouseScroll;

    private static boolean keys[] = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean mouseButtons[] = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;
    private double mouseScrollX, mouseScrollY;

    public Input(){
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods)  {
                keys[key] = action != GLFW.GLFW_RELEASE;
            }
        };

        mouseButton = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int key, int action, int mods) {
                mouseButtons[key] = action != GLFW.GLFW_RELEASE;
            }
        };

        cursorPos = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xPos, double yPos) {
                mouseX = xPos;
                mouseY = yPos;
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double offsetx, double offsety) {
                mouseScrollX += offsetx;
                mouseScrollY += offsety;
            }
        };
    }

    public static boolean isKeyDown(int key){
        return keys[key];
    }


    public static boolean isMouseButtonDown(int button){
        return mouseButtons[button];
    }

    public void update(Window window){


        if(keys[GLFW.GLFW_KEY_ESCAPE]){
            window.closeWindow();
        }

        if (keys[GLFW.GLFW_KEY_E]){
            window.switchFullscreen();
        }
    }

    public void removeCallback(){
        keyboard.free();
        mouseButton.free();
        cursorPos.free();
        mouseScroll.free();
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public GLFWCursorPosCallback getCursorPosCallback() {
        return cursorPos;
    }

    public GLFWMouseButtonCallback getMouseButtonCallback() {
        return mouseButton;
    }

    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

}
