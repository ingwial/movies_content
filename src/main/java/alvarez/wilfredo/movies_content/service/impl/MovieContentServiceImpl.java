package alvarez.wilfredo.movies_content.service.impl;

import alvarez.wilfredo.movies_content.service.MovieContentService;
import alvarez.wilfredo.movies_content.service.contrat.MovieContentTO;
import alvarez.wilfredo.movies_content.service.datasource.MovieContentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static alvarez.wilfredo.movies_content.service.contrat.MovieContentBinder.MOVIE_CONTENT_BINDER;

@Service("movieContentService")
public class MovieContentServiceImpl implements MovieContentService {
    public static final String MOVIE = "movie";
    private final MovieContentRepository movieContentRepository;

    public MovieContentServiceImpl(MovieContentRepository movieContentRepository) {
        this.movieContentRepository = movieContentRepository;
    }

    @Override
    public Mono<List<MovieContentTO>> getAll() {
        return this.movieContentRepository.getAll()
                .map(MOVIE_CONTENT_BINDER::bind);
    }

    @Override
    public Mono<MovieContentTO> getById(String id) {
        return this.movieContentRepository.getById(id)
                .map(MOVIE_CONTENT_BINDER::bind);
    }

    @Override
    public Mono<MovieContentTO> create(MovieContentTO movieContentTO) {
        return this.movieContentRepository.create(MOVIE, MOVIE_CONTENT_BINDER.bind(movieContentTO))
                .map(MOVIE_CONTENT_BINDER::bind);
    }

    @Override
    public Mono<MovieContentTO> update(String id, MovieContentTO movieContentTO) {
        return this.movieContentRepository.getById(id)
                .flatMap(entry -> this.movieContentRepository.update(MOVIE_CONTENT_BINDER.bind(entry, movieContentTO)))
                .map(MOVIE_CONTENT_BINDER::bind);
    }

    @Override
    public Mono<Integer> delete(String id) {
        return this.movieContentRepository.getById(id)
                .flatMap(this.movieContentRepository::delete);
    }
}
