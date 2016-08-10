package springBootApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class JspController {

    @RequestMapping(value = "/")
    public String jspIndex() {
        return "index";
    }

    @RequestMapping(value = "/jspTest")
    public String jspTest() {
        return "test";
    }

    @RequestMapping(value = "/stringThing")
    public String stringThing() {
        return "stringThing";
    }

    @RequestMapping(value= "/combobulateString")
    public String combobulateString(String sentence, ModelMap map){
       map.addAttribute("sentence", sentence);  //sentence can be changed to what ever you want returned to the jsp

        // Count Words
        String[] words = sentence.split("\\s+");
        int wordCount = words.length;
        map.addAttribute("wordCount",wordCount);

        // Count chars
        char[] characters = sentence.toCharArray();
        int charCount = characters.length;
        map.addAttribute("charCount",charCount);

        // Reverse String
        StringBuilder reverseString = new StringBuilder();
        reverseString.append(sentence);
        reverseString.reverse();
        map.addAttribute("reverseString",reverseString);

        // Translate to Pig Latin
        String pigLatin = pigLatinTranslator(words);
        map.addAttribute("pigLatin",pigLatin);

        // Create Google links
        ArrayList<String> googleLinks = new ArrayList<>();
        for(String word : words) {
            googleLinks.add("https://www.google.com/webhp?#safe=off&hl=en&q="+word);
        }
        map.addAttribute("googleLinks",googleLinks);
        return "stringThing";
    }

    private String pigLatinTranslator(String[] words) {
        String vowels = "aeiouAEIOU";
        String pigLatin = "";
        for(String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (vowels.contains(""+word.charAt(i))) {
                    String prefix = word.substring(0, i);
                    String suffix = word.substring(i);
                    word = suffix + prefix + "ay";
                    break;
                }
            }
            pigLatin += word+" ";
        }
        return pigLatin;
    }
}