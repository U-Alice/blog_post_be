package rca.ac.year3.security_starter.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.webjars.NotFoundException;
import rca.ac.year3.security_starter.exceptions.BadRequestException;
import rca.ac.year3.security_starter.exceptions.ResourceNotFoundException;
import rca.ac.year3.security_starter.payload.ApiResponse;


public class ExceptionUtils {
    public static  <T> ResponseEntity<ApiResponse> handleControllerExceptions(Exception e) {
        System.out.println("Exception caught in controller:");

        if (e instanceof NotFoundException) {
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        } else if(e instanceof ResourceNotFoundException){
            System.out.println(e);
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        } else if (e instanceof IllegalArgumentException){
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        } else if(e instanceof BadRequestException){
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }else {
            // Handle other exceptions as needed
            return new ResponseEntity<>(new ApiResponse(
                    false,
                    e.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static <T> void handleServiceExceptions(Exception e) {
        System.out.println("Exception caught in service:");
        if (e instanceof NotFoundException) {
            throw new NotFoundException(e.getMessage());
        } else if(e instanceof IllegalArgumentException){
            throw new IllegalArgumentException(e.getMessage());
        }else if( e instanceof ResourceNotFoundException){
            throw new ResourceNotFoundException(e.getMessage());
        } else if (e instanceof BadRequestException){
            throw new BadRequestException(e.getMessage());
        }else {
            throw new RuntimeException("Failed!! Something went wrong " + e.getMessage(), e);
        }
    }

}