package acs.dao;

import java.util.Date;
import java.util.List;
import acs.data.TicketEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDao  extends JpaRepository<TicketEntity, String> {
    List<TicketEntity> findAllByNameLikeIgnoreCase(@Param("name") String name, Pageable pageable);
    List<TicketEntity> findAllByCreatedTimeStampBetween(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);
    List<TicketEntity> findAllByEmail(@Param("email") String email, Pageable pageable);

}