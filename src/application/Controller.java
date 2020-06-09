package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import music.Song;
import music.SongDAO;

public class Controller extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private SongDAO dao;
  
  public void init()
  {
    final String url = "jdbc:mysql://localhost:3306/library?serverTimezone=EST";
    final String username = "root";
    final String password = "root";
    
    dao = new SongDAO(url, username, password);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    doGet(request, response);
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    final String action = request.getServletPath();
    
    try {
      switch (action) {
        default:
          viewSongs(request, response);
          break;
      }
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
  
  private void viewSongs(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, ServletException, IOException
  {
    List<Song> songs = dao.getSongs();
    request.setAttribute("songs", songs);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("inventory.jsp");
    dispatcher.forward(request, response);
  }
}
