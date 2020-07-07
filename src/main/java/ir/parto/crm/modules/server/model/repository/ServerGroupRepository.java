package ir.parto.crm.modules.server.model.repository;

import ir.parto.crm.modules.server.model.entity.ServerGroup;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerGroupRepository extends JpaRepository<ServerGroup, Long>, RepositoryInterface<ServerGroup> {
    Boolean existsByIsDeletedIsNullAndServerGroupId(Long id);
    ServerGroup findByIsDeletedIsNullAndServerGroupId(Long id);
    Page<ServerGroup> findAllByIsDeletedIsNull(Pageable pageable);
    List<ServerGroup> findAllByIsDeletedIsNull();
}
