/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarreraCarro;

/**
 *
 * @author Carlos
 */
class Coche implements Runnable {
    // Nombre del coche

    private String nombre;
    // Distancia que aún le queda por recorrer
    private int distancia;
    // Velocidad del coche
    private int velocidad;

    public Coche(String nombre, int distancia, int velocidad) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        // Mientras el coche tenga distancia por recorrer
        while (distancia > 0) {
            try {
                // Duerme el hilo durante un segundo para simular el tiempo transcurrido
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Reduce la distancia restante en la cantidad de metros que recorre cada segundo
            distancia -= velocidad;
            System.out.println(nombre + " ha recorrido " + velocidad + " metros. " + distancia + " metros restantes.");
        }
        System.out.println(nombre + " ha terminado la carrera!");
    }

    public static void main(String[] args) {
        Coche coche1 = new Coche("Coche 1", 1000, 50);
        Coche coche2 = new Coche("Coche 2", 1000, 75);
        Coche coche3 = new Coche("Coche 3", 1000, 100);

        Thread t1 = new Thread(coche1);
        Thread t2 = new Thread(coche2);
        Thread t3 = new Thread(coche3);

        t1.start();
        t2.start();
        t3.start();
    }

}
