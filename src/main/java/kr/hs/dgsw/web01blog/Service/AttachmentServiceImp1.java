package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Attachment;
import kr.hs.dgsw.web01blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AttachmentServiceImp1 implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public ResponseFormat attachment(MultipartFile srcfile) {
        String destFilename
                = "C:/Users/JEJ/IdeaProjects/web_01_0326/upload/"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + srcfile.getOriginalFilename();
        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcfile.transferTo(destFile);
            return new ResponseFormat(ResponseType.ATTACHMENT_STORED, new AttachmentProtocol(destFilename, srcfile.getOriginalFilename()));
        } catch (Exception ex) {
            return null;
        }
    }

}
