package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTower;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteTowerRepository extends JpaRepository<PopSiteTower, Long>, RepositoryInterface<PopSiteTower> {
    List<PopSiteTower> findAllByIsDeletedIsNull();

    Page<PopSiteTower> findAllByIsDeletedIsNull(Pageable pageable);

    PopSiteTower findByIsDeletedIsNullAndTowerId(Long id);

    boolean existsByIsDeletedIsNullAndTowerId(Long id);
}
