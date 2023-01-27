package com.example.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeApplication extends Application {
    final Color backgroundColor = Color.BLACK;

    Canvas canvas;
    Pane canvasPane;

    final int CANVASWIDTH = 600;
    final int CANVASHEIGHT = 600;

    Snake testa;
    Frutto frutto;
    double LATO = 10;

    List<Snake> snake;

    int lunghezza = 0;

    int movimento = 10;

    int direzione;

    Timeline timeline;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Crea il canvas per disegnare, canvasPane per dare la dimensione al canvas e la scene per contenere il tutto
        canvas = new Canvas(CANVASWIDTH, CANVASHEIGHT);
        canvasPane = new Pane();
        canvasPane.getChildren().add(canvas);
        Scene scene = new Scene(canvasPane);

        // Inizializza sepente e frutto
        inizializeObjects();

        //si attiva ad ogni pressione del mouse
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                direzione = 0;
            }
            if (event.getCode() == KeyCode.LEFT) {
                direzione = 1;
            }
            if (event.getCode() == KeyCode.UP) {
                direzione = 2;
            }
            if (event.getCode() == KeyCode.DOWN) {
                direzione = 3;
            }
        });

        // Lancia lo stage
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
        initializeTimer();

    }

    public void inizializeObjects(){
        frutto = new Frutto(generateRandomNumber(CANVASWIDTH), generateRandomNumber(CANVASHEIGHT), LATO, LATO); // Genera il frutto
        testa = new Snake(300,300, LATO, LATO); // genera un nuovo snake/quadratino
        snake = new ArrayList<>(); // genera una nuova arraylist
        snake.add(testa); // aggiunge alla lista di snake /quadratini il primo elemento
        lunghezza++; // aggiorna la lunghezza
    }

    public int generateRandomNumber(int upperbound){
        // funzione per generare un numero casuale con un limite massimo (da zero a 'upperbound')
        Random rand = new Random();
        int int_random = rand.nextInt(upperbound);
        return int_random;
    }

    public void mainLoop(){
        //main loop che viene esequito ad ogni chiamata del timer, che a sua volta chiama 3 funzioni
        getEatHimself();// controlla se si mangia la coda e se se la mangia viene scritto sulla console "HAI PERSO, ma puoi continuare"
        getEatedFruit(); // controlla se ci sono collisioni
        moveSnake(); // muovi il serpente
        paint(); // disegna tutto
    }

    public void initializeTimer() {
        timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> mainLoop()),
                new KeyFrame(Duration.millis(100)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void getEatedFruit(){ // controlla se mangia il frutto
        if (snake.get(snake.size()-1).getCollision(frutto)) {
            lunghezza++;
            frutto.setX(generateRandomNumber(CANVASWIDTH));
            frutto.setY(generateRandomNumber(CANVASHEIGHT));
        }
    }

    public void getEatHimself(){ // controlla se mangia se stesso
        for(int i = 0; i< lunghezza-2; i++){
            if (snake.get(snake.size()-1).getCollision(snake.get(i))) {
                System.out.println("HAI PERSO, ma puoi continuare");
            }

        }

    }

    public void moveSnake(){
        // muovi il serpente salvando l'ultima direzione
        if (direzione==0) testa.setX(testa.getX()+movimento);
        if (direzione==1) testa.setX(testa.getX()-movimento);
        if (direzione==2) testa.setY(testa.getY()-movimento);
        if (direzione==3) testa.setY(testa.getY()+movimento);

        getBounds(); // controllo se esco dai limiti del canvas

        // aggiungi un nuovo snake/quadratino nella nuova dimensione
        snake.add(new Snake(testa.getX(), testa.getY(),LATO, LATO));

        // se lo snake è maggiore della lunghezza rimuovi il primo elemento
        //N.B. la lunghezza inizia da 2 (che significa che cancello solo se è maggiore di 1) e viene incrementata ogni volta che mangia un frutto
        if(snake.size() > lunghezza) snake.remove(0); // N.B. se non facessi questo controllo lo snake non verrebbe disegnato
    }

    public void getBounds(){
        if(testa.getX()>=CANVASWIDTH-5) testa.setX(6); // se esco a destra ritorno da zero
        if(testa.getY()>=CANVASHEIGHT-5) testa.setY(6); // se esco dal basso ritorno da zero

        if(testa.getX()<=5) testa.setX(CANVASWIDTH); // se esco da sinistra ritorno da destra
        if(testa.getY()<=5) testa.setY(CANVASHEIGHT); // se esco in alto ritorno dal fondo
    }



    public void drawSnake(GraphicsContext gc){
        // disegna lo snake
        for(int i = 0; i < lunghezza; i++){
            if(i == lunghezza-1){
                snake.get(i).drawTesta(gc); // controlla se i è uguale all'ultimo elemento inserito (testa) e la disegna un colore definito nell'oggetto
                break;
            }
            snake.get(i).draw(gc); // se non è il primo elemento colora gli altri come "corpo"
        }
    }


    public void paint(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); // ricolor il fondo di nero
        // background

        frutto.draw(gc); // disegno prima il frutto cosi ci vado sopra col serpente
        drawSnake(gc); // disegno per ultimo il serpente cosi va sopra al frutto e sembra che lo mangi

    }
}

