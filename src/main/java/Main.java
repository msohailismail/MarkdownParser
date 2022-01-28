import org.unbescape.html.HtmlEscape;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //String filePath = "content\\Conholdate.Total\\2022-01-05-crop-and-resize-jpeg-images-using-csharp\\index.zh.md";
        //GistsTranslation.translateGistsOfABlogPost(filePath, "zh");

        String filePath = "content\\Conholdate.Total\\2022-01-05-crop-and-resize-jpeg-images-using-csharp\\index.md";
        BlogPostParsing.translateABlogPost(filePath, "fr");
    }

    public static void step8() {
        File folder = new File("content/Conholdate.Total/");
        File[] listOfFiles = folder.listFiles(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return !fileName.equals("_index.md");
            }
        });

        for (File listOfFolder : listOfFiles) {
            if (listOfFolder.isDirectory()) {
                File indexFile = new File(listOfFolder, "index.md");
                if (indexFile.exists()) {
                    System.out.println(indexFile.getPath());
                }
            }
        }
    }

}
