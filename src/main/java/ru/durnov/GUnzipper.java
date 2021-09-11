package ru.durnov;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class GUnzipper {
    public static void unzipFile(String filename) throws IOException {
        if (!filename.contains(".gz")) return;
        String resultFileName = filename.substring(0, filename.indexOf(".gz"));
        Files.deleteIfExists(Paths.get(resultFileName));
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(resultFileName)){
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, len);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
