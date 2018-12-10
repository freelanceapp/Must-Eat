
package infobite.must.eat.modal.api_modal.vendor_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorReview implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("comments")
    @Expose
    private String comments;
    public final static Creator<VendorReview> CREATOR = new Creator<VendorReview>() {


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
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.comments = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorReview() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(rating);
        dest.writeValue(comments);
    }

    public int describeContents() {
        return  0;
    }

}
