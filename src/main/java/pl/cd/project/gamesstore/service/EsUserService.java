package pl.cd.project.gamesstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;
import pl.cd.project.gamesstore.helper.IndicesEs;
import pl.cd.project.gamesstore.model.esdocuments.EsUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class EsUserService {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final RestHighLevelClient client;

    public Boolean index(final EsUser user) {
        try {
            final String userAsString = MAPPER.writeValueAsString(user);

            final IndexRequest request = new IndexRequest(IndicesEs.USER_INDEX);
            request.id(user.getId());
            request.source(userAsString, XContentType.JSON);

            final IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

            return indexResponse != null && indexResponse.status().equals(RestStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    public EsUser getById(final String userId) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(IndicesEs.USER_INDEX, userId),
                    RequestOptions.DEFAULT
            );
            if (documentFields == null || documentFields.isSourceEmpty()) {
                return null;
            }
            return MAPPER.readValue(documentFields.getSourceAsString(), EsUser.class);
        } catch (final Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}
