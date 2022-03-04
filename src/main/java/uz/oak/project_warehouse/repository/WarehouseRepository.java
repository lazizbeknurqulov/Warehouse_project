package uz.oak.project_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oak.project_warehouse.entity.WareHouseEntity;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<WareHouseEntity, Long> {
    List<WareHouseEntity> findByOrderByReminder();
}
