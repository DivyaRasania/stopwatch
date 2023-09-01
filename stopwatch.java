import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class stopwatch implements ActionListener {

    public static void main(String[] args) {
        new stopwatch();
    }

    Frame f = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int milliseconds = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String milliseconds_string = String.format("%02d", milliseconds);
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(0, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime += 1000;
            hours = (elapsedTime / 216000000) % 60;
            minutes = (elapsedTime / 3600000) % 60;
            seconds = (elapsedTime / 60000) % 60;
            milliseconds = (elapsedTime / 1000) % 60;

            milliseconds_string = String.format("%02d", milliseconds);
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);

            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string + ":" + milliseconds_string);

        }

    });

    stopwatch() {

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string + ":" + milliseconds_string);
        timeLabel.setBounds(140, 90, 200, 100);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(140, 200, 100, 50);
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(240, 200, 100, 50);
        resetButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        f.setTitle("Stopwatch");
        f.setSize(500, 350);
        f.setResizable(false);
        f.setLayout(null);

        f.add(timeLabel);
        f.add(startButton);
        f.add(resetButton);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {

            if (started == false) {
                started = true;
                startButton.setText("STOP");
                start();
            } else {
                started = false;
                startButton.setText("START");
                stop();
            }

        }
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("START");
            reset();
        }

    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();

        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string + ":" + milliseconds_string);
    }

}