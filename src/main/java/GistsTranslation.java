import Utils.Utils;
import model.GistEntity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GistsTranslation {

    private static final String SYNTAX_HIGHLIGHTER = "```";

    static void translateGistsOfABlogPost(String filePath, String targetLanguage) {
        try {
            String markdownText = Utils.readFile(filePath);
            Pattern p = Pattern.compile("\\{\\{< *gist (.+?) (.+?) \"(.+?)\" *>}}");
            Matcher m = p.matcher(markdownText);

            List<GistEntity> gistEntities = new ArrayList<GistEntity>();

            while (m.find()) {
                GistEntity aGistEntity = new GistEntity();
                aGistEntity.gistShortcode = m.group(0);
                aGistEntity.userName = m.group(1);
                aGistEntity.gistID = m.group(2);
                aGistEntity.filename = m.group(3);
                gistEntities.add(aGistEntity);
            }

            // Since all gists of a blog post have the same RawID, fetch RawID using any Gist username and gistID.
            String rawID = "";
            if(gistEntities.size() > 0) {
                rawID = getRawID(gistEntities.get(0).userName, gistEntities.get(0).gistID);
            }

            for(GistEntity gistEntity : gistEntities) {
                String gistContent = GistsTranslation.getGistContent(gistEntity.userName,
                        gistEntity.gistID, rawID, gistEntity.filename);
                String translatedGist = GistsTranslation.translateGistComments(gistContent, targetLanguage);
                // Enclose in code highlighter syntax.
                StringBuilder codeSample = new StringBuilder(SYNTAX_HIGHLIGHTER);
                codeSample.append("\n");
                codeSample.append(translatedGist);
                codeSample.append("\n");
                codeSample.append(SYNTAX_HIGHLIGHTER);

                markdownText = markdownText.replace(gistEntity.gistShortcode ,codeSample.toString());
                // Replace content of the file with updated markup text.
                FixIssuesInExportedContent.overwriteFileContent(filePath, markdownText);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static String getGistContent(String userName, String gistID, String rawID, String filename) {
        String gistContent = "";

        try {
            String pageSourceURL = " https://gist.githubusercontent.com/" + userName + "/" +
                    gistID + "/raw/" + rawID + "/" + filename;
            gistContent = getPageSourceByLine(pageSourceURL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return gistContent;
    }

    static String translateGistComments(String gistContent, String targetLanguage) {
        ArrayList<String> commentsToTranslate = new ArrayList<>();

        String[] lines = gistContent.split("\n");

        for(String line : lines) {
            if(line.startsWith("//")) {
                String commentLine = line.replaceFirst("//", "");
                commentLine = commentLine.trim();
                commentsToTranslate.add(commentLine);
            }
        }

        ArrayList<String> translatedComments = GoogleTranslationAPI.translateArrayList(commentsToTranslate, targetLanguage);
        if(commentsToTranslate.size() == translatedComments.size()) {
            for(int i=0; i<commentsToTranslate.size(); i++) {
                gistContent = gistContent.replace(commentsToTranslate.get(i), translatedComments.get(i));
            }
        }

        return gistContent;
    }

    static String getRawID(String userName, String gistID) {
        String rawID = "";
        try {
            //String pageSource = Main.getPageSource("https://gist.github.com/conholdate-gists/db16de7a770e0ad02ce9a418a6bee38e");
            String pageSource = FixIssuesInExportedContent.getPageSource("https://gist.github.com/" + userName + "/" + gistID);
            //Pattern p = Pattern.compile("href=\"/conholdate-gists/db16de7a770e0ad02ce9a418a6bee38e/raw/(.+?)/");
            Pattern p = Pattern.compile("href=\"/" + userName + "/" + gistID + "/raw/(.+?)/");
            Matcher m = p.matcher(pageSource);
            if(m.find()) {
                rawID = m.group(1);
                return rawID;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rawID;
    }

    public static String getPageSourceByLine(String aURL) throws Exception {
        //Instantiating the URL class
        URL url = new URL(aURL);
        //Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()) {
            String aline = sc.nextLine();
            //System.out.println(aline);
            sb.append(aline).append("\n");
            //sb.append(sc.next());
        }
        // Remove last \n
        if(sb.length() >=1) {
            sb.delete(sb.length() - 1, sb.length());
        }
        //Retrieving the String from the String Buffer object
        return sb.toString();
    }

}
