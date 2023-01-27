package com.example.snake;


/* Questa classe è una classe astratta "padre" di snake e frutto
* poichè dato che sono due quadratini con colori diversi
* ho semplicemente scritto i metodi per generare i colori
* qui dichiaro tutte le variabili e i getter, setter le collisioni e
* le funzioni getCenter che mi servono per determinare se due elementi
* collidono o no.
* */

public abstract class Colliding {
    double x;
    double y;

    double WIDTH;

    double HEIGHT;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(double WIDTH) {
        this.WIDTH = WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(double HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public double getCenterX(){
        return x + WIDTH/2;
    }

    public double getCenterY(){
        return y + HEIGHT/2;
    }

    public Boolean getCollision(Colliding c){
        Boolean collided = false;

        if((c.getCenterX() >= x) && (c.getCenterX () <= x+WIDTH) && (c.getCenterY() >= y) && (c.getCenterY() <= y+HEIGHT)) collided = true;
        return collided;

    }



}
