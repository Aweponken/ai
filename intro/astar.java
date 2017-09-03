/* 
 * Created by Arvid Viderberg
 */

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;
 
class Astar{

    public static void main (String[] args){

        ArrayList<List<Node>> map = new ArrayList<List<Node>>();
        ArrayList<Node> goals = new ArrayList<Node>();
        Stack<Node> shortestPath = new Stack<Node>();
        Scanner sc = new Scanner(System.in);
        int compare = Integer.MAX_VALUE;
        Node start = new Node(), n;
        String s = "";

        /* Read input */
        for (int i = 0; sc.hasNextLine(); i++){
            List<Node> tempNode = new ArrayList<Node>();
            s = sc.nextLine();
            
            for (int j = 0; j < s.length(); j++){
                n = new Node(i, j, s.charAt(j));

                if(s.charAt(j) == '+'){
                    System.out.println();
                    return;
                }

                if(s.charAt(j) == '@')
                    start = n;

                if(s.charAt(j) == '.')
                    goals.add(n);

                tempNode.add(n);
            }
            map.add(tempNode);
        }

        /* Run a-star algorithm and find the shortest path */
        for(int i = 0; i < goals.size(); i++){
            Stack<Node> path = astar(map, start, goals.get(i));
            if (path.size() > 0 && path.size() < compare){
                shortestPath = path;
                compare = path.size();
            }
        }
        if(shortestPath.size() == 0)
            System.out.println("no path");
        else
            print_path(shortestPath);
    }

    static Stack<Node> astar (ArrayList<List<Node>> map, Node start, Node goal){
        Set<Node> closedSet = new HashSet<Node>();
        Set<Node> openSet = new HashSet<Node>();
        Integer comp = Integer.MAX_VALUE;
        Node current = new Node();

        openSet.add(start);
        start.gScore = 0;
        start.fScore = heuristic(start, goal);
        String[] direction = {"U ", "D ", "L ", "R "};

        while (!openSet.isEmpty()){
            Iterator<Node> e = openSet.iterator();
            while(e.hasNext()){
                Node temp = e.next();
                if(temp.fScore < comp)
                    current = temp;
            }
            
            if (current == goal)
                return reconstruct_path(start, goal);
            
            openSet.remove(current);
            closedSet.add(current);
            
            Node[] neighbors = {
                map.get(current.x-1).get(current.y),
                map.get(current.x+1).get(current.y),
                map.get(current.x).get(current.y-1),
                map.get(current.x).get(current.y+1)};

            for(int i = 0; i < 4; i++){
                Node neighbor = neighbors[i]; 

                // Ignore the neighbor which is already evaluated or is a wall.
                if (closedSet.contains(neighbor) || neighbor.value == '#' || neighbor.value == '$' || neighbor.value == '*')
                    continue;        

                // Found a new node
                if (!openSet.contains(neighbor))  
                    openSet.add(neighbor);
                
                // The distance from start to a neighbor
                int tentative_gScore = current.gScore + heuristic(current, neighbor);
                if (tentative_gScore >= neighbor.gScore)
                    continue;        // This is not a better path.

                // This path is the best until now. Record it!
                neighbor.parent = current;
                neighbor.parentDirection = direction[i];
                neighbor.gScore = tentative_gScore;
                neighbor.fScore = neighbor.gScore + heuristic(neighbor, goal);
            }     
        }
        return new Stack<Node>();
    }

    static int heuristic(Node s, Node t){
        int dx = Math.abs(s.x - t.x);
        int dy = Math.abs(s.y - t.y);
        return dx + dy;
    }

    static Stack<Node> reconstruct_path(Node start, Node current){
        Stack<Node> total_path = new Stack<Node>();
        while (current != start){
            total_path.push(current);
            current = current.parent;
        }
        return total_path;
    }

    static void print_path(Stack<Node> path){
        while(!path.empty()){
            Node current = path.pop();
            System.out.print(current.parentDirection);
        }
        System.out.println();
    }
}