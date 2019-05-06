package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp1 implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        User u = this.userRepository.save(new User("abc", "abcdgsw", "1234", "111111", "111111111", "path"));
        this.postRepository.save(new Post(u.getName(), "hi 111", "123124"));
        this.postRepository.save(new Post(u.getName(), "hi 222", "123123123123"));
        this.postRepository.save(new Post(u.getName(), "hi 333", "123123123"));
    }

    @Override
    public ResponseFormat get(String acccount) {
        Post post = this.postRepository.findTopByUserIdOrderByIdDesc(acccount).orElse(null);
        return new ResponseFormat(ResponseType.POST_GET, post);
    }

    @Override
    public ResponseFormat getPost() {
        List<Post> postList = this.postRepository.findAll();
        List<PostUsernameProtocol> puplist = new ArrayList<>();
        postList.forEach(post->{
            puplist.add(new PostUsernameProtocol(post, this.userRepository.findByAccount(post.getUserId()).map(u->u.getName()).orElse(null)));
        });
        return new ResponseFormat(ResponseType.POST_GET, postList);
    }

    @Override
    public ResponseFormat addPost(Post post) {
        Post added = this.postRepository.save(post);
        new PostUsernameProtocol(added, this.userRepository.findByAccount(added.getUserId()).map(u->u.getName()).orElse(null));
        return new ResponseFormat(ResponseType.POST_ADD, added);
    }

    @Override
    public ResponseFormat deletePost(Long postId) {
        this.postRepository.deleteById(postId);
        return new ResponseFormat(ResponseType.POST_DELETE, postId);
    }

    @Override
    public ResponseFormat updatePost(Long postId, Post post) {
        return new ResponseFormat(ResponseType.POST_UPDATE, this.postRepository.findById(postId)
                .map(found ->{
                    found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
                    found.setTitle(Optional.ofNullable(post.getTitle()).orElse(found.getTitle()));
                    return this.postRepository.save(found);
                })
                .orElse(null));
    }

    @Override
    public ResponseFormat PostCount(String account){
        List<Post> postList = this.postRepository.findAll();
        List<PostUsernameProtocol> puplist = new ArrayList<>();
        postList.forEach(post->{
            puplist.add(new PostUsernameProtocol(post, this.userRepository.findByAccount(account).map(u->u.getName()).orElse(null)));
        });
        return new ResponseFormat(ResponseType.POST_GET, puplist.size());
    }

    @Override
    public ResponseFormat view(Long postId) {
        Optional<Post> post = this.postRepository.findById(postId);
        return new ResponseFormat(ResponseType.POST_GET, post.get());
    }
}
