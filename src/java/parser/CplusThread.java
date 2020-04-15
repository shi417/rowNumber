package parser;

import util.Result;

import java.io.*;

public class CplusThread implements Runnable {
    private File file;
    private Result result;
    private int total = 0;
    private int empty = 0;
    private int effective = 0;
    private int comment = 0;
    private boolean isCommenting = false;
    private boolean isString = false;


    public CplusThread(File file) {
        this.file = file;
        this.result = new Result();
    }

    @Override
    public void run() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = br.readLine()) != null) {
                pattern(line);
            }
            br.close();
            System.out.println(result.resolveResult(file, total, empty, effective, comment));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void pattern(String line) {
        total++;
        line = line.trim();
        if (line.length() < 1) {
            empty++;
        }else{
            judeComment(line);
        }
    }

    private void judeComment(String line) {
        boolean isComment = false;
        boolean isEffective = false;
        char[] chars = line.toCharArray();

        out:if(isCommenting){
            //行开头即在段落注释内
            for(int i = 0;i<chars.length;i++){
                if(chars[i] =='*'&&i < chars.length-1){
                    if(chars[i+1]=='/'){
                        isCommenting = false;
                        isComment = true;
                        i++;
                        continue;
                    }
                }
                if(!isCommenting){
                    if(chars[i] =='/'&&i < chars.length-1&&chars[i+1] =='/'){
                        break;
                    }else{
                        isEffective = true;
                    }
                }
            }
        }else{
            //初始为 "//"
            if(chars.length>=2&&chars[0]=='/'&&chars[1]=='/'){
                isComment = true;
                break out;
            }
            //初始为"/*"
            if(chars.length>=2&&chars[0]=='/'&&chars[1]=='*'){
                isComment = true;
                isCommenting =true;
                if(chars.length>2){
                    for(int i = 2;i<chars.length;i++){
                        if(chars[i] =='*'&&i < chars.length-1&&chars[i+1]=='/'){
                            isCommenting =false;
                        }
                        if(!isCommenting){
                            if(chars[i] =='/'&&i < chars.length-1&&chars[i+1] =='/'){
                                break out;
                            }else{
                                isEffective = true;
                            }
                        }
                    }
                }
            }else{
                //不在段落注释内切不以注释开头即为有效代码，排查当前行是否有注释
                isEffective = true;
                for(int i = 0;i<chars.length;i++){
                    if(chars[i]=='\"'&&i>1&&chars[i-1]!='\\'){
                        if(isString){
                            isString=false;
                        }else{
                            isString=true;
                        }
                    }
                    if(chars[i] =='/'&&i < chars.length-1&&chars[i+1] =='*'&&!isString){
                        isCommenting = true;
                        isComment = true;
                        i++;
                        continue;
                    }
                    //当前行进入段落注释 寻找"*/"
                    if(isCommenting){
                        if(chars[i] =='*'&&i < chars.length-1&&chars[i+1]=='/'){
                            isCommenting =false;
                            i++;
                            continue;
                        }
                    }
                    if(chars[i] =='/'&&i < chars.length-1&&chars[i+1] =='/'&&!isString){
                        isComment = true;
                        break;
                    }
                }
            }
        }
        if(isComment){
            comment++;
        }
        if(isEffective){
            effective++;
        }
    }

}
