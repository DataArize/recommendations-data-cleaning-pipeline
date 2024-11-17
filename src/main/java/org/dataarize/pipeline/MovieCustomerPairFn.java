package org.dataarize.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.transforms.DoFn;
import org.dataarize.constants.PipelineConstants;
import org.dataarize.model.RatingInfo;

import java.util.Objects;


/**
 * A DoFn to parse a line of text representing movie rating information and transform it into a {@link RatingInfo} object.
 * <p>
 * Each line is expected to contain comma-separated fields: movie ID, customer ID, rating, and date.
 * The function splits the line, parses the fields, and outputs a {@link RatingInfo} object for further processing in the pipeline.
 * </p>
 */
@Slf4j
public class MovieCustomerPairFn extends DoFn<String, RatingInfo> {

    /**
     * Processes each line of input text, parses it into a {@link RatingInfo} object, and outputs the result.
     *
     * @param context The context containing the input element (a line of text) and the output method.
     */
    @ProcessElement
    public void processElement(ProcessContext context) {
        try {
            String line = Objects.requireNonNull(context.element()).trim();
            String[] fields = line.split(PipelineConstants.COMMA);

            if (fields.length != 4) {
                log.error("Invalid input format: Expected 4 fields but received {}. Line: {}", fields.length, line);
                return;
            }

            RatingInfo ratingInfo = new RatingInfo(fields[0], fields[1], Integer.parseInt(fields[2]), fields[3]);
            context.output(ratingInfo);

            log.debug("Successfully processed and output RatingInfo: {}", ratingInfo);
        } catch (Exception e) {
            log.error("Error processing line: {}. Exception: {}", context.element(), e.getMessage(), e);
        }
    }
}
