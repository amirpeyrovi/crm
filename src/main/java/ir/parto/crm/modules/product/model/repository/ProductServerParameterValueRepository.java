package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductServerParameterValue;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductServerParameterValueRepository extends JpaRepository<ProductServerParameterValue, Long>, RepositoryInterface<ProductServerParameterValue> {
}
