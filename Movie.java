import java.util.ArrayList;

public class Movie {
    private String name;
    private String genre;
    private String seats;
    private String Duration;
    private String Date;
    private String Time;
    private String movieid;
    public Movie(String name, String genre, String  Duration, String seats,String Date,String Time,String movieid) {
        this.name = name;
        this.genre = genre;
        this.seats = seats;
        this.Duration = Duration;
        this.Date = Date;
        this.Time = Time;
        this.movieid = movieid;
    }

    public String getName() {
        return name;
    }
    public String getGenre() {
        return genre;
    }
    public String getSeats() {
        return seats;

    }
    public String getDuration() {
        return Duration;
    }
    public String getDate() {
        return Date;
    }
    public String getTime() {
        return Time;
    }
    public void setSeats(String seats) {
        this.seats = seats;
    }
    public String getMovieid() {
        return movieid;
    }




}
