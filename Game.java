/**
 * Game.java main driver Class to run Snake game
 *
 * @author Yew Journ Chan
 */

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame { //need JFrame in order to implement GUI
Game() {
    add(new Board());
    setResizable(false);
    pack();

    setTitle("Snake");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() { // will create a new thread so the GUI can process itself
        @Override
        public void run() {
            JFrame frame = new Game();
            frame.setVisible(true);
        }
    });
}
}