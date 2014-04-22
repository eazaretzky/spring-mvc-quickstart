package ar.com.orangebit.springMvcQuickstart.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SampleEntityDto {
  private Long id;
  private String sampleProperty;

  public SampleEntityDto() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSampleProperty() {
    return sampleProperty;
  }

  public void setSampleProperty(String sampleProperty) {
    this.sampleProperty = sampleProperty;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
