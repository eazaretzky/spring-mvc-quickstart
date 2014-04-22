package ar.com.orangebit.springMvcQuickstart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.orangebit.springMvcQuickstart.domain.SampleEntity;
import ar.com.orangebit.springMvcQuickstart.repository.SampleEntityRepository;

@Service
public class SampleEntityService {

  @Autowired
  private SampleEntityRepository sampleEntityRepository;
  
  @Transactional(readOnly = true)
  public List<SampleEntity> findAll() {
    return sampleEntityRepository.findAll();
  }
}
