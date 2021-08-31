package dao;

import bean.Product;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    static DataSource ds;
    
    //--------------------------------------------------------------
    //--- データベースへのコネクションを貼るメソッド
    //--------------------------------------------------------------
    public Connection getConnection() throws Exception {
        if (ds == null) {
            InitialContext ic = new InitialContext();
            ds = (DataSource)ic.lookup("java:/comp/env/jdbc/book");
        }
        return ds.getConnection();
    }
    
    //--------------------------------------------------------------
    //--- productテーブルからキーワードに合致したデータを抽出し、そのリストを返却する
    //--------------------------------------------------------------
    public List<Product> search(String keyword) throws Exception {
    	//--- 返却用リストの確保
    	List<Product> ary = new ArrayList<Product>();
        
    	//--- データベース接続
        Connection con = getConnection();
        
        //--- keywordがnullかどうかで、whereをつけるかどうかを判断してSQL文を作る
        String sql = "select * from product ";
        if (keyword != null) {
        	sql = sql + " where name like ?";
        }
        
        //--- プリペアドステートメントへ登録
        PreparedStatement st = con.prepareStatement(sql);
        
        //--- where句がある（keywordがnullじゃない）場合は、値をセットする
        if (keyword != null) {
            st.setString(1,  "%" + keyword + "%");
        }

        //--- クエリの結果を取得
        ResultSet rs = st.executeQuery();
        
        //--- 結果のリストをaryへ追加するための繰返し処理
        while(rs.next()) {
        	//--- １件分のデータを格納するBeanを作成
            Product p = new Product();
            //--- Beanにクエリで得取得した値を設定（Beanのメソッドを利用）
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getInt("price"));
            //--- Beanを配列aryに追加
            ary.add(p);
        }
        //--- オブジェクトを閉じる
        st.close();
        con.close();
        
        //--- 配列aryを返す
        return ary;
    }
    
    public int insert(Product product) throws Exception {
        Connection con = getConnection();
        
        String sql = "select count(*) as kosu from product where name=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, product.getName());
        ResultSet rs = st.executeQuery();
        rs.next();
        int kosu = rs.getInt("kosu");
        rs.close();
        st.close();

        if (kosu == 0) {
        	st = con.prepareStatement("insert into product values(null, ?, ?)");
			st.setString(1,  product.getName());
			st.setInt(2,  product.getPrice());
			int line = st.executeUpdate();
	        st.close();
	        con.close();
	        return line;
        } else {
        	return 0;
        }
    }
}
