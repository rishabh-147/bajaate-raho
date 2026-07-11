package com.rishabh.musicstream.streaming;

import com.rishabh.musicstream.catalog.SongMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1")
public class StreamController {
    @Autowired
    private StreamingService service;

    @GetMapping("/radio")
    public ResponseEntity<?> getRandomSongData() {
        return ResponseEntity.ok(service.getRandomSongMetaData());
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<InputStreamResource> streamSong(
            @PathVariable(name = "id") long id,
            @RequestHeader HttpHeaders headers) {

        long totalFileLengthInBytes = service.getSongLength(id);
        SongResource resource = null;
        if(!headers.getRange().isEmpty()) {

                    resource = service.stream(id, headers.getRange().getFirst().getRangeStart(totalFileLengthInBytes));
        }else{
            resource = service.stream(id, 0);
        }

        return buildResponse(resource, getRange(headers), totalFileLengthInBytes);
    }

    private ResponseEntity<InputStreamResource> buildResponse(
            SongResource resource,
            HttpRange range,
            long totalFileLength) {

        long requiredLength;

        if (range == null) {
            requiredLength = totalFileLength;
        } else {
            requiredLength = range.getRangeEnd(totalFileLength) - range.getRangeStart(totalFileLength) + 1;
        }

        HttpHeaders headers = createHeaders(resource, range, totalFileLength);

        log.info("Now Streaming :: {}", resource.getFileName());

        return ResponseEntity
                .status(getResponseStatus(range))
                .headers(headers)
                .body(createStream(resource, requiredLength));
    }

    private HttpStatus getResponseStatus(HttpRange range) {
        return range == null
                ? HttpStatus.OK
                : HttpStatus.PARTIAL_CONTENT;
    }

    private HttpRange getRange(HttpHeaders headers) {
        List<HttpRange> ranges = headers.getRange();
        return ranges.isEmpty() ? null : ranges.getFirst();
    }

    private InputStreamResource createStream(
            SongResource resource,
            long requiredLength) {

        return new InputStreamResource(
                new DecoratedInputStream(
                        resource.getInputStream(),
                        requiredLength));
    }

    private HttpHeaders createHeaders(
            SongResource resource,
            HttpRange range,
            long totalFileLength) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(
                MediaType.parseMediaType(resource.getContentType()));

        headers.add("Accept-Ranges", "bytes");

        if (range == null) {

            headers.setContentLength(totalFileLength);

        } else {

            long start = range.getRangeStart(totalFileLength);
            long end = range.getRangeEnd(totalFileLength);

            headers.setContentLength(end - start + 1);

            headers.add(
                    "Content-Range",
                    "bytes " + start + "-" + end + "/" + totalFileLength);
        }

        return headers;
    }
}
