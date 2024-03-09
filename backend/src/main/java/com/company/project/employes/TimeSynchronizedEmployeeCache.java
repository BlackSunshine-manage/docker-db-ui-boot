package com.company.project.employes;

import com.company.project.utils.CollectionsNotEquivalence;
import org.springframework.stereotype.Component;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class TimeSynchronizedEmployeeCache extends StandartEmployeeCache {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private static final int INITIAL_DELAY = 0;
    private static final int PERIOD = 1; // период выполнения задачи в минутах
    public TimeSynchronizedEmployeeCache(EmployeeRepository employeeRepository) {
        super(employeeRepository);
        executor.scheduleAtFixedRate(this::updateDataBase, INITIAL_DELAY, PERIOD, TimeUnit.MINUTES);
        employees = ((ArrayList<EmployeeEntity>)employeeRepository.findAll())
                .stream()
                .map(EmployeeMapper::new)
                .map(EmployeeMapper::mapToBusiness)
                .collect(Collectors.toCollection(ConcurrentSkipListSet::new));
        bypass = new ConcurrentSkipListSet<>(employees);
    }

    protected void updateDataBase() {
        new CollectionsNotEquivalence<>(employees, bypass, () -> {
            bypass = new ConcurrentSkipListSet<>(employees);
            employees.stream()
                    .map(EmployeeMapper::new)
                    .map(EmployeeMapper::mapToEntity)
                    .forEach(employeeRepository::save);
        });
    }
}