package kr.hs.dgsw.web01blog.Protocol;

import kr.hs.dgsw.web01blog.Domain.Post;
import lombok.Data;

@Data
public class PostUsernameProtocol extends Post{

    private String username;

    public PostUsernameProtocol(Post p, String username){
        super(p);
        this.username = username;
    }
}
