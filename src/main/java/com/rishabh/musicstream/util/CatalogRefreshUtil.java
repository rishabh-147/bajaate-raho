package com.rishabh.musicstream.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CatalogRefreshUtil {
    public static List<Path> scanMp3Files(Path root) {
        try (Stream<Path> stream = Files.walk(root)) {
               return stream.filter(Files::isRegularFile)
                        .filter(path -> path.getFileName().toString().endsWith(".mp3"))
                        .toList();

        } catch (IOException e) {
            throw new RuntimeException("Exception while Initializing the catalog");
        }
    }
}
