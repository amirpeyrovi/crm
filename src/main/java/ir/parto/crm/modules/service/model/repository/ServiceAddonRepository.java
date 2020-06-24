package ir.parto.crm.modules.service.model.repository;

import ir.parto.crm.modules.service.model.entity.ServiceAddon;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAddonRepository extends JpaRepository<ServiceAddon, Long>, RepositoryInterface<ServiceAddon> {
}
