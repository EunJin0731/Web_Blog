package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp1 implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostUsernameProtocol> listAllPosts() {
        List<Post> postList = this.postRepository.findAll();
        List<PostUsernameProtocol> puplist = new ArrayList<>();
        postList.forEach(post->{
            puplist.add(new PostUsernameProtocol(post, this.userRepository.findById(post.getUserid()).map(u->u.getName()).orElse(null)));
        });
        return puplist;
    }

    @Override
    public PostUsernameProtocol addPost(Post post) {
        Post added = this.postRepository.save(post);
        return new PostUsernameProtocol(added, this.userRepository.findById(added.getId()).map(u->u.getName()).orElse(null));
    }

    @Override
    public boolean deletePost(Long id) {
        try{
            this.postRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Post updatePost(Long id, Post post) {
        return this.postRepository.findById(id)
                .map(found ->{
                    found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
                    found.setUserid(Optional.ofNullable(post.getUserid()).orElse(found.getUserid()));
                    return this.postRepository.save(found);
                })
                .orElse(null);
    }
}
