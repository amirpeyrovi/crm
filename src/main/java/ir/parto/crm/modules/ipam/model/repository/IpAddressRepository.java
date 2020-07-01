package ir.parto.crm.modules.ipam.model.repository;

import ir.parto.crm.modules.ipam.model.entity.IpAddress;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long>, RepositoryInterface<IpAddress> {
    List<IpAddress> findAllByIsDeletedIsNull();

    IpAddress findByIsDeletedIsNullAndIpAddressId(Long id);
}
