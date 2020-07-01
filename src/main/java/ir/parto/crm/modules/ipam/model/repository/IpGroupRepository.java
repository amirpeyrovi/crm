package ir.parto.crm.modules.ipam.model.repository;

import ir.parto.crm.modules.ipam.model.entity.IpGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpGroupRepository extends JpaRepository<IpGroup, Long>, RepositoryInterface<IpGroup> {
    List<IpGroup> findAllByIsDeletedIsNull();

    IpGroup findByIsDeletedIsNullAndIpGroupId(Long id);
}
