package infobite.must.eat.modal.api_modal.vendor_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorReview implements Parcelable
{

    @SerializedName("review_id")
    @Expose
    private String reviewId;
    @SerializedName("review_rate")
    @Expose
    private String reviewRate;
    @SerializedName("review_date")
    @Expose
    private String reviewDate;
    public final static Parcelable.Creator<VendorReview> CREATOR = new Creator<VendorReview>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorReview createFromParcel(Parcel in) {
            return new VendorReview(in);
        }

        public VendorReview[] newArray(int size) {
            return (new VendorReview[size]);
        }

    }
            ;

    protected VendorReview(Parcel in) {
        this.reviewId = ((String) in.readValue((String.class.getClassLoader())));
        this.reviewRate = ((String) in.readValue((String.class.getClassLoader())));
        this.reviewDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorReview() {
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(String reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(reviewId);
        dest.writeValue(reviewRate);
        dest.writeValue(reviewDate);
    }

    public int describeContents() {
        return 0;
    }

}