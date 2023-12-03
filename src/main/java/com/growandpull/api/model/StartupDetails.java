package com.growandpull.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`startup_details`")
@Entity
public class StartupDetails {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String conceptualization;
    private String competitiveAnalysis;
    private String businessModel;
    private String teamAndLeadership;
    private String investmentPartnership;
    private String solutionDevelopment;
    private String financialStrategy;
    private String investorPropositions;
    private String mentorshipAndAdvisorySupport;
    private String achievementsAndRecognition;


}
