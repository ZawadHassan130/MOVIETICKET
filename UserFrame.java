import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame {
    private JButton bookTicketButton;
    private JButton back;

    public UserFrame() {
        setTitle("User Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        bookTicketButton = new JButton("Book Ticket");
        bookTicketButton.setBounds(75, 50, 150, 25);
        panel.add(bookTicketButton);

        bookTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookTicketFrame bookTicketFrame = new BookTicketFrame();
                bookTicketFrame.setVisible(true);
                dispose();
            }
        });

        back = new JButton("Back");
        back.setBounds(75, 90, 150, 25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

            }
        });
        panel.add(back);

        JButton BookedTicketButton = new JButton("Booked Tickets");
        BookedTicketButton.setBounds(75, 120, 150, 25);
        BookedTicketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                BookedTicketFrame bookedTicketFrame = new BookedTicketFrame();
                bookedTicketFrame.setVisible(true);
            }
        });
        panel.add(BookedTicketButton);
    }
}