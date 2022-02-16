import java.sql.*;

public class main {
    public static void main(String[] args) throws Exception {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        /// if you have a error in this part, check jdbc driver(.jar file)

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres", "cse3207");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        /// if you have a error in this part, check DB information (db_name, user name, password)

        if (connection != null) {
            System.out.println(connection);
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        Statement stmt= connection.createStatement();

        //테이블 생성
        stmt.executeUpdate("create table director(" +
                "directorID int primary key," +
                "directorName varchar(20),"+
                "dateOfBirth varchar(20),"+
                "dateOfDeath varchar(20))"
        );
        stmt.executeUpdate("create table actor(" +
                "actorID int primary key," +
                "actorName varchar(20),"+
                "dateOfBirth varchar(20),"+
                "dateOfDeath varchar(20)," +
                "gender varchar(6))"
        );
        stmt.executeUpdate("create table movie(" +
                "movieID int primary key," +
                "movieName varchar(20),"+
                "releaseYear varchar(5),"+
                "releaseMonth varchar(10)," +
                "releaseDate varchar(20)," +
                "publisherName varchar(25)," +
                "avgRate numeric(3,2))"
        );
        stmt.executeUpdate("create table award(" +
                "awardID int primary key," +
                "awardName varchar(30))"
        );
        stmt.executeUpdate("create table genre(" +
                "genreName varchar(10) primary key)"
        );
        stmt.executeUpdate("create table movieGenre(" +
                "movieID int references movie(movieID) on delete cascade," +
                "genreName varchar(10) references genre(genreName) on delete cascade)"
        );
        stmt.executeUpdate("create table movieObtain(" +
                "movieID int references movie(movieID) on delete cascade," +
                "awardID int references award(awardID) on delete cascade," +
                "year varchar(5))"
        );
        stmt.executeUpdate("create table actorObtain(" +
                "actorID int references actor(actorID) on delete cascade," +
                "awardID int references award(awardID) on delete cascade," +
                "year varchar(5))"
        );
        stmt.executeUpdate("create table directorObtain(" +
                "directorID int references director(directorID) on delete cascade," +
                "awardID int references award(awardID) on delete cascade," +
                "year varchar(5))"
        );
        stmt.executeUpdate("create table casting(" +
                "movieID int references movie(movieID) on delete cascade," +
                "actorID int references actor(actorID) on delete cascade," +
                "role varchar(20))"
        );
        stmt.executeUpdate("create table make(" +
                "movieID int references movie(movieID) on delete cascade," +
                "directorID int references director(directorID) on delete cascade)"
        );
        stmt.executeUpdate("create table customer(" +
                "customerID int primary key," +
                "customerName varchar(20)," +
                "dateOfBirth varchar(20)," +
                "gender varchar(6))"
        );
        stmt.executeUpdate("create table customerRate(" +
                "customerID int references customer(customerID) on delete cascade," +
                "movieID int references movie(movieID) on delete cascade," +
                "rate int)"
        );

        System.out.println("테이블 생성 성공!");
        //초기 데이터 삽입
        String sql=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        //movie 넣기
        sql="insert into movie values(?,?,?,?,?,?,?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setInt(1, 301);
        pstmt.setString(2, "Edward Scissorhands");
        pstmt.setString(3, "1991");
        pstmt.setString(4, "06");
        pstmt.setString(5, "29");
        pstmt.setString(6, "20th Century Fox Presents");
        pstmt.setDouble(7, 0);
        pstmt.executeUpdate();

        pstmt.setInt(1, 302);
        pstmt.setString(2, "Alice In Wonderland");
        pstmt.setString(3, "2010");
        pstmt.setString(4, "03");
        pstmt.setString(5, "04");
        pstmt.setString(6, "Korea Sony Pictures");
        pstmt.setDouble(7, 0);
        pstmt.executeUpdate();

        pstmt.setInt(1, 303);
        pstmt.setString(2, "The Social Network");
        pstmt.setString(3, "2010");
        pstmt.setString(4, "11");
        pstmt.setString(5, "18");
        pstmt.setString(6, "Korea Sony Pictures");
        pstmt.setDouble(7, 0);
        pstmt.executeUpdate();

        pstmt.setInt(1, 304);
        pstmt.setString(2, "The Dark Knight");
        pstmt.setString(3, "2008");
        pstmt.setString(4, "08");
        pstmt.setString(5, "06");
        pstmt.setString(6, "Warner Brothers Korea");
        pstmt.setDouble(7, 0);
        pstmt.executeUpdate();

        pstmt.setInt(1, 305);
        pstmt.setString(2, "Dunkirk");
        pstmt.setString(3, "2017");
        pstmt.setString(4, "07");
        pstmt.setString(5, "13");
        pstmt.setString(6, "Warner Brothers Korea");
        pstmt.setDouble(7, 0);
        pstmt.executeUpdate();
        //actor 넣기
        sql="insert into actor values(?,?,?,?,?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setInt(1, 201);
        pstmt.setString(2, "Jonny Depp");
        pstmt.setString(3, "1963.06.09");
        pstmt.setString(4, "");
        pstmt.setString(5, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 202);
        pstmt.setString(2, "Winona Ryder");
        pstmt.setString(3, "1971.10.29");
        pstmt.setString(4, "");
        pstmt.setString(5, "Female");
        pstmt.executeUpdate();

        pstmt.setInt(1, 203);
        pstmt.setString(2, "Mia Wasikowska");
        pstmt.setString(3, "1989.10.14");
        pstmt.setString(4, "");
        pstmt.setString(5, "Female");
        pstmt.executeUpdate();

        pstmt.setInt(1, 204);
        pstmt.setString(2, "Christian Bale");
        pstmt.setString(3, "1974.01.30");
        pstmt.setString(4, "");
        pstmt.setString(5, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 205);
        pstmt.setString(2, "Heath Ledger");
        pstmt.setString(3, "1979.04.04");
        pstmt.setString(4, "2008.01.22");
        pstmt.setString(5, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 206);
        pstmt.setString(2, "Jesse Eisenberg");
        pstmt.setString(3, "1983.10.05");
        pstmt.setString(4, "");
        pstmt.setString(5, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 207);
        pstmt.setString(2, "Justin Timberlake");
        pstmt.setString(3, "1981.01.31");
        pstmt.setString(4, "");
        pstmt.setString(5, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 208);
        pstmt.setString(2, "Fionn Whitehead");
        pstmt.setString(3, "1997.07.18");
        pstmt.setString(4, "");
        pstmt.setString(5, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 209);
        pstmt.setString(2, "Tom Hardy");
        pstmt.setString(3, "1977.09.15");
        pstmt.setString(4, "");
        pstmt.setString(5, "Male");
        pstmt.executeUpdate();
        //director 넣기
        sql="insert into director values(?,?,?,?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setInt(1, 101);
        pstmt.setString(2, "Tim Burton");
        pstmt.setString(3, "1958.08.25");
        pstmt.setString(4, "");
        pstmt.executeUpdate();

        pstmt.setInt(1, 102);
        pstmt.setString(2, "David Fincher");
        pstmt.setString(3, "1962.08.28");
        pstmt.setString(4, "");
        pstmt.executeUpdate();

        pstmt.setInt(1, 103);
        pstmt.setString(2, "Chirstopher Nolan");
        pstmt.setString(3, "1970.07.30");
        pstmt.setString(4, "");
        pstmt.executeUpdate();
        //customer 넣기
        sql="insert into customer values(?,?,?,?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setInt(1, 501);
        pstmt.setString(2, "Ethan");
        pstmt.setString(3, "1997.11.14");
        pstmt.setString(4, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 502);
        pstmt.setString(2, "John");
        pstmt.setString(3, "1978.01.23");
        pstmt.setString(4, "Male");
        pstmt.executeUpdate();

        pstmt.setInt(1, 503);
        pstmt.setString(2, "Hayden");
        pstmt.setString(3, "1980.05.04");
        pstmt.setString(4, "Female");
        pstmt.executeUpdate();

        pstmt.setInt(1, 504);
        pstmt.setString(2, "Jill");
        pstmt.setString(3, "1981.04.17");
        pstmt.setString(4, "Female");
        pstmt.executeUpdate();

        pstmt.setInt(1, 505);
        pstmt.setString(2, "Bell");
        pstmt.setString(3, "1990.05.14");
        pstmt.setString(4, "Female");
        pstmt.executeUpdate();
        //casting 넣기
        sql="insert into casting values(?,?,?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setInt(1, 301);
        pstmt.setInt(2, 201);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 301);
        pstmt.setInt(2, 202);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 302);
        pstmt.setInt(2, 201);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 302);
        pstmt.setInt(2, 203);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 303);
        pstmt.setInt(2, 206);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 303);
        pstmt.setInt(2, 207);
        pstmt.setString(3, "Supporting Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 304);
        pstmt.setInt(2, 204);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 304);
        pstmt.setInt(2, 205);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 305);
        pstmt.setInt(2, 208);
        pstmt.setString(3, "Main Actor");
        pstmt.executeUpdate();

        pstmt.setInt(1, 305);
        pstmt.setInt(2, 209);
        pstmt.setString(3, "Supporting Actor");
        pstmt.executeUpdate();
        //genre 넣기
        sql="insert into genre values(?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setString(1, "Fantasy");
        pstmt.executeUpdate();

        pstmt.setString(1, "Romance");
        pstmt.executeUpdate();

        pstmt.setString(1, "Adventure");
        pstmt.executeUpdate();

        pstmt.setString(1, "Family");
        pstmt.executeUpdate();

        pstmt.setString(1, "Drama");
        pstmt.executeUpdate();

        pstmt.setString(1, "Action");
        pstmt.executeUpdate();

        pstmt.setString(1, "Mystery");
        pstmt.executeUpdate();

        pstmt.setString(1, "Thriller");
        pstmt.executeUpdate();

        pstmt.setString(1, "War");
        pstmt.executeUpdate();
        //movieGenre 넣기
        sql="insert into movieGenre values(?,?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setInt(1,301);
        pstmt.setString(2,"Fantasy");
        pstmt.executeUpdate();
        pstmt.setString(2,"Romance");
        pstmt.executeUpdate();

        pstmt.setInt(1,302);
        pstmt.setString(2,"Fantasy");
        pstmt.executeUpdate();
        pstmt.setString(2,"Adventure");
        pstmt.executeUpdate();
        pstmt.setString(2,"Family");
        pstmt.executeUpdate();

        pstmt.setInt(1,303);
        pstmt.setString(2,"Drama");
        pstmt.executeUpdate();

        pstmt.setInt(1,304);
        pstmt.setString(2,"Action");
        pstmt.executeUpdate();
        pstmt.setString(2,"Drama");
        pstmt.executeUpdate();
        pstmt.setString(2,"Mystery");
        pstmt.executeUpdate();
        pstmt.setString(2,"Thriller");
        pstmt.executeUpdate();

        pstmt.setInt(1,305);
        pstmt.setString(2,"Action");
        pstmt.executeUpdate();
        pstmt.setString(2,"Drama");
        pstmt.executeUpdate();
        pstmt.setString(2,"Thriller");
        pstmt.executeUpdate();
        pstmt.setString(2,"War");
        pstmt.executeUpdate();

        //make넣기
        sql="insert into make values(?,?)";
        pstmt=connection.prepareStatement(sql);

        pstmt.setInt(1,301);
        pstmt.setInt(2,101);
        pstmt.executeUpdate();

        pstmt.setInt(1,302);
        pstmt.setInt(2,101);
        pstmt.executeUpdate();

        pstmt.setInt(1,303);
        pstmt.setInt(2,102);
        pstmt.executeUpdate();

        pstmt.setInt(1,304);
        pstmt.setInt(2,103);
        pstmt.executeUpdate();

        pstmt.setInt(1,305);
        pstmt.setInt(2,103);
        pstmt.executeUpdate();
        System.out.println("initial data 설정 완료!");

        //2번
        //actorObtain 넣기
        sql="insert into actorObtain values(?,?,?)";
        pstmt=connection.prepareStatement(sql);

        //2.1
        System.out.println("Statement : Winona Ryder won the \"Best supporting actor\" award in 1994");
        System.out.println("Translated SQL : INSERT INTO award values(\'402\',\'Best supporting actor\')");
        stmt.executeUpdate("INSERT INTO award values(402,'Best supporting actor')");

        rs=stmt.executeQuery("select * from award");
        System.out.println("Updated Tables");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO actorObtain VALUES(202,402,1994)");
        System.out.println("Updated Tables");
        pstmt.setInt(1,202);
        pstmt.setInt(2,402);
        pstmt.setString(3,"1994");
        pstmt.executeUpdate();

        rs=stmt.executeQuery("select * from actorObtain");
        print(rs);
        System.out.println();
        //2.2
        System.out.println("Statement : Tom Hardy won the \"Best supporting actor\" award in 2018");;
        System.out.println("Translated SQL : INSERT INTO award values(\'402\',\'Best supporting actor\')");
        System.out.println("Updated Tables(award 'Best supporting actor' already exists)");
        rs=stmt.executeQuery("select * from award");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO actorObtain VALUES(209,402,2018)");
        pstmt.setInt(1,209);
        pstmt.setInt(2,402);
        pstmt.setString(3,"2018");
        pstmt.executeUpdate();

        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from actorObtain");
        print(rs);
        System.out.println();
        //2.3;
        System.out.println("Statement : Heath Ledger won the \"Best villain actor\" award in 2009");
        System.out.println("Translated SQL : INSERT INTO award values(\'403\',\'Best villain actor\')");
        stmt.executeUpdate("INSERT INTO award values(403,'Best villain actor')");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from award");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO actorObtain VALUES(205,403,2009)");
        pstmt.setInt(1,205);
        pstmt.setInt(2,403);
        pstmt.setString(3,"2009");
        pstmt.executeUpdate();
        System.out.println("Updated Tables");

        rs=stmt.executeQuery("select * from actorObtain");
        print(rs);
        System.out.println();

        //2.4
        System.out.println("Statement : Jonny Depp won the \"Best main actor\" award in 2011");
        System.out.println("Translated SQL : INSERT INTO award values(\'401\',\'Best main actor\')");
        stmt.executeUpdate("INSERT INTO award values(401,'Best main actor')");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from award");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO actorObtain VALUES(201,401,2011)");
        pstmt.setInt(1,201);
        pstmt.setInt(2,401);
        pstmt.setString(3,"2011");
        pstmt.executeUpdate();
        System.out.println("Updated Tables");

        rs=stmt.executeQuery("select * from actorObtain");
        print(rs);
        System.out.println();

        //movieObtain 넣기
        sql="insert into movieObtain values(?,?,?)";
        pstmt=connection.prepareStatement(sql);
        //2.5
        System.out.println("Statement : Edward Scissorhands won the \"Best fantasy movie\" award in 1991");
        System.out.println("Translated SQL : INSERT INTO award values(\'404\',\'Best fantasy movie\')");
        stmt.executeUpdate("INSERT INTO award values(404,'Best fantasy movie')");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from award");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO movieObtain VALUES(301,404,1991)");
        pstmt.setInt(1,301);
        pstmt.setInt(2,404);
        pstmt.setString(3,"1991");
        pstmt.executeUpdate();
        System.out.println("Updated Tables");

        rs=stmt.executeQuery("select * from movieObtain");
        print(rs);
        System.out.println();
        //2.6

        System.out.println("Statement : Alice In Wonderland won the \"Best fantasy movie\" award in 2011");
        System.out.println("Translated SQL : INSERT INTO award values(\'404\',\'Best fantasy movie\')");
        System.out.println("Updated Tables(award 'Best fantasy movie' already exists)");
        rs=stmt.executeQuery("select * from award");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO movieObtain VALUES(302,404,1991)");
        pstmt.setInt(1,302);
        pstmt.setInt(2,404);
        pstmt.setString(3,"2011");
        pstmt.executeUpdate();
        System.out.println("Updated Tables");

        rs=stmt.executeQuery("select * from movieObtain");
        print(rs);
        System.out.println();
        //2.7
        System.out.println("Statement : The Dark Knight won the \"Best picture\" award in 2009");
        System.out.println("Translated SQL : INSERT INTO award values(\'406\',\'Best picture\')");
        stmt.executeUpdate("INSERT INTO award values(406,'Best picture')");
        System.out.println("Updated Tables");

        rs=stmt.executeQuery("select * from award");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO movieObtain VALUES(304,406,2009)");
        pstmt.setInt(1,304);
        pstmt.setInt(2,406);
        pstmt.setString(3,"2009");
        pstmt.executeUpdate();
        System.out.println("Updated Tables");

        rs=stmt.executeQuery("select * from movieObtain");
        print(rs);
        System.out.println();

        //directorObtain 넣기
        sql="insert into directorObtain values(?,?,?)";
        pstmt=connection.prepareStatement(sql);
        //2.8
        System.out.println("Statement : Christopher Nolan won the \"Best director\" award in 2018");
        System.out.println("Translated SQL : INSERT INTO award values(\'405\',\'Best director\')");
        stmt.executeUpdate("INSERT INTO award values(405,'Best director')");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from award");
        print(rs);

        System.out.println("Translated SQL : INSERT INTO directorObtain VALUES(103,405,2018)");
        pstmt.setInt(1,103);
        pstmt.setInt(2,405);
        pstmt.setString(3,"2018");
        pstmt.executeUpdate();
        System.out.println("Updated Tables");

        rs=stmt.executeQuery("select * from directorObtain");
        print(rs);
        System.out.println();
        //3번쿼리
        sql="insert into customerRate values(?,?,?)";
        pstmt=connection.prepareStatement(sql);
        //3.1
        pstmt.setInt(1,501);
        pstmt.setInt(2,305);
        pstmt.setInt(3,5);
        pstmt.executeUpdate();

        System.out.println("Statement : Ethan rates 5 to \'Dunkirk\'");
        System.out.println("Translated SQL : INSERT INTO customerRate values(501,305,5)");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from customerRate");
        print(rs);

        System.out.println("Translated SQL : UPDATE movie SET avgRate = (SELECT avg(rate) FROM customerRate WHERE" +
                " movieID='305') WHERE movieID='305'");
        System.out.println("Updated Tables");
        stmt.executeUpdate("update movie set avgRate = (select avg(rate) from customerRate " +
                "where movieID='305') where movieID='305'");
        rs=stmt.executeQuery("select * from movie");
        print(rs);
        System.out.println();
        //3.2
        System.out.println("Statement : Bell rates 5 to the movies whose director is 'Tim Burton'");
        System.out.println("Translated SQL : INSERT INTO customerRate SELECT 505,movieID,5 FROM make natural join " +
                "director where directorName='Tim Burton'");
        stmt.executeUpdate("INSERT INTO customerRate SELECT 505,movieID,5 FROM make natural join" +
                " director where directorName='Tim Burton'");

        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from customerRate");
        print(rs);

        System.out.println("Translated SQL : UPDATE movie SET avgRate=(SELECT avg(rate) from customerRate where " +
                "customerRate.movieID=movie.movieID)");
        stmt.executeUpdate("UPDATE movie SET avgRate=(SELECT avg(rate) from customerRate where" +
                " customerRate.movieID=movie.movieID)");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from movie");
        print(rs);
        System.out.println();
        //3.3
        System.out.println("Statement : Jill rates 4 to the movies whose main actor is female");
        System.out.println("Translated SQL : INSERT INTO customerRate SELECT 504,movieID,4 FROM actor natural" +
                " join casting WHERE gender='Female'");
        stmt.executeUpdate("INSERT INTO customerRate SELECT 504,movieID,4 FROM actor natural " +
                "join casting WHERE gender='Female'");

        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from customerRate");
        print(rs);

        System.out.println("Translated SQL : UPDATE movie SET avgRate=(SELECT avg(rate) FROM customerRate" +
                " where customerRate.movieID=movie.movieID)");
        stmt.executeUpdate("UPDATE movie SET avgRate=(SELECT avg(rate) FROM customerRate " +
                "where customerRate.movieID=movie.movieID)");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from movie");
        print(rs);
        System.out.println();
        //3.4
        System.out.println("Statement : Hayden rates 4 to the fantasy movies");
        System.out.println("Translated SQL : INSERT INTO customerRate SELECT 503,movieID,4 FROM movieGenre" +
                " where genreName='Fantasy'");
        stmt.executeUpdate("INSERT INTO customerRate SELECT 503,movieID,4 FROM movieGenre " +
                "where genreName='Fantasy'");

        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from customerRate");
        print(rs);

        System.out.println("Translated SQL : UPDATE movie SET avgRate=(SELECT avg(rate) FROM customerRate" +
                " where customerRate.movieID=movie.movieID)");
        stmt.executeUpdate("UPDATE movie SET avgRate=(SELECT avg(rate) FROM customerRate " +
                "where customerRate.movieID=movie.movieID)");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from movie");
        print(rs);
        System.out.println();
        //3.5
        System.out.println("Statement : John rates 5 to the movies whose director won the 'Best director' award");
        System.out.println("Translated SQL : INSERT INTO customerRate SELECT 502,movieID,5 FROM make" +
                " natural join directorObtain natural join award where awardName='Best director'");
        stmt.executeUpdate("INSERT INTO customerRate SELECT 502,movieID,5 FROM make " +
                "natural join directorObtain natural join award where awardName='Best director'");

        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from customerRate");
        print(rs);

        System.out.println("Translated SQL : UPDATE movie SET avgRate=(SELECT avg(rate) FROM customerRate" +
                " where customerRate.movieID=movie.movieID)");
        stmt.executeUpdate("UPDATE movie SET avgRate=(SELECT avg(rate) FROM customerRate " +
                "where customerRate.movieID=movie.movieID)");
        System.out.println("Updated Tables");
        rs=stmt.executeQuery("select * from movie");
        print(rs);
        System.out.println();
        //4번쿼리
        System.out.println("Statement : Select the names of the movies whose actor are dead");
        System.out.println("Translated SQL : select movieName from movie natural join casting natural join actor" +
                " where dateOfDeath!=''");
        rs=stmt.executeQuery("select movieName from movie natural join casting natural join actor " +
                "where dateOfDeath!=''");
        print(rs);
        //5번쿼리
        System.out.println("Statement : Select the names of the directors who cast the same actor more than once");
        System.out.println("Translated SQL : select directorName from director natural join casting natural join " +
                "make group by directorID,actorID having count(directorID)>1 and count(actorID)>1");
        rs=stmt.executeQuery("select directorName from director natural join casting natural join" +
                        " make group by directorID,actorID having count(directorID)>1 and" +
                        " count(actorID)>1");
        print(rs);
        System.out.println();
        //6번쿼리
        System.out.println("Statement : Select the names of the movies and the genres, where movies have the" +
                " common genre");
        System.out.println("Translated SQL : select movieName,genreName from movie natural join movieGenre where genreName in (select genreName " +
                "from movie natural join movieGenre " +
                        "group by genreName " +
                        "having count(genreName)>1) " +
                        "order by genreName");
        rs=stmt.executeQuery("select movieName,genreName from movie natural join movieGenre where genreName in (select genreName " +
                "from movie natural join movieGenre " +
                "group by genreName " +
                "having count(genreName)>1) " +
                "order by genreName");
        print(rs);
        System.out.println();
        //7번 쿼리
        System.out.println("Statement : Delete the movies whose director or actor did not get any" +
                " award and delete from related tables");
        System.out.println("Translated SQL : Delete from movie where movieID not in" +
                "(select movieID from directorObtain natural join make) " +
                "intersect (select movieID from actorObtain natural join casting)");
        stmt.executeUpdate("Delete from movie where movieID not in" +
                "((select movieID from directorObtain natural join make)" +
               "intersect (select movieID from actorObtain natural join casting))");
        rs=stmt.executeQuery("select * from movie");
        System.out.println("Updated tables");
        print(rs);

        rs=stmt.executeQuery("select * from movieGenre");
        print(rs);

        rs=stmt.executeQuery("select * from movieObtain");
        print(rs);

        rs=stmt.executeQuery("select * from casting");
        print(rs);

        rs=stmt.executeQuery("select * from make");
        print(rs);

        rs=stmt.executeQuery("select * from customerRate");
        print(rs);
        System.out.println();
       //8번쿼리
        System.out.println("Statement : Delete all customers and delete data from related tables");
        System.out.println("Translated SQL : delete from customer");
        stmt.executeUpdate("delete from customer");
        rs=stmt.executeQuery("select * from customer");
        print(rs);

        rs=stmt.executeQuery("select * from customerRate");
        print(rs);

        stmt.executeUpdate("Update movie set avgRate=null");
        rs=stmt.executeQuery("select * from movie");
        print(rs);

       // 9번쿼리
        System.out.println("Stetement : Delete all tables and data");
        System.out.println("Translated SQL : drop table director,actor,movie,award,genre,movieGenre,movieObtain,actorObtain," +
                "directorObtain,casting,make,customerRate,customer");
        stmt.executeUpdate("drop table director,actor,movie,award,genre,movieGenre,movieObtain,actorObtain," +
                "directorObtain,casting,make,customerRate,customer");
        rs = stmt.executeQuery("select * from PG_TABLES where schemaname = 'public'");
        print(rs);
        connection.close();
    }
    public static void print(ResultSet rs){
        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println("-----< "+rsmd.getTableName(1)+" >-----");
            for(int i=0;i<rsmd.getColumnCount();i++){
                System.out.print(String.format("%27s",rsmd.getColumnName(i+1)));
            }
            System.out.println();
            while(rs.next()){
                for(int i=0;i<rsmd.getColumnCount();i++){
                    System.out.print(String.format("%27s",rs.getString(i+1)));
                }
                System.out.println();
            }
        }catch(SQLException e){
            System.out.println("resultsetmetadata 오류 발생");
            e.printStackTrace();
        }
    }
}
