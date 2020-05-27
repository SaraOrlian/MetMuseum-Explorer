package orlian.metMuseum;

import javax.swing.*;
import java.awt.*;

public class MetMuseumSubFrame extends JFrame {

    JLabel name = new JLabel();
    JLabel title = new JLabel();
    JLabel imageLabel = new JLabel();
    private JButton leftButton = new JButton("Left");
    private JButton rightButton = new JButton("Right");
    private int total;
    int firstOne;
    int lastOne;


    public MetMuseumSubFrame(MetMuseumController controller, String element, MetMuseumMainFrame frame) {

        setTitle("Articles");
        setSize(500, 300);
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
        panel2.add(new JLabel("Hello!"));

        panel3.add(imageLabel, BorderLayout.WEST);

        title.setVisible(true);
        name.setVisible(true);
        imageLabel.setVisible(true);

        controller.requestDepartmentSingleData(element, frame, this,  name, title, imageLabel);
        setFirstOne(Integer.parseInt(frame.getFirstObject()));
        setLastOne(Integer.parseInt(frame.getFirstObject()) + getTotal());

        rightButton.addActionListener(actionEvent -> {
            imageLabel.setText("");
            int ix = Integer.parseInt(frame.getFirstObject());
            System.out.println("Before: " + ix);
            if( ix < getLastOne()) {
                frame.setFirstObject(String.valueOf(ix + 1));
                System.out.println("After: " + frame.getFirstObject());
                controller.requestArticleNames(name, title, imageLabel, frame);
            } else if(ix > getLastOne()) {
                imageLabel.setIcon(null);
                imageLabel.setText("You've looked through them all!");
            }
            });

        leftButton.addActionListener(actionEvent -> {
            imageLabel.setText("");
            int ix = Integer.parseInt(frame.getFirstObject());
            if (ix - getFirstOne() > 0) {
                frame.setFirstObject(String.valueOf(ix - 1));
                controller.requestArticleNames(name, title, imageLabel, frame);
            }else if(ix < getFirstOne()) {
                imageLabel.setIcon(null);
                imageLabel.setText("You're back to the beginning!");
            }
        });

        }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return this.total;
    }

    public void setFirstOne(int firstOne) {
        this.firstOne = firstOne;
    }

    public int getFirstOne() {
        return this.firstOne;
    }

    public void setLastOne(int lastOne) {
        this.lastOne = lastOne;
    }

    public int getLastOne() {
        return this.lastOne;
    }
}

