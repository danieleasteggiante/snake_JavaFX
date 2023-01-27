package com.example.snake;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Frutto extends Colliding {

    // i parametri li prende dalla classe Colliding se si aggiungono anche qui impazzisce

    public Frutto(double x, double y, double WIDTH, double HEIGHT) {
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }


    public void draw(GraphicsContext gc){
        gc.setFill(Color.RED);
        gc.fillRect(this.x, this.y, WIDTH, HEIGHT);
    }


}
