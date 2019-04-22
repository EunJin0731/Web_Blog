package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public List<PostUsernameProtocol> listAllPost(){
        return this.postService.listAllPosts();
    }

    @DeleteMapping("/deletePost/{id}")
    public boolean deletePost(@PathVariable Long id){
        return this.postService.deletePost(id);
    }

    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post post){
        return this.postService.addPost(post);
    }

    @PutMapping("/updatePost/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post){
        return this.postService.updatePost(id, post);
    }
}
