package parser;


import util.FileFilter;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;

public abstract class Statistics {
    private  ExecutorService executorService;
    private FileFilter fileFilter;
    private List<File> files;
    public abstract void doStatistics();

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public FileFilter getFileFilter() {
        return fileFilter;
    }

    public void setFileFilter(FileFilter fileFilter) {
        this.fileFilter = fileFilter;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
