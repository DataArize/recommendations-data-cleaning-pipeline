package org.dataarize;

import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.dataarize.config.CustomPipelineOptions;
import org.dataarize.constants.ExceptionMessages;
import org.dataarize.constants.PipelineConstants;
import org.dataarize.exceptions.MissingArgumentException;
import org.dataarize.io.BigQueryWriter;
import org.dataarize.io.GCSFileReader;
import org.dataarize.pipeline.MovieCustomerPairFn;

/**
 * Main entry point for the Data Processing Pipeline.
 * <p>
 * This class initializes the pipeline options, validates the required parameters,
 * and creates and executes the pipeline. The pipeline reads data from Google Cloud
 * Storage, processes it, and writes the results to BigQuery.
 * </p>
 */
@Slf4j
public class Main {

    /**
     * Main method to execute the pipeline.
     *
     * @param args command-line arguments for configuring pipeline options.
     */
    public static void main(String[] args) {
        log.info("Starting the pipeline execution...");
        CustomPipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation()
                .as(CustomPipelineOptions.class);
        validateOptions(options);
        Pipeline pipeline = createPipeline(options);
        pipeline.run().waitUntilFinish();
        log.info("Pipeline execution completed.");
    }

    /**
     * Validates required options before running the pipeline.
     *
     * @param options CustomPipelineOptions that hold user-defined configurations.
     * @throws MissingArgumentException if required options are not provided.
     */
    private static void validateOptions(CustomPipelineOptions options) {
        log.debug("Validating pipeline options...");
        if (options.getInputBucketPath() == null || options.getInputBucketPath().isEmpty()) {
            log.error("Input bucket path not provided.");
            throw new MissingArgumentException(ExceptionMessages.INPUT_BUCKET_PATH_NOT_PROVIDED);
        }
        if (options.getOutputDataset() == null || options.getOutputDataset().isEmpty()) {
            log.error("Output dataset not provided.");
            throw new MissingArgumentException(ExceptionMessages.OUTPUT_DATASET_NOT_PROVIDED);
        }
        log.info("Pipeline options validated successfully.");
    }

    /**
     * Creates and configures the data processing pipeline.
     *
     * @param options CustomPipelineOptions containing user-specified options for the pipeline.
     * @return Configured {@link Pipeline} object ready to run.
     */
    public static Pipeline createPipeline(CustomPipelineOptions options) {
        log.debug("Creating the pipeline...");
        Pipeline pipeline = Pipeline.create(options);
        GCSFileReader gcsFileReader = new GCSFileReader();
        BigQueryWriter bigQueryWriter = new BigQueryWriter(options.getOutputDataset());
        pipeline
                .apply(PipelineConstants.GCS_READER, gcsFileReader.read(options.getInputBucketPath()))
                .apply(PipelineConstants.EXTRACT_MOVIE_CUSTOMER_PAIR, ParDo.of(new MovieCustomerPairFn()))
                .apply(PipelineConstants.WRITE_TO_BIG_QUERY, bigQueryWriter.getBigQueryWrite());
        log.info("Pipeline created successfully with input: {} and output: {}",
                options.getInputBucketPath(), options.getOutputDataset());
        return pipeline;
    }
}