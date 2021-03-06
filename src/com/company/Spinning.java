//package com.company;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

/*
challenge implemented:
spheres spinning around a central sphere (that code is in commented out code chunks)
 */

public class Spinning extends JPanel{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final double CENTERX = WIDTH / 2.0;
    public static final double CENTERY = HEIGHT / 2.0;


    //Right now spheres isn't being used
    public static Sphere[] spheres;

    //You'll get rid of the following set of variables
    //Instead, let each Sphere keep track of its own velocity and position



    public Spinning(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //Feel free to set default values as you see fit

    }

    public void Go(){
        while(true){
            for (Sphere s : spheres){
                s.update(1.0 / (double)FPS);

                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}

            }

        }
    }

    public static void main(String[] args){
        int numSpheres = 3;
        if (args.length < 1){
            System.out.println("When you run this, you can specify the number of spheres.");
            System.out.println("e.g., java Spinning 10");
        }
        else{
            System.out.println("You specified that there should be " + args[0] + " spheres.");
            numSpheres = Integer.parseInt(args[0]);
        }

        //here you'll set up the spheres array
        spheres = new Sphere[numSpheres];


        for (int i = 0; i < numSpheres; i++) {
            Sphere s = new Sphere(((int)(10+Math.random()*70)), ((int)(-40+Math.random()*1024)), ((int)(-40+Math.random()*768)),
                    ((int)(300 + Math.random()*500)), ((int)(300 + Math.random()*500)), ((int)(Math.random()*255)),
                    ((int)(Math.random()*255)), ((int)(Math.random()*255)));
            spheres[i] = s;

        }

        /*
        Sphere sphere1 = new Sphere(50, (1024/2), (768/2), 0, 0, ((int)(Math.random()*255)),
                ((int)(Math.random()*255)), ((int)(Math.random()*255)));
        spheres[0] = sphere1;
        for (int i = 1; i < numSpheres; i++) {
            if (i%2 == 0) {
                Sphere s = new Sphere(((int) (10 + Math.random() * 40)), (1024 / 2), (int) ((CENTERY / numSpheres) * i),
                        500, 500, ((int) (Math.random() * 255)),
                        ((int) (Math.random() * 255)), ((int) (Math.random() * 255)));
                spheres[i] = s;
            }
            else {
                Sphere s = new Sphere(((int) (10 + Math.random() * 40)), (1024 / 2), (int) ((CENTERY / numSpheres) * i),
                        -500, 500, ((int) (Math.random() * 255)),
                        ((int) (Math.random() * 255)), ((int) (Math.random() * 255)));
                spheres[i] = s;
            }

        }
         */


        JFrame frame = new JFrame("Spinning Spheres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Spinning world = new Spinning();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
        world.Go();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //your code here for drawing the other spheres (you'll replace the following lines)

        for (Sphere s : spheres){
            s.draw(g);
        }
    }

//You'll implement this class
static class Sphere {
    //Put fields here:
    int radius;
    int xPos;
    int yPos;
    int velocityX;
    int velocityY;
    int rval;
    int gval;
    int bval;


    public Sphere(int r, int x, int y, int xvel, int yvel, int red, int green, int blue) {
        //This is the constructor
        radius = r;
        xPos = x;
        yPos = y;
        velocityX = xvel;
        velocityY = yvel;

        if (red > 255) {
            rval = 255;
        }
        if (green > 255) {
            gval = 255;
        }
        if (blue > 255) {
            bval = 255;
        } else {
            rval = red;
            gval = green;
            bval = blue;
        }

    }


//Put methods (update, draw, and whatever else you decide to implement) here:

    public void update(double time) {
        if (this.xPos < 0 || this.xPos + this.radius > 1024) {
            this.velocityX = -this.velocityX;
        }
        if (this.yPos < 0 || this.yPos + this.radius > 768) {
            this.velocityY = -this.velocityY;
        }
        //System.out.println("velocity = " + velocityX);
        this.xPos += this.velocityX*time;
        //System.out.println("position = " + xPos);
        this.yPos += this.velocityY*time;
    }


    /*
    public void update(double time) {
        //move in a circle
        if ((this.velocityX < 0 && this.velocityY > 0) && (this.yPos >= CENTERY)) {
            velocityX = -velocityX;
        }
        if ((this.velocityX > 0 && this.velocityY > 0) && (this.xPos >= CENTERX)) {
            velocityY = -velocityY;
        }
        if ((this.velocityX > 0 && this.velocityY < 0) && (this.yPos <= CENTERY)) {
            velocityX = -velocityX;
        }
        if ((this.velocityX < 0 && this.velocityY < 0) && (this.xPos <= CENTERX)) {
            velocityY = -velocityY;
        }

        this.xPos += this.velocityX*time;
        this.yPos += this.velocityY*time;
    }
     */

    public void draw(Graphics g) {
        g.setColor(new Color(rval, gval, bval));
        g.fillOval(xPos, yPos, radius, radius);
    }

}
}
