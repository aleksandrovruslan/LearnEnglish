package ru.aleksandrov.DAO;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.aleksandrov.Entity.*;
import ru.aleksandrov.util.Settings;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect implements Storage{
    private static Logger log = LogManager.getLogger(DBConnect.class.getName());
    private Connection connection = null;
    private static DBConnect dbConnect = null;
    private ComboPooledDataSource cpds;

    public DBConnect(){
        final Settings settings = Settings.getSettings();
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                connection = DriverManager.getConnection(settings.value("urlDB")
                        , settings.value("login"), settings.value("password"));
            }
        } catch (SQLException e) {
            log.error("DBConnect(): ", e);
        }
    }

    @Override
    public int addRole(Role role){
        String message = "";
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("INSERT INTO role (name) VALUES (?)"
                        , Statement.RETURN_GENERATED_KEYS)){
            pstatement.setString(1, role.getName());
            pstatement.executeUpdate();
            try(ResultSet generatedKeys = pstatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt("role_id");
                }
            }
        }
        catch (SQLException e){
            log.error("addRole(): ", e);
        }
        throw new IllegalStateException("Could not create new role" + message);
    }

    @Override
    public Role getRole(int id){
        try(PreparedStatement pstatement = this.connection
                .prepareStatement("SELECT * FROM role WHERE role_id = (?)")){
            pstatement.setInt(1, id);
            ResultSet resultSet = pstatement.executeQuery();
            if (resultSet.next()){
                return new Role(resultSet.getInt("role_id"), resultSet.getString("name"));
            }

        }catch (SQLException e){
            log.error("getRole(): ", e);
        }
        throw new IllegalStateException("There is no such role");
    }

    @Override
    public List<Role> getRoles() throws SQLException{
        List<Role> roles = new ArrayList<Role>(0);
        String SQL = "SELECT * FROM role";
        try(final PreparedStatement pstatement = this.connection.prepareStatement(SQL)){
            ResultSet result = pstatement.executeQuery();
            while (result.next()){
                int roleId = result.getInt("role_id");
                String roleName = result.getString("name");
                roles.add(new Role(roleId, roleName));
            }
            return roles;
        }catch (SQLException e){
            log.error("getRoles(): ", e);
        }
        return roles;
    }
    
    @Override
    public int addEnglishWord(EnglishWord english){
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("INSERT INTO english_words (english_word) VALUES (?)"
                        , Statement.RETURN_GENERATED_KEYS)){
            pstatement.setString(1, english.getEnglishWord());
            pstatement.executeUpdate();
            try(ResultSet generatedKeys = pstatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    return generatedKeys.getInt("english_id");
                }
            }
        }catch (SQLException e){
            log.error("addEnglishWord(): ", e);
        }
        throw new IllegalStateException("Could not create new english word");
    }

    @Override
    public EnglishWord getEnglishWord(int id){
        try(final PreparedStatement pstatment = this.connection
                .prepareStatement("SELECT * FROM english_words WHERE english_id = (?)")){
            pstatment.setInt(1, id);
            try(final ResultSet result = pstatment.executeQuery()){
                if(result.next()){
                    return new EnglishWord(id, result.getString("english_word"));
                }
            }
        }catch (SQLException e){
            log.error("getEnglishWord(): ", e);
        }
        throw new IllegalStateException("There is no such word");
    }

    @Override
    public RussianWord[] addRussianWords(RussianWord ... russians){
        try(PreparedStatement pstatement = this.connection
                .prepareStatement("INSERT INTO russian_words(russian_word) VALUES (?)")){
            this.connection.setAutoCommit(false);
            if(log.isDebugEnabled()){
                log.info("russian - " + russians);
            }
            for(RussianWord russian:russians) {
                pstatement.setString(1, russian.getRussianWord());
                pstatement.addBatch();
            }
            pstatement.executeBatch();
            ResultSet result = pstatement.getGeneratedKeys();
            for(RussianWord russian:russians){
                if(result.next()){
                    russian.setRussianId(result.getInt("russian_id"));
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return russians;
        }catch (SQLException e){
            log.error(" ", e);
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                log.error("addRussianWords(): ", e);
            }
        }
        throw new IllegalStateException("Don't add russian words");
    }

    @Override
    public RussianWord getRussianWord(int id){
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("SELECT * FROM russian_words WHERE russian_id = (?)")){
            pstatement.setInt(1, id);
            try(final ResultSet result = pstatement.executeQuery()){
                if(result.next()){
                    return new RussianWord(id, result.getString("russian_word"));
                }
            }
        }catch (SQLException e){
            log.error("getRussianWord(): ", e);
        }
        throw new IllegalStateException("There is no such word");
    }

    @Override
    public int addCollection(WordsCollection collection){
        try (final PreparedStatement pstatement = this.connection
                .prepareStatement("INSERT INTO words_collections (name) VALUES (?)"
                        ,Statement.RETURN_GENERATED_KEYS)){
            pstatement.setString(1, collection.getName());
            pstatement.executeUpdate();
            try(ResultSet generatedKeys = pstatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt("collection_id");
                }
            }

        }catch (SQLException e){
            log.error("addCollection(): ", e);
        }
        throw new IllegalStateException("Could not create new collection");
    }

    @Override
    public WordsCollection getCollection(int id){
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("SELECT * FROM words_collections WHERE collection_id = (?)")){
            pstatement.setInt(1, id);
            try(final ResultSet result = pstatement.executeQuery()){
                if(result.next()){
                    return new WordsCollection(id, "name");
                }
            }
        }catch (SQLException e){
            log.error("getCollection(): ", e);
        }
        throw new IllegalStateException("There is no such collection");
    }

    @Override
    public int addUser(User user){
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("INSERT INTO users(name, login, password, email, role_id) VALUES (?, ?, ?, ?, ?)"
                , Statement.RETURN_GENERATED_KEYS)){
            pstatement.setString(1, user.getName());
            pstatement.setString(2, user.getLogin());
            pstatement.setString(3, user.getPassword());
            pstatement.setString(4, user.getEmail());
            pstatement.setInt(5, user.getRole().getRoleId());
            pstatement.executeUpdate();
            try(ResultSet generatedKeys = pstatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt("user_id");
                }
            }
        }catch (SQLException e){
            log.error("addUser(): ", e);
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public User getUser(int id){
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("SELECT users.user_id AS user_id, users.name AS name, users.login AS login, users.password AS password, users.email AS email, role.role_id AS role_id, role.name AS role_name FROM users INNER JOIN role ON users.role_id = role.role_id WHERE user_id = (?)")){
                pstatement.setInt(1, id);
                try(ResultSet resultSet = pstatement.executeQuery()){
                    if (resultSet.next()){
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setName(resultSet.getString("name"));
                        user.setLogin(resultSet.getString("login"));
                        user.setPassword(resultSet.getString("password"));
                        user.setEmail(resultSet.getString("email"));
                        user.setRole(new Role(resultSet.getInt("role_id"), resultSet.getString("role_name")));
                        return user;
                    }
                }
        }catch (SQLException e){
            log.error("getUser(): ", e);
        }
        throw new IllegalStateException("There is no such user");
    }

    @Override
    public List<User> getUsers(){
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("SELECT users.user_id AS user_id, users.name AS name, users.login AS login, users.password AS password, users.email AS email, users.role_id AS role_id, role.name AS role_name FROM users INNER JOIN role ON users.role_id = role.role_id")){
            List<User> users = new ArrayList<>();
            try(ResultSet result = pstatement.executeQuery()){
                while (result.next()){
                    users.add(new User(result.getInt("user_id"), result.getString("name")
                            , result.getString("login"), result.getString("password")
                            , result.getString("email")
                            , new Role(result.getInt("role_id"), result.getString("role_name"))));
                }
                return users;
            }
        }catch (SQLException e){
            log.error("getUsers(): ", e);
        }
        throw new IllegalStateException("There is no such users");
    }

    @Override
    public boolean addWord(Word word){
        try(final PreparedStatement pstatement = this.connection
                .prepareStatement("INSERT INTO answers(user_id, english_id, collection_id, number_answers, correct_answers) VALUES (?, ?, ?, ?, ?)"
                        , Statement.RETURN_GENERATED_KEYS)){
            pstatement.setInt(1, word.getUser().getUserId());
            pstatement.setInt(2, word.getEnglish().getEnglishId());
            pstatement.setInt(3, word.getCollection().getCollectionId());
            pstatement.setInt(4, word.getNumberAnswers());
            pstatement.setInt(5, word.getCorrectAnswers());
            pstatement.executeUpdate();
            try(final ResultSet generatedKeys = pstatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return true ;
                }
            }
        }catch (SQLException e){
            log.error("addWord(): ", e);
        }
        throw new IllegalStateException("Could not create new answer");
    }

    @Override
    public Word getWord(int id){
        String SQL = "";
        try(final PreparedStatement pstatement = this.connection.prepareStatement(SQL)){

        }catch (SQLException e){
            log.error("getWord(): ", e);
        }
        throw new IllegalStateException("There is no such words");
    }
}
