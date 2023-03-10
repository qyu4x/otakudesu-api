package com.qirara.otakudesuapi.controller;

import com.qirara.otakudesuapi.payload.response.*;
import com.qirara.otakudesuapi.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/otakudesu/")
public class OtakudesuV2Controller {

    private final CompleteAnimeV2Service completeAnimeV2Service;

    private final OngoingAnimeV2Service ongoingAnimeV2Service;

    private final ScheduleAnimeService scheduleAnimeService;

    private final AnimeGenresV2Service animeGenresV2Service;

    private final AnimeSearchV2Service animeSearchV2Service;

    @Autowired
    public OtakudesuV2Controller(CompleteAnimeV2Service completeAnimeV2Service, OngoingAnimeV2Service ongoingAnimeV2Service, ScheduleAnimeService scheduleAnimeService, AnimeGenresV2Service animeGenresV2Service, AnimeSearchV2Service animeSearchService) {
        this.completeAnimeV2Service = completeAnimeV2Service;
        this.ongoingAnimeV2Service = ongoingAnimeV2Service;
        this.scheduleAnimeService = scheduleAnimeService;
        this.animeGenresV2Service = animeGenresV2Service;
        this.animeSearchV2Service = animeSearchService;
    }


    @Operation(summary = "get list of the complete anime")
    @GetMapping("complete-anime/{page}")
    public ResponseEntity<WebResponse<List<CompleteAnimeV2Response>>> listCompletenime(@PathVariable Integer page) throws IOException {
        List<CompleteAnimeV2Response> completeAnimeV2Responses = completeAnimeV2Service.getAll(page);
        WebResponse<List<CompleteAnimeV2Response>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                completeAnimeV2Responses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @Operation(summary = "get list of the ongoing anime")
    @GetMapping("ongoing-anime/{page}")
    public ResponseEntity<WebResponse<List<OngoingAnimeV2Response>>> listOngoingAnime(@PathVariable Integer page) throws IOException {
        List<OngoingAnimeV2Response> ongoingAnimeV2Responses = ongoingAnimeV2Service.getAll(page);
        WebResponse<List<OngoingAnimeV2Response>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                ongoingAnimeV2Responses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @Operation(summary = "get list of the schedule anime ongoing broadcast day")
    @GetMapping("schedule")
    public ResponseEntity<WebResponse<List<ScheduleAnimeResponse>>> scheduleAnime() throws IOException {
        List<ScheduleAnimeResponse> scheduleAnimeResponses = scheduleAnimeService.getAll();
        WebResponse<List<ScheduleAnimeResponse>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                scheduleAnimeResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @Operation(summary = "get list of the genre anime")
    @GetMapping("genre-list")
    public ResponseEntity<WebResponse<List<AnimeGenreV2Response>>> genreAnime() throws IOException {
        List<AnimeGenreV2Response> scheduleAnimeResponses = animeGenresV2Service.getAll();
        WebResponse<List<AnimeGenreV2Response>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                scheduleAnimeResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }

    @Operation(summary = "search anime based on keywords")
    @GetMapping("search")
    public ResponseEntity<WebResponse<List<AnimeSearchV2Response>>> searchAnime(@RequestParam String keyword) throws IOException {
        List<AnimeSearchV2Response> scheduleAnimeResponses = animeSearchV2Service.get(keyword);
        WebResponse<List<AnimeSearchV2Response>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                scheduleAnimeResponses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }
}
