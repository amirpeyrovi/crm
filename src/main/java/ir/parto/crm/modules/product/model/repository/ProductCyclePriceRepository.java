package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductCyclePrice;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCyclePriceRepository extends JpaRepository<ProductCyclePrice, Long>, RepositoryInterface<ProductCyclePrice> {
}
