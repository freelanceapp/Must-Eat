
package infobite.must.eat.modal.api_modal.vendor_detail;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorDetailMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vendor")
    @Expose
    private VendorDetail vendor;
    @SerializedName("Review")
    @Expose
    private List<VendorReview> review = new ArrayList<VendorReview>();
    @SerializedName("category")
    @Expose
    private List<VendorItemCategory> category = new ArrayList<VendorItemCategory>();
    @SerializedName("product")
    @Expose
    private List<VendorProduct> product = new ArrayList<VendorProduct>();
    public final static Parcelable.Creator<VendorDetailMainModal> CREATOR = new Creator<VendorDetailMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorDetailMainModal createFromParcel(Parcel in) {
            return new VendorDetailMainModal(in);
        }

        public VendorDetailMainModal[] newArray(int size) {
            return (new VendorDetailMainModal[size]);
        }

    };

    protected VendorDetailMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.vendor = ((VendorDetail) in.readValue((VendorDetail.class.getClassLoader())));
        in.readList(this.review, (VendorReview.class.getClassLoader()));
        in.readList(this.category, (VendorItemCategory.class.getClassLoader()));
        in.readList(this.product, (VendorProduct.class.getClassLoader()));
    }

    public VendorDetailMainModal() {
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

    public VendorDetail getVendor() {
        return vendor;
    }

    public void setVendor(VendorDetail vendor) {
        this.vendor = vendor;
    }

    public List<VendorReview> getReview() {
        return review;
    }

    public void setReview(List<VendorReview> review) {
        this.review = review;
    }

    public List<VendorItemCategory> getCategory() {
        return category;
    }

    public void setCategory(List<VendorItemCategory> category) {
        this.category = category;
    }

    public List<VendorProduct> getProduct() {
        return product;
    }

    public void setProduct(List<VendorProduct> product) {
        this.product = product;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(vendor);
        dest.writeList(review);
        dest.writeList(category);
        dest.writeList(product);
    }

    public int describeContents() {
        return 0;
    }

}