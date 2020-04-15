package util;

import java.io.File;

public class Result {

    private String result;

    public String resolveResult(File file,int total,int empty,int effective,int comment) {
        result = "file:" + file.getPath() + " total:" + total + " empty:" + empty + " effective" + ":" + effective + " comment" + ":" + comment + ";";
        return this.result;
    }
}
