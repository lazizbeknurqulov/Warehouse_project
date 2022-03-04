package uz.oak.project_warehouse.payload.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Warehouse database dagi ma'lumotlarni shu yerda ushlab turamiz
//Warehouse databaseni o'zgartirib yubormaslik uchun
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseDto {

    private Long warehouseId;
    private String materialName;
    private double quantity;
    private double price;
    
}
