package org.dataarize.model;

import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.extensions.avro.coders.AvroCoder;

@DefaultCoder(AvroCoder.class)
public class AverageAccumulator {
    private int sum = 0;
    private int count = 0;

    public void add(int rating) {
        sum += rating;
        count++;
    }

    public void merge(AverageAccumulator other) {
        sum += other.sum;
        count += other.count;
    }

    public double getAverage() {
        return count == 0 ? 0.0 : (double) sum / count;
    }
}