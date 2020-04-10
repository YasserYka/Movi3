# Movie Streaming Service?
This is simple web application for streaming movies in two ways, adaptive streaming and byte-range streaming, also it convert uploaded media into different resolutions and puts them together into manifest file

# REST API

The important endpoints are described below

* ## Adaptive Streaming
  Endpoint for Adaptive streaming needs to be called from Dashjs

  ### Request 
  ```
  GET /api/v1/manifest/id
  ```
  
* ## Range based streaming
  Endpoint for requesing parts of movie using Byte-range headers needs to be called from HTML5's video element
  
  ### Request
  ```
  GET /api/v1/region/id
  ```
  
* ## Mutlipart File Uploading 
  Endpoint for uploading movies
  
  ### Request
  ```
  GET /api/v1/upload
  ```
  ### cURL
  ```
  curl -F 'file=@path-to-meida' http://localhost:8080/api/v1/upload
  ```
  
  # What happens behind the scene?
  
When movie is uploaded one process are started (so far) for extracting audio from the movie
