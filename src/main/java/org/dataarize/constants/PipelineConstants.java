package org.dataarize.constants;

import lombok.experimental.UtilityClass;

/**
 * A utility class that contains constant values used throughout the data pipeline.
 * These constants help maintain consistency in naming across the pipeline stages,
 * making the code easier to maintain and understand.
 */
@UtilityClass
public final class PipelineConstants {

    /**
     * A comma (',') used as a delimiter for splitting strings in CSV or other data formats.
     */
    public static final String COMMA = ",";

    /**
     * The name of the step in the pipeline that calculates average ratings per movie.
     * This constant is used to identify the stage of the pipeline responsible for calculating the
     * average rating for each movie based on user reviews.
     */
    public static final String AVERAGE_RATINGS_PER_MOVIE = "AverageRatingPerMovie";

    /**
     * The name of the step in the pipeline that reads data from Google Cloud Storage (GCS).
     * This constant represents the stage responsible for reading input data from a GCS bucket.
     */
    public static final String GCS_READER = "GCS Reader";

    /**
     * The name of the step in the pipeline that extracts movie-customer pairs from raw data.
     * This constant represents the stage that extracts useful information from raw input data
     * and formats it into a structured movie-customer pair.
     */
    public static final String EXTRACT_MOVIE_CUSTOMER_PAIR = "Extract Movie-Customer Pair";

    /**
     * The name of the step in the pipeline that writes data to BigQuery.
     * This constant represents the final step in the pipeline where the transformed data is
     * written into BigQuery for further analysis and storage.
     */
    public static final String WRITE_TO_BIG_QUERY = "Write To BigQuery";

}
