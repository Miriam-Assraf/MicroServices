package acs.rest;

import acs.logic.utils.CommentFilterType;
import acs.utils.CommentSortBy;
import acs.utils.SortOrder;
import acs.boundary.CommentBoundary;
import acs.logic.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //GET
// Get all comments by filter type with pagination
    @RequestMapping(path = "/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentBoundary[] getAllComments(
            @RequestParam(name = "filterType", required = false) CommentFilterType commentFilterType,
            @RequestParam(name = "filterValue", required = false) String filterValue,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "commentSortBy", required = false, defaultValue = "EMAIL") CommentSortBy commentSortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") SortOrder sortOrder) {
        return commentService.getAllComments(commentFilterType, filterValue, size, page, commentSortBy, sortOrder).
                toArray(new CommentBoundary[0]);
    }

    //POST
    // Create COMMENT
    @RequestMapping(path = "/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentBoundary createComment(@RequestBody CommentBoundary commentBoundary) {
        return this.commentService.createComment(commentBoundary);
    }


}
