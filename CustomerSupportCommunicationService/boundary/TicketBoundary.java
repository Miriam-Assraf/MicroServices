package acs.boundary;

import acs.utils.SubjectType;

import java.util.Date;

public class TicketBoundary {

    private String id;
    private String name;
    private String email;
    private Boolean isOpen;
    private SubjectType subjectType;
    private String externalId;
    private Date createdTimeStamp;
    private Date closingTimeStamp;

    public TicketBoundary() {}

    public TicketBoundary(String name, String email, SubjectType subjectType, Date createdTimeStamp, Date closingTimeStamp, String externalId) {
        this.name = name;
        this.email = email;
        this.closingTimeStamp = closingTimeStamp;
        this.externalId = externalId;
        this.subjectType = subjectType;
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Date getClosingTimeStamp() {
        return closingTimeStamp;
    }

    public void setClosingTimeStamp(Date closingTimeStamp) {
        this.closingTimeStamp = closingTimeStamp;
    }

    @Override
    public String toString() {
        return "TicketBoundary{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isOpen=" + isOpen +
                ", externalServiceType=" + subjectType +
                ", externalId='" + externalId + '\'' +
                ", createdTimeStamp=" + createdTimeStamp +
                ", closingTimeStamp=" + closingTimeStamp +
                '}';
    }
}
