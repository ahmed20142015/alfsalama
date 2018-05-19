package Model;

/**
 * Created by ahmed on 10/05/18.
 */

public class Rate {


   private int ID;
    private  String PatintNamel;
    private int BookingID;
    private float Rating;

    public float getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPatintNamel() {
        return PatintNamel;
    }

    public void setPatintNamel(String patintNamel) {
        PatintNamel = patintNamel;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }
}
