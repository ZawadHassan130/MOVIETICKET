import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private JButton addMovieButton;
    private JButton removeMovieButton;
    private JButton BackButton;
    private JButton rescheduleShowtimeButton;
    //private JButton manageHallsButton;
    //private JButton manageSeatsButton;

    public AdminFrame() {
        setTitle("Admin Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        panel.setVisible(true);


        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        addMovieButton = new JButton("Add Movie");
        addMovieButton.setBounds(50, 30, 150, 25);
        panel.add(addMovieButton);

        removeMovieButton = new JButton("Remove Movie");
        removeMovieButton.setBounds(50, 70, 150, 25);
        panel.add(removeMovieButton);

        rescheduleShowtimeButton = new JButton("Reschedule Showtime");
        rescheduleShowtimeButton.setBounds(50, 110, 150, 25);
        panel.add(rescheduleShowtimeButton);



        // Add action listeners to buttons to open corresponding frames
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddMovieFrame addMovieFrame = new AddMovieFrame();
                addMovieFrame.setVisible(true);
            }
        });

        removeMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to open Remove Movie Frame
                // For now, just showing a message dialog
                dispose();
                JOptionPane.showMessageDialog(null, "Remove Movie Frame will be opened.");
                RemoveMovieFrame removeMovieFrame = new RemoveMovieFrame();
            }
        });

        rescheduleShowtimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to open Reschedule Showtime Frame
                // For now, just showing a message dialog
                JOptionPane.showMessageDialog(null, "Reschedule Showtime Frame will be opened.");
                dispose();
                RescheduleFrame rescheduleFrame = new RescheduleFrame();
                rescheduleFrame.setVisible(true);
            }
        });

        BackButton = new JButton("Back");
        BackButton.setBounds(50,150,150,25);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
        panel.add(BackButton);


    }
}

