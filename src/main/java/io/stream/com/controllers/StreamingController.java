package io.stream.com.controllers;

import java.net.MalformedURLException;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/v1/streaming")
public class StreamingController {

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping("/manifests/{originalFilename}")
	public ResponseEntity<UrlResource> getManifest(@PathVariable("originalFilename") String originalFilename) throws MalformedURLException {
		
		UrlResource manifestResource = new UrlResource(String.format("file:%s%s", uploadPath, originalFilename));
			
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).contentType(MediaTypeFactory.getMediaType("application/dash+xml").orElse(MediaType.APPLICATION_OCTET_STREAM)).body(manifestResource);
	}

	@GetMapping("/region/{originalFilename}")
	public ResponseEntity<ResourceRegion> getRegion(@PathVariable("originalFilename") String originalFilename,  @RequestHeader HttpHeaders headers) throws MalformedURLException {

		UrlResource movieResource = new UrlResource(String.format("file:%s%s", uploadPath, originalFilename));

		return	ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).contentType(MediaTypeFactory.getMediaType(movieResource).orElse(MediaType.APPLICATION_OCTET_STREAM)).body(getRange(headers, movieResource));
	}

	private ResourceRegion getRange(HttpHeaders headers, UrlResource movie){

		long length = getContentLength(movie);
		HttpRange range = headers.getRange().get(0);

		if(range == null) {
			Long rangeLength = Math.min(1 * 1024 * 1024, length);
			return new ResourceRegion(movie, 0, rangeLength);
		}
		else {
			Long start = range.getRangeStart(length);
			Long end = range.getRangeEnd(length);
			Long rangeLength = Math.min(1 * 1024 * 1024, end - start + 1);
			return new ResourceRegion(movie, start, rangeLength);
		}
	}

	private long getContentLength(UrlResource urlResource) {
		try {return urlResource.contentLength();}
		catch(IOException error) {error.printStackTrace();}
		return -1;
	}
}
