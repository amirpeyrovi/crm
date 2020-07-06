package ir.parto.crm.modules.client.model.repository;

import ir.parto.crm.modules.client.model.entity.ClientContact;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface ClientContactRepository extends JpaRepository<ClientContact, Long>, RepositoryInterface<ClientContact> {
    List<ClientContact> findAllByIsDeletedIsNull();
    Page<ClientContact> findAllByIsDeletedIsNull(Pageable pageable);
    ClientContact findByClientContactIdAndIsDeletedIsNull(Long clientContactId);
    Boolean existsByClientContactIdAndIsDeletedIsNull(Long id);
}
