package acs.kafka;

import acs.boundary.CommentBoundary;
import acs.boundary.TicketBoundary;
import acs.logic.CommentService;
import acs.logic.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {

    private TicketService ticketService;
    private CommentService commentService;

    @Autowired
    public KafkaConsumer(TicketService ticketService, CommentService commentService) {
        this.ticketService = ticketService;
        this.commentService = commentService;
    }

    @Bean
    public Consumer<TicketBoundary> createTicketConsumer(){
        return ticket -> ticketService.createTicket(ticket);
    }

    @Bean
    public Consumer<CommentBoundary> createCommentConsumer(){
        return comment -> commentService.createComment(comment);
    }

}
