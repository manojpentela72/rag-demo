package com.springai.agents.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    public String ask(String question) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("RAG QUESTION");
        System.out.println("==================================================");
        System.out.println(question);

        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(question)
                        .topK(4)
                        .similarityThreshold(0.4)
                        .build());
        System.out.println();
        System.out.println("RAG SEARCH RESULTS");
        System.out.println("Number of hits: " + documents.size());

        if (documents.isEmpty()) {
            return "I could not find relevant information in the knowledge base.";
        }

        String context = documents.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + "\n\n---\n\n" + b);

        String systemPrompt = """
                You are a Java and Spring technical assistant and also you are good support assistant in Leave policies for an India company in Hyderabad.

                Answer the user's question using ONLY the information
                provided in the context below.

                If the context does not contain enough information
                to answer the question, say:

                "I don't have enough information in the knowledge base
                to answer that."

                Do not invent information.

                CONTEXT:
                %s

                USER QUESTION:
                %s
                """.formatted(context, question);

        return chatClient
                .prompt()
                .user(systemPrompt)
                .call()
                .content();
    }
}
