package ir.parto.crm.modules.client.model.repository;

import ir.parto.crm.modules.client.model.entity.ClientExternalCode;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientExternalCodeRepository extends JpaRepository<ClientExternalCode, Long>, RepositoryInterface<ClientExternalCode> {
}
