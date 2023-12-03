package com.growandpull.api.dto.startup;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.model.StartupStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@AllArgsConstructor
public class StartupUpdateRequest {
    private final String title;
    private final String description;
    private final StartupStatus status;
    private final String categoryId;
    private final FinanceDto finance;

    private final StartupDetailsDto startupDetails;
    private MultipartFile image;

    @JsonCreator
    public StartupUpdateRequest(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("status") StartupStatus status,
            @JsonProperty("categoryId") String categoryId,
            @JsonProperty("finance") FinanceDto finance,
            @JsonProperty("startupDetails") StartupDetailsDto startupDetails) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
        this.finance = finance;
        this.startupDetails = startupDetails;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
