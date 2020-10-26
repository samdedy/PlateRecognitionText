package id.sam.platerecognitiontext;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.sam.platerecognitiontext.model.room.PlateDAO;
import id.sam.platerecognitiontext.model.room.PlatesModel;


@Database(entities = {PlatesModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "plate_db";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context,
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }
    public abstract PlateDAO plateDAO();
}
