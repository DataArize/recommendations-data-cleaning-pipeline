package org.dataarize.utils;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.dataarize.constants.SchemaConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for creating BigQuery table schemas.
 * <p>
 * This class provides methods to define and retrieve schemas for BigQuery tables,
 * such as the schema for rating information.
 * </p>
 */
@Slf4j
@UtilityClass
public class BigQuerySchemaUtils {

    /**
     * Returns the schema for the RatingInfo table in BigQuery.
     * <p>
     * This schema includes the following fields:
     * <ul>
     *     <li>movie_id (STRING, REQUIRED)</li>
     *     <li>customer_id (STRING, REQUIRED)</li>
     *     <li>rating (INTEGER, REQUIRED)</li>
     *     <li>date (STRING, REQUIRED)</li>
     * </ul>
     * </p>
     *
     * @return {@link TableSchema} object defining the schema for the RatingInfo table.
     */
    public static TableSchema getRatingInfoSchema() {
        log.debug("Creating BigQuery schema for RatingInfo table...");
        List<TableFieldSchema> fields = new ArrayList<>();
        fields.add(new TableFieldSchema().setName(SchemaConstants.MOVIE_ID).setType(SchemaConstants.TYPE_STRING).setMode(SchemaConstants.MODE_REQUIRED));
        fields.add(new TableFieldSchema().setName(SchemaConstants.CUSTOMER_ID).setType(SchemaConstants.TYPE_STRING).setMode(SchemaConstants.MODE_REQUIRED));
        fields.add(new TableFieldSchema().setName(SchemaConstants.RATING).setType(SchemaConstants.TYPE_INTEGER).setMode(SchemaConstants.MODE_REQUIRED));
        fields.add(new TableFieldSchema().setName(SchemaConstants.DATE).setType(SchemaConstants.TYPE_STRING).setMode(SchemaConstants.MODE_REQUIRED));
        log.info("Successfully created BigQuery schema for RatingInfo table with {} fields.", fields.size());
        return new TableSchema().setFields(fields);
    }
}
