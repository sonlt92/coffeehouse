package projecta07.service;

import projecta07.model.Status;

import java.util.List;
import java.util.Optional;

public interface IStatusService {
    List<Status> findAll();
    Optional<Status> findStatusById(Long id);
}
