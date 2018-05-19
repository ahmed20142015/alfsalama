package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmed on 31/12/17.
 */

public class ServiceProidveritem implements Parcelable {

    private String SP_Name;
    private String Subtitle;
    private String Distance;
    private int Rating;
    private String ImageURl;
    private  int SP_ID ;
    private int GOVERNORATE_ID ;
    private int CITY_ID;
    private int AREA_ID;
    private int StreetID;
    private  int BRANCH_ID;
    private String cordX ;
    private String cordY ;
    private float overallRating;


    public static final Parcelable.Creator<ServiceProidveritem> CREATOR = new Creator<ServiceProidveritem>() {
        public ServiceProidveritem createFromParcel(Parcel source) {
            ServiceProidveritem mServiceProidveritem = new ServiceProidveritem();


            mServiceProidveritem.Rating = source.readInt();
            mServiceProidveritem.SP_ID = source.readInt();
            mServiceProidveritem.GOVERNORATE_ID = source.readInt();
            mServiceProidveritem.CITY_ID = source.readInt();
            mServiceProidveritem.AREA_ID = source.readInt();
            mServiceProidveritem.StreetID = source.readInt();
            mServiceProidveritem.BRANCH_ID = source.readInt();
            mServiceProidveritem.overallRating = source.readFloat();
            mServiceProidveritem.SP_Name = source.readString();
            mServiceProidveritem.Subtitle = source.readString();
            mServiceProidveritem.Distance = source.readString();
            mServiceProidveritem.ImageURl = source.readString();
            mServiceProidveritem.cordX = source.readString();
            mServiceProidveritem.cordY = source.readString();

            return mServiceProidveritem;
        }
        public ServiceProidveritem[] newArray(int size) {
            return new ServiceProidveritem[size];
        }
    };

    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(cordX);
        parcel.writeString(cordY);
        parcel.writeString(ImageURl);
        parcel.writeString(Distance);
        parcel.writeString(Subtitle);
        parcel.writeString(SP_Name);
        parcel.writeInt(BRANCH_ID);
        parcel.writeInt(StreetID);
        parcel.writeInt(AREA_ID);
        parcel.writeInt(CITY_ID);
        parcel.writeInt(GOVERNORATE_ID);
        parcel.writeInt(Rating);
        parcel.writeFloat(overallRating);


    }

    public String getCordX() {
        return cordX;
    }

    public void setCordX(String cordX) {
        this.cordX = cordX;
    }

    public String getCordY() {
        return cordY;
    }

    public void setCordY(String cordY) {
        this.cordY = cordY;
    }

    public float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getImageURl() {
        return ImageURl;
    }

    public void setImageURl(String imageURl) {
        ImageURl = imageURl;
    }

    public int getSP_ID() {
        return SP_ID;
    }

    public void setSP_ID(int SP_ID) {
        this.SP_ID = SP_ID;
    }

    public String getSP_Name() {
        return SP_Name;
    }

    public void setSP_Name(String SP_Name) {
        this.SP_Name = SP_Name;
    }

    public int getGOVERNORATE_ID() {
        return GOVERNORATE_ID;
    }

    public void setGOVERNORATE_ID(int GOVERNORATE_ID) {
        this.GOVERNORATE_ID = GOVERNORATE_ID;
    }

    public int getCITY_ID() {
        return CITY_ID;
    }

    public void setCITY_ID(int CITY_ID) {
        this.CITY_ID = CITY_ID;
    }

    public int getAREA_ID() {
        return AREA_ID;
    }

    public void setAREA_ID(int AREA_ID) {
        this.AREA_ID = AREA_ID;
    }

    public int getStreetID() {
        return StreetID;
    }

    public void setStreetID(int streetID) {
        StreetID = streetID;
    }

    public int getBRANCH_ID() {
        return BRANCH_ID;
    }

    public void setBRANCH_ID(int BRANCH_ID) {
        this.BRANCH_ID = BRANCH_ID;
    }
}
