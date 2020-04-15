import parser.CPlusStatistics;
import util.CPlusFileFilter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("路径不能为空,程序退出.");
            return;
        }
//        Scanner in = new Scanner(System.in);
//        String path = in.nextLine();
        String path = args[0];
        Counter counter = new Counter();
        counter.setPath(path);
        //注册分析器
        counter.setStatistics(new CPlusStatistics());
        //注册文件过滤器
        counter.setFileFilter(new CPlusFileFilter());
        counter.count();
    }
}
