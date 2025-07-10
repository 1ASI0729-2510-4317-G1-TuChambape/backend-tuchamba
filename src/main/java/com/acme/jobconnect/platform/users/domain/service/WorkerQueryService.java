package com.acme.jobconnect.platform.users.domain.service;

import com.acme.jobconnect.platform.users.domain.model.aggregates.Worker;
import com.acme.jobconnect.platform.users.domain.model.queries.GetAllWorkersQuery;
import com.acme.jobconnect.platform.users.domain.model.queries.GetWorkerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface WorkerQueryService {
    Optional<Worker> handle(GetWorkerByIdQuery query);
    List<Worker> handle(GetAllWorkersQuery query);
} 