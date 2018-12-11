package infobite.must.eat.modal.api_modal.vendor_detail;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorDetail implements Parcelable
{

    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("vendor_display_name")
    @Expose
    private String vendorDisplayName;
    @SerializedName("vendor_name")
    @Expose
    private String vendorName;
    @SerializedName("vendor_lat")
    @Expose
    private String vendorLat;
    @SerializedName("vendor_long")
    @Expose
    private String vendorLong;
    @SerializedName("vendor_house_number")
    @Expose
    private String vendorHouseNumber;
    @SerializedName("vendor_street")
    @Expose
    private String vendorStreet;
    @SerializedName("vendor_town")
    @Expose
    private String vendorTown;
    @SerializedName("vendor_postalcode")
    @Expose
    private String vendorPostalcode;
    @SerializedName("vendor_county")
    @Expose
    private String vendorCounty;
    @SerializedName("vendor_landmark")
    @Expose
    private String vendorLandmark;
    @SerializedName("vendor_website")
    @Expose
    private String vendorWebsite;
    @SerializedName("vendor_most_popular")
    @Expose
    private String vendorMostPopular;
    @SerializedName("vendor_recommandation")
    @Expose
    private String vendorRecommandation;
    @SerializedName("vendor_banner")
    @Expose
    private String vendorBanner;
    @SerializedName("vendor_logo")
    @Expose
    private String vendorLogo;
    @SerializedName("vendor_description")
    @Expose
    private String vendorDescription;
    @SerializedName("vendor_type")
    @Expose
    private String vendorType;
    @SerializedName("vendor_opening_closing_time")
    @Expose
    private List<VendorOpenCloseTime> vendorOpeningClosingTime = new ArrayList<VendorOpenCloseTime>();
    @SerializedName("vendor_delivery_area")
    @Expose
    private String vendorDeliveryArea;
    @SerializedName("vendor_registration_date")
    @Expose
    private String vendorRegistrationDate;
    public final static Parcelable.Creator<VendorDetail> CREATOR = new Creator<VendorDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorDetail createFromParcel(Parcel in) {
            return new VendorDetail(in);
        }

        public VendorDetail[] newArray(int size) {
            return (new VendorDetail[size]);
        }

    }
            ;

    protected VendorDetail(Parcel in) {
        this.vendorId = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorDisplayName = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorName = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorLat = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorLong = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorHouseNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorStreet = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorTown = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorPostalcode = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorCounty = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorLandmark = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorWebsite = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorMostPopular = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorRecommandation = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorBanner = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.vendorOpeningClosingTime, (VendorOpenCloseTime.class.getClassLoader()));
        this.vendorDeliveryArea = ((String) in.readValue((String.class.getClassLoader())));
        this.vendorRegistrationDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorDetail() {
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorDisplayName() {
        return vendorDisplayName;
    }

    public void setVendorDisplayName(String vendorDisplayName) {
        this.vendorDisplayName = vendorDisplayName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorLat() {
        return vendorLat;
    }

    public void setVendorLat(String vendorLat) {
        this.vendorLat = vendorLat;
    }

    public String getVendorLong() {
        return vendorLong;
    }

    public void setVendorLong(String vendorLong) {
        this.vendorLong = vendorLong;
    }

    public String getVendorHouseNumber() {
        return vendorHouseNumber;
    }

    public void setVendorHouseNumber(String vendorHouseNumber) {
        this.vendorHouseNumber = vendorHouseNumber;
    }

    public String getVendorStreet() {
        return vendorStreet;
    }

    public void setVendorStreet(String vendorStreet) {
        this.vendorStreet = vendorStreet;
    }

    public String getVendorTown() {
        return vendorTown;
    }

    public void setVendorTown(String vendorTown) {
        this.vendorTown = vendorTown;
    }

    public String getVendorPostalcode() {
        return vendorPostalcode;
    }

    public void setVendorPostalcode(String vendorPostalcode) {
        this.vendorPostalcode = vendorPostalcode;
    }

    public String getVendorCounty() {
        return vendorCounty;
    }

    public void setVendorCounty(String vendorCounty) {
        this.vendorCounty = vendorCounty;
    }

    public String getVendorLandmark() {
        return vendorLandmark;
    }

    public void setVendorLandmark(String vendorLandmark) {
        this.vendorLandmark = vendorLandmark;
    }

    public String getVendorWebsite() {
        return vendorWebsite;
    }

    public void setVendorWebsite(String vendorWebsite) {
        this.vendorWebsite = vendorWebsite;
    }

    public String getVendorMostPopular() {
        return vendorMostPopular;
    }

    public void setVendorMostPopular(String vendorMostPopular) {
        this.vendorMostPopular = vendorMostPopular;
    }

    public String getVendorRecommandation() {
        return vendorRecommandation;
    }

    public void setVendorRecommandation(String vendorRecommandation) {
        this.vendorRecommandation = vendorRecommandation;
    }

    public String getVendorBanner() {
        return vendorBanner;
    }

    public void setVendorBanner(String vendorBanner) {
        this.vendorBanner = vendorBanner;
    }

    public String getVendorLogo() {
        return vendorLogo;
    }

    public void setVendorLogo(String vendorLogo) {
        this.vendorLogo = vendorLogo;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public List<VendorOpenCloseTime> getVendorOpeningClosingTime() {
        return vendorOpeningClosingTime;
    }

    public void setVendorOpeningClosingTime(List<VendorOpenCloseTime> vendorOpeningClosingTime) {
        this.vendorOpeningClosingTime = vendorOpeningClosingTime;
    }

    public String getVendorDeliveryArea() {
        return vendorDeliveryArea;
    }

    public void setVendorDeliveryArea(String vendorDeliveryArea) {
        this.vendorDeliveryArea = vendorDeliveryArea;
    }

    public String getVendorRegistrationDate() {
        return vendorRegistrationDate;
    }

    public void setVendorRegistrationDate(String vendorRegistrationDate) {
        this.vendorRegistrationDate = vendorRegistrationDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(vendorId);
        dest.writeValue(vendorDisplayName);
        dest.writeValue(vendorName);
        dest.writeValue(vendorLat);
        dest.writeValue(vendorLong);
        dest.writeValue(vendorHouseNumber);
        dest.writeValue(vendorStreet);
        dest.writeValue(vendorTown);
        dest.writeValue(vendorPostalcode);
        dest.writeValue(vendorCounty);
        dest.writeValue(vendorLandmark);
        dest.writeValue(vendorWebsite);
        dest.writeValue(vendorMostPopular);
        dest.writeValue(vendorRecommandation);
        dest.writeValue(vendorBanner);
        dest.writeValue(vendorLogo);
        dest.writeValue(vendorDescription);
        dest.writeValue(vendorType);
        dest.writeList(vendorOpeningClosingTime);
        dest.writeValue(vendorDeliveryArea);
        dest.writeValue(vendorRegistrationDate);
    }

    public int describeContents() {
        return 0;
    }

}
