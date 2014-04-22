package ar.com.orangebit.springMvcQuickstart.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sample_entity")
public class SampleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "creation_data", nullable = false)
  private Date creationDate;

  @Column(name = "modification_data", nullable = false)
  private Date modificationDate;

  @Version
  private long version = 0;

  @Column(name = "sample_property", nullable = false)
  private String sampleProperty;

  public SampleEntity() {}

  public SampleEntity(String sampleProperty) {
    super();
    this.sampleProperty = sampleProperty;
  }

  public Long getId() {
    return id;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public Date getModificationDate() {
    return modificationDate;
  }

  public long getVersion() {
    return version;
  }

  public String getSampleProperty() {
    return sampleProperty;
  }

  public void setSampleProperty(String sampleProperty) {
    this.sampleProperty = sampleProperty;
  }

  @PreUpdate
  public void preUpdate() {
    modificationDate = new Date();
  }

  @PrePersist
  public void prePersist() {
    Date now = new Date();
    creationDate = now;
    modificationDate = now;
  }

  protected void setId(Long id) {
    this.id = id;
  }
}
