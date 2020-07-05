package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTowerBranch;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteTowerBranchRepository extends JpaRepository<PopSiteTowerBranch, Long>, RepositoryInterface<PopSiteTowerBranch> {
    List<PopSiteTowerBranch> findAllByIsDeletedIsNull();

    PopSiteTowerBranch findByIsDeletedIsNullAndBranchId(Long id);
}
