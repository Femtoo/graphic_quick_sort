package kt.priv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Stack;

public class QQSPanel extends JPanel implements ActionListener {
    static final int SCREEN_HEIGHT = 600;
    static final int SCREEN_WIDTH = 1000;
    static final int UNIT_SIZE = 15;
    static final int VECTOR_UNITS = SCREEN_WIDTH / UNIT_SIZE;
    static final int DELAY = 160;
    private boolean running = true;

    private int[] vector = new int[VECTOR_UNITS - 4];

    Random random;
    Timer timer;
    private Stack<Integer> stack;

    QQSPanel() {
        random = new Random();
        stack = new Stack<>();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        initializeVector();
        startQQS();
    }

    private void startQQS() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initializeVector() {
        for (int i = 0; i < VECTOR_UNITS - 4; i++) {
            this.vector[i] = random.nextInt(SCREEN_HEIGHT - 4 * UNIT_SIZE);
        }
        stack.push(0);
        stack.push(vector.length - 1);

    }

    public void quickSort(int arr[]) {
        if (!stack.isEmpty()) {
            int end = stack.pop();
            int begin = stack.pop();
            if (begin < end) {
                int partitionIndex = partition(arr, begin, end);
                
                stack.push(partitionIndex+1);
                stack.push(end);
            
                stack.push(begin);
                stack.push(partitionIndex-1);
            }
        }
    }

    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(4));
        int tmp = stack.pop();
        //int[] tmp = this.vector;
        for (int i = 0; i < VECTOR_UNITS - 4; i++) {
            if(i>=stack.peek() && i<=tmp) {
                g2.setColor(Color.green);
            } else {
                g2.setColor(Color.red);
            }

            g2.drawRect(2 * UNIT_SIZE + i * UNIT_SIZE, SCREEN_HEIGHT - this.vector[i] - (2 * UNIT_SIZE), UNIT_SIZE, this.vector[i]);
        }
        stack.push(tmp);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(running) {
            quickSort(vector);
        }
        if(stack.isEmpty()) {
            running = false;
            timer.stop();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==0x50){
                running=!running;
            }
        }
    }
}
