package infobite.must.eat.modal.api_modal.vendor_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantCategoryType implements Parcelable
{

    @SerializedName("restaurant_type_id")
    @Expose
    private String restaurantTypeId;
    @SerializedName("restaurant_type_name")
    @Expose
    private String restaurantTypeName;
    @SerializedName("restaurant_type_created_date")
    @Expose
    private String restaurantTypeCreatedDate;
    public final static Parcelable.Creator<RestaurantCategoryType> CREATOR = new Creator<RestaurantCategoryType>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RestaurantCategoryType createFromParcel(Parcel in) {
            return new RestaurantCategoryType(in);
        }

        public RestaurantCategoryType[] newArray(int size) {
            return (new RestaurantCategoryType[size]);
        }

    }
            ;

    protected RestaurantCategoryType(Parcel in) {
        this.restaurantTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantTypeName = ((String) in.readValue((String.class.getClassLoader())));
        this.restaurantTypeCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RestaurantCategoryType() {
    }

    public String getRestaurantTypeId() {
        return restaurantTypeId;
    }

    public void setRestaurantTypeId(String restaurantTypeId) {
        this.restaurantTypeId = restaurantTypeId;
    }

    public String getRestaurantTypeName() {
        return restaurantTypeName;
    }

    public void setRestaurantTypeName(String restaurantTypeName) {
        this.restaurantTypeName = restaurantTypeName;
    }

    public String getRestaurantTypeCreatedDate() {
        return restaurantTypeCreatedDate;
    }

    public void setRestaurantTypeCreatedDate(String restaurantTypeCreatedDate) {
        this.restaurantTypeCreatedDate = restaurantTypeCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(restaurantTypeId);
        dest.writeValue(restaurantTypeName);
        dest.writeValue(restaurantTypeCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}