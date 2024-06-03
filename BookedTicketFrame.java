import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookedTicketFrame extends javax.swing.JFrame {

    private JComboBox <String> movienamecombox;
    private JComboBox <String> Datecombobox;
    private JComboBox <String> Timecombobox;
    private JComboBox <String> bookedTicketcombobox;

    public BookedTicketFrame() {

        setTitle("Booked Ticket");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400,400);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setcomponents(panel);
        panel.setVisible(true);
        add(panel);



    }

    public void setcomponents(JPanel panel) {

        ArrayList<BookedTicket> bookedTickets = new ArrayList<>();

        DataRetriver dataRetriver = new DataRetriver();

        bookedTickets = dataRetriver.objectsofTickets();

        movienamecombox = new JComboBox<>();
        for (BookedTicket bookedTicket : bookedTickets) {

            movienamecombox.addItem(bookedTicket.getMoviename()+"["+bookedTicket.getMovieID()+"]");

        }
        movienamecombox.setBounds(110,20,160,25);
        panel.add(movienamecombox);

        JLabel movienamecomboxlabel = new JLabel("Movie name[ID]");
        movienamecomboxlabel.setBounds(10,20,100,25);
        panel.add(movienamecomboxlabel);


        JLabel Datecomboboxlabel = new JLabel("Date");
        Datecomboboxlabel.setBounds(10,50,100,25);
        panel.add(Datecomboboxlabel);

        Datecombobox = new JComboBox<>();
        Datecombobox.setBounds(110,50,160,25);
        Datecombobox.setEnabled(false);
        panel.add(Datecombobox);

        JLabel Timecomboboxlabel = new JLabel("Time");
        Timecomboboxlabel.setBounds(10,80,100,25);
        panel.add(Timecomboboxlabel);

        Timecombobox = new JComboBox<>();
        Timecombobox.setBounds(110,80,160,25);
        Timecombobox.setEnabled(false);
        panel.add(Timecombobox);

        JLabel Bookedticketcomboboxlabel = new JLabel("Booked Ticket");
        Bookedticketcomboboxlabel.setBounds(10,110,100,25);
        panel.add(Bookedticketcomboboxlabel);

        bookedTicketcombobox = new JComboBox<>();
        bookedTicketcombobox.setBounds(110,110,160,25);
        bookedTicketcombobox.setEnabled(false);
        panel.add(bookedTicketcombobox);

        movienamecombox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == movienamecombox) {
                    int i = movienamecombox.getSelectedIndex();
                    updatedcomboboxes(i);

                }
            }
        });

        JButton backbutton = new JButton("Back");
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                UserFrame userframe = new UserFrame();
                userframe.setVisible(true);
            }
        });
        backbutton.setBounds(110,140,100,25);
        panel.add(backbutton);





    }

    public void updatedcomboboxes(int i){

        ArrayList<BookedTicket> bookedTickets = new ArrayList<>();
        DataRetriver dataRetriver = new DataRetriver();

        bookedTickets = dataRetriver.objectsofTickets();

        Datecombobox.removeAllItems();
        Datecombobox.addItem(bookedTickets.get(i).getDate());
        Datecombobox.setEnabled(true);

        Timecombobox.removeAllItems();
        Timecombobox.addItem(bookedTickets.get(i).getTime());
        Timecombobox.setEnabled(true);

        bookedTicketcombobox.removeAllItems();
        bookedTicketcombobox.addItem(bookedTickets.get(i).getNumberOfTicket());
        bookedTicketcombobox.setEnabled(true);


    }
}
