package online.shixun.project.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import online.shixun.project.spring.model.UserModel;

@Repository
public class UserDao {
	//注入sessionFactory
	private SessionFactory sessionFactory;
	
	public void saveUser(UserModel user){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
	}

	/**
	 * 查询数据库中用户信息
	 * @param sessionFactory
	 */
	public List<UserModel> getUser(){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		List<UserModel> list = session.createQuery("from UserModel u ").list();
		transaction.commit();
		session.close();
		return list;
	}
	
	//@Autowired
	@Resource(name = "mySessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession(){
		return sessionFactory.openSession();
	}
	
	

}
