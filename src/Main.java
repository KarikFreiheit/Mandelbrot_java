import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.nio.Buffer;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;


public class Main {
    public static void main(String[] args) {
        // bunch of mumbo jumbo to draw pixels to screen
        BufferedImage image = new BufferedImage(3840, 2160, BufferedImage.TYPE_INT_RGB);

        JLabel label = new JLabel(new ImageIcon(image));

        JFrame frame = new JFrame("Mandlebrot");


        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane jsp = new JScrollPane(label);
        frame.add(jsp);
        drawMandelbrot(frame, image);

    }

    //Gotta be honest only like half of this makes sense. It involves complex numbers which are a combination of both real and imaginary numbers. It basiccally iterates through every pixal and sets the color based on the number of itereations before the set gets too big./
    // Looks cool tho just run
    private static void drawMandelbrot(JFrame frame, BufferedImage image){
        int height = 2160;
        int width = 3840;
        //resolution change it to like 10 and it looks smooth and not very detailed
        int reso = 1000;
        Color gray = new Color(55,55,55);
        Color white = new Color(255,255,255);
        Color black = new Color(0,0,0);
        for(int row = 0; row< height; row++){
            for(int col = 0; col < width; col++){
                double cr= (col - height /2.0)*4.0/2560;
                double ci= (row - width /2.0)*4.0/2560;
                int it = 0;
                double x = 0, y = 0;
                while(x*x+y*y <= 4 && it < reso){
                    double xnew = x*x - y*y +cr;
                    y = 2*x*y + ci;
                    x = xnew;
                    it++;

                }

                if(it < reso) {
                    //actually colors each individual pixel

                    image.setRGB(col, row, white.getRGB());
                }else{
                    //actually colors each individual pixel
                    image.setRGB(col, row, black.getRGB());
                }


            }
        }
        frame.repaint();




    }
}