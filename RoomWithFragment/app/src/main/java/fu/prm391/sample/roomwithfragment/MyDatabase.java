package fu.prm391.sample.roomwithfragment;


import androidx.room.Database;

import androidx.room.RoomDatabase;


@Database(entities = {Catalog.class, Product.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CatalogDAO createCatalogDAO();
    public abstract ProductDAO createProductDAO();
}
