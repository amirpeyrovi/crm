package ir.parto.crm.modules.ipam.model.repository;

import ir.parto.crm.modules.ipam.model.entity.IpGroupProductLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpGroupProductLinkRepository extends JpaRepository<IpGroupProductLink, Long>, RepositoryInterface<IpGroupProductLink> {
    List<IpGroupProductLink> findAllByIsDeletedIsNull();

    Page<IpGroupProductLink> findAllByIsDeletedIsNull(Pageable pageable);

    IpGroupProductLink findByIsDeletedIsNullAndIpGroupProductLinkId(Long id);

    boolean existsByIsDeletedIsNullAndIpGroupProductLinkId(Long id);
}
