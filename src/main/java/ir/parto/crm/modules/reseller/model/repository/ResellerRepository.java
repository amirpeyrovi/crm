package ir.parto.crm.modules.reseller.model.repository;

import ir.parto.crm.modules.reseller.model.entity.Reseller;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResellerRepository extends JpaRepository<Reseller, Long>, RepositoryInterface<Reseller> {
    List<Reseller> findAllByIsDeletedIsNull();

    Page<Reseller> findAllByIsDeletedIsNull(Pageable pageable);

    Reseller findByIsDeletedIsNullAndResellerId(Long id);

    boolean existsByIsDeletedIsNullAndResellerId(Long id);
}
