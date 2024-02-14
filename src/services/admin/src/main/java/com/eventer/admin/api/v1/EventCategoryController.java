package com.eventer.admin.api.v1;

import com.eventer.admin.api.ControllerBase;
import com.eventer.admin.domain.EventCategory;
import com.eventer.admin.dto.eventCategory.EventCategoryDTO;
import com.eventer.admin.mapper.EventCategoryMapper;
import com.eventer.admin.service.EventCategoryService;
import com.eventer.admin.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event-category")
public class EventCategoryController extends ControllerBase {

    private final EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventCategoryDTO dto) {
        Result<EventCategory> result = this.eventCategoryService.create(dto.name(), dto.description());
        return this.okOrError(result, EventCategoryMapper::toDTO);
    }

    @GetMapping
    public ResponseEntity<?> getCategories(final Pageable pageable) {
        Result<Page<EventCategory>> result = this.eventCategoryService.getCategories(pageable);
        return this.okOrError(result, EventCategoryMapper::toDTOs);
    }
}
