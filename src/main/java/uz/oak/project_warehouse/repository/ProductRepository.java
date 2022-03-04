package uz.oak.project_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oak.project_warehouse.entity.ProductEntity;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);
}
