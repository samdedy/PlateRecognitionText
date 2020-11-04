
package id.sam.platerecognitiontext.model.listall;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListAllModel implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    public final static Creator<ListAllModel> CREATOR = new Creator<ListAllModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListAllModel createFromParcel(Parcel in) {
            return new ListAllModel(in);
        }

        public ListAllModel[] newArray(int size) {
            return (new ListAllModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7755608730507340194L;

    protected ListAllModel(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.product, (Product.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListAllModel() {
    }

    /**
     * 
     * @param product
     * @param message
     */
    public ListAllModel(String message, List<Product> product) {
        super();
        this.message = message;
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeList(product);
    }

    public int describeContents() {
        return  0;
    }

}
