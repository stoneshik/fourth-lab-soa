package lab.soa.service.services.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.soa.domain.models.Flat;
import lab.soa.domain.repositories.flat.FlatRepository;
import lab.soa.infrastructure.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlatTxService {
    private final FlatRepository musicBandRepository;

    @Transactional(readOnly = true)
    public Flat findByIdReturnsEntity(Long id) {
        return musicBandRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException(
                    "Not found flat by id: " + id
                )
            );
    }
}
