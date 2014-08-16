package dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.models.User;

public class DAOImpl implements DAO {

	private SqlSessionFactory sessionFactory;
	private static final String config = "play-java/app/dao/configuration.xml";

	public DAOImpl() {
		try {
			Reader reader = Resources.getResourceAsReader(config);
			String environment = "development";
			this.sessionFactory = new SqlSessionFactoryBuilder().build(reader, environment);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUser(int id) {
		String user = null;
		SqlSession session = null;
		try {	
			session = sessionFactory.openSession();
			DAO mapper = session.getMapper(DAO.class);
			user = mapper.getUser(id);
		} finally {
			if (session != null) {
				session.rollback();
				session.close();
			}
		}
		return user;
	}
}