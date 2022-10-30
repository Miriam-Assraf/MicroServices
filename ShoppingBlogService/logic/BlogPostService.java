package acs.logic;

import acs.boundary.BlogPostBoundary;
import acs.logic.utils.FilterType;
import acs.logic.utils.FilterTypePartial;
import acs.logic.utils.SortOrder;
import java.util.List;

public interface BlogPostService {

    BlogPostBoundary getBlog (String blogId);

    BlogPostBoundary createPost (BlogPostBoundary post);

    List<BlogPostBoundary> getAll(String filterType, String filterValue, int page, int size, String sortBy, SortOrder sortOrder);

    List<BlogPostBoundary> getAllByUser(String email, String filterType, String filterValue, int page, int size, String sortBy, SortOrder sortOrder);

    List<BlogPostBoundary> getAllByProduct(String productId, String filterType, String filterValue,int page, int size, String sortBy, SortOrder sortOrder);
}

