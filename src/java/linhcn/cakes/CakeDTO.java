/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.cakes;

import java.io.Serializable;
import java.sql.Date;
import linhcn.categories.CategoryDTO;
import linhcn.status.StatusDTO;

/**
 *
 * @author nguye
 */
public class CakeDTO implements Serializable{

    private int cakesId;
    private String name;
    private String description;
    private float price;
    private Date createDate;
    private Date expirationDate;
    private String image;
    private int quantity;
    private CategoryDTO category;
    private Date updateDate;
    private String updateBy;
    private StatusDTO status;

    public CakeDTO() {
    }

    public CakeDTO(int cakesId, String name, float price, int quantity) {
        this.cakesId = cakesId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public CakeDTO(int cakesId, String name, String description, float price, Date createDate, Date expirationDate, String image, int quantity, CategoryDTO category) {
        this.cakesId = cakesId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.image = image;
        this.quantity = quantity;
        this.category = category;
    }

    public CakeDTO(int cakesId, String name, String description, float price, Date createDate, Date expirationDate, String image, int quantity, CategoryDTO category, StatusDTO status) {
        this.cakesId = cakesId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.image = image;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
    }

    public CakeDTO(int cakesId, String name, String description, float price, Date createDate, Date expirationDate, String image, int quantity, CategoryDTO category, Date updateDate, String updateBy, StatusDTO status) {
        this.cakesId = cakesId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.image = image;
        this.quantity = quantity;
        this.category = category;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.status = status;
    }

    public int getCakesId() {
        return cakesId;
    }

    public void setCakesId(int cakesId) {
        this.cakesId = cakesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

}
