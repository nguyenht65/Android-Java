package fu.prm391.sample.practicalexam;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface HistoryDAO {

    @Transaction
    @Insert
    public void insert(History history);

    @Query("SELECT * FROM history")
    public List<History> getHistories();
}
