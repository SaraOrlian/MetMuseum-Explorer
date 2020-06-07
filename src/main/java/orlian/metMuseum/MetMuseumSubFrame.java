package orlian.metMuseum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

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
    ArrayList<Integer> objectIDlist;


    public MetMuseumSubFrame(String element, MetMuseumMainFrame frame, JList list, HashMap<String, Integer> departmentsInfo) {


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        objectIDlist = new ArrayList<Integer>();

        MetMuseumService service = new MetMuseumServiceFactory().getInstance();
        MetMuseumArticleController articleController = new MetMuseumArticleController(service
                                                                                        , name
                                                                                        , title
                                                                                        , imageLabel
                                                                                        , this
                                                                                        , objectIDlist
                                                                                        , number);
        MetMuseumDepartmentsSingleController controller = new MetMuseumDepartmentsSingleController(service
                                                                                                    , element
                                                                                                    , list
                                                                                                    , name
                                                                                                    , title
                                                                                                    , imageLabel
                                                                                                    , frame
                                                                                                    , this
                                                                                                    , articleController
                                                                                                    , objectIDlist
                                                                                                    , number
                                                                                                    , outOf
                                                                                                    , departmentsInfo);

        setCounter(0);
        controller.requestDepartmentSingleData();


        rightButton.addActionListener(actionEvent -> {
            imageLabel.setText("");

            if(getCounter() < getTotal()) {
                setCounter(getCounter() + 1);
                articleController.requestArticleNames();
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
                articleController.requestArticleNames();
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

