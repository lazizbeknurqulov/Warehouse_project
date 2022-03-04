package uz.oak.project_warehouse.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class WarehouseDbEntity {

    @Id
    private Long warehouseId;
    private String materialName;
    private double quantity;
    private double price;
}
