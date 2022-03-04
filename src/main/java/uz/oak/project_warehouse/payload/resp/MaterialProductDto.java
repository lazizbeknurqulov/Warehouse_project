package uz.oak.project_warehouse.payload.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialProductDto {

    private String productName;
    private double quantity;

}
