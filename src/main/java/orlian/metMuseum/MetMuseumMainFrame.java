package orlian.metMuseum;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class MetMuseumMainFrame extends JFrame {

    ArrayList<String> label;
    ArrayList<String> id;
    JList list;
    String element;
    private String firstObject;

    public MetMuseumMainFrame() {

        setTitle("Metropolitan Museum");
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        label = new ArrayList<>();
        list = new JList();
        id = new ArrayList<>();

        MetMuseumService service = new MetMuseumServiceFactory().getInstance();
        MetMuseumController controller = new MetMuseumController(service);
        controller.requestDepartmentsData(list);

        JButton button = new JButton("Explore");
        JScrollPane pane = new JScrollPane(list);

        DefaultListSelectionModel m = new DefaultListSelectionModel();
        m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m.setLeadAnchorNotificationEnabled(false);
        list.setSelectionModel(m);

        button.addActionListener(actionEvent -> {
            int selected[] = list.getSelectedIndices();
            // Print Name of Selected Department
            for (int i = 0; i < selected.length; i++) {
                element = (String) list.getModel().getElementAt(
                        selected[i]);
                System.out.println("  " + element);
                new MetMuseumSubFrame(controller,  element, this);
            }
        });


        add(pane, BorderLayout.NORTH);
        add(button, BorderLayout.SOUTH);


    }

    public void setFirstObject(String firstObject) {
        this.firstObject = firstObject;
    }
    public String getFirstObject() {
        return this.firstObject;
    }


    public static void main(String argv[]) throws IOException {
        MetMuseumMainFrame frame = new MetMuseumMainFrame();
        frame.setVisible(true);

    }


}

