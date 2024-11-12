package org.dataarize;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.dataarize.io.GCSFileReader;
import org.dataarize.pipeline.MovieCustomerPairFn;


public class Main {


    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();
        Pipeline pipeline = createPipeline(options);
        pipeline.run().waitUntilFinish();

    }

    public static Pipeline createPipeline(PipelineOptions options) {
        var pipeline = Pipeline.create(options);
        GCSFileReader gcsFileReader = new GCSFileReader();
        pipeline
                .apply(gcsFileReader.read("gs://recommendations-engine-demo/output/Dataset_2024-11-11T15:20:42.126866047.txt"))
                .apply(ParDo.of(new MovieCustomerPairFn()));
        return pipeline;
    }
}