package ir.parto.crm.modules.popSite.model.repository;

import ir.parto.crm.modules.popSite.model.entity.PopSiteTower;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopSiteTowerRepository extends JpaRepository<PopSiteTower, Long>, RepositoryInterface<PopSiteTower> {
    List<PopSiteTower> findAllByIsDeletedIsNull();

    PopSiteTower findByIsDeletedIsNullAndTowerId(Long id);
}
