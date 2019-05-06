package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/get/{account}")
    public ResponseFormat GetPost(@PathVariable String account) {return this.postService.get(account);}
    @GetMapping("/post")
    public ResponseFormat getPost(){
        return this.postService.getPost();
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseFormat deletePost(@PathVariable Long postId){
        return this.postService.deletePost(postId);
    }

    @PostMapping("/addPost")
    public ResponseFormat addPost(@RequestBody Post post){
        return this.postService.addPost(post);
    }

    @PutMapping("/updatePost/{postId}")
    public ResponseFormat updatePost(@PathVariable Long postId, @RequestBody Post post){
        return this.postService.updatePost(postId, post);
    }

    @GetMapping("/view/{postId}")
    public ResponseFormat viewPost(@PathVariable Long postId){
        return this.postService.view(postId);
    }

    @GetMapping("/PostCount/{account}")
    public ResponseFormat PostCount(@PathVariable String account){
        return this.postService.PostCount(account);
    }
}
