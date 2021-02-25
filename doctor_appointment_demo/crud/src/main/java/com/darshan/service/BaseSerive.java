package com.darshan.service;

import java.io.Serializable;
import java.util.List;

public interface BaseSerive<T, ID extends Serializable> {

	/**
	 * This <code>deleteById</code> method is used for delete record by id.
	 *
	 * @param id
	 * @return
	 */
	public void deleteById(ID id);

	/**
	 * This <code>getAll</code> method is used for get all records.
	 *
	 * @param <T>
	 * @return
	 */
	public List<T> findAll();

	/**
	 * This <code>findOne</code> method is used for find single record by id.
	 *
	 * @param <T>
	 * @param id
	 * @return
	 */
	public T findById(ID id);

	/**
	 * This <code>update</code> method is used for update record.
	 *
	 * @param <T>
	 * @param t
	 * @return
	 */
	public T saveORupdate(T t);

}
