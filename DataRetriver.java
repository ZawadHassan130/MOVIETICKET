import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.*;
import java.util.HashMap;


public class DataRetriver {
    //public ArrayList<Movie> Movies = new ArrayList<>();
    public DataRetriver() {

    }
    public ArrayList<Movie> objectcreation() {

        ArrayList<Movie> Movies = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\MovieDetails"))) {
            String line;
            while((line=br.readLine()) != null){
                String [] attributes = line.split(",",7);
                Movie movie = new Movie(attributes[0],attributes[1],attributes[2],attributes[3],attributes[4],attributes[5],attributes[6]);
                Movies.add(movie);
            }


        }catch(IOException e){
            e.printStackTrace();
        }
        return Movies;
    }

    public ArrayList<BookedTicket> objectsofTickets() {

        ArrayList<BookedTicket> BookedTickets = new ArrayList<>();
        HashMap<String,BookedTicket> bookedTickets = new HashMap<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\BookedTickets"))){
            String line;
            while ((line = bufferedReader.readLine())!=null){
                String [] attributes = line.split(",");
                String movieId = attributes[3];
                String tickets = attributes[4];
                BookedTicket bookedTickets1 = bookedTickets.get(attributes[3]);
                if(bookedTickets1==null){


                    bookedTickets.put(attributes[3],new BookedTicket(attributes[0],attributes[1],attributes[2],attributes[3],attributes[4]));
                    BookedTickets.add(bookedTickets.get(attributes[3]));
                }
                else
                    bookedTickets1.addTickets(attributes[4]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(PrintWriter printWriter = new PrintWriter(new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\BookedTickets"))){

            for (BookedTicket B : bookedTickets.values()) {
                printWriter.println(B.getMoviename()+","+B.getDate()+","+B.getTime()+","+B.getMovieID()+","+B.getNumberOfTicket());
            }

            printWriter.close();



        }catch (IOException e){
            e.printStackTrace();

        }
        return BookedTickets;
    }




}
