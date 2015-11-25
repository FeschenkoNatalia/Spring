package com.natalia.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natalia.form.dao.TransactionDao;
import com.natalia.form.model.Transaction;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	TransactionDao transactionDao;

	@Autowired
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	@Override
	public Transaction findById(Integer id) {
		return transactionDao.findById(id);
	}

	@Override
	public List<Transaction> findAll() {
		return transactionDao.findAll();
	}

	@Override
	public List<Transaction> findByPattern(String pattern) {
		return transactionDao.findByPattern(pattern);
	}

	@Override
	public void saveOrUpdate(Transaction transaction) {
		if (findById(transaction.getId())==null) {
			transactionDao.save(transaction);
		} else {
			transactionDao.update(transaction);
		}
	}

	@Override
	public void delete(int id) {
		transactionDao.delete(id);
	}
}