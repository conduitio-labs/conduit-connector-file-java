package io.conduit.connectors.file;

import io.conduit.sdk.Main;
import io.conduit.sdk.Parameter;
import io.conduit.sdk.Record;
import io.conduit.sdk.WriteResult;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.SneakyThrows;
import org.jboss.logging.Logger;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class FileDestination implements io.conduit.sdk.Destination {
    private static final Logger logger = Logger.getLogger(FileDestination.class);

    private Path path;
    private FileOutputStream stream;

    @Override
    public Map<String, Parameter> parameters() {
        return Map.of(
            "path",
            Parameter.builder()
                .description("Path to the file.")
                .required(true)
                .build()
        );
    }

    @Override
    public void configure(Map<String, String> configMap) {
        String pathString = configMap.get("path");
        if (pathString == null) {
            throw new IllegalArgumentException("path not configured");
        }
        this.path = Path.of(pathString);
    }

    @SneakyThrows
    @Override
    public void open() {
        this.stream = new FileOutputStream(this.path.toString(), true);
    }

    @Override
    public WriteResult write(List<Record> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new WriteResult(0, null);
        }

        for (int i = 0; i < records.size(); i++) {
            try {
                stream.write(records.get(i).getPayload().getAfter().bytes());
                stream.flush();
            } catch (Exception e) {
                logger.error("failed writing record", e);

                return new WriteResult(i, e);
            }
        }

        return new WriteResult(CollectionUtils.size(records), null);
    }

    @SneakyThrows
    @Override
    public void teardown() {
        if (stream != null) {
            stream.close();
        }
    }

    @Override
    public void lifecycleOnCreated(Map<String, String> config) {

    }

    @Override
    public void lifecycleOnUpdated(Map<String, String> configBefore, Map<String, String> configAfter) {

    }

    @Override
    public void lifecycleOnDeleted(Map<String, String> config) {

    }
}
