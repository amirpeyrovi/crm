package ir.parto.crm.modules.server.model.repository;

import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerParameterRepository extends JpaRepository<ServerParameter, Long>, RepositoryInterface<ServerParameter> {
}
