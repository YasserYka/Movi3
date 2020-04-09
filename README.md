

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
  
