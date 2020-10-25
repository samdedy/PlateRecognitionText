
package id.sam.platerecognitiontext.model.edit;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEditPlatModel implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Creator<DataEditPlatModel> CREATOR = new Creator<DataEditPlatModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DataEditPlatModel createFromParcel(Parcel in) {
            return new DataEditPlatModel(in);
        }

        public DataEditPlatModel[] newArray(int size) {
            return (new DataEditPlatModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2688795767530515338L;

    protected DataEditPlatModel(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataEditPlatModel() {
    }

    /**
     * 
     * @param data
     * @param message
     */
    public DataEditPlatModel(String message, Data data) {
        super();
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return  0;
    }

}
