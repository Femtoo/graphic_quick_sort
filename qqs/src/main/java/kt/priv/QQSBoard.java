package kt.priv;

import javax.swing.JFrame;

public class QQSBoard extends JFrame{
    
    QQSBoard() {
        this.add(new QQSPanel());
        this.setTitle("Graphic Quick Sort");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
}
