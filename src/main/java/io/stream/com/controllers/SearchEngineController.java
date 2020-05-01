package io.stream.com.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
public class SearchEngineController {

    public List<Movie> searchMovieByKe(@RequestParam String keywords){ return null; }

    }

}