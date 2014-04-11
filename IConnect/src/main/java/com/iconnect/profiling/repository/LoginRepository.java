package com.iconnect.profiling.repository;

public interface LoginRepository<T> {
	public T performLoginCheck(String userName);

}
