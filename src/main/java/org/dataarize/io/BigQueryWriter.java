package org.dataarize.io;

import com.google.api.services.bigquery.model.TableRow;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.dataarize.constants.ExceptionMessages;
import org.dataarize.constants.SchemaConstants;
import org.dataarize.exceptions.MissingArgumentException;
import org.dataarize.model.RatingInfo;
import org.dataarize.utils.BigQuerySchemaUtils;

import java.util.Objects;

/**
 * Class for writing data to BigQuery.
 * <p>
 * This class provides the setup required for configuring and executing writes to a specified
 * BigQuery dataset. The data is written in an append-only mode with schema validation.
 * </p>
 */
@Slf4j
public class BigQueryWriter {

    private final String outputDataset;

    /**
     * Constructs a BigQueryWriter instance.
     *
     * @param outputDataset The BigQuery dataset and table ID in the format "dataset.table" to write to.
     */
    public BigQueryWriter(String outputDataset) {
        if (outputDataset == null || outputDataset.isEmpty()) {
            log.error("Output dataset ID is null or empty.");
            throw new MissingArgumentException(ExceptionMessages.OUTPUT_DATASET_NOT_PROVIDED);
        }
        this.outputDataset = outputDataset;
        log.info("BigQueryWriter initialized for dataset: {}", outputDataset);
    }

    /**
     * Provides a configured BigQueryIO.Write instance for writing RatingInfo records to BigQuery.
     * <p>
     * The data is written in append mode and the table is created if it does not exist.
     * </p>
     *
     * @return A configured {@link BigQueryIO.Write} object for writing to BigQuery.
     */
    public BigQueryIO.Write<RatingInfo> getBigQueryWrite() {
        log.debug("Configuring BigQueryIO.Write for dataset: {}", outputDataset);
        return BigQueryIO.<RatingInfo>write()
                .to(outputDataset)
                .withSchema(BigQuerySchemaUtils.getRatingInfoSchema())
                .withFormatFunction(ratingInfo -> ratingInfo != null ? RatingInfoToTableRow.convert(ratingInfo) : null)
                .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
                .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED);
    }

    /**
     * Utility class for converting RatingInfo objects to TableRow format required by BigQuery.
     */
    @UtilityClass
    public static class RatingInfoToTableRow {

        /**
         * Converts a RatingInfo object to a {@link TableRow}.
         *
         * @param ratingInfo The RatingInfo object to convert.
         * @return A {@link TableRow} populated with data from the RatingInfo object.
         */
        public static TableRow convert(RatingInfo ratingInfo) {
            if (Objects.isNull(ratingInfo)) {
                log.warn("Attempted to convert a null RatingInfo object.");
                return new TableRow();
            }
            log.debug("Converting RatingInfo to TableRow: {}", ratingInfo);
            return new TableRow()
                    .set(SchemaConstants.MOVIE_ID, ratingInfo.getMovieId())
                    .set(SchemaConstants.CUSTOMER_ID, ratingInfo.getCustomerId())
                    .set(SchemaConstants.RATING, ratingInfo.getRating())
                    .set(SchemaConstants.DATE, ratingInfo.getDate());
        }
    }
}
