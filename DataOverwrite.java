import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class DataOverwrite {
    private String Moviename;
    private String  Date;
    private String Time;
    private String BookedTicket;
    private String ID;
    public DataOverwrite(String Moviename, String Date, String Time, String BookedTicket,String ID) {
        this.Moviename = Moviename;
        this.Date = Date;
        this.Time = Time;
        this.BookedTicket = BookedTicket;
        this.ID = ID;
        ArrayList<Movie> movies2 = new ArrayList<>();
        DataRetriver infoRetriver = new DataRetriver();
        movies2=infoRetriver.objectcreation();
        ArrayList<String>strings=new ArrayList<>();
        for (Movie movie : movies2) {
         if(movie.getName().equals(Moviename) && movie.getDate().equals(Date) && movie.getTime().equals(Time) && movie.getMovieid().equals(ID)){

                int a = Integer.parseInt(movie.getSeats());
                int b= Integer.parseInt(BookedTicket);
                int updated = a-b;

                movie.setSeats(String.valueOf(updated));

                strings.add(movie.getName()+","+movie.getGenre()+","+movie.getDuration()+","+movie.getSeats()+","+movie.getDate()+","+movie.getTime()+","+movie.getMovieid());

         }

         else

             strings.add(movie.getName()+","+movie.getGenre()+","+movie.getDuration()+","+movie.getSeats()+","+movie.getDate()+","+movie.getTime()+","+movie.getMovieid());

        }


        try(PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\MovieDetails"))){
            for (String string : strings) {
            pw.println(string);


            }

            pw.close();

        }
        catch (IOException x){

            x.printStackTrace();
        }





    }
    public DataOverwrite(String Date, String Time, String ID){

        this.Date = Date;
        this.Time = Time;
        this.ID = ID;

        ArrayList<Movie> movies2 = new ArrayList<>();
        DataRetriver infoRetriver = new DataRetriver();
        movies2 = infoRetriver.objectcreation();
        ArrayList<String>strings=new ArrayList<>();

        for (Movie movie : movies2) {
            if(movie.getMovieid().equals(ID))
                strings.add(movie.getName()+","+movie.getGenre()+","+movie.getDuration()+","+movie.getSeats()+","+Date+","+Time+","+movie.getMovieid());
            else
                strings.add(movie.getName()+","+movie.getGenre()+","+movie.getDuration()+","+movie.getSeats()+","+movie.getDate()+","+movie.getTime()+","+movie.getMovieid());
        }
        try(PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\MovieDetails"))){
            for (String string : strings) {
                pw.println(string);


            }

            pw.close();

        }
        catch (IOException x){

            x.printStackTrace();
        }
    }

}





