package infobite.must.eat.modal.api_modal.vendor_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorItemCategory implements Parcelable
{

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_description")
    @Expose
    private String categoryDescription;
    @SerializedName("category_availability")
    @Expose
    private String categoryAvailability;
    @SerializedName("category_duration")
    @Expose
    private String categoryDuration;
    @SerializedName("category_created_date")
    @Expose
    private String categoryCreatedDate;
    public final static Parcelable.Creator<VendorItemCategory> CREATOR = new Creator<VendorItemCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorItemCategory createFromParcel(Parcel in) {
            return new VendorItemCategory(in);
        }

        public VendorItemCategory[] newArray(int size) {
            return (new VendorItemCategory[size]);
        }

    }
            ;

    protected VendorItemCategory(Parcel in) {
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryImage = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryAvailability = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryDuration = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorItemCategory() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryAvailability() {
        return categoryAvailability;
    }

    public void setCategoryAvailability(String categoryAvailability) {
        this.categoryAvailability = categoryAvailability;
    }

    public String getCategoryDuration() {
        return categoryDuration;
    }

    public void setCategoryDuration(String categoryDuration) {
        this.categoryDuration = categoryDuration;
    }

    public String getCategoryCreatedDate() {
        return categoryCreatedDate;
    }

    public void setCategoryCreatedDate(String categoryCreatedDate) {
        this.categoryCreatedDate = categoryCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categoryId);
        dest.writeValue(categoryName);
        dest.writeValue(categoryImage);
        dest.writeValue(categoryDescription);
        dest.writeValue(categoryAvailability);
        dest.writeValue(categoryDuration);
        dest.writeValue(categoryCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}
