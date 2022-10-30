package acs.logic.db;

import acs.boundary.BlogPostBoundary;
import acs.dao.BlogDao;
import acs.data.BlogPostEntity;
import acs.exceptions.BadRequestException;
import acs.exceptions.NotFoundException;
import acs.logic.EnhancedBlogPostService;
import acs.logic.utils.*;
import acs.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceWithDB implements EnhancedBlogPostService {
    private BlogDao blogDao;
    private BlogPostConverter converter;

    @Autowired
    public BlogPostServiceWithDB(BlogDao blogDao, BlogPostConverter converter) {
        super();
        this.converter = converter;
        this.blogDao = blogDao;
    }

    @Override
    public BlogPostBoundary getBlog(String blogId) {
        BlogPostEntity blogPostEntity = this.blogDao.findById(blogId).orElseThrow(() -> new NotFoundException("No blog found by id: "+blogId));
        return this.converter.fromEntity(blogPostEntity);
    }

    @Override
    public BlogPostBoundary createPost(BlogPostBoundary post) {
        return this.converter.fromEntity(this.blogDao.save(converter.toEntity(post)));
    }

    @Override
    public List<BlogPostBoundary> getAll(String filterType, String filterValue, int page, int size, String sortBy, SortOrder sortOrder) {

        Sort.Direction direction = sortOrder == SortOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;

        if(filterType!=null &&filterValue!=null) {
            // byCreation
            if (filterType.equals(FilterTypePartial.BY_CREATION.toString())) {
                return this.blogDao.findAllByPostingTimeStampBetween(getFromDate(filterValue),
                        new Date(), PageRequest.of(page, size, direction, sortBy)).stream()
                        .map(this.converter::fromEntity).collect(Collectors.toList());
            }
            // byCount
            if(filterType.equals(FilterTypePartial.BY_COUNT.toString())){
                int count = Integer.parseInt(filterValue);
                if(count <= 0){
                    throw new BadRequestException("count must be greater than zero");
                }
                return this.blogDao.findAll(PageRequest.of(page, count, direction, sortBy)).stream()
                        .map(this.converter::fromEntity).collect(Collectors.toList());
            }
        }
        return this.blogDao.findAll(PageRequest.of(page, size, direction, sortBy)).stream()
                .map(this.converter::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<BlogPostBoundary> getAllByUser(String email, String filterType, String filterValue, int page, int size, String sortBy, SortOrder sortOrder) {
        Sort.Direction direction = sortOrder == SortOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        if (filterType != null && filterValue != null) {
            // byLanguage
            if (filterType.equals(FilterType.BY_LANGUAGE.toString())) {
                return this.blogDao.findAllByUser_Email_AndLanguage(email, filterValue,
                        PageRequest.of(page, size, direction, sortBy)).stream().map(this.converter::fromEntity)
                        .collect(Collectors.toList());
            }
            // byCreation
            if (filterType.equals(FilterType.BY_CREATION.toString())) {
                return this.blogDao.findAllByUser_Email_AndPostingTimeStampBetween(email, getFromDate(filterValue), new Date(),
                        PageRequest.of(page, size, direction, sortBy)).stream().map(this.converter::fromEntity)
                        .collect(Collectors.toList());
            }
            // byProduct
            if (filterType.equals(FilterType.BY_PRODUCT.toString())) {
                return this.blogDao.findAllByUser_Email_AndProduct_Id(email, filterValue,
                        PageRequest.of(page, size, direction, sortBy)).stream().map(this.converter::fromEntity)
                        .collect(Collectors.toList());
            }
        }
        return this.blogDao.findAllByUser_Email(email,
                PageRequest.of(page, size, direction, sortBy)).stream().map(this.converter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogPostBoundary> getAllByProduct(String productId, String filterType, String filterValue, int page, int size, String sortBy, SortOrder sortOrder) {
        Sort.Direction direction = sortOrder == SortOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        if(filterType!=null&&filterValue!=null){
            // byLanguage
            if(filterType.equals(FilterType.BY_LANGUAGE.toString())){
                return this.blogDao.findAllByProduct_Id_AndLanguage(productId, filterValue,
                        PageRequest.of(page, size, direction, sortBy)).stream().map(this.converter::fromEntity)
                        .collect(Collectors.toList());
            }
            // byCreation
            if(filterType.equals((FilterType.BY_CREATION.toString()))){
                return this.blogDao.findAllByProduct_Id_AndPostingTimeStampBetween(productId, getFromDate(filterValue),
                        new Date(), PageRequest.of(page, size, direction, sortBy)).stream().map(this.converter::fromEntity)
                        .collect(Collectors.toList());
            }
        }
        return this.blogDao.findAllByProduct_Id(productId,  PageRequest.of(page, size, direction, sortBy)).stream().map(this.converter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        blogDao.deleteAll();
    }

    private Date getFromDate(String timeEnum){
        LocalDateTime localDateTime = LocalDateTime.now();
        if(timeEnum.equals(TimeEnum.LAST_DAY.toString())){
            localDateTime = localDateTime.minusDays(1);
        }
        else if(timeEnum.equals(TimeEnum.LAST_WEEK.toString())){
            localDateTime = localDateTime.minusDays(7);
        }
        else if(timeEnum.equals(TimeEnum.LAST_MONTH.toString())){
            localDateTime = localDateTime.minusDays(30);
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    }
}
