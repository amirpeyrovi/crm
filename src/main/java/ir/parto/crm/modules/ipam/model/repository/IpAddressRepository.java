package ir.parto.crm.modules.ipam.model.repository;

import ir.parto.crm.modules.ipam.model.entity.IpAddress;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long>, RepositoryInterface<IpAddress> {
    List<IpAddress> findAllByIsDeletedIsNull();

    Page<IpAddress> findAllByIsDeletedIsNull(Pageable pageable);

    IpAddress findByIsDeletedIsNullAndIpAddressId(Long id);

    boolean existsByIsDeletedIsNullAndIpAddressId(Long id);
}
