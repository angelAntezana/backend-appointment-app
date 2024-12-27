package com.backend.appointment.appointment_app.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appointment.appointment_app.dto.CustomerDto;
import com.backend.appointment.appointment_app.exceptions.CustomException;
import com.backend.appointment.appointment_app.services.CustomerService;
import com.backend.appointment.appointment_app.util.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api-version.prefix}/customer")
public class CustomerController {
    
    private CustomerService customerService;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = {"{personId}"})
    public ResponseEntity<CustomerDto> getById(@PathVariable Long personId) throws CustomException {
        return ResponseEntity.ok(customerService.getById(personId));
    }

     /**
     * Get all customers with filtering and pagination options.
     *
     * @param page      Number of page (default: 0)
     * @param size      Size of page (default: 10)
     * @param sortBy    Sort field (default: firstName)
     * @param direction direction of arrangement (ascending or descending, default: asc)
     * @return Page of filtered customer
     */
    @Operation(summary = "Get all customers", description = "Get all customers with filtering and pagination options.")
    @Parameters({
            @Parameter(name = "page", description = "Number of page", example = "0"),
            @Parameter(name = "size", description = "Size of page", example = "10"),
            @Parameter(name = "sortBy", description = "Sort field", example = "firstName"),
            @Parameter(name = "direction", description = "Direction of arrangement", example = "asc")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated list of clients")
    })

    @GetMapping(value = {""})
    public ResponseEntity<PageResponse<CustomerDto>> getAll(
    @RequestParam(defaultValue = "0")int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "firstName")List<String>sortBy,
    @RequestParam(defaultValue = "asc")List<String> direction ) throws CustomException {

        if (sortBy.size() != direction.size()) {
            throw new IllegalArgumentException("The size of orderBy and orderDirection must match");
        }
    
        // Construir el objeto Sort con múltiples criterios y direcciones
        Sort sort = Sort.by(
            IntStream.range(0, sortBy.size())
                .mapToObj(i -> {
                    String field = sortBy.get(i);
                    String orderDirection = direction.get(i);
                    return orderDirection.equalsIgnoreCase("desc") ? Sort.Order.desc(field) : Sort.Order.asc(field);
                })
                .toList()
        );
    
        // Crear el objeto Pageable con la paginación y el ordenamiento
        Pageable pageable = PageRequest.of(page, size, sort);
         // Obtener la página de datos
        Page<CustomerDto> customersDtos = customerService.getAll(pageable);

        // Construir la respuesta con el tipo genérico correctamente definido
        PageResponse<CustomerDto> response = PageResponse.of(customersDtos, sortBy, direction);
        return ResponseEntity.ok(response);
    }

     /**
     * Get all customers by search term with filtering and pagination options.
     *
     * @param page      Number of page (default: 0)
     * @param size      Size of page (default: 10)
     * @param sortBy    Sort field (default: firstName)
     * @param direction direction of arrangement (ascending or descending, default: asc)
     * @param searchTerm Search term (required: true)
     * @return Page of filtered customer by search term
     */
    @Operation(summary = "Get all customers", description = "Get all customers with filtering and pagination options.")
    @Parameters({
            @Parameter(name = "page", description = "Number of page", example = "0"),
            @Parameter(name = "size", description = "Size of page", example = "10"),
            @Parameter(name = "sortBy", description = "Sort field", example = "firstName"),
            @Parameter(name = "direction", description = "Direction of arrangement", example = "asc"),
            @Parameter(name = "searchTerm", description = "Search term", example = "Janes")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated list of clients by search term")
    })
    @GetMapping(value = {"/search"}) 
    public ResponseEntity<PageResponse<CustomerDto>> getBySearchTerm(
        @RequestParam(defaultValue = "0")int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "firstName")List<String>sortBy,
        @RequestParam(defaultValue = "asc")List<String> direction,
        @RequestParam(required = true) String searchTerm) throws CustomException {
    
            if (sortBy.size() != direction.size()) {
                throw new IllegalArgumentException("The size of orderBy and orderDirection must match");
            }
        
            // Construir el objeto Sort con múltiples criterios y direcciones
            Sort sort = Sort.by(
                IntStream.range(0, sortBy.size())
                    .mapToObj(i -> {
                        String field = sortBy.get(i);
                        String orderDirection = direction.get(i);
                        return orderDirection.equalsIgnoreCase("desc") ? Sort.Order.desc(field) : Sort.Order.asc(field);
                    })
                    .toList()
            );
        
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<CustomerDto> customersDtos = customerService.getSearchByTerm(searchTerm, pageable);
    
            PageResponse<CustomerDto> response = PageResponse.of(customersDtos, sortBy, direction);
            return ResponseEntity.ok(response);
        }
    
    

    @PostMapping(value = {""})
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto customerRequest) throws CustomException {
        return ResponseEntity.ok(customerService.create(customerRequest));
    }

    @PutMapping(value = { "{personId}" })
    public ResponseEntity<CustomerDto> update(@PathVariable Long personId, @Valid @RequestBody CustomerDto customerRequest) throws CustomException{
        return ResponseEntity.ok(customerService.update(personId,customerRequest));
    }

    @DeleteMapping(value = { "{personId}" })
    public ResponseEntity<Boolean> delete(@PathVariable Long personId) throws CustomException {
        return ResponseEntity.ok(customerService.delete(personId));
    }
}
