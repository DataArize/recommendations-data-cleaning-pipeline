package org.dataarize.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

import java.util.Objects;

@Slf4j
public class MovieCustomerPairFn extends DoFn<String, KV<String, String>> {


    @ProcessElement
    public void processElement(ProcessContext context) {
        String line = Objects.requireNonNull(context.element()).trim();
        System.out.println(line);
    }
}
