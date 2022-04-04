package fu.prm391.sample.roomwithfragment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CatalogDAO {

    @Transaction
    @Insert
    public long insert(Catalog catalog);

    @Update
    public void update(Catalog catalog);

    @Delete
    public void delete(Catalog catalog);

    @Query("SELECT * FROM Catalog")
    public List<Catalog> getCatalogs();

    @Query("SELECT * FROM Catalog")
    public List<CatalogWithProducts> getCatalogWithProducts();
}
