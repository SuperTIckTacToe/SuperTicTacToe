package network;

import java.io.Serializable;

public class NetworkExchange implements Serializable //may or may not need to implement this
{
  private boolean gameRequest;
  private boolean gameOver = false;
  private String userID;
  private int simpleID;
  private boolean player1;
  private String message;
  private int buttonIndex;
  private int parentIndex;
  public NetworkExchange(String userID_in)
  {
    userID = userID_in;
  }
  public void setPlayer1(boolean player1_in)
  {
    player1 = player1_in;
  }
  public void setSimpleID(int simpleID_in)
  {
    simpleID = simpleID_in;
  }
  public void setGameRequest(boolean gameRequest_in)
  {
    gameRequest = gameRequest_in;
  }
  public void setMessage(String message_in)
  {
    message = message_in;
  }
  public void setButtonIndex(int index)
  {
    buttonIndex = index;
  }
  public void setParentIndex(int index)
  {
    parentIndex = index;
  }
  public void setGameOver(boolean gameOver_in)
  {
    gameOver = gameOver_in;
  }
  public boolean getGameRequest()
  {
    return gameRequest;
  }
  public String getUserID()
  {
    return userID;
  }
  public int getSimpleID()
  {
    return simpleID;
  }
  public boolean isPlayer1()
  {
    return player1;
  }
  public boolean getGameOver()
  {
    return gameOver;
  }
  public String getMessage()
  {
    return message;
  }
  public int getButtonIndex()
  {
    return buttonIndex;
  }
  public int getParentIndex()
  {
    return parentIndex;
  }
}
