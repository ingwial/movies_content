package alvarez.wilfredo.movies_content.dist.rest;

import alvarez.wilfredo.movies_content.service.MovieContentService;
import alvarez.wilfredo.movies_content.service.contrat.MovieContentTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieContentController {
    private final MovieContentService movieContentService;

    public MovieContentController(MovieContentService movieContentService) {
        this.movieContentService = movieContentService;
    }

    @GetMapping
    public ResponseEntity<Mono<List<MovieContentTO>>> getAll() {
        return ResponseEntity.ok(this.movieContentService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<MovieContentTO>> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.movieContentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<MovieContentTO>> create(@RequestBody MovieContentTO movieContentTO) {
        return ResponseEntity.ok(this.movieContentService.create(movieContentTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Mono<MovieContentTO>> update(@PathVariable String id, @RequestBody MovieContentTO movieContentTO) {
        return ResponseEntity.ok(this.movieContentService.update(id, movieContentTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Mono<Integer>> delete(@PathVariable String id) {
        return ResponseEntity.ok(this.movieContentService.delete(id));
    }
}
