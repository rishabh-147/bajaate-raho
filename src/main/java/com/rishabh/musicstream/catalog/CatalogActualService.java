package com.rishabh.musicstream.catalog;


import com.rishabh.musicstream.util.CatalogRefreshUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@Component
@Slf4j
public class CatalogActualService implements CatalogService {

    @Value("${music.folder}")
    private String musicFolder;


    private Map<Long, SongMetadata> songMetadataMap = new HashMap<>();

    @Override
    public Optional<SongMetadata> getSong(long id) {
        return Optional.ofNullable(songMetadataMap.get(id));
    }

    @Override
    public List<SongMetadata> fetchAllSongs() {
        return new ArrayList<>(songMetadataMap.values());
    }

    @PostConstruct
    @Override
    public void refreshCatalog() throws RuntimeException {
        musicFolder = musicFolder.trim();
        long songIndex = 1;
        try {
            for (Path path : CatalogRefreshUtil.scanMp3Files(Paths.get(musicFolder))) {
                songMetadataMap.put(songIndex, SongMetadata.builder()
                        .id(songIndex)
                        .filePath(path)
                        .fileSize(Files.size(path))
                        .fileName(path.toFile().getName())
                        .duration(0)//Dont know
                        .mimeType(Files.probeContentType(path)) // Don''t know
                        .build());
                songIndex++;
            }
            log.info("Catalog initialized with {} songs", songMetadataMap.size());
        } catch (Exception e) {
            throw new RuntimeException("Exception while preparing catalog metadata");
        }
    }
}
