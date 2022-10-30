package acs.data;

import acs.exceptions.BadRequestException;
import acs.utils.Product;
import acs.utils.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document
public class BlogPostEntity {
    @Id
    private String id;
    private User user;
    private Product product;
    private Date postingTimeStamp;
    private String language;
    private Map<String,Object> postContent;

    public BlogPostEntity(){
        this.postContent=new HashMap<>();
    }

    public BlogPostEntity(User user, Product product, Date postingTimeStamp, String language, Map<String,Object> postContent) {
        this.user = user;
        this.product = product;
        this.postingTimeStamp = postingTimeStamp;
        this.language = language;
        this.postContent = postContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user == null){
            throw new BadRequestException("user must be specified");
        }
        user.validate();
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null){
            throw new BadRequestException("product must be specified");
        }
        this.product = product;
    }

    public Date getPostingTimeStamp() {
        return postingTimeStamp;
    }

    public void setPostingTimeStamp(Date postingTimeStamp) {
        this.postingTimeStamp = postingTimeStamp;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, Object> getPostContent() {
        return postContent;
    }

    public void setPostContent(Map<String, Object> postContent) {
        this.postContent = postContent;
    }
}
