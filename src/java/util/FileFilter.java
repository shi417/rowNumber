package util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class FileFilter {
    public abstract List<File> doFilter(List<File> allFile);
    /**
     * 过滤文件后缀名
     *
     * @return
     */
    public List<File> screenForName(List<File> allFile, Set<String> suffixs) {
        int count = allFile.size();
        List<File> res = new ArrayList<File>();
        int i = 0;
        int j = 0;
        while (i < count) {
            String fileName = allFile.get(i).getName();
            Iterator iterator = suffixs.iterator();
            boolean isSuitableFile = false;
            while (iterator.hasNext()) {
                String suffix = (String) iterator.next();
                if (fileName.endsWith(suffix)) {
                    isSuitableFile = true;
                    break;
                }
            }
            if (isSuitableFile) {
                res.add(allFile.get(i));
                j++;
            }
            i++;
        }
        return res;
    }
}
