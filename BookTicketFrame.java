import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookTicketFrame extends JFrame {
    public ArrayList<Movie>movies=new ArrayList<>();
    public DataRetriver dataRetriver=new DataRetriver();


    private JComboBox<String> movieComboBox;
    private JComboBox <String> dateComboBox;
    private JComboBox <String> timeComboBox;
    private JComboBox <String> genrecombobox;
    public String SelectedMovie;
    public String SelectedDate;
    public String SelectedTime;
    public String Selectedid;

    private JButton bookButton;
    private JButton backButton;
   // public JLabel showDate,showTime;


    public BookTicketFrame() {
        movies=dataRetriver.objectcreation();
        setTitle("Book Ticket");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        placeComponents(panel);
        backButton=new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame loginFrame=new LoginFrame();
                loginFrame.setVisible(true);


            }

        });
        panel.add(backButton);
        add(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel movieLabel = new JLabel("Movie:");
        movieLabel.setBounds(10, 20, 80, 25);
        panel.add(movieLabel);

        movieComboBox = new JComboBox<>();
        movieComboBox.setBounds(100, 20, 160, 25);

        for (Movie movie : movies) {
            movieComboBox.addItem(movie.getName()+"["+movie.getMovieid()+"]");
        }
        movieComboBox.addActionListener(this::actionPerformed);

        panel.add(movieComboBox);

        JLabel GenreLabel = new JLabel("Genre:");
        GenreLabel.setBounds(10, 60, 80, 25);
        panel.add(GenreLabel);

        genrecombobox = new JComboBox<>();
        genrecombobox.setBounds(100, 60, 160, 25);
        genrecombobox.setEnabled(false);
        panel.add(genrecombobox);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(10, 100, 80, 25);
        panel.add(dateLabel);
        //JLabel showDate = new JLabel(" ");
        //panel.add(showDate);

        dateComboBox = new JComboBox<>();
        dateComboBox.setBounds(100, 100, 160, 25);
        dateComboBox.setEnabled(false);
        panel.add(dateComboBox);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(10, 140, 80, 25);
        panel.add(timeLabel);

        timeComboBox = new JComboBox<>();
        timeComboBox.setBounds(100, 140, 160, 25);
        timeComboBox.setEnabled(false);
        panel.add(timeComboBox);


        bookButton = new JButton("Book Ticket");
        bookButton.setBounds(100, 180, 160, 25);
        panel.add(bookButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 210, 160, 25);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                UserFrame userFrame=new UserFrame();
                userFrame.setVisible(true);
            }
        });

        panel.add(backButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*(String movie = (String) movieComboBox.getSelectedItem();
                String selectedMovie=movieComboBox.getSelectedItem().toString();
                String selectedDate=dateComboBox.getSelectedItem().toString();
                String selectedTime=timeComboBox.getSelectedItem().toString();
                ArrayList<Movie> movieList=dataRetriver.objectcreation();*/


                // Logic to book the ticket
                JOptionPane.showMessageDialog(null, "Ticket booked successfully");

                // Open PaymentFrame after booking
                PaymentFrame paymentFrame = new PaymentFrame(movies,SelectedMovie, SelectedDate,SelectedTime,Selectedid);
                paymentFrame.setVisible(true);
                dispose();
            }
        });
    }
    /*private void updateRelatedData(String selectedMovie){
        for(Movie movie:movies){
            if(movie.getName().equals(selectedMovie)){
                showTime.setText(movie.getTime());
                showDate.setText(movie.getDate());
            }
        }
    }*/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == movieComboBox) {
            int index=movieComboBox.getSelectedIndex();
            updateDateComboBox(index);
        }
    }
    private void updateDateComboBox(int i) {
                SelectedDate = movies.get(i).getDate();
                SelectedTime = movies.get(i).getTime();
                SelectedMovie = movies.get(i).getName();
                Selectedid =   movies.get(i).getMovieid();
                dateComboBox.removeAllItems();
                timeComboBox.removeAllItems();
                genrecombobox.removeAllItems();
                dateComboBox.addItem(movies.get(i).getDate());
                dateComboBox.setEnabled(true);
                timeComboBox.addItem(movies.get(i).getTime());
                timeComboBox.setEnabled(true);
                genrecombobox.addItem(movies.get(i).getGenre());
                genrecombobox.setEnabled(true);
                System.out.println(movies.get(i).getDate());
                System.out.println(movies.get(i).getTime());
        }

    }


