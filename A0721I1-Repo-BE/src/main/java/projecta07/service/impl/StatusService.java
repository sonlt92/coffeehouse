package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Status;
import projecta07.repository.IStatusRepository;
import projecta07.service.IStatusService;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatusService {
    @Autowired
    private IStatusRepository iStatusRepository;

    @Override
    public List<Status> findAll() {
        return iStatusRepository.findAllStatus();
    }

    @Override
    public Optional<Status> findStatusById(Long id) {
        return iStatusRepository.findById(id);
    }
}
