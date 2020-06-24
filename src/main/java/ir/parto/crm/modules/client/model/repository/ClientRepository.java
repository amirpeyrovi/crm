package ir.parto.crm.modules.client.model.repository;

import ir.parto.crm.modules.client.model.entity.Client;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, RepositoryInterface<Client> {
}
