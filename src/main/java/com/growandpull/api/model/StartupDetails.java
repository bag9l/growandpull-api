package com.growandpull.api.model;

import jakarta.persistence.*;
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

    @Lob
    @Column(name = "`conceptualization_and_problem_identification`", length = 1000, nullable = false)
    private String conceptualizationAndProblemIdentification;

    @Lob
    @Column(name = "`solution_development`", length = 1000, nullable = false)
    private String solutionDevelopment;

    @Lob
    @Column(name = "`competitive_analysis`", length = 1000, nullable = false)
    private String competitiveAnalysis;

    @Lob
    @Column(name = "`financial_strategy`", length = 1000, nullable = false)
    private String financialStrategy;

    @Lob
    @Column(name = "`business_model_and_investment_purpose`", length = 1000, nullable = false)
    private String businessModelAndInvestmentPurpose;

    @Lob
    @Column(name = "`investor_proposition`", length = 1000, nullable = false)
    private String investorProposition;

    @Lob
    @Column(name = "`team_and_leadership`", length = 1000, nullable = false)
    private String teamAndLeadership;

    @Lob
    @Column(name = "`mentorship_and_advisory_support`", length = 1000, nullable = false)
    private String mentorshipAndAdvisorySupport;

    @Lob
    @Column(name = "`investment_partnership`", length = 1000, nullable = false)
    private String investmentPartnership;

    @Lob
    @Column(name = "`achievements_and_recognition`", length = 1000, nullable = false)
    private String achievementsAndRecognition;
}
