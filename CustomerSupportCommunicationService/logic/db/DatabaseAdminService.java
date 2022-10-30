package acs.logic.db;

import acs.dao.CommentDao;
import acs.dao.TicketDao;
import acs.logic.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAdminService implements AdminService {
    private TicketDao ticketDao; // Data access object
    private CommentDao commentDao;

    @Autowired
    public DatabaseAdminService(TicketDao ticketDao, CommentDao commentDao) {
        this.ticketDao = ticketDao;
        this.commentDao = commentDao;
    }

    @Override
    public void deleteAll() {
        this.commentDao.deleteAll();
        this.ticketDao.deleteAll();
    }
}
