package dao;

import org.apache.ibatis.annotations.Param;

import dao.models.User;

public interface DAO {
	public String getUser(@Param("id") int id);
}
