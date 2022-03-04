package uz.oak.project_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oak.project_warehouse.entity.MaterialProductEntity;

import java.util.List;

public interface MaterialProductRepository extends JpaRepository<MaterialProductEntity, Long> {
     List<MaterialProductEntity> findAllByProduct_Id(Long productId);
}
