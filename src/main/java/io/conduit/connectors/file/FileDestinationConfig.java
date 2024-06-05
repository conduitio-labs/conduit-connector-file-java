package io.conduit.connectors.file;

import io.conduit.sdk.specification.Default;
import io.conduit.sdk.specification.GreaterThan;
import io.conduit.sdk.specification.Regex;
import io.conduit.sdk.specification.Required;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// todo get rid of this
@RegisterForReflection
public class FileDestinationConfig {
    @Regex("/home/")
    @Required
    private String path;

    @GreaterThan(0)
    @Default("1")
    private int batchSize;
}
