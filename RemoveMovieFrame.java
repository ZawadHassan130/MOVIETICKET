import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RemoveMovieFrame extends javax.swing.JFrame {

    private int n;
    private ArrayList<Movie> movies;

    JComboBox <String> moviename;
    JComboBox <String> datecombo;
    JComboBox <String> timecombo;
    JComboBox <String> movieid;



    public RemoveMovieFrame() {

        //Settiing up the frame.

        setTitle("Remove Movie");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(300,300);


        DataRetriver dataRetriver = new DataRetriver();

        movies=new ArrayList<>();


        movies = dataRetriver.objectcreation();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);


        moviename = new JComboBox();
        moviename.setBounds(100,20,160,25);

        for(Movie movie:movies){
            moviename.addItem(movie.getName()+"["+movie.getMovieid()+"]");
        }
        panel.add(moviename);


        JLabel movienamelabel = new JLabel("Movie Name:");
        movienamelabel.setBounds(10,20,80,25);
        panel.add(movienamelabel);

        JLabel date = new JLabel("Date:");
        date.setBounds(10,60,80,25);
        panel.add(date);

        datecombo = new JComboBox();
        datecombo.setBounds(100,60,160,25);
        datecombo.setEnabled(false);
        panel.add(datecombo);


        JLabel time = new JLabel("Time:");
        time.setBounds(10,100,80,25);
        panel.add(time);


        timecombo = new JComboBox();
        timecombo.setBounds(100,100,160,25);
        timecombo.setEnabled(false);
        panel.add(timecombo);

        JLabel movieidlabel = new JLabel("MovieID:");
        movieidlabel.setBounds(10,140,80,25);
        panel.add(movieidlabel);


        movieid = new JComboBox();
        movieid.setBounds(100,140,160,25);
        movieid.setEnabled(false);
        panel.add(movieid);


        moviename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==moviename){
                     n=moviename.getSelectedIndex();
                     updatedcombobox(n);


                }

            }
        });

        JButton back = new JButton("Back");
        back.setBounds(100,220,80,25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                AdminFrame adminFrame = new AdminFrame();
                adminFrame.setVisible(true);

            }
        });
        panel.add(back);


        JButton RemoveMovieButton = new JButton("Remove Movie");
        RemoveMovieButton.setBounds(100,180,80,25);
        RemoveMovieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<String>strings = new ArrayList<>();
                try{
                    ArrayList<String> string = new ArrayList<>();
                    DataRetriver dataRetriver = new DataRetriver();
                    for(Movie movie:movies){
                        if(!movieid.getSelectedItem().equals(movie.getMovieid())){
                            strings.add(movie.getName()+","+movie.getGenre()+","+movie.getDuration()+","+movie.getSeats()+","+movie.getDate()+","+movie.getTime()+","+movie.getMovieid());
                        }
                    }

                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

                try(PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\MovieDetails"))){

                    for(String string:strings){
                        pw.println(string);
                    }
                    pw.close();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

                ArrayList<BookedTicket> bookedtickets = new ArrayList<>();
                bookedtickets = dataRetriver.objectsofTickets();
                ArrayList<String>strings2 = new ArrayList<>();
                for(BookedTicket bookedticket:bookedtickets){
                    if(!movieid.getSelectedItem().equals(bookedticket.getMovieID())){
                        strings2.add(bookedticket.getMoviename()+","+bookedticket.getDate()+","+bookedticket.getTime()+","+bookedticket.getMovieID()+","+bookedticket.getNumberOfTicket());
                    }
                }
                try(PrintWriter printWriter = new PrintWriter(new FileWriter("C:\\Users\\User\\IdeaProjects\\MovieManagement2\\src\\BookedTickets"))){

                    for(String string:strings2){
                        printWriter.println(string);
                    }
                    printWriter.close();



                }catch (IOException ex){

                    ex.printStackTrace();
                }











                JOptionPane.showMessageDialog(null,"Movie removed Successfully");
                dispose();
                RemoveMovieFrame removeMovieFrame = new RemoveMovieFrame();


            }
        });
        panel.add(RemoveMovieButton);


        panel.setVisible(true);
    }

    public void updatedcombobox(int n){
        timecombo.removeAllItems();
        timecombo.addItem(movies.get(n).getTime());
        timecombo.setEnabled(true);

        datecombo.removeAllItems();
        datecombo.addItem(movies.get(n).getDate());
        datecombo.setEnabled(true);

        movieid.removeAllItems();
        movieid.addItem(movies.get(n).getMovieid());
        movieid.setEnabled(true);



    }

}
