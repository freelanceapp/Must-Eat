
package infobite.must.eat.modal.api_modal.offer_response;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferMainModal implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("coupon")
    @Expose
    private List<Coupon> coupon = new ArrayList<Coupon>();
    public final static Creator<OfferMainModal> CREATOR = new Creator<OfferMainModal>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OfferMainModal createFromParcel(Parcel in) {
            return new OfferMainModal(in);
        }

        public OfferMainModal[] newArray(int size) {
            return (new OfferMainModal[size]);
        }

    }
    ;

    protected OfferMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.coupon, (Coupon.class.getClassLoader()));
    }

    public OfferMainModal() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public OfferMainModal withError(Boolean error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OfferMainModal withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<Coupon> coupon) {
        this.coupon = coupon;
    }

    public OfferMainModal withCoupon(List<Coupon> coupon) {
        this.coupon = coupon;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(coupon);
    }

    public int describeContents() {
        return  0;
    }

}
