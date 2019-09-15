package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment uploadComment(String comment, User user, Image image) {

        Comment obj = new Comment();
        obj.setText(comment);
        obj.setCreatedDate(new Date());
        obj.setUser(user);
        obj.setImage(image);

        return commentRepository.uploadComment(obj);
    }
}
