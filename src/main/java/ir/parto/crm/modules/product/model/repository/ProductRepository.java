package ir.parto.crm.modules.product.model.repository;

import ir.parto.crm.modules.product.model.entity.Product;
import ir.parto.crm.modules.product.model.entity.ProductCycle;
import ir.parto.crm.modules.product.model.entity.ProductGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, RepositoryInterface<Product> {
    Product findByTitleAndProductGroup(String title, ProductGroup productGroup);

    List<Product> findAllByIsDeletedIsNull();

    Page<Product> findAllByIsDeletedIsNull(Pageable pageable);

    Product findByIsDeletedIsNullAndProductId(Long id);

    boolean existsByIsDeletedIsNullAndProductId(Long id);
}
