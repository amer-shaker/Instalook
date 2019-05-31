package com.instalook.instalook.model.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Amer Shaker
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "services",
        catalog = "heroku_858654a6d05adcb"
)
public class Service implements java.io.Serializable {

    private Integer serviceId;
    private String serviceName;
    private String serviceType;
    private Double servicePrice;
    private Set<Salon> salons = new HashSet<>(0);

    public Service() {
    }

    public Service(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Service(Integer serviceId, String serviceName, String serviceType, Double servicePrice, Set<Salon> salons) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
        this.salons = salons;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", unique = true, nullable = false)
    public Integer getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Column(name = "service_name", length = 45)
    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Column(name = "service_type", length = 45)
    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Column(name = "service_price", precision = 22, scale = 0)
    public Double getServicePrice() {
        return this.servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "services")
    @JsonIgnore
    public Set<Salon> getSalons() {
        return this.salons;
    }

    public void setSalons(Set<Salon> salons) {
        this.salons = salons;
    }

}
