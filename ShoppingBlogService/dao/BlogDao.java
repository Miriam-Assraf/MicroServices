package acs.dao;

import acs.data.BlogPostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface BlogDao extends MongoRepository<BlogPostEntity, String> {

    // find all
    List<BlogPostEntity> findAllByPostingTimeStampBetween(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable page);
    // find all by user
    List<BlogPostEntity> findAllByUser_Email(@Param("email") String email, Pageable pageable);
    List<BlogPostEntity> findAllByUser_Email_AndPostingTimeStampBetween(@Param("email") String email, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);
    List<BlogPostEntity> findAllByUser_Email_AndLanguage(@Param("email") String email, @Param("language") String language, Pageable pageable);
    List<BlogPostEntity> findAllByUser_Email_AndProduct_Id(@Param("email") String email, @Param("productId") String productId, Pageable pageable);

    // find all by product
    List<BlogPostEntity> findAllByProduct_Id(@Param("productId") String productId, Pageable pageable);
    List<BlogPostEntity> findAllByProduct_Id_AndPostingTimeStampBetween(@Param("productId") String productId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);
    List<BlogPostEntity> findAllByProduct_Id_AndLanguage(@Param("productId") String productId, @Param("language") String language, Pageable pageable);

}
