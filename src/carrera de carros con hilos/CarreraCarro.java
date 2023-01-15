package CarreraCarro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class CarreraCarro extends JPanel implements Runnable {

    // Ancho y alto del gráfico en pixels
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    // Velocidades de los coches en pixels por segundo
    private static final int[] VELOCIDADES = {50, 100, 150};
    // Posiciones iniciales de los coches en pixels
    private static final int[][] POSICIONES_INICIALES = {{100, 100}, {100, 150}, {100, 200}};
    // Distancias recorridas por cada coche en pixels
    private int[] distancias = {0, 0, 0};
    // Posición de la meta en pixels
    private static final int META = 600;

    public static void main(String[] args) {
        // Crea el marco y le añade el panel de dibujo
        JFrame marco = new JFrame();
        marco.setSize(ANCHO, ALTO);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CarreraCarro panel = new CarreraCarro();
        marco.add(panel);
        marco.setVisible(true);

        // Crea un hilo para actualizar y dibujar la carrera de coches
        Thread t = new Thread(panel);
        t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        // Llama al método de la superclase para que se dibuje el panel
        super.paintComponent(g);

        // Dibuja la carrera de coches
        dibujarCarrera(g);
    }

    private void dibujarCarrera(Graphics g) {
        // Dibuja la pista
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, ANCHO, ALTO);

        // Dibuja la línea de llegada
        g.setColor(Color.RED);
        g.drawLine(0, META, ANCHO, META);

        // Dibuja los coches
        for (int i = 0; i < 3; i++) {
            g.setColor(Color.RED);
            g.fillRect(POSICIONES_INICIALES[i][0] + distancias[i], POSICIONES_INICIALES[i][1], 50, 25);
        }
    }

    @Override
    public void run() {
        // Bucle infinito que se ejecuta cada 20 milisegundos
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Actualiza la posición de los coches
            for (int i = 0; i < 3; i++) {
                distancias[i] += VELOCIDADES[i] * 20 / 1000;
            }
            // Vuelve a dibujar el gráfico
            repaint();

            // Comprueba si alguno de los coches ha llegado a la meta
            if (distancias[0] > META || distancias[1] > META || distancias[2] > META) {
                // Detiene el hilo
                return;
            }
        }
    }
}
