import Utils.Utils;
import model.GistEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogPostParsing {

    static String targetLanguage = "";
    public static void translateABlogPost(String filePath, String targetLang) {
        try {
            targetLanguage = targetLang;

            String markdownText = Utils.readFile(filePath);
            // Find paragraphs
            Pattern p = Pattern.compile("(.+?)\n");
            Matcher m = p.matcher(markdownText);

            ArrayList<String> contentList = new ArrayList<>();
            while (m.find()) {
                //System.out.println("------------------New Para--------------");
                //System.out.println(m.group(0));
                contentList.add(m.group(0));
            }

            for(String textString : contentList) {
                if(textString.startsWith("title: ")) {
                    //markdownText = translateTitle(textString, markdownText);
                } else if(textString.startsWith("summary: ")) {
                    //markdownText = translateSummary(textString, markdownText);
                } else if(textString.startsWith("url: ")) {
                    markdownText = translateURL(textString, markdownText);
                    System.out.println(markdownText);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static String translateTitle(String textString, String markdownText) {
        Pattern p = Pattern.compile("title: ['|\"](.+?)['|\"]");
        Matcher m = p.matcher(textString);

        if (m.find()) {
            String matchedString = m.group(0);
            String textToTranslate = m.group(1);

            String translatedString = GoogleTranslationAPI.translateString(textToTranslate, targetLanguage);

            markdownText = markdownText.replace(matchedString, "title: \"" + translatedString + "\"");
        }
        return markdownText;
    }

    static String translateSummary(String textString, String markdownText) {
        Pattern p = Pattern.compile("summary: ['|\"](.+?)['|\"]");
        Matcher m = p.matcher(textString);

        if (m.find()) {
            String matchedString = m.group(0);
            String textToTranslate = m.group(1);

            String translatedString = GoogleTranslationAPI.translateString(textToTranslate, targetLanguage);

            markdownText = markdownText.replace(matchedString, "summary: \"" + translatedString + "\"");
        }
        return markdownText;
    }

    static String translateURL(String textString, String markdownText) {
        Pattern p = Pattern.compile("url: (.+?)\n");
        Matcher m = p.matcher(textString);

        if (m.find()) {
            String matchedString = m.group(0);
            String textToTranslate = m.group(1);

            String translatedString = GoogleTranslationAPI.translateString(textToTranslate, targetLanguage);
            // Remove \n as last character from matchedString.


            markdownText = markdownText.replace(matchedString, "url: " + translatedString);
        }
        return markdownText;
    }

}
