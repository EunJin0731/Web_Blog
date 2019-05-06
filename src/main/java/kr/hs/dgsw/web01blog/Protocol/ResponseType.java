package kr.hs.dgsw.web01blog.Protocol;

public enum ResponseType {

    FAIL                    (0,"Fail to run"),

    USER_ADD                (101,"User Id [%d] Add"),
    USER_DELETE             (102,"User Delete"),
    USER_UPDATE             (103,"User Update"),
    USER_GET                (104,"User Get"),

    POST_GET                (201,"Post Get"),
    POST_DELETE             (202,"Post Delete"),
    POST_ADD                (203,"Post Add"),
    POST_UPDATE             (204,"Post Update"),

    ATTACHMENT_STORED       (301,"Attachment Success");

    final private int code;
    final private String desc;

    ResponseType(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int code(){
        return this.code;
    }

    public String desc(){
        return this.desc;
    }
}

