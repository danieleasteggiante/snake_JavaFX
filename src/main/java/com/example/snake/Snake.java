package com.example.snake;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake extends Colliding{

    // i parametri li prende dalla classe Colliding se si aggiungono anche qui impazzisce


    public Snake(double x, double y, double WIDTH, double HEIGHT) {
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.WHITE);
        gc.fillRect(this.x, this.y, WIDTH, HEIGHT);
    }

    public void drawTesta(GraphicsContext gc){
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(this.x, this.y, WIDTH, HEIGHT);
    }


    @Override
    public String toString() {
        return "Snake{" +
                "x=" + x +
                ", y=" + y +
                ", WIDTH=" + WIDTH +
                ", HEIGHT=" + HEIGHT +
                '}';
    }
}
