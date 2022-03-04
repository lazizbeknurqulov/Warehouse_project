package uz.oak.project_warehouse.payload.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.oak.project_warehouse.database.WarehouseDbEntity;

import java.util.List;

//Response ni shu ko'rinishda qaytaramiz
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {

    private String productName;

    private double productQuantity;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<WarehouseDbEntity> productMaterials;

}
