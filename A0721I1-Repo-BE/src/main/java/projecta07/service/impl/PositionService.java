package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Position;
import projecta07.repository.IPositionRepository;
import projecta07.service.IPositionService;

import java.util.List;

@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<Position> listPosition() {
        return positionRepository.findAll();
    }
}
