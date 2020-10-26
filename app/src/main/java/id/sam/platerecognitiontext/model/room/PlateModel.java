package id.sam.platerecognitiontext.model.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "PlateModel")
public class PlateModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "alamat")
    private String alamat;
    @ColumnInfo(name = "no_plat")
    private String no_plat;
    @ColumnInfo(name = "jenis_kendaraan")
    private String jenis_kendaraan;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "penunggakan")
    private String penunggakan;
    @ColumnInfo(name = "lama_cicilan")
    private String lama_cicilan;
    @ColumnInfo(name = "warna")
    private String warna;
    @ColumnInfo(name = "type_kendaraan")
    private String type_kendaraan;
    @ColumnInfo(name = "merk")
    private String merk;
    @ColumnInfo(name = "image_plat")
    private String image_plat;
    @ColumnInfo(name = "lat")
    private String lat;
    @ColumnInfo(name = "lon")
    private String lon;

    protected PlateModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        alamat = in.readString();
        no_plat = in.readString();
        jenis_kendaraan = in.readString();
        status = in.readString();
        penunggakan = in.readString();
        lama_cicilan = in.readString();
        warna = in.readString();
        type_kendaraan = in.readString();
        merk = in.readString();
        image_plat = in.readString();
        lat = in.readString();
        lon = in.readString();
    }

    public static final Creator<PlateModel> CREATOR = new Creator<PlateModel>() {
        @Override
        public PlateModel createFromParcel(Parcel in) {
            return new PlateModel(in);
        }

        @Override
        public PlateModel[] newArray(int size) {
            return new PlateModel[size];
        }
    };

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
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

    public String getNo_plat() {
        return no_plat;
    }

    public void setNo_plat(String no_plat) {
        this.no_plat = no_plat;
    }

    public String getJenis_kendaraan() {
        return jenis_kendaraan;
    }

    public void setJenis_kendaraan(String jenis_kendaraan) {
        this.jenis_kendaraan = jenis_kendaraan;
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

    public String getLama_cicilan() {
        return lama_cicilan;
    }

    public void setLama_cicilan(String lama_cicilan) {
        this.lama_cicilan = lama_cicilan;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getType_kendaraan() {
        return type_kendaraan;
    }

    public void setType_kendaraan(String type_kendaraan) {
        this.type_kendaraan = type_kendaraan;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getImage_plat() {
        return image_plat;
    }

    public void setImage_plat(String image_plat) {
        this.image_plat = image_plat;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(alamat);
        parcel.writeString(no_plat);
        parcel.writeString(jenis_kendaraan);
        parcel.writeString(status);
        parcel.writeString(penunggakan);
        parcel.writeString(lama_cicilan);
        parcel.writeString(warna);
        parcel.writeString(type_kendaraan);
        parcel.writeString(merk);
        parcel.writeString(image_plat);
        parcel.writeString(lat);
        parcel.writeString(lon);
    }
}
