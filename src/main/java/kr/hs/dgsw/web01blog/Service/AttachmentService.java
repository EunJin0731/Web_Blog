package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Attachment;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    ResponseFormat attachment(MultipartFile srcfile);
}
