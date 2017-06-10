package com.globalVisionSystem.repository;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by Dasha on 07.06.2017.
 */

public interface IRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

}
