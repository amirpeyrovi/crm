package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, RepositoryInterface<Product> {
}
