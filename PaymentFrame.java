import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PaymentFrame extends JFrame implements ActionListener {
    private JLabel AvailableTickets;
    private JTextField cardNameField;


    private JComboBox <String> bookedtickets;
    private JComboBox <String>Bill;

    private JButton payButton;
    private JButton Back = new JButton("Back");


    private ArrayList<Movie>movies;


    private String id;
    private String TotalTickets;
    private String MovieName,MovieDate,MovieTime;



    public PaymentFrame(ArrayList<Movie> movies,String MovieName,String MovieDate,String MovieTime,String id) {
        this.movies = movies;
        this.MovieName = MovieName;
        this.MovieDate = MovieDate;
        this.MovieTime = MovieTime;
        this.id = id;


        for (Movie m : movies) {
            if (m.getName().equals(MovieName) && m.getDate().equals(MovieDate) && m.getTime().equals(MovieTime) && m.getMovieid().equals(id)) {
                AvailableTickets=new JLabel("Available TICKETS :"+m.getSeats());
                TotalTickets = m.getSeats();


                break;
            }
        }


        setTitle("Payment");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);




        bookedtickets = new JComboBox<>( new String[] {"1", "2", "3", "4", "5", "6"} );
        Bill = new JComboBox<>();
        Bill.setEnabled(false);



        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        AvailableTickets.setBounds(10,20,220,25 );
        panel.add(AvailableTickets);




        JLabel cardNameLabel = new JLabel("Card Name:");
        cardNameLabel.setBounds(10, 60, 100, 25);
        panel.add(cardNameLabel);


        cardNameField = new JTextField(20);
        cardNameField.setBounds(120, 60, 160, 25);
        panel.add(cardNameField);



        JLabel numberofbookings = new JLabel("Number Of Tickets:");
        numberofbookings.setBounds(10, 100, 100, 25);
        panel.add(numberofbookings);


        bookedtickets.setBounds(120,100,160,25);
        bookedtickets.addActionListener(this::actionPerformed);
        panel.add(bookedtickets);




        JLabel TotalBill = new JLabel("Total Bill:");
        TotalBill.setBounds(10, 140, 100, 25);
        panel.add(TotalBill);



        Bill.setBounds(120,140,160,25);
        panel.add(Bill);




        payButton = new JButton("Pay");
        payButton.setBounds(120, 180, 160, 25);
        panel.add(payButton);



        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Payment processing logic
                try{

                    if(!authenticatePayment((String)bookedtickets.getSelectedItem())){

                        throw new InvalidPayment();
                    }
                    JOptionPane.showMessageDialog(null,"Payment Successful");
                    DataOverwrite dataOverwrite = new DataOverwrite(MovieName,MovieDate,MovieTime,(String) bookedtickets.getSelectedItem(),id);
                    DataWriter dataWriter = new DataWriter(MovieName,MovieDate,MovieTime,id,(String) bookedtickets.getSelectedItem());
                    dispose();
                    BookTicketFrame bookTicketFrame = new BookTicketFrame();
                    bookTicketFrame.setVisible(true);



                }catch(InvalidPayment ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage()+"We are out of tickets");
                }


            }
                  /*if(authenticatePayment((String)bookedtickets.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Payment successful. Booking confirmed.");
                    dispose();
                    DataOverwrite dataOverwrite = new DataOverwrite(MovieName,MovieDate,MovieTime,(String) bookedtickets.getSelectedItem(),id);
                    DataWriter dataWriter = new DataWriter(MovieName,MovieDate,MovieTime,id,(String) bookedtickets.getSelectedItem());
                    BookTicketFrame bookTicketFrame = new BookTicketFrame();
                    bookTicketFrame.setVisible(true);

                }
                else{
                    JOptionPane.showMessageDialog(null, "Sorry We Dont have booked tickets. Please try again.");

                }*/
        });



        Back.setBounds(120,210,160,25);
        panel.add(Back);



        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookTicketFrame bookTicketFrame = new BookTicketFrame();
                bookTicketFrame.setVisible(true);
                dispose();

            }
        });
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== bookedtickets) {
            String tickets =(String) bookedtickets.getSelectedItem();
            Bill.removeAllItems();
            Bill.addItem(calculateBill(tickets));
            Bill.setEnabled(true);
        }
    }




    public String calculateBill(String numberoftickets){
        int a;
        a=Integer.parseInt(numberoftickets)*300;
        return String.valueOf(a);
    }




    public boolean authenticatePayment(String numberoftickets){
        if(Integer.parseInt(numberoftickets)>Integer.parseInt(TotalTickets))
            return false;
        else
            return true;

    }




}