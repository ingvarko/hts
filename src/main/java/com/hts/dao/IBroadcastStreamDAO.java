package com.hts.dao;

import com.hts.entities.BroadcastStream;
import com.hts.exceptions.AppException;

import java.util.List;

public interface IBroadcastStreamDAO {
	BroadcastStream create(BroadcastStream stream) throws AppException;
	BroadcastStream getById(Integer streamId) throws AppException;
	List<BroadcastStream> getByName(String name) throws AppException;
	void update(BroadcastStream stream) throws AppException;
	void delete(BroadcastStream stream) throws AppException;	
	
	List<BroadcastStream> getAll() throws AppException;	
	List<BroadcastStream > list(int firstResult, int maxResults) throws AppException;
	List<BroadcastStream> getActiveByName(String name) throws AppException;
}


