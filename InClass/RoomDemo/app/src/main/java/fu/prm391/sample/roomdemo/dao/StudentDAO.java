package fu.prm391.sample.roomdemo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fu.prm391.sample.roomdemo.entity.Student;

@Dao
public interface StudentDAO {

    @Insert
    public void insert(Student student);

    @Update
    public void update(Student student);

    @Delete
    public void delete(Student student);

    @Query("select * from Student")
    public List<Student> getAllStudent();

    @Query("select * from Student where name like :name")
    public List<Student> getStudentByName(String name);
}
