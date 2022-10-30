package acs.logic.utils;

import acs.boundary.CommentBoundary;
import acs.data.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CommentBoundary fromEntity(CommentEntity entity) {
        CommentBoundary rv = new CommentBoundary();
        rv.setId(entity.getId());
        rv.setEmail(entity.getEmail());
        rv.setDescription(entity.getDescription());
        rv.setCreatedTimeStamp(entity.getCreatedTimeStamp());
        rv.setTicketId(entity.getTicket().getId());
        return rv;
    }

    public CommentEntity toEntity(CommentBoundary boundary) {
        CommentEntity rv = new CommentEntity();
        rv.setEmail(boundary.getEmail());
        rv.setDescription(boundary.getDescription());
        rv.setCreatedTimeStamp(boundary.getCreatedTimeStamp());
        return rv;
    }
}
