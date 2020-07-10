package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketStageParameter;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketStageParameterRepository extends JpaRepository<TicketStageParameter, Long>, RepositoryInterface<TicketStageParameter> {
    TicketStageParameter findByIsDeletedIsNullAndTicketStageParameterId(Long ticketStageParameterId);

    List<TicketStageParameter> findAllByIsDeletedIsNull();

    Page<TicketStageParameter> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean existsByIsDeletedIsNullAndTicketStageParameterId(Long id);
}
