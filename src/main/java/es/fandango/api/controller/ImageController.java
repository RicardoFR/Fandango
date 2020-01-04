package es.fandango.api.controller;

import es.fandango.api.response.FandangoImageResponseApi;
import es.fandango.api.response.FandangoNewImageResponseApi;
import es.fandango.api.response.common.ElementId;
import es.fandango.core.service.ImageService;
import es.fandango.data.model.Image;
import es.fandango.data.model.Info;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.io.IOException;
import java.util.List;

@Controller("/api")
public class ImageController {

    /**
     * The image service
     */
    private final ImageService imageService;

    /**
     * Image Controller constructor
     *
     * @param imageService The image service
     */
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Get the images ids
     *
     * @return The images ids
     */
    @Operation(
            method = "GET",
            description = "Get the list of saved images",
            tags = {"Images"}
    )
    @ApiResponse(responseCode = "200", description = "The list of saved images")
    @Get("/images")
    public Single<List<Info>> getImages() {
        // Request all the images ids
        return imageService.getAllImagesInfo();
    }

    /**
     * Get the image
     *
     * @param imageId The image id
     * @return The image
     */
    @Operation(
            method = "GET",
            description = "Get the given image by Id",
            tags = {"Images"}
    )
    @ApiResponse(responseCode = "404", description = "Image not found")
    @ApiResponse(responseCode = "200", description = "The requested Image")
    @Get("/images/{imageId}")
    public Maybe<HttpResponse<Object>> getImage(String imageId) {
        // Request the image
        Maybe<Image> image = imageService.getImageById(imageId);
        // Build the response
        FandangoImageResponseApi responseApi = new FandangoImageResponseApi(image);
        // Return the response
        return responseApi.getResponseApi();
    }

    /**
     * Upload a target image
     *
     * @param file The target image to upload
     * @return The image id
     * @throws IOException The image Exception
     */
    @Operation(
            method = "POST",
            description = "Upload a Image",
            tags = {"Images"}
    )
    @ApiResponse(
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(anyOf = ElementId.class)
            )
    )
    @RequestBody(
            content = @Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA,
                    schema = @Schema(anyOf = FileUpload.class)
            )
    )
    @Post(uri = "/images",
            consumes = MediaType.MULTIPART_FORM_DATA,
            produces = MediaType.APPLICATION_JSON
    )
    public Maybe<HttpResponse<Object>> uploadImage(
            @Body("file") CompletedFileUpload file
    ) throws IOException {

        // Request the new image
        Single<String> image = imageService.processImageUpload(file);
        // Build the response
        FandangoNewImageResponseApi responseApi = new FandangoNewImageResponseApi(image);
        // Return the response
        return responseApi.getResponseApi();
    }
}
