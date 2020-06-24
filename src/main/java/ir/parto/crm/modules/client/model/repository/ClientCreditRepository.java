package ir.parto.crm.modules.client.model.repository;

import ir.parto.crm.modules.client.model.entity.ClientCredit;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCreditRepository extends JpaRepository<ClientCredit, Long>, RepositoryInterface<ClientCredit> {
}
