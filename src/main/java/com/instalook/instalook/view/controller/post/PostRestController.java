package com.instalook.instalook.view.controller.post;

import com.instalook.instalook.model.dal.dto.PostDTO;
import com.instalook.instalook.model.dal.entity.Post;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aya
 */
@RestController
@RequestMapping("/post")
public class PostRestController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public int addBarber(@RequestBody PostDTO post) {
        return postService.savePost(post);
    }

    /**
     *
     * @param salonId
     * @return
     */
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "application/json")
    public List<Post> getAllSalonPosts(@RequestParam("salonId") int salonId) {
        return postService.getAllSalonPosts(salonId);
    }

    @RequestMapping(value = "/salons", method = RequestMethod.GET, produces = "application/json")
    public List<Salon> getAllUserSalons(@RequestParam("userId") int userId) {
        return postService.getAllUserSalons(userId);
    }

}
