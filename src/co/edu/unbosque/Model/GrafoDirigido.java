package co.edu.unbosque.Model;

import java.util.ArrayList;
import java.util.List;

public class GrafoDirigido {
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

    public Boolean deleteNode(String nombre){
        Node eliminar = searchNode(nombre);
        if(eliminar != null){
            deleteEdges(eliminar);
            nodes.remove(eliminar);
            return true;
        }
        return false;
    }

    public void deleteEdges(Node n){
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getEdges().size(); j++) {
                if(nodes.get(i).getEdges().get(j).getOrigin() == n ||
                        nodes.get(i).getEdges().get(j).getDestination() == n ){
                    nodes.get(i).getEdges().remove(nodes.get(i).getEdges().get(j));
                }
            }
        }
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
