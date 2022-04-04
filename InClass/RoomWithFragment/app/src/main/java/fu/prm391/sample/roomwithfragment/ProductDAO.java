package fu.prm391.sample.roomwithfragment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {

    @Transaction
    @Insert
    public long insert(Product product);

    @Update
    public void update(Product product);

    @Delete
    public void delete(Product product);

    @Query("SELECT * FROM Product")
    public List<Product> getProducts();

}
