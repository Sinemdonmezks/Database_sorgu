import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Runner {

	public static void main(String[] args) throws Exception {
		Driver.class.forName("org.postgresql.Driver");

		String adres = "jdbc:postgresql://localhost:5432/etrade3";
		String kullaniciAdi = "postgres";
		String sifre = "root";
		Connection connect = null;
		connect = DriverManager.getConnection(adres, kullaniciAdi, sifre);
		System.out.println("bağlantı basarılı");

		String SQL = "select u.gender,count (u.gender) as kız_erkek_sayısı from address as a join users as u on a.userid=u.id group by gender\n"
				+ "";
		String SQL2 = " select namesurname,gender,totalprice,addressid \n"
				+ "from users join orders on orders.userid=users.id \n" + "where totalprice <100.000";

		// execute(connect, SQL2);
		execute(connect, SQL);
		System.out.println("sorgu bitti");
	}

	public static void execute(Connection connection, String sorgu) throws Exception {

		PreparedStatement ps = connection.prepareStatement(sorgu);

		ps.execute();

		connection.close();
	}
}
