	try {
			// STEP 3: Obtain a connection to the JDND-C3 database
			try (Connection conn = DriverManager.getConnection(url, user, password)) {
				// STEP 4: Use Statement to INSERT 2 records into the member table
				System.out.println("Database connected : " + conn.getMetaData().getDatabaseProductName());
				// NOTE: The member table is created using Flyway by placing the migration file
				// in src/main/resources/db/migration
				try (Statement stmt = conn.createStatement()) {
					String sqlInsert1 = "INSERT INTO MEMBERS VALUES (1, 'Senior developer', '123 rue de la java 12345 JavaVille')";
					String sqlInsert2 = "INSERT INTO MEMBERS VALUES (2, 'Junior devloper', '456 rue de data engineer 456 Data engineer ville')";
					String sqlSelect = "SELECT * FROM MEMBERS";
					stmt.executeUpdate(sqlInsert1);
					System.out.println("data successfully inserted: " + sqlInsert1);
					stmt.executeUpdate(sqlInsert2);
					System.out.println("data successfully inserted: " + sqlInsert2);
					// STEP 5: Read ALL the rows from the member table and print them here
					ResultSet rs = stmt.executeQuery(sqlSelect);
					// STEP 6: verify that all inserted rows have been printed
					while (rs.next()) {
						int id = rs.getInt("member_id");
						String member = rs.getString("member_name");
						String address = rs.getString("member_address");
						System.out.println("member id: " + id);
						System.out.println("member name : " + member);
						System.out.println("member address: " + address);
					}
				}

			}

		} catch (SQLException | FlywayException e) {
			System.out.println("Database connected : " + e.getMessage());
		}