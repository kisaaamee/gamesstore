package pl.cd.project.gamesstore.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cd.project.gamesstore.helper.IndicesEs;
import pl.cd.project.gamesstore.helper.MappingApplyUtil;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class IndexService {
    private final List<String> INDICES = List.of(IndicesEs.USER_INDEX);
    private final RestHighLevelClient client;

    @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndices() {
        recreateIndices(false);
    }

    public void recreateIndices(final boolean deleteExisting) {
        final String settings = MappingApplyUtil.loadAsString("static/es-settings.json");

        if (settings == null) {
            log.error("Failed to load settings file");
            return;
        }

        for (final String indexName : INDICES) {
            try {
                final boolean indexExists = client
                        .indices()
                        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if (indexExists) {
                    if (!deleteExisting) {
                        continue;
                    }

                    client.indices().delete(
                            new DeleteIndexRequest(indexName),
                            RequestOptions.DEFAULT
                    );
                }

                final CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
                indexRequest.settings(settings, XContentType.JSON);

                final String mappings = loadMappings(indexName);
                if (mappings != null) {
                    indexRequest.mapping(mappings, XContentType.JSON);
                }

                client.indices().create(indexRequest, RequestOptions.DEFAULT);
            } catch (final Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    private String loadMappings(String indexName) {
        final String mappings = MappingApplyUtil.loadAsString("static/mappings/" + indexName + ".json");
        if (mappings == null) {
            log.error("Failed to create index with name {}", indexName);
            return null;
        }
        return mappings;
    }


}
