package edu.gcccd.csis;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SelfAware implements Language {

    public static void main(String[] args) throws Exception {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";
        SelfAware sa = new SelfAware();
        sa.append(code, "\n//Keyword occurrences: " + sa.occurrences(code));

    }

    @Override
    public int occurrences(String sourceFile) throws Exception {
        File file = new File(sourceFile);
        String line = new String(Files.readAllBytes(Paths.get(sourceFile)));
        int counter = 0;
        String[] words = line.split("\\W+");

        for (String src : words) {
            for (String reserved : ReservedWords) {
                if (reserved.equals(src)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public void append(final String sourceFile, final String message) throws IOException {
        Path p = Paths.get(sourceFile);
        Files.write(p, message.getBytes(), StandardOpenOption.APPEND);
    }
}



//Keyword occurrences: 35
//Keyword occurrences: 31