package org.dataarize.config;

import org.apache.beam.sdk.options.PipelineOptions;

/**
 * CustomPipelineOptions is an interface for defining the custom pipeline options
 * that extend the standard {@link PipelineOptions}. These options allow the user
 * to specify the input bucket path and the output dataset when running the pipeline.
 */
public interface CustomPipelineOptions extends PipelineOptions {

    /**
     * Gets the input bucket path.
     * This value is used to specify the location in Google Cloud Storage
     * where the input data resides.
     *
     * @return The input bucket path.
     */
    String getInputBucketPath();

    /**
     * Sets the input bucket path.
     * This value is used to specify the location in Google Cloud Storage
     * where the input data resides.
     *
     * @param value The input bucket path to set.
     */
    void setInputBucketPath(String value);

    /**
     * Gets the output dataset.
     * This value is used to specify the dataset in BigQuery where the processed
     * data will be written.
     *
     * @return The output dataset.
     */
    String getOutputDataset();

    /**
     * Sets the output dataset.
     * This value is used to specify the dataset in BigQuery where the processed
     * data will be written.
     *
     * @param value The output dataset to set.
     */
    void setOutputDataset(String value);

}
