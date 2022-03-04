package uz.oak.project_warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class WareHouseEntity extends BaseEntity{

    @ManyToOne ( fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    private MaterialEntity rawMaterials;

    @Column(name = "reminder")
    private Long reminder;

    @Column(name = "price")
    private double price;


}
