import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RescheduleFrame extends JFrame {

    private JTextField Date;
    private JTextField Time;
    private JComboBox <String> movienamecombobox;
    private JComboBox <String> datecombobox;
    private JComboBox <String> timecombobox;
    private JComboBox <String> movieidcombobox;
    public RescheduleFrame() {

        setTitle("Reschedule");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        setcomponents(panel);
        panel.setVisible(true);

        add(panel);





    }

    public void setcomponents(JPanel panel) {
        panel.setLayout(null);

        JLabel movienameLabel= new JLabel("Movie Name [ID]");
        movienameLabel.setBounds(10, 20, 120, 25);
        panel.add(movienameLabel);

        DataRetriver dataRetriver = new DataRetriver();
        ArrayList<Movie>movies = new ArrayList<>();
        movies = dataRetriver.objectcreation();


        movienamecombobox = new JComboBox<>();
        movienamecombobox.setBounds(140, 20, 140, 25);
        for (Movie movie : movies) {
            movienamecombobox.addItem(movie.getName()+"["+movie.getMovieid()+"]");
        }
        movienamecombobox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 if(e.getSource() == movienamecombobox) {
                     int index = movienamecombobox.getSelectedIndex();

                     updatedCombobox(index);

                 }

            }
        });
        panel.add(movienamecombobox);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(10, 60, 80, 25);
        panel.add(dateLabel);

        datecombobox = new JComboBox<>();
        datecombobox.setBounds(140, 60, 140, 25);
        datecombobox.setEnabled(false);
        panel.add(datecombobox);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setBounds(10, 100, 80, 25);
        panel.add(timeLabel);

        timecombobox = new JComboBox<>();
        timecombobox.setBounds(140, 100, 140, 25);
        timecombobox.setEnabled(false);
        panel.add(timecombobox);

        JLabel MovieIDLabel = new JLabel("Movie ID");
        MovieIDLabel.setBounds(10, 140, 90, 25);
        panel.add(MovieIDLabel);

        movieidcombobox = new JComboBox<>();
        movieidcombobox.setBounds(140, 140, 140, 25);
        movieidcombobox.setEnabled(false);
        panel.add(movieidcombobox);



        Date = new JTextField();
        Date.setBounds(100, 180, 160, 25);
        panel.add(Date);

        JLabel updateddate = new JLabel("Updated Date");
        updateddate.setBounds(10, 180, 80, 25);
        panel.add(updateddate);

        JLabel updatedtime = new JLabel("Updated Time");
        updatedtime.setBounds(10, 220, 80, 25);
        panel.add(updatedtime);

        Time = new JTextField();
        Time.setBounds(100, 220, 160, 25);
        panel.add(Time);

        JButton RescheduleButton = new JButton("UPDATE");
        RescheduleButton.setBounds(100,260,160,25);
        RescheduleButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(authenticateDate(Date.getText()) && authenticateTime(Time.getText())) {
                DataOverwrite dataOverwrite = new DataOverwrite(Date.getText(),Time.getText(),movieidcombobox.getSelectedItem().toString());


                DataRetriver dataRetriver = new DataRetriver();
                ArrayList<BookedTicket>bookedTickets = new ArrayList<>();
                bookedTickets = dataRetriver.objectsofTickets();



                ArrayList<String>updatedBookticket = new ArrayList<>();

                for (BookedTicket bookedTicket : bookedTickets) {

                    if(bookedTicket.getMovieID().equals(movieidcombobox.getSelectedItem().toString())){

                        updatedBookticket.add(bookedTicket.getMoviename()+","+Date.getText()+","+Time.getText()+","+bookedTicket.getMovieID()+","+bookedTicket.getNumberOfTicket());

                    }
                    else
                        updatedBookticket.add(bookedTicket.getMoviename()+","+bookedTicket.getDate()+","+bookedTicket.getTime()+","+bookedTicket.getMovieID()+","+bookedTicket.getNumberOfTicket());
                }
                try(PrintWriter pr = new PrintWriter(new File("C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\BookedTickets"))) {

                    for(String string : updatedBookticket) {
                        pr.println(string);
                    }
                    pr.close();
                }catch(FileNotFoundException ex) {

                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"Scheduled Updated Succcessfully");
                dispose();
                RescheduleFrame rescheduleFrame = new RescheduleFrame();
                rescheduleFrame.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null,"Invalid Date or Time Syntax");
            }
        });
        panel.add(RescheduleButton);


        JButton BACKButton = new JButton("BACK");
        BACKButton.setBounds(100,300,160,25);
            BACKButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    AdminFrame adminFrame = new AdminFrame();
                    adminFrame.setVisible(true);
                }

            });

            panel.add(BACKButton);







    }
    public void updatedCombobox(int index){

        ArrayList<Movie>movies = new ArrayList<>();
        DataRetriver dataRetriver = new DataRetriver();
        movies = dataRetriver.objectcreation();

        timecombobox.removeAllItems();
        timecombobox.addItem(movies.get(index).getTime());
        timecombobox.setEnabled(true);

        datecombobox.removeAllItems();
        datecombobox.addItem(movies.get(index).getDate());
        datecombobox.setEnabled(true);

        movieidcombobox.removeAllItems();
        movieidcombobox.addItem(movies.get(index).getMovieid());
        movieidcombobox.setEnabled(true);
    }

    public boolean authenticateDate(String date) {
        String[] dateParts = date.split("/");
        if (dateParts.length == 3) {
            if (Integer.parseInt(dateParts[1])==1 && Integer.parseInt(dateParts[0]) <= 31 && Integer.parseInt(dateParts[0]) > 0) {
                return true;
            }
            else if (Integer.parseInt(dateParts[1])==3 && Integer.parseInt(dateParts[0]) <= 31 && Integer.parseInt(dateParts[0]) > 0) {
                return true;

            }
            else if (Integer.parseInt(dateParts[1])==4 && Integer.parseInt(dateParts[0]) <= 30 && Integer.parseInt(dateParts[0]) > 0) {
                return true;
            }
            else if (Integer.parseInt(dateParts[1])==5 && Integer.parseInt(dateParts[0]) <= 31 && Integer.parseInt(dateParts[0]) > 0) {
                return true;

            }
            else if (Integer.parseInt(dateParts[1])==6 && Integer.parseInt(dateParts[0]) <= 30 && Integer.parseInt(dateParts[0]) > 0) {
                return true;
            }
            else if (Integer.parseInt(dateParts[1])==7 && Integer.parseInt(dateParts[0]) <= 31 && Integer.parseInt(dateParts[0]) > 0) {
                return true;

            }
            else if (Integer.parseInt(dateParts[1])==8 && Integer.parseInt(dateParts[0]) <= 31 && Integer.parseInt(dateParts[0]) > 0) {
                return true;
            }
            else if (Integer.parseInt(dateParts[1])==9 && Integer.parseInt(dateParts[0]) <= 30 && Integer.parseInt(dateParts[0]) > 0) {
                return true;

            }
            else if (Integer.parseInt(dateParts[1])==10 && Integer.parseInt(dateParts[0]) <= 31 && Integer.parseInt(dateParts[0]) > 0) {
                return true;
            }
            else if (Integer.parseInt(dateParts[1])==11 && Integer.parseInt(dateParts[0]) <= 30 && Integer.parseInt(dateParts[0]) > 0) {
                return true;

            }
            else if (Integer.parseInt(dateParts[1])==12 && Integer.parseInt(dateParts[0]) <= 31 && Integer.parseInt(dateParts[0]) > 0) {
                return true;

            }
            else if (Integer.parseInt(dateParts[1])==2  && Integer.parseInt(dateParts[0]) <= 28 && Integer.parseInt(dateParts[0]) > 0 && Integer.parseInt(dateParts[2]) % 4 != 0) {
                return true;
            }
            else if (Integer.parseInt(dateParts[1])==2  && Integer.parseInt(dateParts[0]) <= 29 && Integer.parseInt(dateParts[0]) > 0 && Integer.parseInt(dateParts[2]) % 4 == 0) {
                return true;
            }
            else
                return false;

        }
        else
            return false;
    }

    public boolean authenticateTime(String time) {
        String[] timeParts = time.split(":");
        String hours = timeParts[0];
        String minutes = timeParts[1].trim().split(" ")[0];
        String meridian = timeParts[1].trim().split(" ")[1];
        if (Integer.parseInt(hours) <= 12 && Integer.parseInt(minutes) <= 60 && Integer.parseInt(hours) > 0 && Integer.parseInt(minutes) >= 0 && (meridian.equals("am") || meridian.equals("pm")))
            return true;
        else
            return false;

    }





}
