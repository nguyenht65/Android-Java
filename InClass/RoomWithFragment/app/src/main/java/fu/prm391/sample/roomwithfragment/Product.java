package fu.prm391.sample.roomwithfragment;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    private long productId;
    @ColumnInfo
    private String productName;
    @ColumnInfo
    private double productPrice;
    //    @ForeignKey(entity = Catalog.class,
//            parentColumns = {"catalogId"},
//            childColumns = {"catalogOwnerId"},
//            onDelete = ForeignKey.CASCADE
//    )
    @ColumnInfo
    private long catalogOwnerId;

    public Product() {
    }

    public Product(String productName, double productPrice, long catalogOwnerId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.catalogOwnerId = catalogOwnerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public long getCatalogOwnerId() {
        return catalogOwnerId;
    }

    public void setCatalogOwnerId(long catalogOwnerId) {
        this.catalogOwnerId = catalogOwnerId;
    }

    @Override
    public String toString() {
        return productName + "-" + productPrice + "-" + catalogOwnerId;
    }
}
