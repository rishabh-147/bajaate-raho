package com.rishabh.musicstream.streaming;

import com.rishabh.musicstream.catalog.CatalogService;
import com.rishabh.musicstream.catalog.SongMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class StreamingService {

    private final CatalogService catalog;

    public StreamingService(CatalogService catalog){
        this.catalog = catalog;
    }

    public List<SongMetadata> getSongs(){
        return catalog.fetchAllSongs();
    }

    public long getSongLength(long id ){
        return catalog.getSong(id).orElseThrow(() ->
                new RuntimeException("Song not found : " + id)).getFileSize();
    }

    public SongMetadata getRandomSongMetaData(){
        int id = ThreadLocalRandom.current().nextInt(1, catalog.fetchAllSongs().size());

        return catalog.getSong(id).orElseThrow(() -> new RuntimeException("Unable to fetch metadata for the SongId :: "+id));

    }

    public SongResource stream(long id, long start){
        SongMetadata metadata = catalog.getSong(id)
                .orElseThrow(() ->
                        new RuntimeException("Song not found : " + id));
        try {

                SongResource resource = new SongResource();

                FileInputStream fileInputStream = createInputStream(metadata.getFilePath(), start);

                resource.setInputStream(fileInputStream);
                resource.setContentLength(metadata.getFileSize());
                resource.setContentType(metadata.getMimeType());
                resource.setFileName(metadata.getFileName());
                return resource;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileInputStream createInputStream(Path path, long startRange) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(path.toFile());
        fileInputStream.skip(startRange);
        return fileInputStream;
    }

}
