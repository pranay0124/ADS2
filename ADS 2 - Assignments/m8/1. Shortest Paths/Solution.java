import java.util.Scanner;
import java.util.ArrayList;
class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> stationlist = new ArrayList<>();
		String[] tokens = scan.nextLine().split(" ");
		String[] stations = scan.nextLine().split(" ");
		for (int j = 0; j < Integer.parseInt(tokens[0]); j++) {
			stationlist.add(stations[j]);
		}
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(Integer.parseInt(tokens[1]));
		for (int i = 0; i < Integer.parseInt(tokens[0]); i++) {
			String[] connections = scan.nextLine().split(" ");
			DirectedEdge edge = new DirectedEdge(stationlist.indexOf(connections[0]), stationlist.indexOf(connections[1]), stationlist.indexOf(connections[2]));
			ewd.addEdge(edge);
		}
		int noOfQueries = Integer.parseInt(scan.nextLine());
		while (scan.hasNext()) {
			String[] query = scan.nextLine().split(" ");
			DijkstraSP dijkstra = new DijkstraSP(ewd, stationlist.indexOf(query[0]));
			System.out.println(dijkstra.distTo(stationlist.indexOf(query[1])));
		}
	}
}

