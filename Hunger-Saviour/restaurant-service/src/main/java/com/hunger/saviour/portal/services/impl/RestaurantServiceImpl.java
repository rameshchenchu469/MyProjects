package com.hunger.saviour.portal.services.impl;

import com.hunger.saviour.portal.dtos.RestaurantDTO;
import com.hunger.saviour.portal.entities.RestaurantEntity;
import com.hunger.saviour.portal.services.RestaurantService;
import com.hunger.saviour.portal.repositories.RestaurantRepository;
import com.hunger.saviour.portal.utilities.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = RestaurantMapper.INSTANCE.DTOtoEntity(restaurantDTO);
        this.restaurantRepository.save(restaurantEntity);
        return RestaurantMapper.INSTANCE.entityToDTO(restaurantEntity);
    }

    @Override
    public Page<RestaurantDTO> getRestaurants(int offset, int pagesize) {
        Page<RestaurantEntity> restaurantEntityPage = this.restaurantRepository.findAll(PageRequest.of(offset, pagesize));
        return restaurantEntityPage.map(RestaurantMapper.INSTANCE::entityToDTO);
    }

    @Override
    public RestaurantDTO getRestaurantById(Integer id) {
        Optional<RestaurantEntity> opt=restaurantRepository.findById(id);
        RestaurantEntity restaurant=opt.orElseThrow(()->new IllegalArgumentException("restaurant id not found"));
        RestaurantDTO restaurantDTO= RestaurantMapper.INSTANCE.entityToDTO(restaurant);
        return restaurantDTO ;
    }
}
