package lab.soa.presentation.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.PriceType;
import lab.soa.domain.models.SortType;
import lab.soa.domain.models.TransportType;
import lab.soa.presentation.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.presentation.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.soa.service.services.flat.FlatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/flats/agency")
@RequiredArgsConstructor
public class FlatAgencyController {
    private final FlatService flatService;

    @GetMapping(
        value = "/find-with-balcony/{priceType}/{balconyType}",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatResponseByIdDto> findWithBalcony(
        HttpServletRequest request,
        @PathVariable PriceType priceType,
        @PathVariable BalconyType balconyType
    ) {
        logRequest(request);
        return ResponseEntity.ok(
            flatService.findWithBalcony(
                priceType,
                balconyType
            )
        );
    }

    @GetMapping(
        value = "/get-ordered-by-time-to-metro/{transportType}/{sortType}",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<WrapperListFlatsResponseDto> getOrderedByTimeToMetro(
        HttpServletRequest request,
        @PathVariable TransportType transportType,
        @PathVariable SortType sortType,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        logRequest(request);
        return ResponseEntity.ok(
            flatService.getOrderedByTimeToMetro(
                transportType,
                sortType,
                page,
                size
            )
        );
    }

    private void logRequest(HttpServletRequest request) {
        String fullUrl = String.format("%s://%s:%s%s",
            request.getScheme(),
            request.getServerName(),
            request.getServerPort(),
            request.getRequestURI()
        );
        String queryString = request.getQueryString();
        if (queryString != null) {
            fullUrl += "?" + queryString;
        }
        log.info("Proxy {} -> {}, Secure: {}",
            request.getMethod(),
            fullUrl,
            request.isSecure()
        );
    }
}
