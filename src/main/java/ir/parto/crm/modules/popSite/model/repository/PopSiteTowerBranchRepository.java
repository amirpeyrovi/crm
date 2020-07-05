package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTowerBranch;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteTowerBranchRepository extends JpaRepository<PopSiteTowerBranch, Long>, RepositoryInterface<PopSiteTowerBranch> {
    List<PopSiteTowerBranch> findAllByIsDeletedIsNull();

    Page<PopSiteTowerBranch> findAllByIsDeletedIsNull(Pageable pageable);

    PopSiteTowerBranch findByIsDeletedIsNullAndBranchId(Long id);

    boolean existsByIsDeletedIsNullAndBranchId(Long id);
}
