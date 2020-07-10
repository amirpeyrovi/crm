package ir.parto.crm.modules.ticket.model.repository;

import ir.parto.crm.modules.ticket.model.entity.TicketStage;
import ir.parto.crm.utils.interfaces.RepositoryInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketStageRepository extends JpaRepository<TicketStage, Long>, RepositoryInterface<TicketStage> {
    TicketStage findByIsDeletedIsNullAndTicketStageId(Long ticketStageId);

    List<TicketStage> findAllByIsDeletedIsNull();

    Page<TicketStage> findAllByIsDeletedIsNull(Pageable pageable);

    Boolean eixstsByIsDeletedIsNullAndTicketStageId(Long id);

    TicketStage findByIsDeletedIsNullAndTitle(String title);
}
