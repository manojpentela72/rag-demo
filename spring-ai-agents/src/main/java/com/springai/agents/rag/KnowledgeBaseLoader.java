package com.springai.agents.rag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.annotation.PostConstruct;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class KnowledgeBaseLoader {

    private final VectorStore vectorStore;
    private final JdbcTemplate jdbcTemplate;

    public KnowledgeBaseLoader(
            VectorStore vectorStore,
            JdbcTemplate jdbcTemplate) {

        this.vectorStore = vectorStore;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void loadKnowledgeBase() throws IOException {

        // Don't re-index if documents already exist
        Integer documentCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM vector_store",
                Integer.class
        );

        if (documentCount != null && documentCount > 0) {

            System.out.println(
                    "Knowledge base already contains "
                            + documentCount
                            + " documents. Skipping indexing."
            );

            return;
        }

        System.out.println("Loading knowledge base...");

        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();

        // Find Markdown files
        Resource[] markdownResources =
                resolver.getResources("classpath:/knowledge/*.md");

        // Find PDF files
        Resource[] pdfResources =
                resolver.getResources("classpath:/knowledge/*.pdf");

        List<Document> allDocuments = new ArrayList<>();

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(500)
                .withMinChunkSizeChars(200)
                .withMinChunkLengthToEmbed(10)
                .build();

        // -------------------------
        // Process Markdown files
        // -------------------------

        for (Resource resource : markdownResources) {
            System.out.println("Processing Markdown: " + resource.getFilename());
            TextReader reader = new TextReader(resource);
            List<Document> documents = reader.read();
            List<Document> chunks = splitter.split(documents);
            for (Document chunk : chunks) {
                chunk.getMetadata().put("source", Objects.requireNonNull(resource.getFilename()));
                chunk.getMetadata().put("fileType", "markdown");
            }
            System.out.println("  Chunks created: " + chunks.size());
            allDocuments.addAll(chunks);
        }

        // -------------------------
        // Process PDF files
        // -------------------------

        for (Resource resource : pdfResources) {

            System.out.println("Processing PDF: " + resource.getFilename());

            PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
                            .withPagesPerDocument(1)
                            .build();

            PagePdfDocumentReader reader = new PagePdfDocumentReader(resource, config);
            List<Document> documents = reader.read();
            List<Document> chunks = splitter.split(documents);
            for (Document chunk : chunks) {
                chunk.getMetadata().put("source", Objects.requireNonNull(resource.getFilename()));
                chunk.getMetadata().put("fileType", "pdf");
            }

            System.out.println("  Chunks created: " + chunks.size());

            allDocuments.addAll(chunks);
        }

        // -------------------------
        // Store in pgvector
        // -------------------------

        System.out.println("Total chunks to embed: " + allDocuments.size());

        if (!allDocuments.isEmpty()) {
            vectorStore.add(allDocuments);
        }

        System.out.println("Knowledge base loaded. Documents indexed: " + allDocuments.size());
    }
}