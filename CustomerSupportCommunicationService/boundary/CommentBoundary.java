package acs.boundary;
import java.util.Date;

public class CommentBoundary {

    private String id;
    private String ticketId;
    private String email;
    private String description;
    private Date createdTimeStamp;

    public CommentBoundary() {
    }

    public CommentBoundary(String ticketId, String email, String description) {
        this.ticketId = ticketId;
        this.email = email;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
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
}
