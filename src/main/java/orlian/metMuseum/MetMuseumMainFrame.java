package orlian.metMuseum;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;


public class MetMuseumMainFrame extends JFrame {


    private JList list;
    private String element;
    private HashMap<String, Integer> departmentsInfo;

    public MetMuseumMainFrame() {

        setTitle("Metropolitan Museum");
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        list = new JList();
        departmentsInfo = new HashMap<String, Integer>();

        MetMuseumService service = new MetMuseumServiceFactory().getInstance();
        MetMuseumDepartmentsController controller = new MetMuseumDepartmentsController(service, element, list, departmentsInfo);
        controller.requestDepartmentsData();

        JButton button = new JButton("Explore");
        JScrollPane pane = new JScrollPane(list);

        DefaultListSelectionModel m = new DefaultListSelectionModel();
        m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m.setLeadAnchorNotificationEnabled(false);
        list.setSelectionModel(m);

        button.addActionListener(actionEvent -> {
            int selected[] = list.getSelectedIndices();

            for (int i = 0; i < selected.length; i++) {
                element = (String) list.getModel().getElementAt(
                        selected[i]);
                System.out.println("  " + element);// Print Name of Selected Department

                new MetMuseumSubFrame(element, this, list, departmentsInfo);

            }
        });

        add(pane, BorderLayout.NORTH);
        add(button, BorderLayout.SOUTH);
    }


    public static void main(String argv[]) throws IOException {
        MetMuseumMainFrame frame = new MetMuseumMainFrame();
        frame.setVisible(true);

    }

}

