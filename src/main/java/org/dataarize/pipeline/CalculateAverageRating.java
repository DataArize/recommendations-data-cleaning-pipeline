package org.dataarize.pipeline;

import org.apache.beam.sdk.transforms.Combine;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.dataarize.model.AverageAccumulator;
import org.dataarize.model.RatingInfo;

import java.util.Objects;

/**
 * A Combine function to calculate the average rating from a collection of {@link RatingInfo} objects.
 * This class uses {@link AverageAccumulator} as an intermediate data structure to accumulate ratings
 * and calculate the average at the end of the combine operation.
 */
public class CalculateAverageRating extends Combine.CombineFn<RatingInfo, AverageAccumulator, Double> {

    /**
     * Creates a new accumulator for the combine operation.
     *
     * @return A new instance of {@link AverageAccumulator} initialized with zero values.
     */
    @Override
    public AverageAccumulator createAccumulator() {
        return new AverageAccumulator();
    }

    /**
     * Adds a new {@link RatingInfo} input to the accumulator, updating the total sum and count of ratings.
     *
     * @param mutableAccumulator The current state of the accumulator.
     * @param input              The new {@link RatingInfo} to add to the accumulator.
     * @return The updated accumulator with the new rating included.
     */
    @Override
    public AverageAccumulator addInput(AverageAccumulator mutableAccumulator, RatingInfo input) {
        if (Objects.isNull(input)) {
            mutableAccumulator.add(input.getRating());
        }
        return mutableAccumulator;
    }

    /**
     * Merges multiple accumulators into a single accumulator by combining their counts and sums.
     *
     * @param accumulators The list of accumulators to merge.
     * @return A merged {@link AverageAccumulator} containing the combined sum and count of all ratings.
     */
    @Override
    public AverageAccumulator mergeAccumulators(@UnknownKeyFor @NonNull @Initialized Iterable<AverageAccumulator> accumulators) {
        AverageAccumulator merged = new AverageAccumulator();
        for (AverageAccumulator acc: accumulators) {
            merged.merge(acc);
        }
        return merged;
    }

    /**
     * Extracts the final output from the accumulator by calculating the average rating.
     *
     * @param accumulator The final state of the accumulator after all inputs are added and merged.
     * @return The calculated average rating as a Double. Returns 0.00 if the accumulator is null.
     */
    @Override
    public Double extractOutput(AverageAccumulator accumulator) {
        if (Objects.isNull(accumulator)) return 0.00;
        return accumulator.getAverage();
    }
}
