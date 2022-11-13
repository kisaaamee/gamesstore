package pl.cd.project.gamesstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;
import pl.cd.project.gamesstore.dto.es.SearchRequestDTO;
import pl.cd.project.gamesstore.helper.IndicesEs;
import pl.cd.project.gamesstore.helper.searchutils.SearchUtil;
import pl.cd.project.gamesstore.model.esdocuments.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EsUserService {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final RestHighLevelClient client;

    public Boolean index(final User user) {
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

    public User getById(final String userId) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(IndicesEs.USER_INDEX, userId),
                    RequestOptions.DEFAULT
            );
            if (documentFields == null || documentFields.isSourceEmpty()) {
                return null;
            }
            return MAPPER.readValue(documentFields.getSourceAsString(), User.class);
        } catch (final Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public List<User> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                IndicesEs.USER_INDEX,
                dto
        );

        if (request == null) {
            log.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<User> users = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                users.add(
                        MAPPER.readValue(hit.getSourceAsString(), User.class)
                );
            }
            return  users;
        } catch (final Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
}
