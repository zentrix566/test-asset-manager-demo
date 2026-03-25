package com.assetmanager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 资产 REST API 控制器
 */
@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController() {
        this.assetService = new AssetService();
    }

    /**
     * 获取所有资产
     */
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.findAll();
    }

    /**
     * 根据ID获取资产
     */
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable String id) {
        Asset asset = assetService.findById(id);
        if (asset == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asset);
    }

    /**
     * 搜索资产按名称
     */
    @GetMapping("/search")
    public List<Asset> searchByName(@RequestParam String keyword) {
        return assetService.searchByName(keyword);
    }

    /**
     * 按类型筛选
     */
    @GetMapping("/type/{type}")
    public List<Asset> filterByType(@PathVariable Asset.AssetType type) {
        return assetService.findByType(type);
    }

    /**
     * 按状态筛选
     */
    @GetMapping("/status/{status}")
    public List<Asset> filterByStatus(@PathVariable String status) {
        return assetService.findByStatus(status);
    }

    /**
     * 添加新资产
     */
    @PostMapping
    public ResponseEntity<String> addAsset(@RequestBody Asset asset) {
        boolean success = assetService.add(asset);
        if (success) {
            return ResponseEntity.ok("添加成功");
        } else {
            return ResponseEntity.badRequest().body("ID已存在");
        }
    }

    /**
     * 更新资产
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAsset(@PathVariable String id, @RequestBody Asset asset) {
        asset.setId(id);
        boolean success = assetService.update(asset);
        if (success) {
            return ResponseEntity.ok("更新成功");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 删除资产
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable String id) {
        boolean success = assetService.delete(id);
        if (success) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 获取统计信息
     */
    @GetMapping("/statistics")
    public AssetService.AssetStatistics getStatistics() {
        return assetService.getStatistics();
    }

    /**
     * 按类型获取价值统计
     */
    @GetMapping("/value-by-type")
    public Map<Asset.AssetType, BigDecimal> getValueByType() {
        return assetService.getValueByType();
    }
}
