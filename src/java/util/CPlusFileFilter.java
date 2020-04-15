package util;


import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CPlusFileFilter extends FileFilter {
    public static Set<String> suffixs = new HashSet<>();
    private void initMap() {
        //初始化文件后缀名后续进行筛选
        suffixs.add(".c");
        suffixs.add(".cpp");
        suffixs.add(".cc");
        suffixs.add(".C");
        suffixs.add(".c++");
    }
    public CPlusFileFilter(){
        initMap();
    }

    @Override
    public List<File> doFilter(List<File> allFile) {
        return super.screenForName(allFile,suffixs);
    }
}
