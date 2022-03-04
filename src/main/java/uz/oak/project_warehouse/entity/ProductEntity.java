package uz.oak.project_warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{

    @Column(name = "name", unique = true)
    private String name;

    @Column(unique = true, name = "code")
    private Long code;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<MaterialProductEntity>materialProducts;

}
