package com.hts.service;

import java.util.List;

import com.hts.entity.BroadcastStream;
import com.hts.exceptions.AppException;

public interface IBroadcastStreamService {

	BroadcastStream create(String name) throws AppException;
	void unregisterBroadcastStream(String name) throws AppException;
	List<BroadcastStream> getAllBroadcastStreams() throws AppException;
	BroadcastStream getById(Integer streamId) throws AppException;
	void delete(BroadcastStream broadcastStream) throws AppException;

	List<BroadcastStream> getByName(String name) throws AppException;
	List<BroadcastStream> getActiveByName(String name) throws AppException;
	void unregisterAllActiveBroadcastStreams() throws AppException;
}
