import parser.Statistics;
import util.FileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Counter {
    private ExecutorService executorService;
    private Statistics statistics;
    private FileFilter fileFilter;
    private List<File> allFile = new ArrayList<>();
    private String path;

    public Counter(){
        initExecutor();
    }

    private void initExecutor() {
        executorService = Executors.newFixedThreadPool(20);
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void count() {
        statistics.setFileFilter(this.fileFilter);
        getFiles(this.path);
        List<File> files = this.fileFilter.doFilter(allFile);
        statistics.setFiles(files);
        if(statistics!= null){
            statistics.setExecutorService(executorService);
            statistics.doStatistics();
        }
        quit();
    }
    /*
     *  程序退出
     */
    private void quit() {
        executorService.shutdown();
        while (!executorService.isTerminated()){}
        this.executorService=null;
    }
    private void getFiles(String path) {
        File file = new File(path);
        if(file.isDirectory()){
            File[] files=file.listFiles();
            if(files!=null){
                for(File f:files){
                    getFiles(f.getPath()); //递归获取文件
                }
            }
        }else{
            allFile.add(file);
        }
    }

    public void setFileFilter(FileFilter fileFilter) {
        this.fileFilter = fileFilter;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
