package com.theironyard.Pagination.services;

import com.theironyard.Pagination.entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Keith on 5/16/17.
 */
    public interface PaginationRepository extends PagingAndSortingRepository<Address, Integer> {
    Page<Address> findByState(Pageable pageable, String state);
    }


