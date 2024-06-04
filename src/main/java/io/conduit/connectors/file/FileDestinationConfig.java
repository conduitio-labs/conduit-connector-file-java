package io.conduit.connectors.file;

import io.conduit.sdk.specification.GreaterThan;
import io.conduit.sdk.specification.Regex;
import io.conduit.sdk.specification.Required;

public class FileDestinationConfig {
    @Regex("/home/")
    @Required
    private String path;

    @GreaterThan(0)
    private int batchSize;
}
