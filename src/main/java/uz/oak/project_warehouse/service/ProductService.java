package uz.oak.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.oak.project_warehouse.database.WarehouseDbEntity;
import uz.oak.project_warehouse.entity.MaterialProductEntity;
import uz.oak.project_warehouse.entity.ProductEntity;
import uz.oak.project_warehouse.entity.WareHouseEntity;
import uz.oak.project_warehouse.exception.ProductException;
import uz.oak.project_warehouse.payload.req.ProductDto;
import uz.oak.project_warehouse.payload.resp.MaterialProductDto;
import uz.oak.project_warehouse.payload.resp.ProductResponseDto;
import uz.oak.project_warehouse.payload.resp.ResultDto;
import uz.oak.project_warehouse.repository.MaterialProductRepository;
import uz.oak.project_warehouse.repository.ProductRepository;
import uz.oak.project_warehouse.repository.WarehouseDbRepo;
import uz.oak.project_warehouse.repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    /**
     * Bean qilgan servicelarni chaqirayapmiz
     */
    private final ProductRepository productRepository;
    private final MaterialProductRepository materialProductRepository;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseDbRepo warehouseDbRepo;


    /**
     Productning name i orqali producni olish
     */
    public ProductEntity getProductByName(String productName) {
        Optional<ProductEntity> byName = productRepository.findByName(productName);
        if (byName.isPresent()) {
            return byName.get();
        }
        throw new ProductException( productName + " topilamdi!");
    }

    /**
     Wharehouse dagi barcha ma'lumotlarni olib DTO clasga o'girayapmiz
     */
    public List<WarehouseDbEntity> getWarehouseList() {
        List<WarehouseDbEntity> warehouseDbEntityList = new ArrayList<>();
        List<WareHouseEntity> wareHouseList = warehouseRepository.findByOrderByReminder();
        for (WareHouseEntity wareHouse : wareHouseList) {
            WarehouseDbEntity warehouseDbEntity = new WarehouseDbEntity();
            warehouseDbEntity.setMaterialName(wareHouse.getRawMaterials().getName());
            warehouseDbEntity.setWarehouseId(wareHouse.getId());
            warehouseDbEntity.setQuantity(wareHouse.getReminder());
            warehouseDbEntity.setPrice(wareHouse.getPrice());
            warehouseDbEntityList.add(warehouseDbEntity);
        }
        return warehouseDbEntityList;
    }


    /**
     Response qaytarishimiz kk bo'lgan productlarni chiqaradi
     */
    public List<MaterialProductDto> getMaterialProduct(String productName, double qnt) {
        ProductEntity product = getProductByName(productName);
        List<MaterialProductDto> materialProductDtoList = new ArrayList<>();
        List<MaterialProductEntity> allByProduct_id = materialProductRepository.findAllByProduct_Id(product.getId());
        for (MaterialProductEntity materialProduct : allByProduct_id) {
            MaterialProductDto materialProductDto = new MaterialProductDto();
            materialProductDto.setProductName(materialProduct.getMaterial().getName());
            materialProductDto.setQuantity(materialProduct.getQuantity() * qnt);
            materialProductDtoList.add(materialProductDto);
        }
        return materialProductDtoList;
    }

    public List<WarehouseDbEntity> getByMaterialName(String materialName) {
        return warehouseDbRepo.findAllByMaterialNameOrderByQuantity(materialName);
    }


    /**
     * Warehouse database o'zgarmasligi uchun warehousedagi barcha ma'lumotlarni
     *     boshqa databasega saqlanayapti
     */
    public void saveAll() {
        warehouseDbRepo.saveAll(getWarehouseList());
    }

    public List<WarehouseDbEntity> getMaterials(String materialName, double qnt) {

        List<WarehouseDbEntity> warehouseDbEntityListDb = getByMaterialName(materialName);
        List<WarehouseDbEntity> warehouseDbEntityList = new ArrayList<>();

        for (WarehouseDbEntity w : warehouseDbEntityListDb) {
            if (w.getQuantity() >= qnt) {
                warehouseDbEntityList.add(new WarehouseDbEntity(
                        w.getWarehouseId(),
                        w.getMaterialName(),
                        qnt,
                        w.getPrice()
                ));
                System.out.println(w.getQuantity());
                w.setQuantity(w.getQuantity() - qnt);
                warehouseDbRepo.saveAll(warehouseDbEntityListDb);
                return warehouseDbEntityList;
            } else if(w.getQuantity() > 0){
                warehouseDbEntityList.add(new WarehouseDbEntity(
                        w.getWarehouseId(),
                        w.getMaterialName(),
                        w.getQuantity(),
                        w.getPrice()
                ));
                System.out.println(w.getQuantity());
                qnt = qnt - w.getQuantity();
                w.setQuantity(0);
            }
        }
        if (qnt > 0) {
            warehouseDbEntityList.add(new WarehouseDbEntity(
                    null,
                    materialName,
                    qnt,
                    0
            ));
        }
        warehouseDbRepo.saveAll(warehouseDbEntityListDb);
        return warehouseDbEntityList;
    }

    public ProductResponseDto getAllMaterialsProduct(String productName, double qnt) {
        ProductResponseDto prd = new ProductResponseDto();
        List<WarehouseDbEntity> w = new ArrayList<>();
        prd.setProductName(productName);
        prd.setProductQuantity(qnt);

        for (MaterialProductDto material : getMaterialProduct(productName, qnt)) {
            w.addAll(getMaterials(material.getProductName(), material.getQuantity()));
        }

        prd.setProductMaterials(w);
        return prd;
    }

    public ResultDto getAllInfo(List<ProductDto> productDtoList) {

        warehouseDbRepo.deleteAll();
        saveAll();
        List<ProductResponseDto> dtoList = new ArrayList<>();



        ResultDto resultDto = new ResultDto();

        for (ProductDto productDto : productDtoList) {
            if(getProductByName(productDto.getName()) == null){
                throw new ProductException("Product name not found!");
            }
            dtoList.add(getAllMaterialsProduct(productDto.getName(), productDto.getQnt()));
        }
        resultDto.setResult(dtoList);
        warehouseDbRepo.deleteAll();
        return resultDto;
    }
}
