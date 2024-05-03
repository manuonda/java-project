package com.tutorial.base.rag.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class RagConfiguration {
  
    private static final Logger log = LoggerFactory.getLogger(RagConfiguration.class);



    @Value("classpath:/docs/pokemon-faq.txt")
    private Resource faq;

    @Value("vectorstore.json")
    private String vectorStoreName;


    /**
     * Bean : VectorStore para almacenar en memoria
     * @return
     */
    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingClient embeddingClient){
        SimpleVectorStore simpleVectorStore = new SimpleVectorStore(embeddingClient);   
        File vectorStoreFile = getVectorStoreFile();
        if(vectorStoreFile.exists()){
         log.info("Vector Store file exists");
          simpleVectorStore.load(vectorStoreFile);
        } else {
           log.info("Vector Store file does not exists, loading documents");
           TextReader textReader = new TextReader(faq);
           textReader.getCustomMetadata().put("filename", "pokemon-faq.txt");
           List<Document> documents = textReader.get();
           TokenTextSplitter textSplitter = new TokenTextSplitter();
           List<Document> splitDocuments = textSplitter.apply(documents);
           simpleVectorStore.add(splitDocuments);
           simpleVectorStore.save(vectorStoreFile);



        }

        return simpleVectorStore;
    }

    private File getVectorStoreFile(){
        Path path = Paths.get("src", "main","resources","data");
        String absolutePath = path.toFile().getAbsolutePath() + "/" + vectorStoreName; 
        return new File(absolutePath);
    }
}
