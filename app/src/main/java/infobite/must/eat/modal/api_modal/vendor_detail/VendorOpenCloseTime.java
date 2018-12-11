package infobite.must.eat.modal.api_modal.vendor_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorOpenCloseTime implements Parcelable
{

    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<VendorOpenCloseTime> CREATOR = new Creator<VendorOpenCloseTime>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorOpenCloseTime createFromParcel(Parcel in) {
            return new VendorOpenCloseTime(in);
        }

        public VendorOpenCloseTime[] newArray(int size) {
            return (new VendorOpenCloseTime[size]);
        }

    }
            ;

    protected VendorOpenCloseTime(Parcel in) {
        this.week = ((String) in.readValue((String.class.getClassLoader())));
        this.start = ((String) in.readValue((String.class.getClassLoader())));
        this.end = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorOpenCloseTime() {
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(week);
        dest.writeValue(start);
        dest.writeValue(end);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}