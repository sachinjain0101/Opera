package com.bullhorn.persistence.timecurrent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bullhorn.persistence.timecurrent.model.TblIntegrationMappings;

public interface GetMap extends JpaRepository<TblIntegrationMappings, Long>, GetMapCustom{

}
