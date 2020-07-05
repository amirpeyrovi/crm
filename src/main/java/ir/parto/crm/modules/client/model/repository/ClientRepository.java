package ir.parto.crm.modules.client.model.repository;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, RepositoryInterface<Client> {
    List<Client> findAllByIsDeletedIsNull();
    Page<Client> findAllByIsDeletedIsNull(Pageable pageable);
    Client findByEmailAddressAndIsDeletedIsNull(String email);
    Boolean existsByClientIdAndIsDeletedIsNull(Long id);
}
