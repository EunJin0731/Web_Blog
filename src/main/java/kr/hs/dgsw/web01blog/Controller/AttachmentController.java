package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Repository.AttachmentRepository;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import kr.hs.dgsw.web01blog.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/attachment")
    public ResponseFormat upload(@RequestPart MultipartFile srcfile) {
        return this.attachmentService.attachment(srcfile);
    }

    /*@GetMapping("/download/{type}/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, @PathVariable String type){
        try {
            String filepath;
            String filename;
            if(type.equals("user")) {
                Optional<User> found = this.userRepository.findById(id);
                filepath = found.get().getFilelocal();
                filename = found.get().getFilename();
            }else{
                Optional<Post> found = this.postRepository.findById(id);
                filepath = found.get().getFilelocal();
                filename = found.get().getFilename();
            }
            File file = new File(filepath);
            if (file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) mimeType = "application/octet-stream";

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            response.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }*/
}
