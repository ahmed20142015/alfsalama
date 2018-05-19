package Model;

/**
 * Created by ahmed on 26/01/18.
 */

public class ServiceProivder {

    int SP_ID ;
    String SP_Name;
    int GOVERNORATE_ID ;
    int CITY_ID;
    int AREA_ID;
    int StreetID;
    int BRANCH_ID;


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
