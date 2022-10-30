package acs.data;

import acs.annotations.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="COMMENTS")
public class CommentEntity {

    @Id
    private String id;

    @Email
    private String email;

    @NotEmpty(message="Description can not be empty")
    private String description;

    @NotNull
    private Date createdTimeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private TicketEntity ticket;

    public CommentEntity() {
    }

    public CommentEntity(String email, String description) {
        this.email = email;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
}
