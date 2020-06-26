package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.ProductAddonsLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAddonsLinkRepository extends JpaRepository<ProductAddonsLink, Long>, RepositoryInterface<ProductAddonsLink> {
}
