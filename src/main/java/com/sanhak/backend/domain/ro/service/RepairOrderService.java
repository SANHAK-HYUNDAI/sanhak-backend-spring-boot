package com.sanhak.backend.domain.ro.service;

import com.sanhak.backend.domain.ro.RepairOrder;
import com.sanhak.backend.domain.ro.repository.RepairOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RepairOrderService {
    private RepairOrderRepository repairOrderRepository;

    public RepairOrder findById(Long id) {
        return repairOrderRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Long deleteById(Long id) {
        repairOrderRepository.deleteById(id);
        return id;
    }
}
