package io.conduit.connectors.file;

import io.conduit.sdk.specification.Default;
import io.conduit.sdk.specification.GreaterThan;
import io.conduit.sdk.specification.Regex;
import io.conduit.sdk.specification.Required;
import lombok.Data;

@Data
public class FileDestinationConfig {
    @Regex("/home/")
    @Required
    private String path;

    @GreaterThan(0)
    @Default("1")
    private int batchSize;
}
