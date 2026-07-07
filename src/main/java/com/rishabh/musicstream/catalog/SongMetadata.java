package com.rishabh.musicstream.catalog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.nio.file.Path;

@Data
@Builder
public class SongMetadata implements Serializable {

    private Long id;

    private String title;

    private String fileName;

    private long duration;

    private long fileSize;

    private transient String mimeType;

    private transient Path filePath;
}
