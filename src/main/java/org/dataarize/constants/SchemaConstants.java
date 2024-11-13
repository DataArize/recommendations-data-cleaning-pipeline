package org.dataarize.constants;

import lombok.experimental.UtilityClass;

/**
 * A utility class that contains constant values for schema field names and types.
 * These constants are used to define the schema for BigQuery tables, ensuring consistency
 * when working with the schema definition in various stages of the pipeline.
 */
@UtilityClass
public final class SchemaConstants {

    /**
     * The name of the field representing the movie ID in the schema.
     * This is used to uniquely identify each movie in the dataset.
     */
    public static final String MOVIE_ID = "movie_id";

    /**
     * The name of the field representing the customer ID in the schema.
     * This is used to uniquely identify each customer in the dataset.
     */
    public static final String CUSTOMER_ID = "customer_id";

    /**
     * The name of the field representing the rating given by the customer in the schema.
     * This field stores the rating value given by the customer for a particular movie.
     */
    public static final String RATING = "rating";

    /**
     * The name of the field representing the date when the rating was given in the schema.
     * This field stores the date associated with the customer's rating for a particular movie.
     */
    public static final String DATE = "date";

    /**
     * The BigQuery data type for string fields.
     * Used when defining string-type fields in the schema.
     */
    public static final String TYPE_STRING = "STRING";

    /**
     * The BigQuery data type for integer fields.
     * Used when defining integer-type fields in the schema, such as the rating.
     */
    public static final String TYPE_INTEGER = "INTEGER";

    /**
     * The mode that defines a field as mandatory (required) in the schema.
     * This is used to specify that a field must be provided when inserting data into BigQuery.
     */
    public static final String MODE_REQUIRED = "REQUIRED";
}
