package com.cruiseline.modules.analytics.entity;

import com.cruiseline.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "voyage_reports")
public class VoyageReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(length = 30)
    private String scope;   // VOYAGE | CABIN_CATEGORY | PORT | PERIOD

    /** Metrics stored as JSON text for flexibility. */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String metrics;

    @Column(nullable = false)
    private Instant generatedDate;

    @PrePersist
    void onCreate() {
        if (generatedDate == null) generatedDate = Instant.now();
    }

    public VoyageReport() {
    }

    public VoyageReport(Long reportId, String scope, String metrics, Instant generatedDate) {
        this.reportId = reportId;
        this.scope = scope;
        this.metrics = metrics;
        this.generatedDate = generatedDate;
    }

    public Long getReportId() {
        return this.reportId;
    }

    public String getScope() {
        return this.scope;
    }

    public String getMetrics() {
        return this.metrics;
    }

    public Instant getGeneratedDate() {
        return this.generatedDate;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public void setGeneratedDate(Instant generatedDate) {
        this.generatedDate = generatedDate;
    }

    public static VoyageReportBuilder builder() {
        return new VoyageReportBuilder();
    }

    public static class VoyageReportBuilder {
        private Long reportId;
        private String scope;
        private String metrics;
        private Instant generatedDate;

        public VoyageReportBuilder reportId(Long reportId) {
            this.reportId = reportId;
            return this;
        }
        public VoyageReportBuilder scope(String scope) {
            this.scope = scope;
            return this;
        }
        public VoyageReportBuilder metrics(String metrics) {
            this.metrics = metrics;
            return this;
        }
        public VoyageReportBuilder generatedDate(Instant generatedDate) {
            this.generatedDate = generatedDate;
            return this;
        }

        public VoyageReport build() {
            VoyageReport instance = new VoyageReport();
            instance.reportId = this.reportId;
            instance.scope = this.scope;
            instance.metrics = this.metrics;
            instance.generatedDate = this.generatedDate;
            return instance;
        }
    }

}
