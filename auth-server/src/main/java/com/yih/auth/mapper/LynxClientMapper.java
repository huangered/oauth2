package com.yih.auth.mapper;

import com.yih.auth.domain.oauth2.AppClient;
import com.yih.auth.entity.AppClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LynxClientMapper {
    LynxClientMapper instance = Mappers.getMapper(LynxClientMapper.class);

    AppClient entityToDomain(AppClientEntity entity);
}
