package acs.logic;

import acs.boundary.CommentBoundary;
import acs.logic.utils.CommentFilterType;
import acs.utils.CommentSortBy;
import acs.utils.SortOrder;
import java.util.List;

public interface CommentService {

    CommentBoundary createComment(CommentBoundary commentBoundary);

    List<CommentBoundary> getAllComments(CommentFilterType commentFilterType, String filterValue, int size, int page, CommentSortBy commentSortBy, SortOrder sortOrder);
}
