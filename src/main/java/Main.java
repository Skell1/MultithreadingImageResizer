import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "data";
        String dstFolder = "data2";
        File srcDir = new File(srcFolder);
        int cores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < files.length; i++) {
            service.execute(new ImageRes(files[i],newWidth,dstFolder,start));
        }
        service.shutdown();
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
