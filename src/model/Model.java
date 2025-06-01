package model;

public class Model {
    private int modelId, brandId, categoryId, fuelTypeId;
    private String modelName;
    private Double engineCapacity;

    public Model(int modelId, int brandId, int categoryId, String modelName, int fuelTypeId, Double engineCapacity) {
        this.modelId = modelId;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelName = modelName;
        this.fuelTypeId = fuelTypeId;
        this.engineCapacity = engineCapacity;
    }
}
