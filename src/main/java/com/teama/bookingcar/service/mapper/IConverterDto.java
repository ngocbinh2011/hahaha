package com.teama.bookingcar.service.mapper;

import java.util.Collection;
import java.util.List;

public interface IConverterDto<T1, T2> {
    T2 convertToDto(T1 entity);

    T1 convertToEntity(T2 dto);

    List<T2> convertToListDto(Collection<T1> collection);

    List<T1> convertToListEntity(Collection<T2> collection);
}
