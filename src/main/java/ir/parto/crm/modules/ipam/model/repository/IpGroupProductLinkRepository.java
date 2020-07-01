package ir.parto.crm.modules.ipam.model.repository;

import ir.parto.crm.modules.ipam.model.entity.IpGroupProductLink;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpGroupProductLinkRepository extends JpaRepository<IpGroupProductLink, Long>, RepositoryInterface<IpGroupProductLink> {
    List<IpGroupProductLink> findAllByIsDeletedIsNull();

    IpGroupProductLink findByIsDeletedIsNullAndIpGroupProductLinkId(Long id);
}
