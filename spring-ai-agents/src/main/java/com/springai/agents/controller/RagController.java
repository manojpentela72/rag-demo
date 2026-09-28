package com.springai.agents.controller;

import com.springai.agents.rag.RagService;
import com.springai.agents.resource.RagRequest;
import com.springai.agents.resource.RagResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/ask")
    public RagResponse ask(@RequestBody RagRequest request) {
        String answer = ragService.ask(request.question());
        return new RagResponse(answer);
    }
}
