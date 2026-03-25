package com.assetmanager;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 资产实体类
 */
public class Asset {
    private String id;
    private String name;
    private AssetType type;
    private BigDecimal value;
    private LocalDate purchaseDate;
    private String location;
    private String status; // AVAILABLE, IN_USE, MAINTENANCE, RETIRED

    public enum AssetType {
        ELECTRONICS("电子设备"),
        FURNITURE("家具"),
        VEHICLE("交通工具"),
        EQUIPMENT("设备"),
        OTHER("其他");

        private final String description;

        AssetType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public Asset() {
    }

    public Asset(String id, String name, AssetType type, BigDecimal value,
                 LocalDate purchaseDate, String location, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.purchaseDate = purchaseDate;
        this.location = location;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %s%n" +
            "名称: %s%n" +
            "类型: %s%n" +
            "价值: ¥%.2f%n" +
            "采购日期: %s%n" +
            "位置: %s%n" +
            "状态: %s%n" +
            "------------------------",
            id, name, type.getDescription(), value, purchaseDate, location, getStatusText()
        );
    }

    private String getStatusText() {
        switch (status) {
            case "AVAILABLE": return "可用";
            case "IN_USE": return "使用中";
            case "MAINTENANCE": return "维护中";
            case "RETIRED": return "已报废";
            default: return status;
        }
    }
}
