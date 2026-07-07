package com.rishabh.musicstream.catalog;

import java.util.List;
import java.util.Optional;

public interface CatalogService {

//  Fetches all the songs with metadata
    List<SongMetadata> fetchAllSongs();

//  Fetches a particular song's metadata
    Optional<SongMetadata> getSong(long id);

//  Refreshes the catalog of songs by re-scanning the directory and return int value and assigns it to the songListCache;
    void refreshCatalog() throws RuntimeException;
}