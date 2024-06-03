import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class DataWriter {
    private String moviename;
    private String moviegenre;
    private String movieduration;
    private String movieDate;
    private String movieTime;
    private String  movieseats;
    private String movieid;
    public DataWriter(String a , String b , String c , String d , String e , String f,String g){
        moviename = a;
        moviegenre = b;
        movieduration = c;
        movieseats = d;
        movieDate = e;
        movieTime = f;
        movieid = g;
        String Filename= "C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\MovieDetails";
        try(PrintWriter pw = new PrintWriter(new FileWriter(Filename,true))){
            pw.println(moviename+","+moviegenre+","+movieduration+","+movieseats+","+movieDate+","+movieTime+","+movieid);
            pw.close();

        }catch (IOException x){
            x.printStackTrace();
        }

    }

    public DataWriter(String a, String c , String d , String e,String f){
        moviename = a;
        movieDate=c;
        movieTime=d;
        movieseats=f;
        movieid=e;
        String Filename="C:\\Users\\ASUS\\IdeaProjects\\Final Presentation\\src\\BookedTickets" ;
        try(PrintWriter pw = new PrintWriter(new FileWriter(Filename,true))){
            pw.println(moviename+","+movieDate+","+movieTime+","+movieid+","+movieseats);
            pw.close();

        }catch (IOException x){
            x.printStackTrace();
        }

    }





    }

