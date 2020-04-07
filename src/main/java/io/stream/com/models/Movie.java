package io.stream.com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
	private Long id;
	private String filename;
	private boolean storedInS3;
	private String extension;
	private String resolution;
	private String url;
	private String originalFilename;

}
