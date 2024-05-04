package com.ai.docs.springaidocs;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.jline.utils.Log;
import org.slf4j.Logger;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


/**
 * Class Component that load and read document pokemons.pdf 
 * and store in the database vector store of pgsql.
 */
@Component
public class ReferenceDocsLoader {

    private static final Logger log= org.slf4j.LoggerFactory.getLogger(ReferenceDocsLoader.class);

    private final JdbcClient jdbcClient ;
    private final VectorStore vectorStore;

    @Value("classpath:/docs/pokemons.pdf")
    private Resource resource;


    public ReferenceDocsLoader( JdbcClient jdbcClient, VectorStore vectorStore){
        this.jdbcClient = jdbcClient;
        this.vectorStore = vectorStore;
    }


    @PostConstruct
    public void init(){
        Integer count = jdbcClient.sql("select count(*) from vector_store").query(Integer.class)
        .single();

        log.info("Current count of the Vecto Store : {}",count);
        
        if ( count == 0 ){
            log.info("Loading Spring Boot  Reference PDF into Vector Store");
            var config = PdfDocumentReaderConfig.builder()
					.withPageTopMargin(0)
					.withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
						.withNumberOfTopTextLinesToDelete(0)
						.build())
					.withPagesPerDocument(1)
					.build();

            var pdfReader = new PagePdfDocumentReader(resource, config);
            var textSplitter = new TokenTextSplitter();
            vectorStore.accept(textSplitter.apply(pdfReader.get()));
            Log.info("Application is ready");

        }
    }
    

}
