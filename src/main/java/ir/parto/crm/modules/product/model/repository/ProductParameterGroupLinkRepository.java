package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductParameterGroupLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductParameterGroupLinkRepository extends JpaRepository<ProductParameterGroupLink, Long>, RepositoryInterface<ProductParameterGroupLink> {
}
