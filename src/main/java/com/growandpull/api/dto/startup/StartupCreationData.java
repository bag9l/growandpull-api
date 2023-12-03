package com.growandpull.api.dto.startup;

import com.growandpull.api.model.entity.Category;
import com.growandpull.api.model.enums.StartupStatus;

import java.util.List;

//is used to send to client selection data
public record StartupCreationData(
        List<StartupStatus> statuses,
        List<Category> categories
) {
}
