package io.conduit.connectors.file;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// todo get rid of this
@RegisterForReflection
public class FileDestinationConfig {
    @Pattern(regexp = "/tmp/file-.*")
    private String path;

    @Valid
    private BatchConfig batchConfig;
}
