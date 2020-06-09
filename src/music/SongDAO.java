package music	;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDAO
{
  private final String url;
  private final String username;
  private final String password;
  
  public SongDAO(String url, String username, String password)
  {
    super();
    
    this.url = url;
    this.username = username;
    this.password = password;
  }
  
  public Song getSong(int id) throws SQLException
  {
    final String sql = "SELECT * FROM music.song WHERE Song_id = ?";
    
    Song Song = null;
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setInt(1, id);
    ResultSet rs = pstmt.executeQuery();
    
    if (rs.next()) {
      String title = rs.getString("title");
      String artist = rs.getString("artist");
      String album = rs.getString("album");
      String length = rs.getString("length");
      
      Song = new Song(id, title, artist, album, length);
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return Song;
  }
  
  public List<Song> getSongs() throws SQLException
  {
    final String sql = "SELECT * FROM music.Song ORDER BY Song_id ASC";
    
    List<Song> Songs = new ArrayList<>();
    Connection conn = getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    
    while (rs.next()) {
      int id = rs.getInt("Song_id");
      String title = rs.getString("title");
      String artist = rs.getString("artist");
      String album = rs.getString("album");
      String length = rs.getString("length");
      
      Songs.add(new Song(id, title, artist, album, length));
    }
    
    rs.close();
    stmt.close();
    conn.close();
    
    return Songs;
  }
  
  public boolean insertSong(Song Song) throws SQLException
  {
    final String sql = "INSERT INTO music.song (title, artist, album, length) " +
        "VALUES (?, ?, ?, ?)";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setString(1, Song.getTitle());
    pstmt.setString(2, Song.getArtist());
    pstmt.setString(3, Song.getAlbum());
    pstmt.setString(4, Song.getLength());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  public boolean updateSong(Song Song) throws SQLException
  {
    final String sql = "UPDATE music.song SET title = ?, artist = ?, album = ?, length = ? " +
        "WHERE Song_id = ?";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setString(1, Song.getTitle());
    pstmt.setString(2, Song.getArtist());
    pstmt.setString(3, Song.getAlbum());
    pstmt.setString(4, Song.getLength());
    pstmt.setInt(5, Song.getId());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  public boolean deleteSong(Song Song) throws SQLException
  {
    final String sql = "DELETE FROM music.song WHERE Song_id = ?";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setInt(1, Song.getId());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  private Connection getConnection() throws SQLException
  {
    final String driver = "com.mysql.cj.jdbc.Driver";
    
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    return DriverManager.getConnection(url, username, password);
  }
}