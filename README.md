# 资产管理系统 Demo

基于 Spring Boot 的简单资产管理系统演示项目，使用内存模拟数据，网页前端。

## 功能特性

- ✅ 资产列表展示（表格视图）
- 🔍 按名称搜索资产
- 🎯 按类型和状态筛选
- 📊 统计信息展示（总数、总价值）
- ➕ 添加新资产
- 🗑️ 删除资产
- 💻 响应式网页界面

## 资产类型

- 电子设备
- 家具
- 交通工具
- 设备
- 其他

## 资产状态

- 可用
- 使用中
- 维护中
- 已报废

## 技术栈

- Java 11
- Spring Boot 2.7
- Maven
- HTML + CSS + 原生 JavaScript

## 编译和运行

### 前置条件

需要安装：
- JDK 11 或更高版本
- Maven 3.6+

### 1. 使用 Maven 编译

```bash
mvn clean compile
```

### 2. 直接运行（推荐方式）

```bash
mvn spring-boot:run
```

### 3. 打包运行

```bash
mvn clean package
java -jar target/asset-manager-demo-1.0-SNAPSHOT.jar
```

### 4. 访问应用

启动成功后，在浏览器打开：

**http://localhost:9090**

## 说明

- 所有数据都存储在内存中，应用重启后数据会重置
- 启动时自动初始化 10 条模拟测试数据
- REST API 可直接调用，详见 [CLAUDE.md](./CLAUDE.md)
