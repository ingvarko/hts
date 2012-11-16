package com.hts.dao;

import java.util.List;

import com.hts.entity.BroadcastStream;
import com.hts.exceptions.AppException;

public interface IBroadcastStreamDAO {
	BroadcastStream create(BroadcastStream stream) throws AppException;
	BroadcastStream getById(Integer streamId) throws AppException;
	List<BroadcastStream> getByName(String name) throws AppException;
	void update(BroadcastStream stream) throws AppException;
	void delete(BroadcastStream stream) throws AppException;	
	
	List<BroadcastStream> getAll() throws AppException;	
	List<BroadcastStream > list(int firstResult, int maxResults) throws AppException;
	List<BroadcastStream> getActiveByName(String name) throws AppException;
	List<BroadcastStream> getAllActive() throws AppException;
}


