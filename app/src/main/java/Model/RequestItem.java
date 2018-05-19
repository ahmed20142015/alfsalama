package Model;

/**
 * Created by ahmed on 07/02/18.
 */

public class RequestItem {
/*
"ID":80
,"Service_providor_branch_id":1
,"Patient_id":1
,"Status_Name":"\u062A\u0645 \u0637\u0644\u0628 \u0627\u0644\u062E\u062F\u0645\u0629"
,"Money_paid":0
,"Name_Of_Other_person":"ahmed"
,"Mobile_Of_Other_Person":1122287793
 */

    private int ID;
    private int BranchID;
    private int PatientID;
    private String StatusName;
    private String MoneyPaid;
    private  String NameOfOtherPerson ;
    private String MobileOfOtherPerson ;
    private String Date ;
    private  String RatingFalg;
    private  String branchName;


    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int patientID) {
        PatientID = patientID;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getRatingFalg() {
        return RatingFalg;
    }

    public void setRatingFalg(String ratingFalg) {
        RatingFalg = ratingFalg;
    }

    public String getMoneyPaid() {
        return MoneyPaid;
    }

    public void setMoneyPaid(String moneyPaid) {
        MoneyPaid = moneyPaid;
    }

    public String getNameOfOtherPerson() {
        return NameOfOtherPerson;
    }

    public void setNameOfOtherPerson(String nameOfOtherPerson) {
        NameOfOtherPerson = nameOfOtherPerson;
    }

    public String getMobileOfOtherPerson() {
        return MobileOfOtherPerson;
    }

    public void setMobileOfOtherPerson(String mobileOfOtherPerson) {
        MobileOfOtherPerson = mobileOfOtherPerson;
    }
}
