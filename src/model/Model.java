package model;

public class Model {
    private int modelId, brandId, categoryId, fuelTypeId;
    private String modelName;
    private Double engineCapacity;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Model(int modelId, int brandId, int categoryId, String modelName, int fuelTypeId, Double engineCapacity) {
        this.modelId = modelId;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelName = modelName;
        this.fuelTypeId = fuelTypeId;
        this.engineCapacity = engineCapacity;
    }
}
