package orlian.metMuseum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MetMuseumSubFrame extends JFrame {

    JLabel name = new JLabel();
    JLabel title = new JLabel();
    JLabel imageLabel = new JLabel();
    JLabel number = new JLabel();
    JLabel outOf = new JLabel();
    private JButton leftButton = new JButton("Left");
    private JButton rightButton = new JButton("Right");
    int counter;
    int total;


    public MetMuseumSubFrame(String element, MetMuseumMainFrame frame, MetMuseumController controller) {

        setTitle("Articles");
        setSize(700, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        setLocation(350, 0);

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        GridLayout gridLayout = new GridLayout();
        BoxLayout boxLayout = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel.setLayout(flowLayout);
        panel2.setLayout(boxLayout);
        panel3.setLayout(gridLayout);
        add(panel, BorderLayout.SOUTH);
        add(panel2, BorderLayout.WEST);
        add(panel3, BorderLayout.EAST);

        panel.add(leftButton);
        panel.add(rightButton);

        panel2.add(title);
        panel2.add(name);
        panel2.add(number);
        panel2.add(outOf);

        panel3.add(imageLabel, BorderLayout.WEST);

        title.setVisible(true);
        name.setVisible(true);
        imageLabel.setVisible(true);
        number.setVisible(true);
        outOf.setVisible(true);

        ArrayList<Integer> objectIDlist = new ArrayList<Integer>();


        setCounter(0);
        controller.requestDepartmentSingleData(element, frame, this, name, title, imageLabel, objectIDlist, outOf, number);


        rightButton.addActionListener(actionEvent -> {
            imageLabel.setText("");

            if(getCounter() < getTotal()) {
                setCounter(getCounter() + 1);
                controller.requestArticleNames(name, title, imageLabel, frame, this, objectIDlist, number);
            } else if(getCounter() >= getTotal()) {
                title.setText("");
                name.setText("You've reached the end");
                number.setText("");
                outOf.setText("");
                imageLabel.setText("");
                imageLabel.setIcon(null);
            }

        });

        leftButton.addActionListener(actionEvent -> {
            imageLabel.setText("");

            if(getCounter() > 0) {
                setCounter(getCounter() - 1);
                controller.requestArticleNames(name, title, imageLabel, frame, this, objectIDlist, number);
            }else if(getCounter() <= 0) {
                title.setText("");
                name.setText("You're back at the beginning");
                number.setText("");
                outOf.setText("");
                imageLabel.setText("");
                imageLabel.setIcon(null);
            }

        });


    }
    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return this.counter;
    }
}

