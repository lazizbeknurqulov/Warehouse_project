package uz.oak.project_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.oak.project_warehouse.database.WarehouseDbEntity;

import java.util.List;

public interface WarehouseDbRepo extends JpaRepository<WarehouseDbEntity, Long> {
    List<WarehouseDbEntity> findAllByMaterialNameOrderByQuantity(String materialName);

    @Query(value = "drop table WahrehouseDbEntity", nativeQuery = true)
    void delete();

}
