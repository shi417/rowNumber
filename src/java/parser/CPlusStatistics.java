package parser;

import java.io.File;
import java.util.*;

public class CPlusStatistics extends Statistics{
    public void doStatistics() {
        List<File> cfiles = super.getFiles();
        for (File file : cfiles
        ) {
            Runnable task = new CplusThread(file);
            getExecutorService().execute(task);
        }
    }
}
