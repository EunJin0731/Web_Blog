package kr.hs.dgsw.web01blog.Protocol;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AttachmentProtocol {
    private String storedPath;
    private String originaName;


    public AttachmentProtocol(String storedPath, String originaName) {
        this.storedPath = storedPath;
        this.originaName = originaName;
    }
}
