
package id.sam.platerecognitiontext.model.searchplat;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSearchPlatModel implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Creator<DataSearchPlatModel> CREATOR = new Creator<DataSearchPlatModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DataSearchPlatModel createFromParcel(Parcel in) {
            return new DataSearchPlatModel(in);
        }

        public DataSearchPlatModel[] newArray(int size) {
            return (new DataSearchPlatModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3357900432835775868L;

    protected DataSearchPlatModel(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataSearchPlatModel() {
    }

    /**
     * 
     * @param data
     * @param message
     */
    public DataSearchPlatModel(String message, Data data) {
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
