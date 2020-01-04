# Fandango

## Summary 
  
Fandango is a [Micronaut](http://micronaut.io/)-based microservice providing a REST API that allows to store and recover images (jpeg, jpg, png) or documents like PDF, and also allowing to generate thumbnails, modify and retrieve images, using a NoSQL database storage via HTTP-based API.  
 
## Benefits 
  
1. Uncouple the image or documents storage management from the rest of any application.  
2. Improve efficiency and simplicity when dealing with images or documents.  
3. Micro Service Architecture oriented using Micronaut framework

## Build & Deploy 

Fandango require a MongoDB installed instance in the host machine to store the image and files.

You can clone the project and generate your own jar :

```console
./gradlew shadowJar
```

Or you can use the provided docker image :

```console
$ docker pull vettonum/fandango
$ docker run --net host vettonum/fandango
```
## **Contact information:**  
Ricardo  
rflores@gloin.es  

**License:** [GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.html)

## Api & Doc

You can found the swagger ui on project itself in the path /swagger-ui

## **/api/thumbnails/{thumbnailId}**

#### GET
##### Description:

Get the given thumbnail by Id

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| thumbnailId | path | The thumbnail id | Yes | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | The requested Thumbnail |
| 404 | Thumbnail not found |

#
## **/api/images/{imageId}/{width}**

#### GET
##### Description:

Get the given image resized by Id and new resolution

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| imageId | path | The image id | Yes | string |
| width | path | The new width | Yes | integer |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | The requested Image Resized |
| 404 | Image not found |

#
## **/api/files**

#### GET
##### Description:

Get the list of uploaded files

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | The list of saved files |

#### POST
##### Description:

Upload a File

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| file | body | The file | Yes | File |


##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Ok |

### /api/files/{fileId}

#### GET
##### Description:

Get the given file by Id

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| fileId | path | The file id | Yes | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | The requested File |
| 404 | File not found |

#
## **/api/images**

#### GET
##### Description:

Get the list of saved images

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | The list of saved images |

#### POST
##### Description:

Upload a Image

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| file | body | The image | Yes | File |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | Ok |

#
## **/api/images/{imageId}**

#### GET
##### Description:

Get the given image by Id

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| imageId | path | The image id | Yes | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | The requested Image |
| 404 | Image not found |
