package alvarez.wilfredo.movies_content.service.datasource.impl;

import alvarez.wilfredo.movies_content.config.ContentConfig;
import alvarez.wilfredo.movies_content.service.datasource.MovieContentRepository;
import com.contentful.java.cma.CMAClient;
import com.contentful.java.cma.model.CMAArray;
import com.contentful.java.cma.model.CMAEntry;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository("")
public class MovieContentRepositoryImpl implements MovieContentRepository {
    private final CMAClient cmaClient;

    public MovieContentRepositoryImpl(ContentConfig config) {
        this.cmaClient = new CMAClient.Builder()
                .setAccessToken(config.getToken())
                .setSpaceId(config.getSpace())
                .setEnvironmentId(config.getEnvironment())
                .build();
    }

    @Override
    public Mono<CMAArray<CMAEntry>> getAll() {
        return Mono.just(this.cmaClient.entries().fetchAll());
    }

    @Override
    public Mono<CMAEntry> getById(String id) {
        return Mono.just(this.cmaClient.entries().fetchOne(id));
    }

    @Override
    public Mono<CMAEntry> create(String type, CMAEntry entry) {
        return Mono.just(this.cmaClient.entries().create(type, entry));
    }

    @Override
    public Mono<CMAEntry> update(CMAEntry entry) {
        return Mono.just(this.cmaClient.entries().update(entry));
    }

    @Override
    public Mono<Integer> delete(CMAEntry entry) {
        return Mono.just(this.cmaClient.entries().delete(entry));
    }
}
