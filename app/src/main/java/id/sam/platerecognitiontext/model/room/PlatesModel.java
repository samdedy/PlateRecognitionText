
package id.sam.platerecognitiontext.model.room;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "PlatesModel")
public class PlatesModel implements Serializable, Parcelable
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private Integer id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "alamat")
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @ColumnInfo(name = "no_plat")
    @SerializedName("no_plat")
    @Expose
    private String noPlat;
    @ColumnInfo(name = "jenis_kendaraan")
    @SerializedName("jenis_kendaraan")
    @Expose
    private String jenisKendaraan;
    @ColumnInfo(name = "status")
    @SerializedName("status")
    @Expose
    private String status;
    @ColumnInfo(name = "penunggakan")
    @SerializedName("penunggakan")
    @Expose
    private String penunggakan;
    @ColumnInfo(name = "lama_cicilan")
    @SerializedName("lama_cicilan")
    @Expose
    private String lamaCicilan;
    @ColumnInfo(name = "warna")
    @SerializedName("warna")
    @Expose
    private String warna;
    @ColumnInfo(name = "type_kendaraan")
    @SerializedName("type_kendaraan")
    @Expose
    private String typeKendaraan;
    @ColumnInfo(name = "merk")
    @SerializedName("merk")
    @Expose
    private String merk;
    @ColumnInfo(name = "image_plat")
    @SerializedName("image_plat")
    @Expose
    private String imagePlat;
    @ColumnInfo(name = "lat")
    @SerializedName("lat")
    @Expose
    private String lat;
    @ColumnInfo(name = "lon")
    @SerializedName("lon")
    @Expose
    private String lon;
    public final static Creator<PlatesModel> CREATOR = new Creator<PlatesModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PlatesModel createFromParcel(Parcel in) {
            return new PlatesModel(in);
        }

        public PlatesModel[] newArray(int size) {
            return (new PlatesModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1416481918815106205L;

    protected PlatesModel(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.noPlat = ((String) in.readValue((String.class.getClassLoader())));
        this.jenisKendaraan = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.penunggakan = ((String) in.readValue((String.class.getClassLoader())));
        this.lamaCicilan = ((String) in.readValue((String.class.getClassLoader())));
        this.warna = ((String) in.readValue((String.class.getClassLoader())));
        this.typeKendaraan = ((String) in.readValue((String.class.getClassLoader())));
        this.merk = ((String) in.readValue((String.class.getClassLoader())));
        this.imagePlat = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.lon = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PlatesModel() {
    }

    /**
     * 
     * @param warna
     * @param merk
     * @param noPlat
     * @param lon
     * @param penunggakan
     * @param typeKendaraan
     * @param imagePlat
     * @param alamat
     * @param lamaCicilan
     * @param name
     * @param jenisKendaraan
     * @param id
     * @param lat
     * @param status
     */
    public PlatesModel(Integer id, String name, String alamat, String noPlat, String jenisKendaraan, String status, String penunggakan, String lamaCicilan, String warna, String typeKendaraan, String merk, String imagePlat, String lat, String lon) {
        super();
        this.id = id;
        this.name = name;
        this.alamat = alamat;
        this.noPlat = noPlat;
        this.jenisKendaraan = jenisKendaraan;
        this.status = status;
        this.penunggakan = penunggakan;
        this.lamaCicilan = lamaCicilan;
        this.warna = warna;
        this.typeKendaraan = typeKendaraan;
        this.merk = merk;
        this.imagePlat = imagePlat;
        this.lat = lat;
        this.lon = lon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoPlat() {
        return noPlat;
    }

    public void setNoPlat(String noPlat) {
        this.noPlat = noPlat;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPenunggakan() {
        return penunggakan;
    }

    public void setPenunggakan(String penunggakan) {
        this.penunggakan = penunggakan;
    }

    public String getLamaCicilan() {
        return lamaCicilan;
    }

    public void setLamaCicilan(String lamaCicilan) {
        this.lamaCicilan = lamaCicilan;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getTypeKendaraan() {
        return typeKendaraan;
    }

    public void setTypeKendaraan(String typeKendaraan) {
        this.typeKendaraan = typeKendaraan;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getImagePlat() {
        return imagePlat;
    }

    public void setImagePlat(String imagePlat) {
        this.imagePlat = imagePlat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(alamat);
        dest.writeValue(noPlat);
        dest.writeValue(jenisKendaraan);
        dest.writeValue(status);
        dest.writeValue(penunggakan);
        dest.writeValue(lamaCicilan);
        dest.writeValue(warna);
        dest.writeValue(typeKendaraan);
        dest.writeValue(merk);
        dest.writeValue(imagePlat);
        dest.writeValue(lat);
        dest.writeValue(lon);
    }

    public int describeContents() {
        return  0;
    }

}
