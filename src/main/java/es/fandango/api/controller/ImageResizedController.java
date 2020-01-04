package es.fandango.api.controller;

import es.fandango.api.response.FandangoImageResizedResponseApi;
import es.fandango.core.service.ImageResizedService;
import es.fandango.data.model.ImageResized;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller("/api")
public class ImageResizedController {

    /**
     * The image service
     */
    private final ImageResizedService imageResizedService;

    /**
     * Image Controller constructor
     *
     * @param imageResizedService The image service
     */
    public ImageResizedController(ImageResizedService imageResizedService) {
        this.imageResizedService = imageResizedService;
    }


    /**
     * Get the image
     *
     * @param imageId The image id
     * @return The image
     */
    @Operation(
            method = "GET",
            description = "Get the given image resized by Id and new resolution",
            tags = {"Images"}
    )
    @ApiResponse(responseCode = "404", description = "Image not found")
    @ApiResponse(responseCode = "200", description = "The requested Image Resized")
    @Get("/images/{imageId}/{width}")
    public Maybe<HttpResponse<Object>> getResizedImage(
            String imageId,
            Integer width
    ) {
        // Request the resized image
        Maybe<ImageResized> imageResized = imageResizedService.getResizedImageById(
                imageId,
                width,
                width
        );
        // Build the response
        FandangoImageResizedResponseApi responseApi = new FandangoImageResizedResponseApi(imageResized);
        // Return the response
        return responseApi.getResponseApi();
    }
}