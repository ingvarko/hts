package com.hts.service;

import java.util.List;

import com.hts.entities.BroadcastStream;
import com.hts.exceptions.AppException;

public interface IBroadcastStreamService {

	BroadcastStream create(String name) throws AppException;
	BroadcastStream unregisterBroadcastStream(BroadcastStream stream) throws AppException;
	List<BroadcastStream> getAllBroadcastStreams() throws AppException;
	BroadcastStream getById(Integer streamId) throws AppException;
	void delete(BroadcastStream broadcastStream) throws AppException;

	//
	void allowRoomAccess() throws AppException;
	void withdrawRoomAccess() throws AppException;
	
	//
	void connectRoom() throws AppException;
	void disconnectRoom()throws AppException;
	List<BroadcastStream> getByName(String name) throws AppException;
}
