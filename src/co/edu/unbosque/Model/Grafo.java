package co.edu.unbosque.Model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Node> nodes;

    public void addNode(Node node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }

        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return " Nodos:\n" + nodes;
    }

    public String returnNodeNames(int i){
        return nodes.get(i).getCity();
    }

    public Node searchNode(String name){
        for (int i = 0; i < nodes.size() ; i++) {
            if(nodes.get(i).getCity().equalsIgnoreCase(name)){
                return nodes.get(i);
            }
        }
        return null;
    }



}
