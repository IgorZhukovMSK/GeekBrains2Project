package Level2.Lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    Sprite[] sprites = new Sprite[1];
    private int spritesCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);
        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased (MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1)
                    addGameObject (new Ball(e.getX(), e.getY()));
                else if (e.getButton() == MouseEvent.BUTTON3)
                    removeSprites();
            }
        });
        setTitle("Circles");
        initApplication();
        setVisible(true);
    }

    private void addGameObject (Sprite s){
        if (spritesCount == sprites.length){
            Sprite [] temp = new Sprite [sprites.length * 2];
            System.arraycopy (sprites, 0, temp, 0, sprites.length);
            sprites = temp;
        }
        sprites [spritesCount++] = s;
    }

    private void removeSprites(){
        if (spritesCount > 1) spritesCount --;
    }

    private void initApplication() {
        sprites [0] = new Background();
        spritesCount++;
        }


    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].render(canvas, g);
        }
    }
}
