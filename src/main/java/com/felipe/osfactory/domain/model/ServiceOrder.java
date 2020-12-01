package com.felipe.osfactory.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.felipe.osfactory.domain.ValidationGroups;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
    @NotNull
    @ManyToOne
    private Client client;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusServiceOrder status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime openDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime finishDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public StatusServiceOrder getStatus() {
        return status;
    }
    public void setStatus(StatusServiceOrder status) {
        this.status = status;
    }
    public OffsetDateTime getOpenDate() {
        return openDate;
    }
    public void setOpenDate(OffsetDateTime openDate) {
        this.openDate = openDate;
    }
    public OffsetDateTime getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(OffsetDateTime finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceOrder that = (ServiceOrder) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
