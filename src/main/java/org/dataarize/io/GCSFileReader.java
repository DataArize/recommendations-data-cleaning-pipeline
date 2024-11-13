package org.dataarize.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.io.TextIO;
import org.dataarize.exceptions.MissingArgumentException;

/**
 * Utility class for reading text files from Google Cloud Storage (GCS).
 * <p>
 * This class provides a method to configure reading from a specified GCS file path using Apache Beam's TextIO.
 * </p>
 */
@Slf4j
public class GCSFileReader {

    /**
     * Configures a TextIO.Read operation to read data from a specified GCS file path.
     *
     * @param filePath The GCS file path to read from. It should be in the format "gs://bucket-name/path/to/file".
     * @return A {@link TextIO.Read} object configured to read from the specified GCS file path.
     * @throws IllegalArgumentException if the filePath is null or empty.
     */
    public TextIO.Read read(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            log.error("File path for GCS read operation is null or empty.");
            throw new MissingArgumentException("File path for GCS read operation cannot be null or empty.");
        }
        log.info("Configuring TextIO.Read for GCS file path: {}", filePath);
        return TextIO.read().from(filePath);
    }

}
