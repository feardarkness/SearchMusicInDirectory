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
import java.util.ArrayList;

/**
 *
 * @author aalvarado
 */
public class FileByTypeVisitor extends AllFilesVisitor {

    private String type;

    public FileByTypeVisitor(String type) {
        if (type == null){
            type = "";
        }
        this.type = type;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        int dotPosition = fileName.lastIndexOf(".");
        String extension = file.getFileName().toString().substring(dotPosition + 1).toUpperCase();
        if (type.equals(extension)) {
            fileList.add(file.getFileName());
        }
        return CONTINUE;
    }
}
