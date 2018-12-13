
package infobite.must.eat.modal.api_modal.notification_response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification implements Parcelable
{

    @SerializedName("notification_id")
    @Expose
    private String notificationId;
    @SerializedName("notification_title")
    @Expose
    private String notificationTitle;
    @SerializedName("notification_message")
    @Expose
    private String notificationMessage;
    @SerializedName("notification_date")
    @Expose
    private String notificationDate;
    public final static Creator<Notification> CREATOR = new Creator<Notification>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        public Notification[] newArray(int size) {
            return (new Notification[size]);
        }

    }
    ;

    protected Notification(Parcel in) {
        this.notificationId = ((String) in.readValue((String.class.getClassLoader())));
        this.notificationTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.notificationMessage = ((String) in.readValue((String.class.getClassLoader())));
        this.notificationDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Notification() {
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public Notification withNotificationId(String notificationId) {
        this.notificationId = notificationId;
        return this;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public Notification withNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
        return this;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Notification withNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
        return this;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Notification withNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(notificationId);
        dest.writeValue(notificationTitle);
        dest.writeValue(notificationMessage);
        dest.writeValue(notificationDate);
    }

    public int describeContents() {
        return  0;
    }

}
