
package infobite.must.eat.modal.api_modal.offer_response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coupon implements Parcelable
{

    @SerializedName("coupon_id")
    @Expose
    private String couponId;
    @SerializedName("coupon_name")
    @Expose
    private String couponName;
    @SerializedName("coupon_description")
    @Expose
    private String couponDescription;
    @SerializedName("coupon_type")
    @Expose
    private String couponType;
    @SerializedName("coupon_max_amount")
    @Expose
    private String couponMaxAmount;
    @SerializedName("coupon_min_order_amount")
    @Expose
    private String couponMinOrderAmount;
    @SerializedName("coupon_start_date")
    @Expose
    private String couponStartDate;
    @SerializedName("coupon_end_date")
    @Expose
    private String couponEndDate;
    @SerializedName("coupon_use_per_customer")
    @Expose
    private String couponUsePerCustomer;
    @SerializedName("coupon_amount")
    @Expose
    private String couponAmount;
    @SerializedName("coupon_percentage")
    @Expose
    private String couponPercentage;
    @SerializedName("coupon_x_order")
    @Expose
    private String couponXOrder;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("coupon_entry_date")
    @Expose
    private String couponEntryDate;
    @SerializedName("coupon_update_date")
    @Expose
    private String couponUpdateDate;
    @SerializedName("coupon_image")
    @Expose
    private String couponImage;
    @SerializedName("coupon_status")
    @Expose
    private String couponStatus;
    public final static Creator<Coupon> CREATOR = new Creator<Coupon>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coupon createFromParcel(Parcel in) {
            return new Coupon(in);
        }

        public Coupon[] newArray(int size) {
            return (new Coupon[size]);
        }

    }
    ;

    protected Coupon(Parcel in) {
        this.couponId = ((String) in.readValue((String.class.getClassLoader())));
        this.couponName = ((String) in.readValue((String.class.getClassLoader())));
        this.couponDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.couponType = ((String) in.readValue((String.class.getClassLoader())));
        this.couponMaxAmount = ((String) in.readValue((String.class.getClassLoader())));
        this.couponMinOrderAmount = ((String) in.readValue((String.class.getClassLoader())));
        this.couponStartDate = ((String) in.readValue((String.class.getClassLoader())));
        this.couponEndDate = ((String) in.readValue((String.class.getClassLoader())));
        this.couponUsePerCustomer = ((String) in.readValue((String.class.getClassLoader())));
        this.couponAmount = ((String) in.readValue((String.class.getClassLoader())));
        this.couponPercentage = ((String) in.readValue((String.class.getClassLoader())));
        this.couponXOrder = ((String) in.readValue((String.class.getClassLoader())));
        this.couponCode = ((String) in.readValue((String.class.getClassLoader())));
        this.couponEntryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.couponUpdateDate = ((String) in.readValue((String.class.getClassLoader())));
        this.couponImage = ((String) in.readValue((String.class.getClassLoader())));
        this.couponStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Coupon() {
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Coupon withCouponId(String couponId) {
        this.couponId = couponId;
        return this;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Coupon withCouponName(String couponName) {
        this.couponName = couponName;
        return this;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public Coupon withCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
        return this;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Coupon withCouponType(String couponType) {
        this.couponType = couponType;
        return this;
    }

    public String getCouponMaxAmount() {
        return couponMaxAmount;
    }

    public void setCouponMaxAmount(String couponMaxAmount) {
        this.couponMaxAmount = couponMaxAmount;
    }

    public Coupon withCouponMaxAmount(String couponMaxAmount) {
        this.couponMaxAmount = couponMaxAmount;
        return this;
    }

    public String getCouponMinOrderAmount() {
        return couponMinOrderAmount;
    }

    public void setCouponMinOrderAmount(String couponMinOrderAmount) {
        this.couponMinOrderAmount = couponMinOrderAmount;
    }

    public Coupon withCouponMinOrderAmount(String couponMinOrderAmount) {
        this.couponMinOrderAmount = couponMinOrderAmount;
        return this;
    }

    public String getCouponStartDate() {
        return couponStartDate;
    }

    public void setCouponStartDate(String couponStartDate) {
        this.couponStartDate = couponStartDate;
    }

    public Coupon withCouponStartDate(String couponStartDate) {
        this.couponStartDate = couponStartDate;
        return this;
    }

    public String getCouponEndDate() {
        return couponEndDate;
    }

    public void setCouponEndDate(String couponEndDate) {
        this.couponEndDate = couponEndDate;
    }

    public Coupon withCouponEndDate(String couponEndDate) {
        this.couponEndDate = couponEndDate;
        return this;
    }

    public String getCouponUsePerCustomer() {
        return couponUsePerCustomer;
    }

    public void setCouponUsePerCustomer(String couponUsePerCustomer) {
        this.couponUsePerCustomer = couponUsePerCustomer;
    }

    public Coupon withCouponUsePerCustomer(String couponUsePerCustomer) {
        this.couponUsePerCustomer = couponUsePerCustomer;
        return this;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Coupon withCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
        return this;
    }

    public String getCouponPercentage() {
        return couponPercentage;
    }

    public void setCouponPercentage(String couponPercentage) {
        this.couponPercentage = couponPercentage;
    }

    public Coupon withCouponPercentage(String couponPercentage) {
        this.couponPercentage = couponPercentage;
        return this;
    }

    public String getCouponXOrder() {
        return couponXOrder;
    }

    public void setCouponXOrder(String couponXOrder) {
        this.couponXOrder = couponXOrder;
    }

    public Coupon withCouponXOrder(String couponXOrder) {
        this.couponXOrder = couponXOrder;
        return this;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Coupon withCouponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public String getCouponEntryDate() {
        return couponEntryDate;
    }

    public void setCouponEntryDate(String couponEntryDate) {
        this.couponEntryDate = couponEntryDate;
    }

    public Coupon withCouponEntryDate(String couponEntryDate) {
        this.couponEntryDate = couponEntryDate;
        return this;
    }

    public String getCouponUpdateDate() {
        return couponUpdateDate;
    }

    public void setCouponUpdateDate(String couponUpdateDate) {
        this.couponUpdateDate = couponUpdateDate;
    }

    public Coupon withCouponUpdateDate(String couponUpdateDate) {
        this.couponUpdateDate = couponUpdateDate;
        return this;
    }

    public String getCouponImage() {
        return couponImage;
    }

    public void setCouponImage(String couponImage) {
        this.couponImage = couponImage;
    }

    public Coupon withCouponImage(String couponImage) {
        this.couponImage = couponImage;
        return this;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Coupon withCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(couponId);
        dest.writeValue(couponName);
        dest.writeValue(couponDescription);
        dest.writeValue(couponType);
        dest.writeValue(couponMaxAmount);
        dest.writeValue(couponMinOrderAmount);
        dest.writeValue(couponStartDate);
        dest.writeValue(couponEndDate);
        dest.writeValue(couponUsePerCustomer);
        dest.writeValue(couponAmount);
        dest.writeValue(couponPercentage);
        dest.writeValue(couponXOrder);
        dest.writeValue(couponCode);
        dest.writeValue(couponEntryDate);
        dest.writeValue(couponUpdateDate);
        dest.writeValue(couponImage);
        dest.writeValue(couponStatus);
    }

    public int describeContents() {
        return  0;
    }

}
