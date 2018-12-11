package infobite.must.eat.modal.api_modal.vendor_list;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorListMainModal implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vendor")
    @Expose
    private List<VendorList> vendor = new ArrayList<VendorList>();
    @SerializedName("restaurant_type")
    @Expose
    private List<RestaurantCategoryType> restaurantType = new ArrayList<RestaurantCategoryType>();
    public final static Parcelable.Creator<VendorListMainModal> CREATOR = new Creator<VendorListMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorListMainModal createFromParcel(Parcel in) {
            return new VendorListMainModal(in);
        }

        public VendorListMainModal[] newArray(int size) {
            return (new VendorListMainModal[size]);
        }

    }
            ;

    protected VendorListMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.vendor, (VendorList.class.getClassLoader()));
        in.readList(this.restaurantType, (RestaurantCategoryType.class.getClassLoader()));
    }

    public VendorListMainModal() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<VendorList> getVendor() {
        return vendor;
    }

    public void setVendor(List<VendorList> vendor) {
        this.vendor = vendor;
    }

    public List<RestaurantCategoryType> getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(List<RestaurantCategoryType> restaurantType) {
        this.restaurantType = restaurantType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(vendor);
        dest.writeList(restaurantType);
    }

    public int describeContents() {
        return 0;
    }

}