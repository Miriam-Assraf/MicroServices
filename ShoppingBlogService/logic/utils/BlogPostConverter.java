package acs.logic.utils;
import acs.boundary.BlogPostBoundary;
import acs.data.BlogPostEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BlogPostConverter {

    public BlogPostBoundary fromEntity(BlogPostEntity entity) {
        BlogPostBoundary rv = new BlogPostBoundary();
        rv.setId(entity.getId());
        rv.setLanguage(entity.getLanguage());
        rv.setPostContent(entity.getPostContent());
        rv.setPostingTimeStamp(entity.getPostingTimeStamp());
        rv.setProduct(entity.getProduct());
        rv.setUser(entity.getUser());
        return rv;
    }

    public BlogPostEntity toEntity(BlogPostBoundary boundary) {
        BlogPostEntity rv = new BlogPostEntity();
        rv.setLanguage(boundary.getLanguage());
        rv.setPostContent(boundary.getPostContent());
        rv.setPostingTimeStamp(new Date());
        rv.setProduct(boundary.getProduct());
        rv.setUser(boundary.getUser());
        return rv;
    }
}
