package fu.prm391.sample.roomwithfragment;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CatalogWithProducts {
    @Embedded
    public Catalog catalog;
    @Relation(
            parentColumn = "catalogId",
            entityColumn = "catalogOwnerId")
    public List<Product> products;
}
