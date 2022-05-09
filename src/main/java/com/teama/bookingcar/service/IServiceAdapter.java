package com.teama.bookingcar.service;

import com.teama.bookingcar.service.exception.InvalidDataException;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface IServiceAdapter<T> {
    T create(T dto) throws Exception;

    T update(Long id, T dto) throws Exception;

    T getById(Long id);

    boolean deleteById(Long id) throws Exception;

    Collection<T> getAll(Pageable pageable);
}
