package Model;

/**
 * Created by ahmed on 05/05/18.
 */

public class Comment {

    private String Patient_name ;
    private String comment;


    public String getPatient_name() {
        return Patient_name;
    }

    public void setPatient_name(String patient_name) {
        Patient_name = patient_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
