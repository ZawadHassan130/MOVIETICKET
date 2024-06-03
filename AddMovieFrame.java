import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class AddMovieFrame extends JFrame {
    private JTextField movieNameField;
    private JTextField durationField;
    private JTextField genreField;
    private JTextField time;
    private JTextField date;
    private JTextField availableseats;
    private JTextField movieId;
    private JButton addButton;
    private JButton BackButton;
    private ArrayList<Movie> movieList;


    public AddMovieFrame() {

        DataRetriver dataRetriver = new DataRetriver();
        movieList = dataRetriver.objectcreation();


        setTitle("Add Movie");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        BackButton = new JButton("Back");
        BackButton.setBounds(130, 260, 160, 25);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

            }
        });
        panel.add(BackButton);
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Movie Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        movieNameField = new JTextField(20);
        movieNameField.setBounds(130, 20, 160, 25);
        panel.add(movieNameField);

        JLabel durationLabel = new JLabel("Duration:");
        durationLabel.setBounds(10, 50, 80, 25);
        panel.add(durationLabel);

        durationField = new JTextField(20);
        durationField.setBounds(130, 50, 160, 25);
        panel.add(durationField);

        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(10, 80, 80, 25);
        panel.add(genreLabel);

        genreField = new JTextField(20);
        genreField.setBounds(130, 80, 160, 25);
        panel.add(genreField);

        JLabel AvailableSeatsLabel = new JLabel("Available Seats:");
        AvailableSeatsLabel.setBounds(10, 110, 120, 25);
        panel.add(AvailableSeatsLabel);

        availableseats = new JTextField(20);
        availableseats.setBounds(130, 110, 160, 25);
        panel.add(availableseats);

        JLabel DateLabel = new JLabel("Date:");
        DateLabel.setBounds(10, 140, 80, 25);
        panel.add(DateLabel);

        date = new JTextField(20);
        date.setBounds(130, 140, 160, 25);
        panel.add(date);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(10, 170, 80, 25);
        panel.add(timeLabel);

        time = new JTextField(20);
        time.setBounds(130, 170, 160, 25);
        panel.add(time);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 200, 80, 25);
        panel.add(idLabel);

        movieId = new JTextField(20);
        movieId.setBounds(130, 200, 160, 25);
        panel.add(movieId);


        addButton = new JButton("Add Movie");
        addButton.setBounds(130, 230, 160, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = movieNameField.getText();
                String genre = genreField.getText();
                String duration = durationField.getText();
                String availableSeats = availableseats.getText();
                String Date = date.getText();
                String Time = time.getText();
                String id = movieId.getText();

                DataRetriver dataRetriver2 = new DataRetriver();
                movieList = dataRetriver2.objectcreation();

                /*if (authenticateInformtion(movieName, genre, duration, availableSeats, Date, Time, id)) {
                    if (authenticatenameAndGenre(movieName, genre)) {
                        if (authenticateDate(Date) && authenticateTime(Time)) {
                            if (authenticateID(id)) {
                                if(Integer.parseInt(availableSeats)<=100 && Integer.parseInt(availableSeats)>0) {
                                    JOptionPane.showMessageDialog(null, "Movie has been added successfully");
                                    DataWriter dataWriter = new DataWriter(movieName, genre, duration, availableSeats, Date, Time, id);
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "Seats needs to be greater than 0 and less or equaal to 100");
                                }

                            else
                                JOptionPane.showMessageDialog(null, "Given ID Already Exists");
                            // Logic to add the movie to the database or a list
                            }
                        else
                            JOptionPane.showMessageDialog(null, "Incorrect date or time syntax or entered");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Use other symbol to separate different words other than comma");
                }
                else
                    JOptionPane.showMessageDialog(null, "Please Provide All The Information");*/

                try {


                    if (!authenticateInformtion(movieName, genre, duration, availableSeats, Date, Time, id)) {

                        throw new InvalidDataException("Please Give All Information");
                    }

                    if (!authenticatenameAndGenre(movieName, genre)) {
                        throw new InvalidDataException("Name and genre cannot contain commas.");
                    }
                    // Check for valid date and time format
                    if (!(authenticateDate(Date) && authenticateTime(Time))) {
                        throw new InvalidDataException("Invalid date or time format.");
                    }
                    // Check for duplicate ID
                    if (!authenticateID(id)) {
                        throw new InvalidDataException("Movie ID already exists.");
                    }
                    // Check for valid number of available seats
                    if (Integer.parseInt(availableSeats) > 100 || Integer.parseInt(availableSeats) <= 0) {
                        throw new InvalidDataException("Available seats must be between 1 and 100.");
                    }

                    // If all checks pass, add movie logic
                    JOptionPane.showMessageDialog(null, "Movie has been added successfully");
                    DataWriter dataWriter = new DataWriter(movieName, genre, duration, availableSeats, Date, Time, id);


                } catch (InvalidDataException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }



            }
        });
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
            else if (Integer.parseInt(dateParts[1])==2 && Integer.parseInt(dateParts[0]) <= 28 && Integer.parseInt(dateParts[0]) > 0 && Integer.parseInt(dateParts[2]) % 4 != 0) {
                return true;
            }
            else if (Integer.parseInt(dateParts[1])==2 && Integer.parseInt(dateParts[0]) <= 29 && Integer.parseInt(dateParts[0]) > 0 && Integer.parseInt(dateParts[2]) % 4 == 0) {
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

    public boolean authenticateID(String id) {
        boolean checking=true;
        for (Movie movie : movieList) {
            if (id.equals(movie.getMovieid())) {
                return checking = false;
            }
        }
        return checking;

    }
    public boolean authenticateInformtion(String name ,String genre, String duration, String availableSeats, String Date, String Time, String id ) {

        return !(name.isEmpty() || genre.isEmpty() || duration.isEmpty() || availableSeats.isEmpty() || Date.isEmpty() || Time.isEmpty() || id.isEmpty());


    }
    public boolean authenticatenameAndGenre(String name, String genre){

        String [] string1=name.trim().split(",");
        String [] string2=genre.trim().split(",");
        return string1.length == 1 && string2.length == 1;
    }
}