package uz.oak.project_warehouse.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.oak.project_warehouse.enums.TypeEnum;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "material")
public class MaterialEntity extends BaseEntity{

    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private TypeEnum typeEnum;

}
