package jdbcdemo;

import java.sql.*;
import java.util.*;

public class JDBC_RDBMS {
	private static final String URL = "jdbc:postgresql://localhost:5432/mydb";
	private static final String USER = "postgres";
	private static final String PASSWD = "bappa@19";

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWD);
	}

	// POJO class
	public static class Employee {
		int id;
		String name;
		int age;
		double salary;

		public Employee(int id, String name, int age, double salary) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.salary = salary;
		}
	}

	public static void createTbl(String tbl, String sql) {
		try (Connection con = JDBC_RDBMS.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(tbl + " created successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertData(String tblName, String name, int age, double salary) {
		String query = "INSERT INTO " + tblName + "(name,age,salary) VALUES(?,?,?)";
		try (Connection con = JDBC_RDBMS.getConnection();
			 PreparedStatement pstm = con.prepareStatement(query)) {
			pstm.setString(1, name);
			pstm.setInt(2, age);
			pstm.setDouble(3, salary);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateData(String tblName, int id, String name, int newAge, double salary) {
		String query = "UPDATE " + tblName + " SET name=?,age=?,salary=? WHERE id=?";
		try (Connection con = JDBC_RDBMS.getConnection();
			 PreparedStatement pstm = con.prepareStatement(query)) {
			pstm.setString(1, name);
			pstm.setInt(2, newAge);
			pstm.setDouble(3, salary);
			pstm.setInt(4, id);
			int rowAffected = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateMultipleEmployees(String tblName, List<Employee> employees) {
		String query = "UPDATE " + tblName + " SET name=?, age=?, salary=? WHERE id=?";
		try (Connection con = JDBC_RDBMS.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {

			for (Employee emp : employees) {
				pstmt.setString(1, emp.name);
				pstmt.setInt(2, emp.age);
				pstmt.setDouble(3, emp.salary);
				pstmt.setInt(4, emp.id);
				pstmt.addBatch();
			}
			int[] updated = pstmt.executeBatch();
			System.out.println(updated.length + " rows updated using batch.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteMultipleById(String tblName, List<Integer> ids) {
		String query = "DELETE FROM " + tblName + " WHERE id = ?";
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {

			for (int id : ids) {
				pstmt.setInt(1, id);
				pstmt.addBatch();
			}

			int[] deleted = pstmt.executeBatch();
			System.out.println(deleted.length + " rows deleted using batch.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showTable(String tblName) {
		String sql = "SELECT * FROM " + tblName;
		try (Connection con = JDBC_RDBMS.getConnection();
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			System.out.println("Data from " + tblName);
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " | " +
						rs.getString("name") + " | " +
						rs.getInt("age") + " | " +
						rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String tableName = "empdetails";
		int choice;
		while (true) {
			System.out.println("\n1 . Create Table ");
			System.out.println("2 . Insert Data into Table ");
			System.out.println("3 . Update Single Row ");
			System.out.println("4 . Show Table ");
			System.out.println("5 . Update Multiple Employees");
			System.out.println("6 . Delete Multiple Employees by ID");
			System.out.println("7 . Exit Application"); 
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // flush newline

			switch (choice) {
				case 1:
					String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
							"(id SERIAL PRIMARY KEY," +
							"name VARCHAR(100) NOT NULL," +
							"age INT NOT NULL," +
							"salary DECIMAL(10,2) NOT NULL)";
					JDBC_RDBMS.createTbl(tableName, sql);
					break;

				case 2:
					System.out.print("Enter Name: ");
					String name = scanner.nextLine();
					System.out.print("Enter Age: ");
					int age = scanner.nextInt();
					System.out.print("Enter Salary: ");
					double salary = scanner.nextDouble();
					JDBC_RDBMS.insertData(tableName, name, age, salary);
					break;

				case 3:
					System.out.print("Enter ID to update: ");
					int updateID = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter Name: ");
					String newname = scanner.nextLine();
					System.out.print("Enter Age: ");
					int newage = scanner.nextInt();
					System.out.print("Enter Salary: ");
					double newsalary = scanner.nextDouble();
					JDBC_RDBMS.updateData(tableName, updateID, newname, newage, newsalary);
					break;

				case 4:
					JDBC_RDBMS.showTable(tableName);
					break;

				case 5:
					List<Employee> list = new ArrayList<>();
					list.add(new Employee(1, "Pranita", 23, 60000));
					list.add(new Employee(2, "Mahesh", 28, 62000));
					list.add(new Employee(3, "Amit", 31, 64000));
					updateMultipleEmployees(tableName, list);
					break;

				case 6:
					System.out.print("How many IDs you want to delete? ");
					int n = scanner.nextInt();
					List<Integer> idsToDelete = new ArrayList<>();
					for (int i = 0; i < n; i++) {
						System.out.print("Enter ID to delete: ");
						idsToDelete.add(scanner.nextInt());
					}
					deleteMultipleById(tableName, idsToDelete);
					break;

				case 7:
					System.out.println("Exiting....");
					scanner.close();
					return;

				default:
					System.out.println("Invalid choice... select again");
			}
		}
	}
}
