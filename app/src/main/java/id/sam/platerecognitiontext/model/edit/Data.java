
package id.sam.platerecognitiontext.model.edit;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("no_plat")
    @Expose
    private String noPlat;
    @SerializedName("informasi_id")
    @Expose
    private Integer informasiId;
    @SerializedName("jenis_kendaraan")
    @Expose
    private String jenisKendaraan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("penunggakan")
    @Expose
    private String penunggakan;
    @SerializedName("lama_cicilan")
    @Expose
    private String lamaCicilan;
    @SerializedName("warna")
    @Expose
    private String warna;
    @SerializedName("type_kendaraan")
    @Expose
    private String typeKendaraan;
    @SerializedName("merk")
    @Expose
    private String merk;
    @SerializedName("image_plat")
    @Expose
    private String imagePlat;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7170475164533119717L;

    protected Data(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.noPlat = ((String) in.readValue((String.class.getClassLoader())));
        this.informasiId = ((Integer) in.readValue((Integer.class.getClassLoader())));
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
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
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
     * @param createdAt
     * @param lamaCicilan
     * @param name
     * @param jenisKendaraan
     * @param id
     * @param informasiId
     * @param lat
     * @param status
     * @param updatedAt
     */
    public Data(Integer id, String name, String alamat, String noPlat, Integer informasiId, String jenisKendaraan, String status, String penunggakan, String lamaCicilan, String warna, String typeKendaraan, String merk, String imagePlat, String lat, String lon, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.alamat = alamat;
        this.noPlat = noPlat;
        this.informasiId = informasiId;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Integer getInformasiId() {
        return informasiId;
    }

    public void setInformasiId(Integer informasiId) {
        this.informasiId = informasiId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(alamat);
        dest.writeValue(noPlat);
        dest.writeValue(informasiId);
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
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    public int describeContents() {
        return  0;
    }

}
