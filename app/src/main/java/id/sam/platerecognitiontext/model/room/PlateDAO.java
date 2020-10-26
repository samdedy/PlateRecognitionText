package id.sam.platerecognitiontext.model.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlateDAO {
    @Query("SELECT * FROM PlatesModel")
    List<PlatesModel> getAll();

    @Query("SELECT * FROM PlatesModel WHERE no_plat = :no_plat")
    PlatesModel findByNoPlate(String no_plat);

    @Query("SELECT * FROM PlatesModel WHERE id = :id")
    PlatesModel findById(int id);

    @Insert
    void insertAll(PlatesModel... plate);

    @Delete
    void deletePlate(PlatesModel plate);

    @Query("DELETE FROM PlatesModel")
    void deletePlateAll();

    @Update
    int updatePlate(PlatesModel plate);
}
