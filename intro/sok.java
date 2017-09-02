import java.util.*;
import java.io.*;
 
class sok{

    public static String path = "";
    public static boolean foundPath = false;

    public static void main (String[] args){
        List<String> map = read();
        boolean visited[][] = new boolean[map.size()][];
        int playerX = -1; 
        int playerY = -1;
        //System.out.println(map.size());
        for(int i = 0; i < map.size(); i++){
            String s = map.get(i);
            //System.out.println(s.length());
            visited[i] = new boolean[s.length()];
            if(s.indexOf('+') >= 0){
                System.out.println("");
                return;
            }
            if(s.indexOf('@') >= 0){
                playerX = i;
                playerY = s.indexOf('@');
            }
            //System.out.println(s);
        }
        //System.out.println("playerX: " + playerX + " playerY: " + playerY);
        findWay(map, visited, playerX, playerY);
        if(foundPath)
            System.out.println(path.trim());
        else
            System.out.println("no path");
    }

    static void findWay(List<String> map, boolean visited[][], int currX, int currY){
        
        if(foundPath)
            return;
        boolean hasChoice = false;
        visited[currX][currY] = true;

        /* Try if we found the goal */
        char c = map.get(currX).charAt(currY);
        if(c == '.'){
            System.out.println("Found goal at " + currX + currY);
            foundPath = true;
            return;
        }
        
        /* Try go up */
        c = map.get(currX-1).charAt(currY);
        if(c != '#' && c != '$' && c != '*' && !visited[currX-1][currY] && !foundPath){
            path += "U ";
            //System.out.println("Up" + currX + currY);
            hasChoice = true;
            findWay(map, visited, currX-1, currY);
        }

        /* Try go down */
        c = map.get(currX+1).charAt(currY);
        if(c != '#' && c != '$' && c != '*' && !visited[currX+1][currY] && !foundPath){
            path += "D ";
            //System.out.println("Down" + currX + currY);
            hasChoice = true;
            findWay(map, visited, currX+1, currY);
        }

        /* Try go right */
        c = map.get(currX).charAt(currY+1);
        if(c != '#' && c != '$' && c != '*' && !visited[currX][currY+1] && !foundPath){
            path += "R ";
            //System.out.println("Right" + currX + currY);
            hasChoice = true;
            findWay(map, visited, currX, currY+1);
        }

        /* Try go left */
        c = map.get(currX).charAt(currY-1);
        if(c != '#' && c != '$' && c != '*' && !visited[currX][currY-1] && !foundPath){
            path += "L ";
            //System.out.println("Left" + currX + currY);
            hasChoice = true;
            findWay(map, visited, currX, currY-1);
        }

        if(!hasChoice && !foundPath)
            path = path.substring(0, path.length() - 2);
    }

    static List<String> read(){
        List<String> map = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        String s;

        while(sc.hasNextLine()){
            map.add(sc.nextLine());
        }

        return map;

    }
}
