package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductAddon;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAddonRepository extends JpaRepository<ProductAddon, Long>, RepositoryInterface<ProductAddon> {
    ProductAddon findByTitleAndProductGroup(String title, ProductGroup productGroup);
}
