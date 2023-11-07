package com.growandpull.api.dto;

import com.growandpull.api.model.Category;
import com.growandpull.api.model.StartupStatus;

import java.util.List;

//is used to send to client selection data
public record StartupCreateData(
        List<StartupStatus> statuses,
        List<Category> categories
) {
}
