package com.rishabh.musicstream.streaming;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.InputStream;

@Data
public class SongResource {

    private InputStream inputStream;
    private long contentLength;
    private String contentType;
    private String fileName;
    private long start;

    private long end;
    private HttpStatus status;
}
