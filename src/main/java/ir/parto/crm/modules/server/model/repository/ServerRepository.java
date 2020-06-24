package ir.parto.crm.modules.server.model.repository;

import ir.parto.crm.modules.server.model.entity.Server;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long>, RepositoryInterface<Server> {
}
