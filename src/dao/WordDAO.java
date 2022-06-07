package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Word;

public class WordDAO {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	private void connect() throws NamingException, SQLException{
		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/ejword");
		this.db=ds.getConnection();
	}
	private void disconnection(){
		try {
			if(rs !=null) {
				rs.close();
			}
			if(ps !=null) {
				ps.close();
			}
			if(db !=null) {
				db.close();
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public List<Word> getListBySearchWord(String searchWord,String mode){
List<Word> list = new ArrayList<>();
		switch(mode) {
		case "startsWith":
			searchWord=searchWord+"%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM words WHERE title Like ?");
			ps.setString(1, searchWord);
			rs=ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String body = rs.getString("body");
				Word word = new Word(title,body);
				list.add(word);
			}
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnection();
		}
		return list;
	}

	public List<Word> getListBySearchWord(String searchWord,String mode,int limit){
		List<Word> list = new ArrayList<>();
		switch(mode) {
		case "startsWith":
			searchWord=searchWord+"%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM words WHERE title Like ? LIMIT ?");
			ps.setString(1, searchWord);
			ps.setInt(2, limit);
			rs=ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String body = rs.getString("body");
				Word word = new Word(title,body);
				list.add(word);
			}
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnection();
		}
		return list;
	}
	public List<Word> getListBySearchWord(String searchWord,String mode,int limit,int offset){
		List<Word> list = new ArrayList<>();
		switch(mode) {
		case "startsWith":
			searchWord=searchWord+"%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM words WHERE title Like ? LIMIT ? OFFSET ?");
			ps.setString(1, searchWord);
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			rs=ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String body = rs.getString("body");
				Word word = new Word(title,body);
				list.add(word);
			}
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnection();
		}
		return list;
	}
	public int getCount(String searchWord,String mode) {
		int total=0;
		switch(mode) {
		case "startsWith":
			searchWord=searchWord+"%";
			break;
		case "contains":
			searchWord="%"+searchWord+"%";
			break;
		case "endsWith":
			searchWord="%"+searchWord;
		}
		try {
			this.connect();
			ps=db.prepareStatement("SELECT count(*) AS total FROM words WHERE title LIKE ?");
			ps.setString(1,searchWord);
			//execute実行、queryお問い合わせ
			rs=ps.executeQuery();
			if(rs.next()) {
				total = rs.getInt("total");
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnection();
		}
		return total;
	}
}
