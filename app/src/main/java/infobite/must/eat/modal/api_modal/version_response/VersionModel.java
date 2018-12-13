
package infobite.must.eat.modal.api_modal.version_response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionModel implements Parcelable
{

    @SerializedName("version")
    @Expose
    private String version;
    public final static Creator<VersionModel> CREATOR = new Creator<VersionModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VersionModel createFromParcel(Parcel in) {
            return new VersionModel(in);
        }

        public VersionModel[] newArray(int size) {
            return (new VersionModel[size]);
        }

    }
    ;

    protected VersionModel(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VersionModel() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public VersionModel withVersion(String version) {
        this.version = version;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
    }

    public int describeContents() {
        return  0;
    }

}
