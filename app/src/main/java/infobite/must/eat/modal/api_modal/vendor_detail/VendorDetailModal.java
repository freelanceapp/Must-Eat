
package infobite.must.eat.modal.api_modal.vendor_detail;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorDetailModal implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vendor")
    @Expose
    private VendorVendor vendor;
    @SerializedName("VendorReview")
    @Expose
    private List<VendorReview> review = new ArrayList<VendorReview>();
    @SerializedName("category")
    @Expose
    private List<VendorCategory> category = new ArrayList<VendorCategory>();
    @SerializedName("product")
    @Expose
    private List<VendorProduct> product = new ArrayList<VendorProduct>();
    public final static Creator<VendorDetailModal> CREATOR = new Creator<VendorDetailModal>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VendorDetailModal createFromParcel(Parcel in) {
            return new VendorDetailModal(in);
        }

        public VendorDetailModal[] newArray(int size) {
            return (new VendorDetailModal[size]);
        }

    }
    ;

    protected VendorDetailModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.vendor = ((VendorVendor) in.readValue((VendorVendor.class.getClassLoader())));
        in.readList(this.review, (VendorReview.class.getClassLoader()));
        in.readList(this.category, (VendorCategory.class.getClassLoader()));
        in.readList(this.product, (VendorProduct.class.getClassLoader()));
    }

    public VendorDetailModal() {
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

    public VendorVendor getVendor() {
        return vendor;
    }

    public void setVendor(VendorVendor vendor) {
        this.vendor = vendor;
    }

    public List<VendorReview> getReview() {
        return review;
    }

    public void setReview(List<VendorReview> review) {
        this.review = review;
    }

    public List<VendorCategory> getCategory() {
        return category;
    }

    public void setCategory(List<VendorCategory> category) {
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
        return  0;
    }

}
