/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod.filevisitors;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author aalvarado
 */
public class FileByExtensionVisitor extends AllFilesVisitor {

    private final Set<String> fileExtensions;

    public FileByExtensionVisitor(String... extensions) {
        fileExtensions = new HashSet<String>(Arrays.asList(extensions));
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        int dotPosition = fileName.lastIndexOf(".");
        String extension = file.getFileName().toString().substring(dotPosition + 1).toUpperCase();
        if (fileExtensions.contains(extension)) {
            fileList.add(file.getFileName());
        }
        return CONTINUE;
    }
}
