package fu.prm391.sample.roomwithfragment;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Catalog {
    @PrimaryKey(autoGenerate = true)
    private long catalogId;
    @ColumnInfo
    private String catalogName;

    public Catalog() {
    }

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    @Override
    public String toString() {
        return catalogName;
    }
}
