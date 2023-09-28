package com.embeddings.ai.embeddings.subModules.embeddingsSystem;


import com.embeddings.ai.embeddings.entity.GPTResponse;
import com.embeddings.ai.embeddings.entity.PDFChunks;
import com.embeddings.ai.embeddings.entity.TextEmbeddingData;
import com.embeddings.ai.embeddings.entity.VectorData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class embeddingsService {
    @Value("${openai.api.key}")
    private static String apiKey;



    private static String loadTextFromPDF(String fileName) throws IOException {

        // Load the PDF document
        PDDocument document = PDDocument.load(new File("C:\\Users\\USER\\Downloads\\embeddings (1)\\TEST\\src\\main\\java\\org\\example\\files\\"+fileName));
        PDFTextStripper textStripper = new PDFTextStripper();
        String result=textStripper.getText(document);

        document.close();
        return result ;
    }

    private static List<VectorData> displayParagraphs(String filename) throws IOException {


        // Chunk the text into paragraphs
        List<String> paragraphs = chunkTextIntoParagraphs(loadTextFromPDF(filename));
        List<VectorData> dataVect=new ArrayList<>();

        //saveEmbeddingsInPinecone();
        // Process and display the paragraphs
        for (int i = 0; i < paragraphs.size(); i++) {
            String paragraph = paragraphs.get(i);
            TextEmbeddingData data= getEmbeddingsFromOpenAI(paragraph);

            Map<String,String> map=new HashMap<>();
            map.put("chunks",paragraph);

            TextEmbeddingData.Data[] doubles=data.getData();
             List<Double> embeddings=new ArrayList<>();
            for (TextEmbeddingData.Data doubleNumbers:doubles){
                embeddings = Arrays.asList(doubleNumbers.getEmbedding());
            }

            VectorData vectorData=
                    VectorData
                            .builder()
                            .name("id"+i)
                            .metadata(map)
                            .values(embeddings)
                            .build();

            System.out.println( vectorData );



            dataVect.add(vectorData);
        }

        return dataVect;
    }

    private static TextEmbeddingData getEmbeddingsFromOpenAI(String chunk) {
        PDFChunks chunks=PDFChunks
                .builder()
                .input(chunk)
                .model("text-embedding-ada-002")
                .build();
        String apiUrl="https://api.openai.com/v1/embeddings";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer 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");
            return execution.execute(request, body);
        });


        TextEmbeddingData response=restTemplate.postForObject(apiUrl, chunks, TextEmbeddingData.class);
        assert response != null;
        System.out.println(Arrays.toString(response.getData()));

        // return the first response
        return response;

    }

    private static List<String> chunkTextIntoParagraphs(String text) {
        // Define a regular expression to split the text into sentences.
        String sentenceSplitter = "[.!?]";

        // Split the text into sentences using the regular expression.
        String[] sentenceArray = text.split(sentenceSplitter);
        List<String> paragraphs = new ArrayList<>();

        StringBuilder paragraph = new StringBuilder();

        for (int i = 0; i < sentenceArray.length; i++) {
            String trimmedSentence = sentenceArray[i].trim();
            if (!trimmedSentence.isEmpty()) {
                // Append the sentence to the current paragraph.
                paragraph.append(trimmedSentence);
                paragraph.append(" ");

                // If we have aggregated 8 sentences or reached the end of the array, start a new paragraph.
                if ((i + 1) % 5 == 0 || i == sentenceArray.length - 1) {
                    paragraphs.add(paragraph.toString().trim());
                    paragraph.setLength(0); // Clear the current paragraph.
                }
            }
        }

        return paragraphs;
    }

    public List<VectorData> getEmbeddingsFromText() {
        List<VectorData> vectorData;
        try {
            vectorData = new ArrayList<>();

            // Specify the directory path you want to list files from
            String directoryPath = "C:\\Users\\USER\\Downloads\\embeddings (1)\\TEST\\src\\main\\java\\org\\example\\files\\";

            // Create a File object for the directory
            File directory = new File(directoryPath);

            // Check if the specified path is a directory
            if (directory.isDirectory()) {
                // List all files in the directory
                File[] files = directory.listFiles();

                if (files != null) {
                    System.out.println("Files in directory: " + directoryPath);

                    // Iterate through the list of files and print their names

                    for (File file : files) {

                        if (file.isFile()) {
                            vectorData.addAll(displayParagraphs(file.getName()));
                        }
                        //send api request to backend

                    }



                    // Chunk size
                    int chunkSize = 100;

                    // Split the dataList into chunks
                    List<List<VectorData>> chunks = new ArrayList<>();
                    for (int i = 0; i < vectorData.size(); i += chunkSize) {
                        int end = Math.min(i + chunkSize, vectorData.size());
                        chunks.add(vectorData.subList(i, end));
                    }

                    for (List<VectorData> chunk:chunks){
                        String apiUrl="http://192.168.100.4:5000/store_embedding";
                        RestTemplate restTemplate = new RestTemplate();

                        restTemplate.postForObject(apiUrl, chunk, GPTResponse.class);
                    }

                    System.out.println("I am done");
                } else {
                    System.err.println("Error: Unable to list files in the directory.");
                }
            } else {
                System.err.println("Error: The specified path is not a directory.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return vectorData;
    }
}
