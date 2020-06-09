package music;

public class Song
{
  private int id;
  private String title;
  private String artist;
  private String album;
  private String length;

  public Song(int id, String title, String artist, String album, String length) {
    super();

    this.id = id;
    this.title = title;
    this.artist = artist;
    this.album = album;
    
    this.length = length;
  }
  
  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }
    
  public String getArtist()
  {
    return artist;
  }

  public String getAlbum()
  {
    return album;
  }
    
  public void setAlbum(String album)
  {
    this.album = album;
  }
    
  public String getLength()
  {
    return length;
  }
    
  public void setLength(String length)
  {
    this.length = length;
  }
}