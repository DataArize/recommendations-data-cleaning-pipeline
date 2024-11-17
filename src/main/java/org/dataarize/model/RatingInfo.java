package org.dataarize.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.extensions.avro.coders.AvroCoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DefaultCoder(AvroCoder.class)
public class RatingInfo {

    private String movieId;
    private String customerId;
    private int rating;
    private String date;
}
