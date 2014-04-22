package ar.com.orangebit.springMvcQuickstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.orangebit.springMvcQuickstart.domain.SampleEntity;

public interface SampleEntityRepository extends JpaRepository<SampleEntity, Long> {
}
