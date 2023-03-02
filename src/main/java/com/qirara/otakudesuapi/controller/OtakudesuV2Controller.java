package com.qirara.otakudesuapi.controller;

import com.qirara.otakudesuapi.payload.response.CompleteAnimeV2Response;
import com.qirara.otakudesuapi.payload.response.WebResponse;
import com.qirara.otakudesuapi.service.CompleteAnimeV2Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/otakudesu/")
public class OtakudesuV2Controller {

    private final CompleteAnimeV2Service completeAnimeV2Service;

    @Autowired
    public OtakudesuV2Controller(CompleteAnimeV2Service completeAnimeV2Service) {
        this.completeAnimeV2Service = completeAnimeV2Service;
    }


    @Operation(summary = "get list of the complete anime")
    @GetMapping("complete-anime/{page}")
    public ResponseEntity<WebResponse<List<CompleteAnimeV2Response>>> listLatestAnime(@PathVariable Integer page) throws IOException {
        List<CompleteAnimeV2Response> completeAnimeV2Responses = completeAnimeV2Service.getAll(page);
        WebResponse<List<CompleteAnimeV2Response>> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                completeAnimeV2Responses
        );
        return new ResponseEntity<>(webResponse, HttpStatus.OK);
    }
}
