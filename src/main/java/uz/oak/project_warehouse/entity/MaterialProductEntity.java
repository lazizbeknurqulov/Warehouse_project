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
@Table(name = "material_product")
public class MaterialProductEntity extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.EAGER)
    private MaterialEntity material;

    @Column(name = "quantity")
    private double quantity;

}
