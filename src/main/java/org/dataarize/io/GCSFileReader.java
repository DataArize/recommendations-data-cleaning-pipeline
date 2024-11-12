package org.dataarize.io;

import org.apache.beam.sdk.io.TextIO;

public class GCSFileReader {

    public TextIO.Read read(String filePath) {
        return TextIO.read().from(filePath);
    }

}
