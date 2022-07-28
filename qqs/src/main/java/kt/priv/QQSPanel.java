package kt.priv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QQSPanel extends JPanel implements ActionListener{
    static final int SCREEN_HEIGHT = 600;
    static final int SCREEN_WIDTH = 600;
    static final int UNIT_SIZE = 15;
    static final int VECTOR_UNITS = SCREEN_WIDTH / UNIT_SIZE;
    static final int DELAY = 80;
    boolean running = true;
    int iterator = 0;

    int [] vector = new int[VECTOR_UNITS-4];
    List<List<Integer>> lists = new ArrayList<>();

    Random random;
    Timer timer;

    QQSPanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        initializeVector();
        //quickSort(vector, 0, vector.length-1);
        startQQS();
    }

    private void startQQS() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initializeVector() {
        for(int i=0;i<VECTOR_UNITS-4;i++) {
            this.vector[i] = random.nextInt(SCREEN_HEIGHT - 4 * UNIT_SIZE);
        }
        /*List<Integer> tmp = new ArrayList<>();
        for(int a : this.vector) {
            tmp.add(a);
        }
        lists.add(tmp);
        */
    }

    public void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            /*List<Integer> tmp = new ArrayList<>();
            for(int a : this.vector) {
                tmp.add(a);
            }
            lists.add(tmp);*/

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
            
        }
    }

    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);
    
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
    
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
    
        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;
    
        return i+1;
    }

    public void draw(Graphics g) {
        if (running) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(4));
            //Integer [] tmp = (Integer[]) lists.get(iterator).toArray();
            int [] tmp = this.vector;
            //iterator++;
            for(int i=0;i<VECTOR_UNITS-4;i++) {
                g2.drawRect(2 * UNIT_SIZE + i * UNIT_SIZE, SCREEN_HEIGHT-tmp[i]-(2*UNIT_SIZE), UNIT_SIZE, tmp[i]);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(running && iterator<lists.size()) {
        repaint();
        }*/
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            
        }
    }
}
