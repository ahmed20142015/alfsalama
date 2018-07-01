package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmedpc on 23/6/2018.
 */

public class ServiceSupplier {
    @SerializedName("items")
    @Expose
    private List<Supplier> items = null;

    public List<Supplier> getItems() {
        return items;
    }

    public void setItems(List<Supplier> items) {
        this.items = items;
    }

    public static class Supplier{

        @SerializedName("sp_name")
        @Expose
        private String spName;
        @SerializedName("x_cordinate")
        @Expose
        private Double xCordinate;
        @SerializedName("y_cordinate")
        @Expose
        private Double yCordinate;
        @SerializedName("logo_url")
        @Expose
        private String logoUrl;
        @SerializedName("over_all_rating")
        @Expose
        private float overAllRating;
        @SerializedName("opening_hours")
        @Expose
        private String openingHours;

        @SerializedName("category_name")
        @Expose
        private String categoryName;

        public String getSpName() {
            return spName;
        }

        public void setSpName(String spName) {
            this.spName = spName;
        }

        public Double getXCordinate() {
            return xCordinate;
        }

        public void setXCordinate(Double xCordinate) {
            this.xCordinate = xCordinate;
        }

        public Double getYCordinate() {
            return yCordinate;
        }

        public void setYCordinate(Double yCordinate) {
            this.yCordinate = yCordinate;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public float getOverAllRating() {
            return overAllRating;
        }

        public void setOverAllRating(float overAllRating) {
            this.overAllRating = overAllRating;
        }

        public String getOpeningHours() {
            return openingHours;
        }

        public void setOpeningHours(String openingHours) {
            this.openingHours = openingHours;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }


    }
}
