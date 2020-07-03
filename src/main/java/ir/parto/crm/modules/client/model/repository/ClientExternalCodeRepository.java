package ir.parto.crm.modules.client.model.repository;

import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientExternalCodeRepository extends JpaRepository<ClientExternalCode, Long>, RepositoryInterface<ClientExternalCode> {
    List<ClientExternalCode> findAllByIsDeletedIsNull();
    Page<ClientExternalCode> findAllByIsDeletedIsNull(Pageable pageable);
}
