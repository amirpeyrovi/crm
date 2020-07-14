package ir.parto.crm.modules.server.model.repository;

import ir.parto.crm.modules.server.model.entity.Server;
import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long>, RepositoryInterface<Server> {
    Boolean existsByIsDeletedIsNullAndServerId(Long id);

    Server findByIsDeletedIsNullAndServerId(Long id);

    Page<Server> findAllByIsDeletedIsNull(Pageable pageable);
    Page<Server> findAllByIsDeletedIsNullAndServerGroup(ServerGroup serverGroup, Pageable pageable);

    List<Server> findAllByIsDeletedIsNull();
}
