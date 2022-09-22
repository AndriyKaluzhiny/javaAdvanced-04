package ua.lviv.lgs.domain;

import java.sql.Timestamp;

public class Bucket {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Timestamp purchaseDate;

    public Bucket(Integer id,Integer userId, Integer productId, Timestamp purchaseDate) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
    }

    public Bucket(Integer productId,Integer userId, Timestamp purchaseDate) {
        this.productId = productId;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
