
package infobite.must.eat.modal.api_modal.notification_response;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationMainModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("notification")
    @Expose
    private List<Notification> notification = new ArrayList<Notification>();
    public final static Creator<NotificationMainModel> CREATOR = new Creator<NotificationMainModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public NotificationMainModel createFromParcel(Parcel in) {
            return new NotificationMainModel(in);
        }

        public NotificationMainModel[] newArray(int size) {
            return (new NotificationMainModel[size]);
        }

    }
    ;

    protected NotificationMainModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.notification, (Notification.class.getClassLoader()));
    }

    public NotificationMainModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public NotificationMainModel withError(Boolean error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationMainModel withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Notification> getNotification() {
        return notification;
    }

    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }

    public NotificationMainModel withNotification(List<Notification> notification) {
        this.notification = notification;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(notification);
    }

    public int describeContents() {
        return  0;
    }

}
