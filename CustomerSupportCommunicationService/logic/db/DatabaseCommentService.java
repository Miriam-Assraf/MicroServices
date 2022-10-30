package acs.logic.db;

import acs.boundary.CommentBoundary;
import acs.dao.CommentDao;
import acs.dao.TicketDao;
import acs.data.CommentEntity;
import acs.data.TicketEntity;
import acs.exceptions.BadRequestException;
import acs.logic.CommentService;
import acs.logic.utils.CommentConverter;
import acs.logic.utils.CommentFilterType;
import acs.logic.utils.User;
import acs.externalServices.UserManagementRestService;
import acs.utils.CommentSortBy;
import acs.utils.SortOrder;
import acs.utils.UserRoles;
import acs.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DatabaseCommentService implements CommentService {

    private CommentDao commentDao; // Data access object
    private TicketDao ticketDao; // Data access object
    private CommentConverter converter;
    private UserManagementRestService userManagementRestService;

    @Autowired
    public DatabaseCommentService(CommentDao commentDao ,TicketDao ticketDao, CommentConverter converter,
                                  UserManagementRestService userManagementRestService) {
        this.commentDao = commentDao;
        this.ticketDao = ticketDao;
        this.converter = converter;
        this.userManagementRestService = userManagementRestService;
    }

    @Override
    public CommentBoundary createComment(CommentBoundary commentBoundary) {
        User user = this.userManagementRestService.getUser(commentBoundary.getEmail());//The UserManagementService will throw an exception if the user doesn't exists

        CommentEntity entity = this.converter.toEntity(commentBoundary);
        TicketEntity ticketEntity = this.ticketDao.findById(commentBoundary.getTicketId()).orElseThrow(
                () -> new RuntimeException("No ticket found by id: " + commentBoundary.getTicketId()));

        if(Arrays.asList(user.getRoles()).contains(Utils.upperCaseToCamelCase(UserRoles.SUPPORT_AGENT.toString()))
                || (Arrays.asList(user.getRoles()).contains(Utils.upperCaseToCamelCase(UserRoles.CUSTOMER.toString()))
                    && ticketEntity.getEmail().equals(commentBoundary.getEmail()))) {// Only supportAgent and original client who open the ticket can comment
            if (ticketEntity.getOpen()) {
                entity.setTicket(ticketEntity);
                entity.setCreatedTimeStamp(new Date());
                entity.setId(UUID.randomUUID().toString());
                return this.converter.fromEntity(commentDao.save(entity));
            }
            throw new BadRequestException("Can't comment to close tickets");
        }
        throw new BadRequestException("Only supportAgent and original client who open the ticket can comment");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentBoundary> getAllComments(CommentFilterType commentFilterType, String filterValue,
                                                int size, int page, CommentSortBy commentSortBy, SortOrder sortOrder) {
        Sort.Direction direction = sortOrder == SortOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;

        if (commentFilterType != null && filterValue != null) {
            if (commentFilterType.equals(CommentFilterType.BY_TICKET_ID)) {
                return this.commentDao
                        .findAllByTicket_Id(filterValue,
                                PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(commentSortBy.toString())))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
            else if (commentFilterType.equals(CommentFilterType.BY_CREATION)) {
                return this.commentDao
                        .findAllByCreatedTimeStampBetween(Utils.getFromDate(filterValue),new Date(),
                                PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(commentSortBy.toString())))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
            else if (commentFilterType.equals(CommentFilterType.BY_EMAIL)) {
                return this.commentDao.findAllByEmail(filterValue,
                        PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(commentSortBy.toString())))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
        }

        return this.commentDao.findAll(PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(commentSortBy.toString()))).getContent()
                .stream().map(this.converter::fromEntity).collect(Collectors.toList());
    }
}
