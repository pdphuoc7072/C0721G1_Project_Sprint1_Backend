package com.example.statsticals.service.Impl;

import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.repository.SuppliesDtoRepository;
import com.example.statsticals.service.ISuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
import java.util.List;

@Service
public class SuppliesService implements ISuppliesService {
    @Autowired
    SuppliesDtoRepository suppliesDtoRepository;


    @Override
    public List<SuppiliesDtoInterface> getAll() {
        return suppliesDtoRepository.getAll();
    }
<<<<<<< HEAD

    @Override
    public List<SuppiliesDtoInterface> getSuppliesByTime(LocalDate startDate, LocalDate endDate) {
        return suppliesDtoRepository.getSuppliesByTime(startDate,endDate);
    }
=======
>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
}
