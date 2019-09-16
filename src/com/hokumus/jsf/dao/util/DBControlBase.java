package com.hokumus.jsf.dao.util;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.hokumus.jsf.utils.myHbUtil;

public class DBControlBase<T> implements IDBService<T> {

	private Session session;
	private Transaction transaction;

	public Session getSession() {
		return session;
	}

	public void getSessionAndTrans() {
		session = (Session) myHbUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

	public void closeSesionAndCommit() {
		transaction.commit();
		session.close();
	}

	public void closeSesionAndRoolback() {
		transaction.rollback();
		session.close();
	}

	@Override
	public Boolean kaydet(T temp) {
		try {
			getSessionAndTrans();
			session.save(temp);
			closeSesionAndCommit();
		} catch (Exception e) {
			// log al
			closeSesionAndRoolback();
			e.printStackTrace();

			return false;
		}

		return true;
	}

	@Override
	public Boolean guncelle(T temp) {
		try {
			getSessionAndTrans();
			session.update(temp);
			closeSesionAndCommit();
		} catch (Exception e) {
			// log al
			closeSesionAndRoolback();
			e.printStackTrace();

			return false;
		}

		return true;
	}

	@Override
	public Boolean sil(T temp) {
		try {
			getSessionAndTrans();
			session.delete(temp);
			closeSesionAndCommit();
		} catch (Exception e) {
			// log al
			closeSesionAndRoolback();
			e.printStackTrace();

			return false;
		}

		return true;
	}

	@Override
	public List<T> getir(T temp) {
		List<T> liste = null;
		try {
			getSessionAndTrans();
			Criteria cr = session.createCriteria(temp.getClass());
			liste = cr.list();
			closeSesionAndCommit();
		} catch (Exception e) {
			// log al
			closeSesionAndRoolback();
			e.printStackTrace();

			return null;
		}

		return liste;
	}

	@Override
	public T bul(Long id, T temp) {
		T t = null;
		try {
			getSessionAndTrans();
			Criteria cr = session.createCriteria(temp.getClass());
			cr.add(Restrictions.eq("id", id));
			t = (T) cr.uniqueResult();
			closeSesionAndCommit();
		} catch (Exception e) {
			// log al
			closeSesionAndRoolback();
			e.printStackTrace();

			return null;
		}
		return t;
	}

	@Override
	public List<T> getir(String kolonAdi, String deger, T temp) {
		List<T> liste = null;
		try {
			getSessionAndTrans();
			Criteria cr = session.createCriteria(temp.getClass());
			cr.add(Restrictions.ilike(kolonAdi, "%" + deger + "%"));
			liste = cr.list();
			closeSesionAndCommit();
		} catch (Exception e) {
			// log al
			closeSesionAndRoolback();
			e.printStackTrace();

			return null;
		}

		return liste;
	}

	@Override
	public List<T> ara(T temp) {
		List<T> liste = null;
		try {
			getSessionAndTrans();
			Criteria cr = session.createCriteria(temp.getClass());
			Class cl = temp.getClass();
			Field[] fields = cl.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if (fields[i].get(temp) != null && !fields[i].get(temp).toString().equals("")) {
					if (fields[i].getType().getName().substring(fields[i].getType().getName().lastIndexOf('.') + 1)
							.equals("Long")) {
						cr.add(Restrictions.eq(fields[i].getName(), fields[i].get(temp)));
					} else {
						cr.add(Restrictions.ilike(fields[i].getName(), "%" + fields[i].get(temp) + "%"));
					}
				}

			}
			liste = cr.list();
			closeSesionAndCommit();
		} catch (Exception e) {
			// log al
			closeSesionAndRoolback();
			e.printStackTrace();

			return null;
		}

		return liste;
	}

	@Override
	public T bul(T temp) {
		// TODO Auto-generated method stub
		return null;
	}

}
