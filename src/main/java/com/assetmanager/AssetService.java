package com.assetmanager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 资产管理服务 - 使用模拟数据
 */
public class AssetService {
    private final Map<String, Asset> assetStorage;

    public AssetService() {
        this.assetStorage = new HashMap<>();
        initMockData();
    }

    /**
     * 初始化模拟数据
     */
    private void initMockData() {
        List<Asset> mockAssets = Arrays.asList(
            new Asset("A001", "戴尔 XPS 15 笔记本电脑", Asset.AssetType.ELECTRONICS,
                new BigDecimal("12999.00"), LocalDate.of(2023, 3, 15), "研发部-302室", "IN_USE"),
            new Asset("A002", "苹果 iMac 27寸 一体机", Asset.AssetType.ELECTRONICS,
                new BigDecimal("14999.00"), LocalDate.of(2023, 5, 20), "设计部-201室", "IN_USE"),
            new Asset("A003", "办公室老板桌", Asset.AssetType.FURNITURE,
                new BigDecimal("3500.00"), LocalDate.of(2022, 12, 10), "总经理办公室", "IN_USE"),
            new Asset("A004", "员工工位椅", Asset.AssetType.FURNITURE,
                new BigDecimal("850.00"), LocalDate.of(2022, 12, 10), "研发部-302室", "IN_USE"),
            new Asset("A005", "丰田凯美瑞 公司车", Asset.AssetType.VEHICLE,
                new BigDecimal("185000.00"), LocalDate.of(2021, 8, 5), "停车场", "AVAILABLE"),
            new Asset("A006", "工业打印机 理光MPC", Asset.AssetType.EQUIPMENT,
                new BigDecimal("12000.00"), LocalDate.of(2022, 6, 1), "行政部-101室", "MAINTENANCE"),
            new Asset("A007", "华为 MateStation 台式机", Asset.AssetType.ELECTRONICS,
                new BigDecimal("5999.00"), LocalDate.of(2024, 1, 10), "研发部-301室", "AVAILABLE"),
            new Asset("A008", "会议室投影仪", Asset.AssetType.EQUIPMENT,
                new BigDecimal("8500.00"), LocalDate.of(2023, 2, 20), "一号会议室", "IN_USE"),
            new Asset("A009", "旧服务器 Dell R740", Asset.AssetType.EQUIPMENT,
                new BigDecimal("35000.00"), LocalDate.of(2020, 5, 15), "机房", "RETIRED"),
            new Asset("A010", "沙发 会客区", Asset.AssetType.FURNITURE,
                new BigDecimal("4200.00"), LocalDate.of(2022, 12, 15), "前台会客区", "IN_USE")
        );

        for (Asset asset : mockAssets) {
            assetStorage.put(asset.getId(), asset);
        }
    }

    /**
     * 查询所有资产
     */
    public List<Asset> findAll() {
        return new ArrayList<>(assetStorage.values());
    }

    /**
     * 根据ID查询资产
     */
    public Asset findById(String id) {
        return assetStorage.get(id);
    }

    /**
     * 根据类型筛选资产
     */
    public List<Asset> findByType(Asset.AssetType type) {
        return assetStorage.values().stream()
            .filter(asset -> asset.getType() == type)
            .collect(Collectors.toList());
    }

    /**
     * 根据状态筛选资产
     */
    public List<Asset> findByStatus(String status) {
        return assetStorage.values().stream()
            .filter(asset -> asset.getStatus().equals(status))
            .collect(Collectors.toList());
    }

    /**
     * 根据名称模糊搜索
     */
    public List<Asset> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return Collections.emptyList();
        }
        String lowerKeyword = keyword.toLowerCase();
        return assetStorage.values().stream()
            .filter(asset -> asset.getName().toLowerCase().contains(lowerKeyword))
            .collect(Collectors.toList());
    }

    /**
     * 添加新资产
     */
    public boolean add(Asset asset) {
        if (assetStorage.containsKey(asset.getId())) {
            return false;
        }
        assetStorage.put(asset.getId(), asset);
        return true;
    }

    /**
     * 更新资产信息
     */
    public boolean update(Asset asset) {
        if (!assetStorage.containsKey(asset.getId())) {
            return false;
        }
        assetStorage.put(asset.getId(), asset);
        return true;
    }

    /**
     * 删除资产
     */
    public boolean delete(String id) {
        return assetStorage.remove(id) != null;
    }

    /**
     * 获取资产总价值统计
     */
    public BigDecimal getTotalValue() {
        return assetStorage.values().stream()
            .map(Asset::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 按类型分组统计资产价值
     */
    public Map<Asset.AssetType, BigDecimal> getValueByType() {
        Map<Asset.AssetType, BigDecimal> result = new EnumMap<>(Asset.AssetType.class);
        for (Asset asset : assetStorage.values()) {
            Asset.AssetType type = asset.getType();
            BigDecimal current = result.getOrDefault(type, BigDecimal.ZERO);
            result.put(type, current.add(asset.getValue()));
        }
        return result;
    }

    /**
     * 获取资产统计信息
     */
    public AssetStatistics getStatistics() {
        int totalCount = assetStorage.size();
        BigDecimal totalValue = getTotalValue();
        Map<Asset.AssetType, Long> countByType = assetStorage.values().stream()
            .collect(Collectors.groupingBy(Asset::getType, Collectors.counting()));
        Map<String, Long> countByStatus = assetStorage.values().stream()
            .collect(Collectors.groupingBy(Asset::getStatus, Collectors.counting()));

        return new AssetStatistics(totalCount, totalValue, countByType, countByStatus);
    }

    /**
     * 统计数据DTO
     */
    public static class AssetStatistics {
        private final int totalCount;
        private final BigDecimal totalValue;
        private final Map<Asset.AssetType, Long> countByType;
        private final Map<String, Long> countByStatus;

        public AssetStatistics(int totalCount, BigDecimal totalValue,
                               Map<Asset.AssetType, Long> countByType,
                               Map<String, Long> countByStatus) {
            this.totalCount = totalCount;
            this.totalValue = totalValue;
            this.countByType = countByType;
            this.countByStatus = countByStatus;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public BigDecimal getTotalValue() {
            return totalValue;
        }

        public Map<Asset.AssetType, Long> getCountByType() {
            return countByType;
        }

        public Map<String, Long> getCountByStatus() {
            return countByStatus;
        }
    }
}
