package fu.prm391.sample.roomdemo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fu.prm391.sample.roomdemo.dao.StudentDAO;
import fu.prm391.sample.roomdemo.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
