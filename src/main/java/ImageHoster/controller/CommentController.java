package ImageHoster.controller;
import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId,@PathVariable("imageTitle") String imageTitle,@RequestParam(name = "comment") String comment,HttpSession session,Model model) throws IOException {
        Image image = imageService.getImageByTitle(imageTitle,imageId);
        if (image == null){
           Image obj = new Image();
           obj.setId(imageId);
           obj.setTitle(imageTitle);

            model.addAttribute("image", obj);
            model.addAttribute("tags", obj.getTags());
            //model.addAttribute("comments",comment);
            return "redirect:/images/" + imageId + "/" + imageTitle + "";
        }
        else {
            User user = (User) session.getAttribute("loggeduser");

            Comment com = commentService.uploadComment(comment, user, image);

            model.addAttribute("image", image);
            model.addAttribute("tags", image.getTags());
            model.addAttribute("comments", com);

            return "/images/image";
        }
    }
}
