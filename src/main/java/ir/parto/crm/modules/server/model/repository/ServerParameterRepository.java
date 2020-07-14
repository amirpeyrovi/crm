package ir.parto.crm.modules.server.model.repository;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.modules.server.model.entity.ServerParameter;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerParameterRepository extends JpaRepository<ServerParameter, Long>, RepositoryInterface<ServerParameter> {
    List<ServerParameter> findAllByIsDeletedIsNull();

    Page<ServerParameter> findAllByIsDeletedIsNull(Pageable pageable);

    Page<ServerParameter> findAllByIsDeletedIsNullAndServerGroup(ServerGroup serverGroup, Pageable pageable);

    ServerParameter findByIsDeletedIsNullAndServerParameterId(Long id);

    Boolean existsByIsDeletedIsNullAndServerParameterId(Long id);
}
