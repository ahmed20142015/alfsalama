package Model;

/**
 * Created by ahmed on 27/02/18.
 */

public class City {
    private int id;
    private int Gov_id;
    private String cityName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGov_id() {
        return Gov_id;
    }

    public void setGov_id(int gov_id) {
        Gov_id = gov_id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
