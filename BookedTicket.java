public class BookedTicket {

    private String moviename;
    private String Date;
    private String Time;
    private String NumberOfTicket;
    private String MovieID;
    public BookedTicket(String moviename, String Date, String Time, String MovieID, String NumberOfTicket) {
        this.moviename = moviename;
        this.Date = Date;
        this.Time = Time;
        this.NumberOfTicket = NumberOfTicket;
        this.MovieID = MovieID;
    }

    public String getMoviename() {
        return moviename;
    }
    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getDate() {

        return Date;
    }

    public void setDate(String Date) {

        this.Date = Date;
    }
    public String getTime() {
        return Time;
    }
    public void setTime(String Time) {

        this.Time = Time;
    }
    public String getNumberOfTicket() {

        return NumberOfTicket;
    }
    public void setNumberOfTicket(String NumberOfTicket) {

        this.NumberOfTicket = NumberOfTicket;
    }
    public String getMovieID() {
        return MovieID;
    }
    public void setMovieID(String MovieID) {
        this.MovieID = MovieID;
    }
    public void addTickets(String tickets){
        int a = Integer.parseInt(tickets);
        int b = Integer.parseInt(NumberOfTicket);
        int c = a+b;
        NumberOfTicket =String.valueOf(c);

    }


}
