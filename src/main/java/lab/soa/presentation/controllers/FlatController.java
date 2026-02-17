package lab.soa.presentation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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

import lab.soa.presentation.dto.requests.flat.FlatRequestCreateDto;
import lab.soa.presentation.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.presentation.dto.responses.LongValueResponseDto;
import lab.soa.presentation.dto.responses.flat.FlatGroupsByHeightResponseDto;
import lab.soa.presentation.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.presentation.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.soa.service.filters.flat.FlatFilterParam;
import lab.soa.service.filters.flat.StringToFlatFilterParamConverter;
import lab.soa.service.services.flat.FlatService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/flats")
@RequiredArgsConstructor
public class FlatController {
    private final FlatService flatService;
    private final StringToFlatFilterParamConverter stringToFlatFilterParamConverter;

    @PostMapping(
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatResponseByIdDto> create(@RequestBody FlatRequestCreateDto requestDto) {
        FlatResponseByIdDto responseDto = flatService.create(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<WrapperListFlatsResponseDto> getAll(
        @RequestParam(name = "filter", required = false) List<String> filterStrings,
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<FlatFilterParam> filterParams = filterStrings != null ?
            filterStrings.stream()
                .map(filterString -> stringToFlatFilterParamConverter.convert(filterString))
                .collect(Collectors.toList()) : null;
        return ResponseEntity.ok(
            flatService.findAll(
                filterParams,
                pageable
            )
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOneFlatByFilter(
        @RequestParam(required = false) String houseName,
        @RequestParam(required = false) Integer houseYear,
        @RequestParam(required = false) Integer numberOfFlatsOnFloor
    ) {
        flatService.deleteOneFlatByFilter(
            houseName,
            houseYear,
            numberOfFlatsOnFloor
        );
        return ResponseEntity.noContent().build();
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatResponseByIdDto> getById(@PathVariable Long id) {
        FlatResponseByIdDto dto = flatService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(
        value = "/{id}",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatResponseByIdDto> update(
        @PathVariable Long id,
        @RequestBody FlatRequestUpdateDto requestDto
    ) {
        FlatResponseByIdDto responseDto = flatService.update(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        flatService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(
        value = "/sum/height",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<LongValueResponseDto> getAmountOfHeights() {
        LongValueResponseDto dto = flatService.getAmountOfHeights();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(
        value = "/group-by/height",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatGroupsByHeightResponseDto> getGroupsByHeight() {
        FlatGroupsByHeightResponseDto dto = flatService.getGroupsByHeight();
        return ResponseEntity.ok(dto);
    }
}
