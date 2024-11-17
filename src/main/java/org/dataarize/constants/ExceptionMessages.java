package org.dataarize.constants;

import lombok.experimental.UtilityClass;

/**
 * A utility class that holds all the exception messages used across the application.
 * This class provides a centralized location for managing error messages, ensuring consistency
 * throughout the codebase when throwing exceptions.
 */
@UtilityClass
public final class ExceptionMessages {

    /**
     * Error message indicating that the input bucket path was not provided.
     * This message is used when the input path for reading data from Google Cloud Storage
     * is missing.
     */
    public static final String INPUT_BUCKET_PATH_NOT_PROVIDED = "Input bucket path must be provided.";

    /**
     * Error message indicating that the output dataset was not provided.
     * This message is used when the output dataset for writing to BigQuery is missing.
     */
    public static final String OUTPUT_DATASET_NOT_PROVIDED = "Output dataset must be provided.";
}
