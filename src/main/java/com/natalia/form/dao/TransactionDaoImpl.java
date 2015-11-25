package com.natalia.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.natalia.form.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Transaction findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String sql = "SELECT * FROM transactions WHERE id=:id";
		Transaction result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new TransactionMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}

		/*
		 * Transaction result = namedParameterJdbcTemplate.queryForObject( sql, params,
		 * new BeanPropertyRowMapper<Transaction>());
		 */
		return result;
	}

	@Override
	public List<Transaction> findAll() {
		String sql = "SELECT * FROM transactions";
		List<Transaction> result = namedParameterJdbcTemplate.query(sql, new TransactionMapper());
		return result;
	}

	@Override
	public List<Transaction> findByPattern(String pattern) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pattern", pattern);
		String sql = "SELECT t FROM transactions t WHERE t.transaction_date=:pattern";
		List<Transaction> result = namedParameterJdbcTemplate.query(sql, params, new TransactionMapper());
		return result;
	}

		/*pattern="10/10/2015";
		String sql = "SELECT t FROM transactions t WHERE t.transaction_date=:pattern";
		List<Transaction> result = namedParameterJdbcTemplate.query(sql, new TransactionMapper());
		return result;
	}*/

	@Override
	public void save(Transaction transaction) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO transactions(user, transaction_date, transaction_type, source_type, source_sum, transaction_desc) "
				+ "VALUES ( :user, :transaction_date, :transaction_type, :source_type, :source_sum, :transaction_desc)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(transaction), keyHolder);
		transaction.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Transaction transaction) {
		String sql = "UPDATE transactions SET user=:user, transaction_date=:transaction_date, transaction_type=:transaction_type, "
				+ "source_type=:source_type, source_sum=:source_sum, transaction_desc=:transaction_desc WHERE id=:id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(transaction));
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM transactions WHERE id= :id";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
	}

	private SqlParameterSource getSqlParameterByModel(Transaction transaction) {
		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", transaction.getId());
		paramSource.addValue("user", transaction.getUser());
		paramSource.addValue("transaction_date", transaction.getTransaction_date());
		paramSource.addValue("transaction_type", convertListToDelimitedString(transaction.getTransaction_type()));
		paramSource.addValue("source_type", convertListToDelimitedString(transaction.getSource_type()));
		paramSource.addValue("source_sum", transaction.getSource_sum());
		paramSource.addValue("transaction_desc", transaction.getTransaction_desc());
		return paramSource;
	}

	private static final class TransactionMapper implements RowMapper<Transaction> {
		public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
			Transaction transaction = new Transaction();
			transaction.setId(rs.getInt("id"));
			transaction.setUser(rs.getString("user"));
			transaction.setTransaction_date(rs.getString("transaction_date"));
			transaction.setTransaction_type(convertDelimitedStringToList(rs.getString("transaction_type")));
			transaction.setSource_type(convertDelimitedStringToList(rs.getString("source_type")));
			transaction.setSource_sum(rs.getDouble("source_sum"));
			transaction.setTransaction_desc(rs.getString("transaction_desc"));
			return transaction;
		}
	}

	private static List<String> convertDelimitedStringToList(String delimitedString) {
		List<String> result = new ArrayList<String>();
		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;
	}

	private String convertListToDelimitedString(List<String> list) {
		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;
	}
}