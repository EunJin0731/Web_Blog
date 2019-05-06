package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;

import java.util.List;

public interface PostService {

    ResponseFormat getPost();

//    List<PostUsernameProtocol> listAllPosts();

    ResponseFormat addPost(Post post);

    ResponseFormat deletePost(Long postId);

    ResponseFormat updatePost(Long id, Post post);

    ResponseFormat PostCount(String account);

    ResponseFormat view(Long postId);

    ResponseFormat get(String account);
}
